package dev.dmohindru.model;

import dev.dmohindru.annotations.BuilderProperty;
import dev.dmohindru.annotations.MyBuilder;

//@MyBuilder
public class Person {

    private String firstName;
    private String familyName;
    private Double weight;
    private String nationality;

    //@BuilderProperty
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
