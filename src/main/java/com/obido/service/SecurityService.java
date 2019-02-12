package com.obido.service;

import com.obido.domain.UserBean;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String login, String password);

    void registerUser(UserBean user);
}