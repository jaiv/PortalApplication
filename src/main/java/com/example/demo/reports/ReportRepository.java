package com.example.demo.reports;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReportRepository extends Repository<Reports, Long> {
    
	//@Query("SELECT reports FROM Reports reports WHERE reports.id =:id")
    @Transactional(readOnly = true)
	Reports findById(@Param("id") Long id);
    
    @Query("SELECT DISTINCT reports FROM Reports reports WHERE reports.name LIKE :name%")
    @Transactional(readOnly = true)
    Collection<Reports> findByName(@Param("name") String name);

}