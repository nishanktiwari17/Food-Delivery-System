package org.fdschinecenter.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.fdschinecenter.annotations.NotEmptyNotBlank;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

/**
 * FoodMenu entity class for table in database. It has a many-to-one relationship with Restaurant entity.
 */
@Getter
@Setter
@Entity
@DynamicUpdate
public class FoodMenu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmptyNotBlank
    private String name;

    @NotEmptyNotBlank
    private String description;

    @NotNull
    private Double price;
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Restaurant restaurant;
}