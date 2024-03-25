package com.eliu.tourist.agency.touristagency.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double cost;

    @NotNull
    private Double total;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    // Por defecto, cuando se creá una reserva que esta relacionada a un servio
    // esta activa.
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name="tourist_package_id")
    @JsonIgnoreProperties({"bookings", "hibernateLazyInitializer", "handler"})
    private TouristPackage touristPackage;

    private Service service;

}
