package ru.gerch.transaction.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import ru.gerch.transaction.service.UsersService;

@Component
public class AuthenticationSuccessEventListener
        implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private UsersService usersService;

    public void onApplicationEvent(AuthenticationSuccessEvent event) {

        String username = event.getAuthentication().getName();
        usersService.success(username);
    }
}