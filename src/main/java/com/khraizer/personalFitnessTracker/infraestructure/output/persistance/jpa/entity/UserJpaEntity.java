package com.khraizer.personalFitnessTracker.infraestructure.output.persistance.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@Builder
public class UserJpaEntity {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @NotBlank(message = "The first name can't be empty!")
    @Column(name = "first_name")
    private String firstName;
    @NotBlank(message = "The last name can't be empty!")
    @Column(name = "last_name")
    private String lastName;
    private Double weight;
    @NotBlank(message = "The email can't be empty!")
    @Email(message = "The email format is invalid!")
    private String email;
    @NotBlank(message = "The password can't be empty!")
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleJpaEntity role;

    public UserJpaEntity(UUID id, String firstName, String lastName, Double weight, String email, String password, RoleJpaEntity role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @PrePersist
    public void prePersist(){
        if (id == null){
            id = UUID.randomUUID();
        }
    }

}
