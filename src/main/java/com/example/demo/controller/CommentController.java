package com.example.demo.controller;

import com.example.demo.model.CommentModel;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 댓글 목록 화면
    @GetMapping("/comments")
    public String getComments(Model model) {

        List<CommentModel> cmList = commentService.getALlCommentList();
        model.addAttribute("commentList" ,cmList);//댓글 리스트 view로 전달한다

        return "main";          //main 이란 application.yml 에 보면 thymeleaf의  prefix의 부분을 나타냄
    }

    // 댓글 등록 처리
    @PostMapping("/comments")
    public String createComment(CommentModel commentModel) {

        commentService.createComment(commentModel);

        return "redirect:/";
    }

    //댓글 삭제 처리
    @DeleteMapping("/comments/{no}")
    public String deleteComment(@PathVariable int no) {

        commentService.deleteComment(no);

        return "redirect:/";
    }

    // 댓글 수정 화면 요청 처리
    @GetMapping("/comments/{no}")   //화면을 가져오는건 GetMapping 씀
    public String modifyCommentForm(@PathVariable int no, Model model) {

        CommentModel comment =commentService.getComment(no);
        model.addAttribute("comment", comment);

        return "comment-form";
    }


    @PostMapping("/comments/{no}")
    public String modifyComment(@PathVariable int no, CommentModel commentModel) {
        //댓글 정보 update 처리
        commentModel.setNo(no);
        commentService.updateComment(commentModel);

        return "redirect:/";
    }

}
