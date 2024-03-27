package com.eliu.tourist.agency.touristagency.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eliu.tourist.agency.touristagency.validations.IsRequired;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @NotNull
    private Double cost;

    // En caso de ser un servicio de compras de pasajes, este campo nos ayudará
    // a saber cuantos es el máximo de pasajes que se pueden comprar
    @NotNull
    private Integer maxLimit;

    // Este campo nos permitirá saber la cantidad de pasajes
    // que se han gastado hasta el momento, por defecto, al crear el objeto
    // el valor es 0
    private Integer currentLimit;

    // Nos permitirá saber el estado del servicio, saber si aún esta disponible,
    // por defecto, al crearse uno, está disponible
    private Boolean isAvailable;

    @ManyToOne
    @JoinColumn(name="service_type_id")
    @JsonIgnoreProperties({"services", "hibernateLazyInitializer", "handler"})
    @NotNull
    private ServiceType serviceType;

    @OneToMany(mappedBy="service")
    @JsonIgnoreProperties({"service", "hibernateLazyInitializer", "handler"})
    private List<Booking> bookings;

    public Service() {
        bookings = new ArrayList<>();
    }

    public Service(@Size(min = 10, max = 50) String name, @Size(min = 20, max = 255) String description, String address,
            String destiny, @NotNull Date startDate, @NotNull Date endDate, @NotNull Double cost,
            @NotNull Integer maxLimit, Integer currentLimit, Boolean isAvailable, @NotNull ServiceType serviceType,
            List<Booking> bookings) {
        this();
        this.name = name;
        this.description = description;
        this.address = address;
        this.destiny = destiny;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
        this.maxLimit = maxLimit;
        this.currentLimit = currentLimit;
        this.isAvailable = isAvailable;
        this.serviceType = serviceType;
        this.bookings = bookings;
    }

    @PrePersist
    public void prePersist() {
        this.currentLimit = 0;
        this.isAvailable = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(Integer maxLimit) {
        this.maxLimit = maxLimit;
    }

    public Integer getCurrentLimit() {
        return currentLimit;
    }

    public void setCurrentLimit(Integer currentLimit) {
        this.currentLimit = currentLimit;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", name=" + name +
                ", description=" + description +
                ", address=" + address +
                ", destiny=" + destiny +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", cost=" + cost +
                ", maxLimit=" + maxLimit +
                ", currentLimit=" + currentLimit +
                ", isAvailable=" + isAvailable +
                ", serviceType=" + serviceType + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Service other = (Service) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
