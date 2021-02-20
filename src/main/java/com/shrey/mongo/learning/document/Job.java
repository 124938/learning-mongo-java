package com.shrey.mongo.learning.document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Job {

    private String company;
    private String start;
    private String end;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Job{");
        sb.append("company='").append(company).append('\'');
        sb.append(", start='").append(start).append('\'');
        sb.append(", end='").append(end).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
