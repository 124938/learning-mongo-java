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
}
