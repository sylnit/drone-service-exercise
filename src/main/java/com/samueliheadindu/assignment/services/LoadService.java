package com.samueliheadindu.assignment.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.samueliheadindu.assignment.entity.Drone;
import com.samueliheadindu.assignment.entity.DroneLoad;
import com.samueliheadindu.assignment.entity.Medication;
import com.samueliheadindu.assignment.repository.IDroneRepo;
import com.samueliheadindu.assignment.repository.IDroneLoadRepo;
import com.samueliheadindu.assignment.repository.IMedicationRepo;

@Service
public class LoadService {

    @Autowired
    IDroneLoadRepo loadRepo;

    @Autowired
    IDroneRepo droneRepo;

    @Autowired
    IMedicationRepo medRepo;

    public ResponseEntity<?> loadDrone(Long droneId, Long medId) {
        try {
            List<DroneLoad> alreadyLoaded = getLoad(droneId);
            Medication med = getMedication(medId);
            Drone drone = droneRepo.getById(droneId);
            Long totalLoadedWeight = getTotalLoadWeight(alreadyLoaded);
            Boolean canTakeLoad = canLoad(drone.getWeightLimit(), totalLoadedWeight, med.getWeight());
            String state = drone.getState();
            Boolean loadableState = (state.equals("IDLE") || state.equals("LOADING")) ? true : false;
            if (alreadyLoaded != null && canTakeLoad && loadableState && drone.getBatteryCapacity() >= 25) {
                DroneLoad load = new DroneLoad();
                load.setDrone(drone);
                load.setMedicationName(med.getName());
                load.setWeight(med.getWeight());
                loadRepo.save(load);
                return new ResponseEntity<>(load, HttpStatus.OK);
            }
            String msg = "";
            if (alreadyLoaded == null) {
                msg = "No drone found";
            } else if (drone.getBatteryCapacity() < 25) {
                msg = "Battery Low, Drone can't be loaded.";
            } else if (!canTakeLoad) {
                msg = "Overload!!! Load can't fit into drone.";
            } else if (!loadableState) {
                msg = "Drone is not IDLE or LOADING.";
            }
            return new ResponseEntity<>(msg, HttpStatus.FORBIDDEN);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<DroneLoad>> getDroneLoad(Long droneId) {
        try {
            List<DroneLoad> loads = getLoad(droneId);
            return new ResponseEntity<>(loads, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private List<DroneLoad> getLoad(Long droneId) {
        Drone drone = droneRepo.getById(droneId);
        List<DroneLoad> loads = loadRepo.findByDrone(drone);
        return loads;
    }

    private boolean canLoad(Long droneLoadCapacity, Long totalLoad, Long newLoad) {
        if (totalLoad + newLoad > droneLoadCapacity) {
            return false;
        }
        return true;
    }

    private Medication getMedication(Long medId) {
        Medication med = medRepo.getById(medId);
        return med;
    }

    private Long getTotalLoadWeight(List<DroneLoad> loads) {
        Long total = 0L;
        for (DroneLoad load : loads) {
            total += load.getWeight();
        }
        return total;
    }

}
