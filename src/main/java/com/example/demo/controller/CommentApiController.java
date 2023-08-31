package com.example.demo.controller;

import com.example.demo.model.CommentModel;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// 댓글의 CRUD를 처리하는 Api 컨트롤러
@RestController
public class CommentApiController {

    @Autowired
    private CommentService commentService;

    // 댓글 조회 API
    @GetMapping("/api/comments/{no}")
    public CommentModel getComment(@PathVariable int no) {

        CommentModel comment = commentService.getComment(no);

        return comment;
    }

    // 댓글 등록 API
    @PostMapping("/api/comments")
    public CommentModel postComment(@RequestBody CommentModel comment) {
        //등록 처리
        return commentService.createComment(comment);

    }

    // 댓글 수정 API
    @PutMapping("/api/comments/{no}")
    public CommentModel modifyComment(@RequestBody CommentModel comment, @PathVariable int no){
        comment.setNo(no);
        return commentService.updateComment(comment);
    }
}
