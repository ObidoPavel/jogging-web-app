package com.obido.repository;

import com.obido.domain.AverageReportBean;
import com.obido.entity.Jog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AverageReportRepository extends JpaRepository<Jog, Long> {

    @Query(
            value = "SELECT EXTRACT(WEEK FROM date) AS numberOfWeek, " +
                    "AVG(distance/duration) AS avgSpeed, " +
                    "AVG(duration) AS avgDuration, " +
                    "SUM(distance) AS sumDist FROM JOG " +
                    "WHERE user_id = :userId GROUP BY numberOfWeek",
            nativeQuery = true)
    List<AverageReportBean> retrieveReport(@Param("userId") Long userId);

}