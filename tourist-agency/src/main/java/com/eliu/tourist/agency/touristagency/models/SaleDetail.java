package com.eliu.tourist.agency.touristagency.models;

import com.eliu.tourist.agency.touristagency.validations.IsRequired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="sale_details")
public class SaleDetail {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @IsRequired
    private String serviceName;
    
    @NotNull
    private Double serviceCost;
    
    @NotNull
    private Integer quantity;

    @NotNull
    private Double total;

    @ManyToOne
    @JoinColumn(name="sale_id")
    @NotNull
    private Sale sale;

    public SaleDetail() {
    }

    public SaleDetail(String serviceName, @NotNull Double serviceCost, @NotNull Integer quantity, @NotNull Double total,
            @NotNull Sale sale) {
        this.serviceName = serviceName;
        this.serviceCost = serviceCost;
        this.quantity = quantity;
        this.total = total;
        this.sale = sale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(Double serviceCost) {
        this.serviceCost = serviceCost;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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
                ", serviceName=" + serviceName +
                ", serviceCost=" + serviceCost +
                ", quantity=" + quantity +
                ", total=" + total + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((serviceName == null) ? 0 : serviceName.hashCode());
        result = prime * result + ((serviceCost == null) ? 0 : serviceCost.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
        SaleDetail other = (SaleDetail) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (serviceName == null) {
            if (other.serviceName != null)
                return false;
        } else if (!serviceName.equals(other.serviceName))
            return false;
        if (serviceCost == null) {
            if (other.serviceCost != null)
                return false;
        } else if (!serviceCost.equals(other.serviceCost))
            return false;
        if (quantity == null) {
            if (other.quantity != null)
                return false;
        } else if (!quantity.equals(other.quantity))
            return false;
        if (total == null) {
            if (other.total != null)
                return false;
        } else if (!total.equals(other.total))
            return false;
        return true;
    }
    
}
