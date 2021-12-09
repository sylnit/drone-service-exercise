package com.samueliheadindu.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samueliheadindu.assignment.entity.Drone;

@Repository
public interface IDroneRepo extends JpaRepository<Drone, Long> {

}
