package com.springbootkata.kata.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;


@Entity
@Table(name = "users")
public class User {

    public User(String name, String lastname, LocalDate dateOfBirth, String email) {
        this.name = name;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

//Fields

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно быть от 2 до 30 символов")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 30, message = "Фамилия должна быть от 2 до 30 символов")
    @Column(name = "lastName")
    private String lastname;


    @NotNull(message = "Поле не должно быть пустым")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @Transient
    private byte age;

    @NotBlank(message = "Поле не должно быть пустым")
    @Column(name = "email")
    private String email;

    public User() {

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Getters and Setters


    public long getId() {
        return id;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public byte getAge() {
        return (byte) Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + getAge() +
                ", email='" + email + '\'' +
                '}';
    }


}