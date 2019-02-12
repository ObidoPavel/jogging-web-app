package com.obido.service.impl;

import com.obido.domain.AverageReportBean;
import com.obido.repository.AverageReportRepository;
import com.obido.service.AverageReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AverageReportServiceImpl implements AverageReportService {

    @Autowired
    private AverageReportRepository reportRepository;

    @Override
    public List<AverageReportBean> createReport(Long userId) {
        return reportRepository.retrieveReport(userId);
    }
}