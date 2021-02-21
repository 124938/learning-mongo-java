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
    private List<Job> jobs;
    private List<Comment> comments;

    // To avoid jackson reader issue
    public Employee() {

    }

    public Employee(final String firstName, final String lastName, final List<Job> jobs) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobs = jobs;
        this.comments = new ArrayList<>();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", address=").append(address);
        sb.append(", jobs=").append(jobs);
        sb.append(", comments=").append(comments);
        sb.append('}');
        return sb.toString();
    }
}
