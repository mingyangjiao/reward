package com.example.reward.model;

/*
 * POJO class RewardPoint
 * The 'month' attribute is an integer value from 1 to 12, representing the twelve months of the year.
 * The 'totalPoints' attribute is the quantity of points earned by the customer in the given month.
 */

public class RewardPoint {

    private Long customerID;
    private int month;
    private int totalPoints;

    public RewardPoint(Long customerID, int month, int totalPoints) {
        this.customerID = customerID;
        this.month = month;
        this.totalPoints = totalPoints;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
}
