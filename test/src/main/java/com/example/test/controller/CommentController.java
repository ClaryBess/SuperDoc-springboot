package com.example.test.controller;

import com.example.test.bean.Comment;
import com.example.test.bean.CommonResult;
import com.example.test.bean.Document;
import com.example.test.service.CommentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/comment")
    public Comment insertComment(Comment comment){
        return commentService.insertComment(comment);
    }

    @PostMapping("/comment/add")
    public CommonResult addComment(@RequestBody Comment comment){
        comment.setDateTime((Date) new java.util.Date());
        Comment result=commentService.insertComment(comment);
        if(result!=null)
            return new CommonResult(200,null,result);
        else
            return new CommonResult(500,"Failed",null);
    }

    @PostMapping("/comment/commentList/{DocID}")
    public List<Comment> getCommentList(@PathVariable Integer DocID){
        List<Comment> result=commentService.getCommentByDoc(DocID);
        return result;
    }
}
