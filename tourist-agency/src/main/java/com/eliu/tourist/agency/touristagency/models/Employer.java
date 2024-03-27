package com.eliu.tourist.agency.touristagency.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="employers")
public class Employer extends Person {

    // El salario del empleador dependerá de su cargo o posición en la empresa el valor mínimo
    // por defecto, será $1200
    private Double currentSalary;

    @ManyToOne
    @JoinColumn(name="position_id")
    @JsonIgnoreProperties({"employers", "hibernateLazyInitializer", "handler"})
    @NotNull
    private Position position;

    @OneToMany(mappedBy="employer")
    @JsonIgnoreProperties({"employer", "hibernateLazyInitializer", "handler"})
    protected List<TouristPackage> touristPackages;

    public Employer() {
        touristPackages = new ArrayList<>();
    }

    public Employer(@Size(min = 3, max = 20) String name, @Size(min = 3, max = 20) String lastname,
            @Size(min = 7, max = 15) String pid, @NotNull Date dateOfBirth, @Size(min = 9, max = 12) String phone,
            String emailAddress, @NotNull Address address, @NotNull Nationality nationality, @NotNull User user,
            @NotNull PersonType personType, Double currentSalary, @NotNull Position position,
            List<TouristPackage> touristPackages) {
        super(name, lastname, pid, dateOfBirth, phone, emailAddress, address, nationality, user, personType);
        this.currentSalary = currentSalary;
        this.position = position;
        this.touristPackages = touristPackages;
    }

    public Double getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(Double currentSalary) {
        this.currentSalary = currentSalary;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<TouristPackage> getTouristPackages() {
        return touristPackages;
    }

    public void setTouristPackages(List<TouristPackage> touristPackages) {
        this.touristPackages = touristPackages;
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", name=" + name +
                ", lastname=" + lastname +
                ", pid=" + pid +
                ", dateOfBirth=" + dateOfBirth +
                ", phone=" + phone +
                ", emailAddress=" + emailAddress +
                ", address=" + address +
                ", nationality=" + nationality +
                ", personType=" + personType +
                ", currentSalary=" + currentSalary +
                ", position=" + position + "}";
    }

}
