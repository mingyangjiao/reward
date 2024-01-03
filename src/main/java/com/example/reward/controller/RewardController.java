package com.example.reward.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.reward.model.RewardPoint;
import com.example.reward.service.RewardService;

@RestController
@RequestMapping("/api/reward")
public class RewardController {

    // RewardService in Service layer 
    @Autowired
    private RewardService rewardService;

    // GET request to /api/reward/1 to retrieve reward points for customer with ID 1
    @GetMapping("/{customerID}")
    public ResponseEntity<List<RewardPoint>> getRewardForCustomer(@PathVariable Long customerID) {

        // For the specified customerID, retrieve list of reward points 
        List<RewardPoint> rewardPointList = rewardService.getRewardPoint(customerID);

        // Return a ResponseEntity with HTTP ok and list of RewardPoint in response
        return ResponseEntity.ok(rewardPointList);
    }
}
