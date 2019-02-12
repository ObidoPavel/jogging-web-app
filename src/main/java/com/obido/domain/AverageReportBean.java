package com.obido.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class AverageReportBean {

    private Week week;

    private Double averageSpeed;

    private Long averageTime;

    private Double totalDistance;

    @Getter
    @Setter
    public static class Week {

        private Integer weekOfYear;
        private Date from;
        private Date to;

        public Week(Integer weekOfYear, Date from, Date to) {
            this.weekOfYear = weekOfYear;
            this.from = from;
            this.to = to;
        }
    }
}
