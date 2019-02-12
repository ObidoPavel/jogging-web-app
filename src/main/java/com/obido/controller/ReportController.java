package com.obido.controller;

import com.obido.domain.AverageReportBean;
import com.obido.service.AverageReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class ReportController {

    @Autowired
    private AverageReportService reportService;

    @RequestMapping(value = "/reports/{userId}", method = RequestMethod.GET)
    public List<AverageReportBean> retrieveUserRaces(@PathVariable Long userId) {
        return reportService.createReport(userId);
    }
}