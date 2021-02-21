package com.shrey.mongo.learning.document;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Employee {

    private String firstName;
    private String lastName;
    private Address address;
    private List<Job> jobs = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", address=").append(address);
        sb.append(", history=").append(jobs);
        sb.append('}');
        return sb.toString();
    }
}
