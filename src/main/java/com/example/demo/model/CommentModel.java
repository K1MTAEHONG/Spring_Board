package com.example.demo.model;

import com.example.demo.validator.Author;
import com.example.demo.validator.PreventFuxx;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentModel {
    private int no;

    @NotBlank(message = "작성자 항목은 필수 입니다.")
    @Size(min=2 , max= 10, message = "{min}자에서~{max}자 이내로 입력 해야 합니다.")      //작성자 항목 의 글자 사이즈 조절
    @Pattern(regexp = "^[a-z|A-Z|ㄱ-ㅎ|-가-힣]*$" , message = "영문 대소문자 및 한글만 가능 합니다.")
    @Author
    private String author;

    @NotBlank(message = " 댓글 내용은 필수 입니다.")
    @PreventFuxx
    private  String comment;

    private String userId;

    private LocalDateTime modDatetime;
    private LocalDateTime regDatetime;
    private int likeCount;

    @Deprecated
    public LocalDateTime getDate() {       //getter 수동 으로 만든거
        return regDatetime;
    }
}
