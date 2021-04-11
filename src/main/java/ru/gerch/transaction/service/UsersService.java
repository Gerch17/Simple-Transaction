package ru.gerch.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gerch.transaction.config.WebSecurityConfig;
import ru.gerch.transaction.entity.Users;
import ru.gerch.transaction.repos.UsersRepository;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    WebSecurityConfig webSecurityConfig;

    public void makeTransaction(String user){
        Users users = usersRepository.findUsersByUserName(user);
        if(!(users.getBalance() < 11)) {
            users.setBalance(users.getBalance() - 11);
        }
    }

    public void failure(String name){
        Users users = usersRepository.findUsersByUserName(name);
        users.setAttempts(users.getAttempts()+1);
        usersRepository.save(users);
        if(usersRepository.findUsersByUserName(name).getAttempts() > 5){
            Users userss = usersRepository.findUsersByUserName(name);
            userss.setIsBlocked(true);
            usersRepository.save(userss);
        }
    }

    public void success(String name){
        Users users = usersRepository.findUsersByUserName(name);
        users.setAttempts(0);
        usersRepository.save(users);
    }

    public int getAttempts(String name){
        return usersRepository.findUsersByUserName(name).getAttempts();
    }

    public void create(){
        Users users = new Users();

            if(usersRepository.findUsersByUserName("qwe") == null)
            users.setAttempts(0);
            users.setIsBlocked(false);
            users.setPassword(webSecurityConfig.passwordEncoder.encode("asd"));
            users.setUserName("qwe");
            users.setBalance(80);
            users.setUser_role("ROLE_USER");
            usersRepository.save(users);


    }
}
