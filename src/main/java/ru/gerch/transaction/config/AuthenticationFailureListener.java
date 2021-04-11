package ru.gerch.transaction.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import ru.gerch.transaction.service.UsersService;

@Component
public class AuthenticationFailureListener
        implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private UsersService usersService;

    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent ev) {

        String username = ev.getAuthentication().getName();

        usersService.failure(username);
    }
}