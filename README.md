README

## Introduction

This is a reward program to calculate points in the past three months based on each recorded purchase. A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction.

## Restful API Design

### Request
- **Method:** GET
- **Path Parameter:** customerID 
- **Path:** /api/reward/{customerID}
- **Description:** Retrieve points of a specific customerID.

### Response: 
The response JSON contain list of objects and each object has three attributes: customerID, month, totalPoints. The return List is sorted by month in ascending order. For customer didn't earn any point, list will be empty.

- customerID: unique ID of the customer.
- month: The month for which the total reward points are calculated (from 1 to 12).
- totalPoints: The reward points of customerID in the given month.


### Examples:
Retrieve points of a customerID 3 in the past three months.
GET request: /api/reward/3

GET Response: 
```
[
   {
      "customerID":3,
      "month":10,
      "totalPoints":90
   },
   {
      "customerID":3,
      "month":11,
      "totalPoints":130
   },
   {
      "customerID":3,
      "month":12,
      "totalPoints":170
   }
]
```
The response JSON represents the reward points earned by the customer 3 over past three months:
    October 90 points; 
    November 130 points; 
    December 170 points.

### Project Structure
```
├── RewardApplication.java
├── controller
│   └── RewardController.java
├── dao
│   └── TransactionRepository.java
├── entity
│   └── Transaction.java
├── model
│   └── RewardPoint.java
└── service
    └── RewardService.java
```