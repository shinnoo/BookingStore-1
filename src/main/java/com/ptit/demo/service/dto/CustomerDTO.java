package com.ptit.demo.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.ptit.demo.domain.Customer} entity.
 */
public class CustomerDTO implements Serializable {
    
    private Long id;

    private Integer age;

    private Integer addressId;

    private Integer fullnameId;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getFullnameId() {
        return fullnameId;
    }

    public void setFullnameId(Integer fullnameId) {
        this.fullnameId = fullnameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomerDTO)) {
            return false;
        }

        return id != null && id.equals(((CustomerDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomerDTO{" +
            "id=" + getId() +
            ", age=" + getAge() +
            ", addressId=" + getAddressId() +
            ", fullnameId=" + getFullnameId() +
            "}";
    }
}
