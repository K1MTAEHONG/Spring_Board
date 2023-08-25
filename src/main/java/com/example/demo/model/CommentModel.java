package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentModel {
    private int no;
    private  String author;
    private  String comment;
    private LocalDateTime date;
}
