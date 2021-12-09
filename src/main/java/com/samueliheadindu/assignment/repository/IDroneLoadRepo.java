package com.samueliheadindu.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samueliheadindu.assignment.entity.DroneLoad;

@Repository
public interface IDroneLoadRepo extends JpaRepository<DroneLoad, Long> {

}
