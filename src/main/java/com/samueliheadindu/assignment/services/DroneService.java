package com.samueliheadindu.assignment.services;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.samueliheadindu.assignment.entity.Drone;
import com.samueliheadindu.assignment.repository.IDroneRepo;
import com.samueliheadindu.assignment.templates.BatteryLevelTpl;

@Service
public class DroneService {

    @Autowired
    IDroneRepo droneRepo;

    public ResponseEntity<Drone> registerDrone(Drone drone) {
        try {
            return new ResponseEntity<>(droneRepo.save(drone), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Drone>> getAllDrones() {
        try {
            List<Drone> drones = droneRepo.findAll();
            return new ResponseEntity<>(drones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // public ResponseEntity<Drone> getDrone(Long droneId) {
    // try {
    // Drone drone = droneRepo.getById(droneId);
    // return new ResponseEntity<>(drone, HttpStatus.OK);
    // } catch (Exception e) {
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
    // }

    public ResponseEntity<List<Drone>> getAvailable() {
        try {
            List<Drone> available = new ArrayList<>();
            List<Drone> drones = droneRepo.findAll();
            if (drones != null && !drones.isEmpty()) {
                for (Drone d : drones) {
                    Long batteryCapacity = d.getBatteryCapacity();
                    String state = d.getState();
                    Boolean loadableState = (state.equals("IDLE") || state.equals("LOADING")) ? true : false;
                    if (batteryCapacity >= 25 && loadableState) {
                        available.add(d);
                    }
                }
            }
            return new ResponseEntity<>(available, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<BatteryLevelTpl> getBatteryLevel(Long droneId) {
        try {
            Drone drone = droneRepo.getById(droneId);
            BatteryLevelTpl tpl = new BatteryLevelTpl();
            tpl.setId(drone.getId());
            tpl.setBatteryLevel(drone.getBatteryCapacity().toString() + "%");
            return new ResponseEntity<>(tpl, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
