package com.samueliheadindu.assignment.entity;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

@Table(name = "drones")
@Entity
@Setter
@Getter
@ToString
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial_number")
    private String serialNumber;

    private String model;

    @Column(name = "weight_limit")
    private Long weightLimit;

    @Column(name = "battery_capacity")
    private Long batteryCapacity;

    private String state;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "drone")
    private List<DroneLoad> items = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "drone")
    private List<BatteryLog> history = new ArrayList<>();
}
