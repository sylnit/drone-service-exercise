package com.samueliheadindu.assignment.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samueliheadindu.assignment.entity.DroneLoad;
import com.samueliheadindu.assignment.entity.Drone;

@Repository
public interface IDroneLoadRepo extends JpaRepository<DroneLoad, Long> {

    List<DroneLoad> findByDrone(Drone drone);
}
