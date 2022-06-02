package dev.dmohindru.model;

import dev.dmohindru.annotations.BuilderProperty;

public class Player {
    Integer ageWhat;

    @BuilderProperty
    public void setAgeWhat(Integer age) {
        this.ageWhat = age;
    }
}
