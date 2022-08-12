package dev.dmohindru.entity;

import jakarta.persistence.*;

import jakarta.persistence.GenerationType.*;
import org.hibernate.annotations.UuidGenerator;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Integer age;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
