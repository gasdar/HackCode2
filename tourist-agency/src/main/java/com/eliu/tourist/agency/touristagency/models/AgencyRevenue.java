package com.eliu.tourist.agency.touristagency.models;

import java.util.Date;

import com.eliu.tourist.agency.touristagency.validations.IsRequired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="agency_revenues")
public class AgencyRevenue {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date revenueDate;

    @NotNull
    private Double total;

    @IsRequired
    private String paymentMethod;

    @OneToOne
    @JoinColumn(name="sale_id")
    @NotNull
    private Sale sale;

    public AgencyRevenue() {
    }

    public AgencyRevenue(@NotNull Date revenueDate, @NotNull Double total, String paymentMethod, @NotNull Sale sale) {
        this.revenueDate = revenueDate;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.sale = sale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRevenueDate() {
        return revenueDate;
    }

    public void setRevenueDate(Date revenueDate) {
        this.revenueDate = revenueDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", revenueDate=" + revenueDate +
                ", total=" + total +
                ", paymentMethod=" + paymentMethod +
                ", sale=" + sale + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((revenueDate == null) ? 0 : revenueDate.hashCode());
        result = prime * result + ((total == null) ? 0 : total.hashCode());
        result = prime * result + ((paymentMethod == null) ? 0 : paymentMethod.hashCode());
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
        AgencyRevenue other = (AgencyRevenue) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (revenueDate == null) {
            if (other.revenueDate != null)
                return false;
        } else if (!revenueDate.equals(other.revenueDate))
            return false;
        if (total == null) {
            if (other.total != null)
                return false;
        } else if (!total.equals(other.total))
            return false;
        if (paymentMethod == null) {
            if (other.paymentMethod != null)
                return false;
        } else if (!paymentMethod.equals(other.paymentMethod))
            return false;
        return true;
    }    

}
