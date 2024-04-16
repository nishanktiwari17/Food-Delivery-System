package org.fdschinecenter.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Address entity class for table in database. It has a one-to-one relationship with Restaurant entity.
 */
@Entity
@Getter
@Setter
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String streetAddress;
    private String city;
    private String state;
    private String zipcode;

    @OneToOne
    @JsonBackReference
    private Restaurant restaurant;
}
