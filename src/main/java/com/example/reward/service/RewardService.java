package com.example.reward.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reward.dao.TransactionRepository;
import com.example.reward.entity.Transaction;
import com.example.reward.model.RewardPoint;

@Service
public class RewardService {

    @Autowired
    private TransactionRepository transactionRepository;

    private int calculatePoint(int money) {
        int point = 0;

        if (money > 100) {
            // for over $100 transaction
            // 2 points for every dollar spent over $100
            point += (money - 100) * 2;
            // 1 point for every dollar spent over $50 which is 100 - 50
            point += (100 - 50) * 1;
        } else if (money > 50) {
            // for transaction less than $100 but over $50
            // 1 point for every dollar spent over $50 in each transaction
            point = (money - 50) * 1;
        }
        // for other transaction amount, point is 0

        return point;
    }

    public List<RewardPoint> getRewardPoint(Long customerID) {

        // automatically sorts the entries based on keys (months) with TreeMap
        TreeMap<Integer, Integer> monthlyPointMap = new TreeMap<>();

        // get all transactions of customer by customerID
        List<Transaction> transactions = transactionRepository.findTransactionsByCustomerID(customerID);

        // LocalDate of three months ago for date comparision
        LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);

        for (Transaction transaction : transactions) {

            // process transactions within three-month period
            if (!transaction.getDate().isBefore(threeMonthsAgo)) {

                // Get money amount and month from transaction
                int money = transaction.getMoney();
                int month = transaction.getDate().getMonthValue();
                // calculate point for the purchase
                int point = calculatePoint(money);
                
                // Check if point > 0 to ignore 0 point
                // Check if monthlyPointMap already contains the specified month
                // if yes, retrieve the existing value
                // if not, the default value to 0
                // Then add the new reward points to the existing point
                if (point > 0) {
                    monthlyPointMap.put(month, monthlyPointMap.getOrDefault(month, 0) + point);
                }
            }
        }

        List<RewardPoint> monthlyPointList = new ArrayList<>();

        // convert TreeMap to List
        for (Map.Entry<Integer, Integer> entry : monthlyPointMap.entrySet()) {
            // System.out.println("customerID " + customerID + " month " + entry.getKey() + " point " + entry.getValue());
            monthlyPointList.add(new RewardPoint(customerID, entry.getKey(), entry.getValue()));
        }

        return monthlyPointList;
    }
}
