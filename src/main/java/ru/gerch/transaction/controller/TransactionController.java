package ru.gerch.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gerch.transaction.service.HistoryService;
import ru.gerch.transaction.service.UsersService;

@Controller
public class TransactionController {
    @Autowired
    HistoryService historyService;

    @Autowired
    UsersService usersService;


    @GetMapping("/")
    public String toLogin(){
        return "home";
    }


    @GetMapping("/transaction")
    public String toTransaction(){
        return "transaction";
    }


    @GetMapping("/submitTransaction")
    public String submit(){
        usersService.makeTransaction(SecurityContextHolder.getContext().getAuthentication().getName());
        historyService.addToHistory(SecurityContextHolder.getContext().getAuthentication().getName());
        return "transaction";
    }

    @GetMapping("/login")
    public String auth(){
        return "login";
    }

    @GetMapping("/logout")
    public String logouts(){
        return "logout";
    }
}