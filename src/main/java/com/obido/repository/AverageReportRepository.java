package com.obido.repository;

import com.obido.domain.AverageReportBean;

import java.util.List;

public interface AverageReportRepository {

    List<AverageReportBean> retrieveReport(Long userId);

}