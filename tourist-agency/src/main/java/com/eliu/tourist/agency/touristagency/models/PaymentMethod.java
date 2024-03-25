package com.eliu.tourist.agency.touristagency.models;

import java.util.ArrayList;
import java.util.List;

import com.eliu.tourist.agency.touristagency.validations.IsRequired;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="payment_methods")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Short id;

    @Column(unique=true)
    @IsRequired
    @Size(min=8, max=25)
    private String name;

    private Float commission;

    @OneToMany(mappedBy="paymentMethod")
    @JsonIgnoreProperties({"paymentMethod", "hibernateLazyInitializer", "handler"})
    private List<PaymentAccount> paymentAccounts;

    public PaymentMethod() {
        paymentAccounts = new ArrayList<>();
    }

    public PaymentMethod(@Size(min = 8, max = 25) String name, Float commission, List<PaymentAccount> paymentAccounts) {
        this();
        this.name = name;
        this.commission = commission;
        this.paymentAccounts = paymentAccounts;
    }

    @PrePersist
    public void prePersist() {
        this.commission = 0F;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCommission() {
        return commission;
    }

    public void setCommission(Float commission) {
        this.commission = commission;
    }

    public List<PaymentAccount> getPaymentAccounts() {
        return paymentAccounts;
    }

    public void setPaymentAccounts(List<PaymentAccount> paymentAccounts) {
        this.paymentAccounts = paymentAccounts;
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", name=" + name +
                ", commission=" + commission + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((commission == null) ? 0 : commission.hashCode());
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
        PaymentMethod other = (PaymentMethod) obj;
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
        if (commission == null) {
            if (other.commission != null)
                return false;
        } else if (!commission.equals(other.commission))
            return false;
        return true;
    }
    
}
