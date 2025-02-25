package com.example.sd_95_polo_store_be.Model.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@MappedSuperclass
@Data
@Accessors(chain = true)
public abstract class BaseEntity<T> {
    @JsonProperty("create_date")
    OffsetDateTime createDate;

    @JsonProperty("update_date")
    OffsetDateTime updateDate;

    protected abstract T self();

    public T setCreatedAt(OffsetDateTime createdAt) {
        this.createDate = createdAt;
        return self();
    }

    public T setUpdatedAt(OffsetDateTime updatedAt) {
        this.updateDate = updatedAt;
        return self();
    }

}
