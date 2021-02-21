package com.shrey.mongo.learning.document;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Comment {

    private String from;
    private String message;
    private Date updatedAt;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("from='").append(from).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }
}
