package com.example.demo.reports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "applicationreports")
public class Reports {
	
	@Id
    @GeneratedValue
    private Long id;

	@Column(name = "name")
    @NotEmpty
	private String name;
	
	@Column(name = "description")
    @NotEmpty
	private String description;
	
	@Column(name = "reporturl")
    @NotEmpty
    private String reporturl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReporturl() {
		return reporturl;
	}

	public void setReporturl(String reporturl) {
		this.reporturl = reporturl;
	}

	@Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId())
                .append("name", this.getName())
                .append("description", this.getDescription())
                .append("reporturl", this.getReporturl()).toString();
    }

}
