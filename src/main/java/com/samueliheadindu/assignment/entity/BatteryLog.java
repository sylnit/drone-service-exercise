package com.samueliheadindu.assignment.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

@Table(name = "battery_log")
@Entity
@Setter
@Getter
@ToString
public class BatteryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "battery_level")
    private Long batteryLevel;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "drone_id")
    private Drone drone;

}
