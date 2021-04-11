package ru.gerch.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        usersService.create();
        return "home";
    }


    @GetMapping("/transaction")
    public String toTransaction(){
        return "transaction";
    }


    @GetMapping("/payment")
    public String submit(){
        usersService.makeTransaction(SecurityContextHolder.getContext().getAuthentication().getName());
        historyService.addToHistory(SecurityContextHolder.getContext().getAuthentication().getName());
        return "transaction";
    }

    @GetMapping("/login")
    public String auth(@RequestParam(value = "error", defaultValue = "false") boolean loginError,@RequestParam(value = "username", defaultValue = "") String username, @ModelAttribute("forma") String forma , Model model){
        if (loginError) {
            String message = "Неверный пароль";
            String blockedMessage = "";
            int attempts;
            try {
                attempts = usersService.getAttempts(forma);
                if(5-attempts < 0) {
                    attempts = 0;
                    blockedMessage = ". Аккаунт заблокирован";
                }
                else attempts = 5 - attempts;
            }catch (Exception e){
                model.addAttribute("WrongPassword", "Неверный логин или пароль");
                return "login";
            }
            model.addAttribute("WrongPassword", "Неверный пароль. Осталось попыток " + attempts + blockedMessage);
        }
        return "login";
    }


    @GetMapping("/logout")
    public String logouts(){
        return "logout";
    }
}