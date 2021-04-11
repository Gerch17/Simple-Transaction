package ru.gerch.transaction.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="history")
public class History {
    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="user_name", nullable = false)
    private String userName;

    @Column(name="amount", nullable = false)
    private int amount;

    @Column(name="transaction_date", nullable = false)
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
