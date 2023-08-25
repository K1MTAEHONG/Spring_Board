package com.example.demo.controller;

import com.example.demo.dao.CommentDAO;
import com.example.demo.model.CommentModel;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// 댓글의 CRUD를 처리하는 Api 컨트롤러
@RestController
public class CommentApiController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/api/comments/{no}")
    public CommentModel getComment(@PathVariable int no) {

        CommentModel comment = commentService.getComment(no);

        return comment;
    }
}
