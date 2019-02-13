package com.obido.service;

import com.obido.domain.JogBean;

import java.util.List;

public interface JogService {

    JogBean retrieveJog(Long id);

    Long save(Long userId, JogBean jogBean);

    void deleteJog(Long id);

    void update(Long userId, JogBean jogBean);

    List<JogBean> readAllByUserId(Long userId);

}