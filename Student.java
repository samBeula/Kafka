package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Student implements Serializable {

    @JsonProperty("name")
    String name;
    @JsonProperty("college")
    String college;
    @JsonProperty("percentage")
    double percentage;

    public Student(String name, String college, double percentage) {
        this.name = name;
        this.college = college;
        this.percentage = percentage;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}