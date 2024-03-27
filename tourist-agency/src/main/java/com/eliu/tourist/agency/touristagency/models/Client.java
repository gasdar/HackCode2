package com.eliu.tourist.agency.touristagency.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="clients")
public class Client extends Person {

    @OneToMany(mappedBy="client")
    @JsonIgnoreProperties({"client", "hibernateLazyInitializer", "handler"})
    protected List<PaymentAccount> paymentAccounts;

    @OneToMany(mappedBy="client")
    @JsonIgnoreProperties({"client", "hibernateLazyInitializer", "handler"})
    protected List<TouristPackage> touristPackages;

    public Client() {
        paymentAccounts = new ArrayList<>();
        touristPackages = new ArrayList<>();
    }

    public Client(@Size(min = 3, max = 20) String name, @Size(min = 3, max = 20) String lastname,
            @Size(min = 7, max = 15) String pid, @NotNull Date dateOfBirth, @Size(min = 9, max = 12) String phone,
            String emailAddress, @NotNull Address address, @NotNull Nationality nationality, @NotNull User user,
            @NotNull PersonType personType, List<PaymentAccount> paymentAccounts,
            List<TouristPackage> touristPackages) {
        super(name, lastname, pid, dateOfBirth, phone, emailAddress, address, nationality, user, personType);
        this.paymentAccounts = paymentAccounts;
        this.touristPackages = touristPackages;
    }

    public List<PaymentAccount> getPaymentAccounts() {
        return paymentAccounts;
    }

    public void setPaymentAccounts(List<PaymentAccount> paymentAccounts) {
        this.paymentAccounts = paymentAccounts;
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
                ", paymentAccounts=" + paymentAccounts + "}";
    }

}
