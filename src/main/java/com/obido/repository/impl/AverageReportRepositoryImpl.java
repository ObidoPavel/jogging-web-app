package com.obido.repository.impl;

import com.obido.domain.AverageReportBean;
import com.obido.repository.AverageReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

public class AverageReportRepositoryImpl implements AverageReportRepository {

    private static final String REPORT_QUERY = "SELECT EXTRACT(WEEK FROM date) AS numberOfWeek, " +
            "AVG(distance/duration) AS avgSpeed, " +
            "AVG(duration) AS avgDuration, " +
            "SUM(distance) AS sumDist FROM JOG " +
            "WHERE user_id = :userId GROUP BY numberOfWeek";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<AverageReportBean> retrieveReport(Long userId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", userId);
        return jdbcTemplate.query(REPORT_QUERY, params, (rs, rowNum) -> {
            AverageReportBean averageReportBean = new AverageReportBean();
            averageReportBean.setWeek(new AverageReportBean.Week(rs.getInt("numberOfWeek"), null, null));
            averageReportBean.setAverageSpeed(rs.getDouble("avgSpeed"));
            averageReportBean.setAverageTime(rs.getLong("avgDuration"));
            averageReportBean.setTotalDistance(rs.getDouble("sumDist"));
            return averageReportBean;
        });
    }
}
