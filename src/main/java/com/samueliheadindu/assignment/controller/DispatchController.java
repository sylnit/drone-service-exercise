package com.samueliheadindu.assignment.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.samueliheadindu.assignment.entity.Drone;
import com.samueliheadindu.assignment.entity.DroneLoad;
import com.samueliheadindu.assignment.services.DroneService;
import com.samueliheadindu.assignment.services.LoadService;
import com.samueliheadindu.assignment.templates.BatteryLevelTpl;

@RestController
public class DispatchController {

    @Autowired
    DroneService droneService;

    @Autowired
    LoadService loadService;

    @PostMapping("/drones")
    public ResponseEntity<Drone> registerDrone(@RequestBody Drone drone) {
        return droneService.registerDrone(drone);
    }

    @GetMapping("/drones")
    public ResponseEntity<List<Drone>> getAllDrones() {
        return droneService.getAllDrones();
    }

    @PostMapping("/drones/{droneId}/load/{medId}")
    public ResponseEntity<?> loadDrone(@PathVariable Long droneId, @PathVariable Long medId) {
        return loadService.loadDrone(droneId, medId);
    }

    @GetMapping("/drones/{droneId}/medications")
    public ResponseEntity<List<DroneLoad>> getLoadedItems(@PathVariable Long droneId) {
        return loadService.getDroneLoad(droneId);
    }

    @GetMapping("/drones/available")
    public ResponseEntity<List<Drone>> getAvailable() {
        return droneService.getAvailable();
    }

    @GetMapping("/battery-level/{droneId}")
    public ResponseEntity<BatteryLevelTpl> getBatteryLevel(@PathVariable Long droneId) {
        return droneService.getBatteryLevel(droneId);
    }

}
