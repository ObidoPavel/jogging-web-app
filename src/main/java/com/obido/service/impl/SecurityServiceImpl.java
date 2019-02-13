package com.obido.service.impl;

import com.obido.domain.UserBean;
import com.obido.entity.User;
import com.obido.exception.BadRequestException;
import com.obido.service.SecurityService;
import com.obido.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SecurityServiceImpl implements SecurityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }
        return null;
    }

    @Override
    public void autoLogin(String userName, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            LOGGER.debug(String.format("Auto login %s successfully!", userName));
        }
    }

    @Override
    public void registerUser(UserBean user) {
        if (!validateUserBean(user)) {
            LOGGER.error("Invalid request while registration");
            throw new BadRequestException("Invalid request");
        }
        String userPassword = user.getPassword();
        userService.save(mapToUserEntity(user));
        securityService.autoLogin(user.getLogin(), userPassword);
    }

    private User mapToUserEntity(UserBean userBean) {
        return new User(userBean.getLogin(), userBean.getPassword());
    }

    private boolean validateUserBean(UserBean userBean) {
        return userBean != null
                && !StringUtils.isEmpty(userBean.getLogin())
                && !StringUtils.isEmpty(userBean.getPassword())
                && userBean.getPassword().equals(userBean.getPasswordConfirmation());
    }
}
