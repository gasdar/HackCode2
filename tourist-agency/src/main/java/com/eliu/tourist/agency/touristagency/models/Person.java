package com.eliu.tourist.agency.touristagency.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="people")
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    // Este campo es el DNI, que en inglés sería id, pero, como ya existe este atributo
    // en la clase, se crea el campo: pid (Personal Identity Document)
    @Column(unique=true)
    private String pid;
    
    private Date dataOfBirth;
    
    private String phone;
    
    @Column(unique=true)
    private String emailAddress;
    
    @ManyToOne
    @JoinColumn(name="address_id")
    @JsonIgnoreProperties({"people", "hibernateLazyInitializer", "handler"})
    private Address address;

    @ManyToOne
    @JoinColumn(name="nationality_id")
    @JsonIgnoreProperties({"people", "hibernateLazyInitializer", "handler"})
    private Nationality nationality;

    @OneToOne(mappedBy="person")
    @JsonIgnoreProperties({"person", "hibernateLazyInitializer", "handler"})
    private User user;

    private List<PaymentAccount> paymentAccounts;

    private List<TouristPackage> touristPackages;

    public Person() {
        paymentAccounts = new ArrayList<>();
        touristPackages = new ArrayList<>();
    }

    public Person(String name, String lastname, String pid, Date dataOfBirth, String phone, String emailAddress,
            Address address, Nationality nationality, User user, List<PaymentAccount> paymentAccounts,
            List<TouristPackage> touristPackages) {
        this();
        this.name = name;
        this.lastname = lastname;
        this.pid = pid;
        this.dataOfBirth = dataOfBirth;
        this.phone = phone;
        this.emailAddress = emailAddress;
        this.address = address;
        this.nationality = nationality;
        this.user = user;
        this.paymentAccounts = paymentAccounts;
        this.touristPackages = touristPackages;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Date getDataOfBirth() {
        return dataOfBirth;
    }

    public void setDataOfBirth(Date dataOfBirth) {
        this.dataOfBirth = dataOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
        result = prime * result + ((pid == null) ? 0 : pid.hashCode());
        result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
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
        Person other = (Person) obj;
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
        if (lastname == null) {
            if (other.lastname != null)
                return false;
        } else if (!lastname.equals(other.lastname))
            return false;
        if (pid == null) {
            if (other.pid != null)
                return false;
        } else if (!pid.equals(other.pid))
            return false;
        if (emailAddress == null) {
            if (other.emailAddress != null)
                return false;
        } else if (!emailAddress.equals(other.emailAddress))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", name=" + name +
                ", lastname=" + lastname +
                ", pid=" + pid +
                ", dataOfBirth=" + dataOfBirth +
                ", phone=" + phone +
                ", emailAddress=" + emailAddress +
                ", address=" + address +
                ", nationality=" + nationality + "}";
    }

    
    
}
