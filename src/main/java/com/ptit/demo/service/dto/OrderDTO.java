package com.ptit.demo.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.ptit.demo.domain.Order} entity.
 */
public class OrderDTO implements Serializable {
    
    private Long id;

    private Float totalPrice;

    private String customerId;

    private Integer cartId;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderDTO)) {
            return false;
        }

        return id != null && id.equals(((OrderDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderDTO{" +
            "id=" + getId() +
            ", totalPrice=" + getTotalPrice() +
            ", customerId='" + getCustomerId() + "'" +
            ", cartId=" + getCartId() +
            "}";
    }
}
