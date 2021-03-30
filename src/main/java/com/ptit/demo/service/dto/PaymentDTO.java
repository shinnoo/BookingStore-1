package com.ptit.demo.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.ptit.demo.domain.Payment} entity.
 */
public class PaymentDTO implements Serializable {
    
    private Long id;

    private Instant createAt;

    private String code;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentDTO)) {
            return false;
        }

        return id != null && id.equals(((PaymentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentDTO{" +
            "id=" + getId() +
            ", createAt='" + getCreateAt() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
