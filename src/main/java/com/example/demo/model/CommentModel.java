package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentModel {
    private int no;
    @NotBlank(message = "작성자 항목은 필수 입니다.")
    private  String author;
    @NotBlank(message = " 댓글 내용은 필수  입니다.")
    private  String comment;
    private LocalDateTime modDatetime;
    private LocalDateTime regDatetime;
    private int likeCount;

    @Deprecated
    public LocalDateTime getDate() {       //getter 수동 으로 만든거
        return regDatetime;
    }
}
