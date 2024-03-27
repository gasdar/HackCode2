package com.eliu.tourist.agency.touristagency.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date endDate;

    // Por defecto, cuando se creá una reserva que esta relacionada a un servio
    // esta activa.
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name="tourist_package_id")
    @JsonIgnoreProperties({"bookings", "hibernateLazyInitializer", "handler"})
    @NotNull
    private TouristPackage touristPackage;

    @ManyToOne
    @JoinColumn(name="service_id")
    @JsonIgnoreProperties({"bookings", "hibernateLazyInitializer", "handler"})
    @NotNull
    private Service service;

    public Booking() {
    }

    public Booking(@NotNull Integer quantity, @NotNull Double cost, @NotNull Double total, @NotNull Date startDate,
            @NotNull Date endDate, Boolean isActive, TouristPackage touristPackage, Service service) {
        this.quantity = quantity;
        this.cost = cost;
        this.total = total;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.touristPackage = touristPackage;
        this.service = service;
    }

    @PrePersist
    public void prePersist() {
        this.isActive = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public TouristPackage getTouristPackage() {
        return touristPackage;
    }

    public void setTouristPackage(TouristPackage touristPackage) {
        this.touristPackage = touristPackage;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", quantity=" + quantity +
                ", cost=" + cost +
                ", total=" + total +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isActive=" + isActive +
                ", service=" + service + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        result = prime * result + ((cost == null) ? 0 : cost.hashCode());
        result = prime * result + ((total == null) ? 0 : total.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Booking other = (Booking) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (quantity == null) {
            if (other.quantity != null)
                return false;
        } else if (!quantity.equals(other.quantity))
            return false;
        if (cost == null) {
            if (other.cost != null)
                return false;
        } else if (!cost.equals(other.cost))
            return false;
        if (total == null) {
            if (other.total != null)
                return false;
        } else if (!total.equals(other.total))
            return false;
        return true;
    }

}
