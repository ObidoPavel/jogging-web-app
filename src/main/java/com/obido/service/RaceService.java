package com.obido.service;

import com.obido.domain.RaceBean;

import java.util.List;

public interface RaceService {

    RaceBean retrieveRace(Long id);

    Long save(Long userId, RaceBean race);

    void deleteRace(Long id);

    void update(Long userId, RaceBean race);

    List<RaceBean> readAllByUserId(Long userId);

}