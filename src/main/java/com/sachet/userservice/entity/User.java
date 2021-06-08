package com.sachet.userservice.entity;

import com.sachet.userservice.custom_constraints.UniqueEmail;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "First Name of user cannot be null")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last Name of user cannot be null")
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    @NotNull(message = "Email of user cannot be null")
    @UniqueEmail(message = "User with that email already exist!")
    private String email;

    @Column(name = "department_id")
    private Long departmentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
