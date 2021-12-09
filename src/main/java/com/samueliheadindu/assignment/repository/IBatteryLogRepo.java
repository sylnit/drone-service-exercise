package com.samueliheadindu.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samueliheadindu.assignment.entity.BatteryLog;

@Repository
public interface IBatteryLogRepo extends JpaRepository<BatteryLog, Long> {

}
