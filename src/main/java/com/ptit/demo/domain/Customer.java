package com.ptit.demo.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "age")
    private Integer age;

    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "fullname_id")
    private Integer fullnameId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public Customer age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public Customer addressId(Integer addressId) {
        this.addressId = addressId;
        return this;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getFullnameId() {
        return fullnameId;
    }

    public Customer fullnameId(Integer fullnameId) {
        this.fullnameId = fullnameId;
        return this;
    }

    public void setFullnameId(Integer fullnameId) {
        this.fullnameId = fullnameId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        return id != null && id.equals(((Customer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", age=" + getAge() +
            ", addressId=" + getAddressId() +
            ", fullnameId=" + getFullnameId() +
            "}";
    }
}
