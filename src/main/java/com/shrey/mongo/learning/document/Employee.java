package com.shrey.mongo.learning.document;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Employee {
    private String firstName;
    private String lastName;
    private Address address;
    private List<Job> history;
}
