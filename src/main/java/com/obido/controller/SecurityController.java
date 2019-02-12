package com.obido.controller;

import com.obido.domain.UserBean;
import com.obido.service.SecurityService;
import com.obido.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
class SecurityController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @PostMapping(value = "/registration")
    public ResponseEntity<String> registration(@RequestBody UserBean userBean) {
        securityService.registerUser(userBean);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<String> login() {
        return ResponseEntity.ok().build();
    }

}
