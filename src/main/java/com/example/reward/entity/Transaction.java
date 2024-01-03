package com.example.reward.entity;

import java.time.LocalDate;

/*
 * Transaction class 
 * for a customer with ID 123, a transaction amount of 50, and a date of January 15, 2022.
 * Transaction transaction = new Transaction(123L, 50, LocalDate.of(2022, 1, 15));
*/

/*
 * In real world @Entity annotation should be used
 * didn't include Spring Data framework for demo purpose
*/
// @Entity
public class Transaction {
    private Long customerID;
    private int money;
    private LocalDate date;

    // Constructor
    public Transaction(Long customerID, int money, LocalDate date) {
        this.customerID = customerID;
        this.money = money;
        this.date = date;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
