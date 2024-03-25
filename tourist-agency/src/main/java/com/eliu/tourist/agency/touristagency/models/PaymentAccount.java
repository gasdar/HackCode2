package com.eliu.tourist.agency.touristagency.models;

import java.util.ArrayList;
import java.util.List;

import com.eliu.tourist.agency.touristagency.validations.IsRequired;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="payment_accounts")
public class PaymentAccount {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    @IsRequired
    @Size(min=8, max=35)
    private String accountName;

    @JsonProperty(access=Access.WRITE_ONLY)
    @IsRequired
    @Size(min=5, max=20)
    private String pinpass;

    private Double salary;

    @ManyToOne
    @JoinColumn(name="person_id")
    @JsonIgnoreProperties({"paymentAccounts", "hibernateLazyInitializer", "handler"})
    @NotNull
    private Person person;
    
    @ManyToOne
    @JoinColumn(name="payment_method_id")
    @JsonIgnoreProperties({"paymentAccounts", "hibernateLazyInitializer", "handler"})
    @NotNull
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy="paymentAccount")
    @JsonIgnoreProperties({"paymentAccount", "hibernateLazyInitializer", "handler"})
    private List<Movement> movements;

    public PaymentAccount() {
        movements = new ArrayList<>();
    }

    public PaymentAccount(@Size(min = 8, max = 35) String accountName, @Size(min = 5, max = 20) String pinpass,
            Double salary, @NotNull Person person, @NotNull PaymentMethod paymentMethod, List<Movement> movements) {
        this();
        this.accountName = accountName;
        this.pinpass = pinpass;
        this.salary = salary;
        this.person = person;
        this.paymentMethod = paymentMethod;
        this.movements = movements;
    }

    @PrePersist
    public void prePersist() {
        this.salary = 0.0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPinpass() {
        return pinpass;
    }

    public void setPinpass(String pinpass) {
        this.pinpass = pinpass;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<Movement> getMovements() {
        return movements;
    }

    public void setMovements(List<Movement> movements) {
        this.movements = movements;
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", accountName=" + accountName +
                ", salary=" + salary +
                ", paymentMethod=" + paymentMethod +
                ", movements=" + movements + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
        result = prime * result + ((pinpass == null) ? 0 : pinpass.hashCode());
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
        PaymentAccount other = (PaymentAccount) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (accountName == null) {
            if (other.accountName != null)
                return false;
        } else if (!accountName.equals(other.accountName))
            return false;
        if (pinpass == null) {
            if (other.pinpass != null)
                return false;
        } else if (!pinpass.equals(other.pinpass))
            return false;
        return true;
    }

}
