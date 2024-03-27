package com.eliu.tourist.agency.touristagency.models;

import java.util.ArrayList;
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
    @JoinColumn(name="client_id")
    @JsonIgnoreProperties({"touristPackages", "hibernateLazyInitializer", "handler"})
    @NotNull
    private Client client;
    
    @ManyToOne
    @JoinColumn(name="employer_id")
    @JsonIgnoreProperties({"touristPackages", "hibernateLazyInitializer", "handler"})
    @NotNull
    private Employer employer;

    @OneToOne(mappedBy="touristPackage")
    @JsonIgnoreProperties({"touristPackage", "hibernateLazyInitializer", "handler"})
    private Sale sale;
    
    @NotNull
    @OneToMany(mappedBy="touristPackage")
    @JsonIgnoreProperties({"touristPackage", "hibernateLazyInitializer", "handler"})
    private List<Booking> bookings;

    public TouristPackage() {
        bookings = new ArrayList<>();
    }

    public TouristPackage(@Size(min = 10, max = 50) String description, Boolean isIndividual, @NotNull Double total,
            Boolean isConfirm, Boolean isFinished, @NotNull Client client, @NotNull Employer employer, Sale sale, @NotNull List<Booking> bookings) {
        this();
        this.description = description;
        this.isIndividual = isIndividual;
        this.total = total;
        this.isConfirm = isConfirm;
        this.isFinished = isFinished;
        this.client = client;
        this.employer = employer;
        this.sale = sale;
        this.bookings = bookings;
    }

    @PrePersist
    public void prePersist() {
        this.isConfirm = false;
        this.isFinished = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsIndividual() {
        return isIndividual;
    }

    public void setIsIndividual(Boolean isIndividual) {
        this.isIndividual = isIndividual;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getIsConfirm() {
        return isConfirm;
    }

    public void setIsConfirm(Boolean isConfirm) {
        this.isConfirm = isConfirm;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
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
                ", description=" + description +
                ", isIndividual=" + isIndividual +
                ", total=" + total +
                ", isConfirm=" + isConfirm +
                ", isFinished=" + isFinished +
                ", client=" + client +
                ", employer=" + employer +
                ", bookings=" + bookings + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((isIndividual == null) ? 0 : isIndividual.hashCode());
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
        TouristPackage other = (TouristPackage) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (isIndividual == null) {
            if (other.isIndividual != null)
                return false;
        } else if (!isIndividual.equals(other.isIndividual))
            return false;
        if (total == null) {
            if (other.total != null)
                return false;
        } else if (!total.equals(other.total))
            return false;
        return true;
    }

}
