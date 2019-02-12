package com.obido.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@EqualsAndHashCode
@Entity
@Table(schema = "PUBLIC", name = "JOGGING_RACE")
@Getter
@Setter
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false, precision = 9)
    private Long id;

    @Column(name = "distance", nullable = false)
    private Double distance;

    @Column(name = "date", nullable = false)
    private Timestamp date;

    @Column(name = "duration", nullable = false)
    private Long duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Race() {
    }

    public Race(Double distance, Timestamp date, Long duration, User user) {
        this.distance = distance;
        this.date = date;
        this.duration = duration;
        this.user = user;
    }
}