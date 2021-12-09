package com.samueliheadindu.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samueliheadindu.assignment.entity.Medication;

@Repository
public interface IMedicationRepo extends JpaRepository<Medication, Long> {

}
