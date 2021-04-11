package ru.gerch.transaction.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import ru.gerch.transaction.entity.Users;
import ru.gerch.transaction.repos.UsersRepository;
import ru.gerch.transaction.service.UsersService;

@Component
public class AuthenticationSuccessEventListener
        implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersRepository usersRepository;

    public void onApplicationEvent(AuthenticationSuccessEvent event) {

        String username = event.getAuthentication().getName();
        Users users = usersRepository.findUsersByUserName(username);
        users.setAttempts(1);
        usersRepository.save(users);
    }
}