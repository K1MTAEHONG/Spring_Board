package com.example.demo.service;

import com.example.demo.dao.CommentDAO;
import com.example.demo.dao.LikeDAO;
import com.example.demo.model.CommentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private  LikeDAO likeDAO;

    //댓글 조회
    public CommentModel getComment(int no){

        CommentModel comment = commentDAO.selectComment(no);
        comment.setLikeCount(likeDAO.selectLikeCount("COMMENT", comment.getNo()));
        return commentDAO.selectComment(no);
    }

    // 모든 댓글 목록
    public List<CommentModel> getALlCommentList(){
        return commentDAO.selectAllComment();
    }

    // 댓글 등록

    public CommentModel createComment(CommentModel commentModel) {
        commentDAO.insertComment(commentModel);
        likeDAO.insertLike("COMMENT", commentModel.getNo());
        return getComment(commentModel.getNo());
    }

    // 댓글 수정
    public CommentModel updateComment(CommentModel commentModel){
        commentDAO.updateComment(commentModel);
        return getComment(commentModel.getNo());
    }

    // 댓글 삭제
    public void deleteComment(int no){
        commentDAO.deleteComment(no);
        likeDAO.deleteLike("COMMENT", no);
    }
}
