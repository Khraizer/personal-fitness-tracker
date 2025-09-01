package com.khraizer.personalFitnessTracker.domain.model;

import java.util.UUID;

public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private Double weight;
    private String email;
    private String password;
    private Role role;

    public User() {}

    public User(UUID id, String firstName, String lastName, Double weight, String email, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters y setters para todos los campos

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private String firstName;
        private String lastName;
        private Double weight;
        private String email;
        private String password;
        private Role role;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder firstName(String firstName) { this.firstName = firstName; return this; }
        public Builder lastName(String lastName) { this.lastName = lastName; return this; }
        public Builder weight(Double weight) { this.weight = weight; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder password(String password) { this.password = password; return this; }
        public Builder role(Role role) { this.role = role; return this; }

        public User build() {
            return new User(id, firstName, lastName, weight, email, password, role);
        }
    }
}
