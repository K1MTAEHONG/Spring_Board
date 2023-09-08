package com.example.demo.controller;

import com.example.demo.model.CommentModel;
import com.example.demo.model.LoginUser;
import com.example.demo.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 댓글 목록 화면
    @GetMapping("/comments")
    public String getComments(Model model, CommentModel commentModel, @AuthenticationPrincipal LoginUser user) {

        //로그인 유저 정보를 구하는 첫번째 방법
        //Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //System.out.println(principal.getClass().getName());
        //if(!StringUtils.equals("anonymousUser","principal")) {
        //System.out.println(principal.toString());
        // model.addAttribute("userName", ((User) principal).getUsername());    // 강제로 형 변환
        //}
        model.addAttribute("userName", Optional.ofNullable(user).map(LoginUser::getName).orElse("익명"));

        List<CommentModel> cmList = commentService.getALlCommentList();
        model.addAttribute("commentList", cmList);//댓글 리스트 view로 전달한다

        return "main";          //main 이란 application.yml 에 보면 thymeleaf의  prefix의 부분을 나타냄
    }

    // 댓글 등록 처리
    @PostMapping("/comments")
    public String createComment(@Valid CommentModel commentModel, BindingResult bindingResult,
                                @AuthenticationPrincipal LoginUser user, Model model){
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("errMessage", bindingResult.getFieldError().getDefaultMessage());
            return getComments(model, commentModel, user);
        }else{
            commentService.createComment(commentModel);
        }

        return "redirect:/";
    }


    // 댓글 수정 화면 요청 처리
    //@Secured({"ROLE_ADMIN","ROLE_USER"})

    @GetMapping("/comments/{no}")   //화면을 가져오는건 GetMapping 씀
    public String modifyCommentForm(@PathVariable int no, Principal principal, Model model) throws Exception {
        System.out.println("로그인한 사용자:" + Optional.ofNullable(principal).map(Principal::getName).orElse("로그인안함"));

        CommentModel comment = commentService.getComment(no);
        model.addAttribute("comment", comment);

        //if(StringUtils.equals(comment.getUserId(),  principal.getName())){
        //    throw new Exception("권한이 없습니다.");
        //}

        return "comment-form";
    }

    // 댓글 수정 요청 처리
    @PostMapping("/comments/{no}")
    public String modifyComment(@PathVariable int no, @Valid CommentModel commentModel) {
        //댓글 정보 update 처리
        commentModel.setNo(no);
        commentService.updateComment(commentModel);


        return "redirect:/";
    }
}
