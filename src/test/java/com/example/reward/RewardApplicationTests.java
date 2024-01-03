package com.example.reward;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.reward.controller.RewardController;

@SpringBootTest
class RewardApplicationTests {

	@Autowired
    private RewardController controller;
	
	// if application context is correctly set up
	// controller should not be null
	@Test
	void invokeRewardControllerTest() {
		assertThat(controller).isNotNull();
	}
}
