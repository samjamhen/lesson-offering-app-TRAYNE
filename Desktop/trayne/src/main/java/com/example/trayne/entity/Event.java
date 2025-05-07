package com.example.trayne.entity;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jdk.jfr.DataAmount;

@Entity
@DataAmount
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Date eventDate = null;
    private String location;
    private boolean equipmentProvided;
    private String description;
    private BigDecimal price;
    @ManyToMany(mappedBy = "events")
    private List<Client> attendees = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    // Constructor
    public Event() {}

    public Event(String name, Date eventDate, String location, boolean equipmentProvided, String description, BigDecimal price, Instructor instructor) {
        this.name = name;
        this.eventDate = eventDate;
        this.location = location;
        this.equipmentProvided = equipmentProvided;
        this.description = description;
        this.instructor = instructor;
    }

    public Event(long id, String name, Date eventDate, String location, boolean equipmentProvided, String description, BigDecimal price, List<Client> attendees, Instructor instructor) {
        this.id = id;
        this.name = name;
        this.eventDate = eventDate;
        this.location = location;
        this.equipmentProvided = equipmentProvided;
        this.description = description;
        this.attendees = attendees;
        this.instructor = instructor;
    }

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for eventDate
    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    // Getter and Setter for location
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Getter and Setter for equipmentProvided
    public boolean isEquipmentProvided() {
        return equipmentProvided;
    }

    public void setEquipmentProvided(boolean equipmentProvided) {
        this.equipmentProvided = equipmentProvided;
    }

    // Getter and Setter for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and Setter for price
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }    

    public Instructor getInstructor() {
        return instructor;
    }
    
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    
}
