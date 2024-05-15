package com.ra.btspring_thymleaf.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;


@Entity
@Table(name = "employees")
public class Employee {
    @NotNull(message = "employee id is empty!")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "Name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Address is required")
    @Size(max = 200, message = "Address cannot exceed 200 characters")
    @Column(name = "address")
    private String address;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @NotEmpty(message = "Phone is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    @Column(name = "phone")
    private String phone;

    public Employee() {
    }

    public Employee(Integer id, String name, String address, Date dateOfBirth, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}


