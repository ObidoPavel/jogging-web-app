package com.obido.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@EqualsAndHashCode
@Getter
@Setter
public class RaceBean {

    private Long id;

    private Double distance;

    private Date date;

    private Long duration;

}