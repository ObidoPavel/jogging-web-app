package com.obido.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
public class UserBean {

    private String login;

    private String password;

    private String passwordConfirmation;

}
