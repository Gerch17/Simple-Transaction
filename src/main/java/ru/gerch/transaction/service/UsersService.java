package ru.gerch.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gerch.transaction.entity.Users;
import ru.gerch.transaction.repos.UsersRepository;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    public void makeTransaction(String user){
        Users users = usersRepository.findUsersByUserName(user);
        users.setBalance(users.getBalance()-11);
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

    public String getPass(String name){
        return usersRepository.findUsersByUserName(name).getPassword();
    }

    public int getAttempts(String name){
        return usersRepository.findUsersByUserName(name).getAttempts();
    }
}
