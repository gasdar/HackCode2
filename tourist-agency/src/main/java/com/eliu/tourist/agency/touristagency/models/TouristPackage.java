package com.eliu.tourist.agency.touristagency.models;

import java.util.List;

import com.eliu.tourist.agency.touristagency.validations.IsRequired;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tourist_packages")
public class TouristPackage {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @IsRequired
    @Size(min=10, max=50)
    private String description;

    // Este atributo nos va ayudar a obtener el valor total de un paquete turístico, ya que,
    // en caso de ser más de un servicio hay un 10% menos del total del valor.
    private Boolean isIndividual;

    @NotNull
    private Double total;

    // El paquete turístico tendrá varios procesos, entre ellos, cuando el cliente quiera
    // confirmar el paquete turístico, el que lo tiene que firmar es el empleado de la empresa
    // y así una vez confirmado el paquete, si todo esta bien, el cliente podrá pagarlo
    private Boolean isConfirm;

    // Una vez que el cliente pague el paquete turístico, entonces, se crea la venta y
    // el paquete turístico esta finalizado
    private Boolean isFinished;

    @ManyToOne
    @JoinColumn(name="person_id")
    @JsonIgnoreProperties({"touristPackages", "hibernateLazyInitializer", "handler"})
    @NotNull
    private Person person;

    @OneToOne(mappedBy="touristPackage")
    @JsonIgnoreProperties({"touristPackage", "hibernateLazyInitializer", "handler"})
    private Sale sale;
    
    @NotNull
    @OneToMany(mappedBy="touristPackage")
    @JsonIgnoreProperties({"touristPackage", "hibernateLazyInitializer", "handler"})
    private List<Booking> bookings;

    

    @PrePersist
    public void prePersist() {
        this.isConfirm = false;
        this.isFinished = false;
    }

}
