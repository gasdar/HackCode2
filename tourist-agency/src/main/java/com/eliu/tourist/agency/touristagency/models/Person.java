package com.eliu.tourist.agency.touristagency.models;

import java.util.Date;

import com.eliu.tourist.agency.touristagency.validations.IsRequired;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// En caso de crear una tabla para todas las clases hijas, con el tipo de herencia por defecto
// En este caso las clases hijas no pueden llevar la anotación @Table
// @Inheritance(strategy=InheritanceType.SINGLE_TABLE)

// En caso de persistir solo las clases hijas
// @MappedSuperclass

@Entity
@Table(name="people")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Person {

    // En caso de crear una tabla para todas las clases hijas, con el tipo de herencia por defecto
    // @GeneratedValue(strategy=GenerationType.IDENTITY)
    
    // En caso de persistir solo las clases hijas con la anotación @MappedSuperClass
    // @GeneratedValue(strategy=GenerationType.TABLE)
    
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    protected Long id;

    @IsRequired
    @Size(min=3, max=20)
    protected String name;

    @IsRequired
    @Size(min=3, max=20)
    protected String lastname;

    // Este campo es el DNI, que en inglés sería id, pero, como ya existe este atributo
    // en la clase, se crea el campo: pid (Personal Identity Document)
    @Column(unique=true)
    @IsRequired
    @Size(min=7, max=15)
    protected String pid;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    protected Date dateOfBirth;
    
    @IsRequired
    @Size(min=9, max=12)
    protected String phone;
    
    @Column(unique=true)
    @IsRequired
    protected String emailAddress;
    
    @ManyToOne
    @JoinColumn(name="address_id")
    @JsonIgnoreProperties({"people", "hibernateLazyInitializer", "handler"})
    @NotNull
    protected Address address;

    @ManyToOne
    @JoinColumn(name="nationality_id")
    @JsonIgnoreProperties({"people", "hibernateLazyInitializer", "handler"})
    @NotNull
    protected Nationality nationality;

    @OneToOne(mappedBy="person")
    @JsonIgnoreProperties({"person", "hibernateLazyInitializer", "handler"})
    @NotNull
    protected User user;

    @ManyToOne
    @JoinColumn(name="person_type_id")
    @JsonIgnoreProperties({"people", "hibernateLazyInitializer", "handler"})
    @NotNull
    protected PersonType personType;

    public Person() {
    }

    public Person(@Size(min = 3, max = 20) String name, @Size(min = 3, max = 20) String lastname,
            @Size(min = 7, max = 15) String pid, @NotNull Date dateOfBirth, @Size(min = 9, max = 12) String phone,
            String emailAddress, @NotNull Address address, @NotNull Nationality nationality, @NotNull User user,
            @NotNull PersonType personType) {
        this.name = name;
        this.lastname = lastname;
        this.pid = pid;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.emailAddress = emailAddress;
        this.address = address;
        this.nationality = nationality;
        this.user = user;
        this.personType = personType;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
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
                ", personType=" + personType + "}";
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
    
}
