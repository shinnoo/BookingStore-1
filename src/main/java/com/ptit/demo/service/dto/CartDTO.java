package com.ptit.demo.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.ptit.demo.domain.Cart} entity.
 */
public class CartDTO implements Serializable {
    
    private Long id;

    private Integer quantity;

    private Integer paymentId;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CartDTO)) {
            return false;
        }

        return id != null && id.equals(((CartDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CartDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", paymentId=" + getPaymentId() +
            "}";
    }
}
