package com.example.reward.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.example.reward.entity.Transaction;

/*
 * In real world, TransactionRepository access database to read Transaction
 * For demo purpose, this class will make up some data set
 */
@Repository
public class TransactionRepository {

    // Simulating list of transactions records in database
    private List<Transaction> transactions = new ArrayList<>();

    public TransactionRepository() {
        // Adding some transactions with hardcoded data
        // customer 1
        transactions.add(new Transaction(1L, 0, LocalDate.of(2023, 11, 5)));
        transactions.add(new Transaction(1L, 120, LocalDate.of(2023, 12, 5)));
        transactions.add(new Transaction(1L, 50, LocalDate.of(2023, 10, 10)));
        transactions.add(new Transaction(1L, 3, LocalDate.of(2023, 10, 11)));
        transactions.add(new Transaction(1L, 53, LocalDate.of(2023, 10, 23)));
        transactions.add(new Transaction(1L, 150, LocalDate.of(2023, 9, 10)));
        transactions.add(new Transaction(1L, 150, LocalDate.of(2023, 9, 9)));

        // customer 2
        transactions.add(new Transaction(2L, 80, LocalDate.of(2023, 12, 30)));

        // customer 3
        transactions.add(new Transaction(3L, 120, LocalDate.of(2023, 10, 5)));
        transactions.add(new Transaction(3L, 140, LocalDate.of(2023, 11, 5)));
        transactions.add(new Transaction(3L, 160, LocalDate.of(2023, 12, 5)));

        // customer 4
        transactions.add(new Transaction(4L, 0, LocalDate.of(2023, 12, 15)));

        // customer 5
        transactions.add(new Transaction(5L, 100, LocalDate.of(2023, 12, 15)));
    }

    public List<Transaction> findTransactionsByCustomerID(Long customerID) {
        List<Transaction> result = new ArrayList<>();

        // find transaction made by customer ID
        for (Transaction transaction : transactions) {
            if (transaction.getCustomerID().equals(customerID)) {
                result.add(transaction);
            }
        }

        return result;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

}
