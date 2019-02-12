package com.obido.service;

import com.obido.entity.User;

public interface UserService {

    void save(User user);

    User findByLogin(String login);
}