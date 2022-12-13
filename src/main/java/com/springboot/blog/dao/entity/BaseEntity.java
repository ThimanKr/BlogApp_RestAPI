package com.springboot.blog.dao.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/** The BaseEntity is the base class for entities */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    /** The property id is the generated id for all entity */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
