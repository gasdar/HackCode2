package com.eliu.tourist.agency.touristagency.models;

import java.util.Date;

import com.eliu.tourist.agency.touristagency.validations.IsRequired;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="movements")
public class Movement {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @IsRequired
    @Size(min=4, max=50)
    private String description;

    @NotNull
    private Double currentAmount;

    @NotNull
    private Double movementAmount;

    @NotNull
    private Date movementDate;

    @ManyToOne
    @JoinColumn(name="payment_account_id")
    @JsonIgnoreProperties({"movements", "hibernateLazyInitializer", "handler"})
    @NotNull
    private PaymentAccount paymentAccount;

    @ManyToOne
    @JoinColumn(name="movement_id")
    @JsonIgnoreProperties({"movements", "hibernateLazyInitializer", "handler"})
    @NotNull
    private MovementType movementType;

    public Movement() {
    }

    public Movement(@Size(min = 4, max = 50) String description, @NotNull Double currentAmount,
            @NotNull Double movementAmount, @NotNull Date movementDate, @NotNull PaymentAccount paymentAccount,
            @NotNull MovementType movementType) {
        this.description = description;
        this.currentAmount = currentAmount;
        this.movementAmount = movementAmount;
        this.movementDate = movementDate;
        this.paymentAccount = paymentAccount;
        this.movementType = movementType;
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

    public Double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public Double getMovementAmount() {
        return movementAmount;
    }

    public void setMovementAmount(Double movementAmount) {
        this.movementAmount = movementAmount;
    }

    public Date getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(Date movementDate) {
        this.movementDate = movementDate;
    }

    public PaymentAccount getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(PaymentAccount paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", description=" + description +
                ", currentAmount=" + currentAmount +
                ", movementAmount=" + movementAmount +
                ", movementDate=" + movementDate +
                ", movementType=" + movementType + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((currentAmount == null) ? 0 : currentAmount.hashCode());
        result = prime * result + ((movementAmount == null) ? 0 : movementAmount.hashCode());
        result = prime * result + ((movementDate == null) ? 0 : movementDate.hashCode());
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
        Movement other = (Movement) obj;
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
        if (currentAmount == null) {
            if (other.currentAmount != null)
                return false;
        } else if (!currentAmount.equals(other.currentAmount))
            return false;
        if (movementAmount == null) {
            if (other.movementAmount != null)
                return false;
        } else if (!movementAmount.equals(other.movementAmount))
            return false;
        if (movementDate == null) {
            if (other.movementDate != null)
                return false;
        } else if (!movementDate.equals(other.movementDate))
            return false;
        return true;
    }

}
