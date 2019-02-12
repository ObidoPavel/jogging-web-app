package com.obido.service;

import com.obido.domain.AverageReportBean;

import java.util.List;

public interface AverageReportService {

    List<AverageReportBean> createReport(Long userId);

}
