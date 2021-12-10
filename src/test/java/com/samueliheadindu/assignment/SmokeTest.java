package com.samueliheadindu.assignment;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.samueliheadindu.assignment.controller.DispatchController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

    @Autowired
    DispatchController controller;

    @Test
    void contextLoads() {
        assertNotNull(controller);
    }

}
