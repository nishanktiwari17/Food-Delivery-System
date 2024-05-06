package org.fdschinecenter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.fdschinecenter.annotations.NotEmptyNotBlank;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * User entity class for table in database. It has a one-to-many relationship with CustomerOrder entity.
 */
@Getter
@Setter
@Entity
@DynamicUpdate
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Size(min = 3)
    @NotEmptyNotBlank
    private String name;

    @Size(min = 5, message = "Password must be contains minimum 5 characters")
    private String password;

    @Transient
    @Size(min = 5, message = "Password must be contains minimum 5 characters")
    private String confirmPassword;

    @NotEmptyNotBlank
    private String phone;

    @NotEmptyNotBlank
    private String address;

    @Email(message = "Please provide a valid email!")
    @NotEmptyNotBlank
    private String email;

    private Role role;

    private boolean enable;
    private LocalDateTime userRegistrationDate;
    private String verificationCode;

    @OneToMany(mappedBy = "user")
    private List<CustomerOrder> customerOrderList;

}
