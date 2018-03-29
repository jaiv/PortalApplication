package com.example.demo.reports;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportController {

	// private static final String VIEWS_REPORTS = "/reports";
	private final ReportRepository reportrepository;

	@Autowired
	public ReportController(ReportRepository reps) {
		this.reportrepository = reps;
	}

	@GetMapping("/reports/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("report", new Reports());
		return "reports/findReports";
	}

	@GetMapping("/reports")
	public String processFindForm(Reports report, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /reports to return all records
		if (report.getName() == null) {
			report.setName(""); // empty string signifies broadest possible search
		}

		// find reports by name
		Collection<Reports> results = this.reportrepository.findByName(report.getName());
		if (results.isEmpty()) {
			// no reports found
			result.rejectValue("name", "notFound", "not found");
			return "reports/findReports";
		} else if (results.size() == 1) {
			// 1 report found
			report = results.iterator().next();
			return "redirect:/reports/" + report.getId();
		} else {
			// multiple reports found
			model.put("selections", results);
			return "reports/reportsList";
		}
	}
	
	@GetMapping("/reports/{reportId}")
	public ModelAndView showReport(@PathVariable("reportId") Long reportId) {
		ModelAndView mav = new ModelAndView("reports/reportDetails");
		 
		mav.addObject(this.reportrepository.findById(reportId));
		return mav;
	}
}
