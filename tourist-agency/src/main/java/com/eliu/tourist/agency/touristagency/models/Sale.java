package com.eliu.tourist.agency.touristagency.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eliu.tourist.agency.touristagency.validations.IsRequired;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="sales")
public class Sale {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date saleDate;

    @IsRequired
    private String clientAccountName;

    @IsRequired
    private String paymentMethod;

    @NotNull
    private Float commission;

    @NotNull
    private Double total;

    @OneToOne
    @JoinColumn(name="tourist_package_id")
    @JsonIgnoreProperties({"sale", "hibernateLazyInitializer", "handler"})
    @NotNull
    private TouristPackage touristPackage;

    // Este objeto nos permitirá saber la ganacia de la venta, que dependerá de la comisión
    // del método de pago
    @OneToOne(mappedBy="sale")
    @JsonIgnoreProperties({"sale", "hibernateLazyInitializer", "handler"})
    @NotNull
    private AgencyRevenue agencyRevenue;
    
    @OneToMany(mappedBy="sale")
    @JsonIgnoreProperties({"sale", "hibernateLazyInitializer", "handler"})
    @NotNull
    private List<SaleDetail> sailDetails;

    public Sale() {
        sailDetails = new ArrayList<>();
    }

    public Sale(@NotNull Date saleDate, String clientAccountName, String paymentMethod, @NotNull Float commission,
            @NotNull Double total, @NotNull TouristPackage touristPackage, @NotNull AgencyRevenue agencyRevenue,
            @NotNull List<SaleDetail> sailDetails) {
        this();
        this.saleDate = saleDate;
        this.clientAccountName = clientAccountName;
        this.paymentMethod = paymentMethod;
        this.commission = commission;
        this.total = total;
        this.touristPackage = touristPackage;
        this.agencyRevenue = agencyRevenue;
        this.sailDetails = sailDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String getClientAccountName() {
        return clientAccountName;
    }

    public void setClientAccountName(String clientAccountName) {
        this.clientAccountName = clientAccountName;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Float getCommission() {
        return commission;
    }

    public void setCommission(Float commission) {
        this.commission = commission;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public TouristPackage getTouristPackage() {
        return touristPackage;
    }

    public void setTouristPackage(TouristPackage touristPackage) {
        this.touristPackage = touristPackage;
    }

    public AgencyRevenue getAgencyRevenue() {
        return agencyRevenue;
    }

    public void setAgencyRevenue(AgencyRevenue agencyRevenue) {
        this.agencyRevenue = agencyRevenue;
    }

    public List<SaleDetail> getSailDetails() {
        return sailDetails;
    }

    public void setSailDetails(List<SaleDetail> sailDetails) {
        this.sailDetails = sailDetails;
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", saleDate=" + saleDate +
                ", clientAccountName=" + clientAccountName +
                ", paymentMethod=" + paymentMethod +
                ", commission=" + commission +
                ", total=" + total +
                ", touristPackage=" + touristPackage +
                ", sailDetails=" + sailDetails + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((saleDate == null) ? 0 : saleDate.hashCode());
        result = prime * result + ((clientAccountName == null) ? 0 : clientAccountName.hashCode());
        result = prime * result + ((paymentMethod == null) ? 0 : paymentMethod.hashCode());
        result = prime * result + ((commission == null) ? 0 : commission.hashCode());
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
        Sale other = (Sale) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (saleDate == null) {
            if (other.saleDate != null)
                return false;
        } else if (!saleDate.equals(other.saleDate))
            return false;
        if (clientAccountName == null) {
            if (other.clientAccountName != null)
                return false;
        } else if (!clientAccountName.equals(other.clientAccountName))
            return false;
        if (paymentMethod == null) {
            if (other.paymentMethod != null)
                return false;
        } else if (!paymentMethod.equals(other.paymentMethod))
            return false;
        if (commission == null) {
            if (other.commission != null)
                return false;
        } else if (!commission.equals(other.commission))
            return false;
        if (total == null) {
            if (other.total != null)
                return false;
        } else if (!total.equals(other.total))
            return false;
        return true;
    }    
    
}
