package ru.gerch.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gerch.transaction.entity.History;
import ru.gerch.transaction.repos.HistoryRepository;

@Service
public class HistoryService {
    @Autowired
    HistoryRepository historyRepository;

    public void addToHistory(String str){
        History history = new History();
        history.setAmount(11);
        history.setUserName(str);
        history.setDate(new java.sql.Date(new java.util.Date().getTime()));
        historyRepository.save(history);
    }
}
