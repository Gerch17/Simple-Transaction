package ru.gerch.transaction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransactionController {
    @GetMapping("/")
    public String toLogin(){
        return "home";
    }

    @GetMapping("/transaction")
    public String toTransaction(){
        return "transaction";
    }

    @GetMapping("/login")
    public String auth(){
        return "login";
    }
}
