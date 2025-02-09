package com.scaler.productservice.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass // it does not create a separate table
public class BaseModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date createdAt;

    private Date lastUpdatedAt;

    private boolean isDeleted;
}
