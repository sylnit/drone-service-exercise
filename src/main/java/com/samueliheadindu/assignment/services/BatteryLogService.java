package com.samueliheadindu.assignment.services;

import com.samueliheadindu.assignment.entity.BatteryLog;
import com.samueliheadindu.assignment.repository.IBatteryLogRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatteryLogService {

    @Autowired
    IBatteryLogRepo logRepo;

    public void logBatteryLevel(Long droneId, Long batteryLevel) {
        //
    }
}
