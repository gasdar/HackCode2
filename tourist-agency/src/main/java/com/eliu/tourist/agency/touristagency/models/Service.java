package com.eliu.tourist.agency.touristagency.models;

import java.util.Date;
import java.util.List;

import com.eliu.tourist.agency.touristagency.validations.IsRequired;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="services")
public class Service {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    @IsRequired
    @Size(min=10, max=50)
    private String name;

    @IsRequired
    @Size(min=20, max=255)
    private String description;

    @IsRequired
    private String address;

    // El destino será requerido, pero, para servicios como estadia de hotel, será
    // un campo no aplicable, solo en servicios de compras de boletos de avión o tren o
    // servicios similares, será utilizado este campo
    @IsRequired
    private String destiny;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    private Double cost;

    // En caso de ser un servicio de compras de pasajes, este campo nos ayudará
    // a saber cuantos es el máximo de pasajes que se pueden comprar
    @NotNull
    private Integer maxLimit;

    // Este campo nos permitirá saber los pasajes la cantidad de pasajes
    // que se han gastado hasta el momento, por defecto, al crear el objeto
    // el valor es 0
    private Integer currentLimit;

    // Nos permitirá saber el estado del servicio, saber si aún esta disponible,
    // por defecto, al crearse uno, está disponible
    private Boolean isAvailable;

    @NotNull
    private ServiceType serviceType;

    
    private List<Booking> bookings;


}
