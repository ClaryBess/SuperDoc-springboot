package com.example.test.controller;

import com.example.test.bean.Comment;
import com.example.test.bean.CommentShow;
import com.example.test.bean.CommonResult;
import com.example.test.bean.Edit;
import com.example.test.service.EditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EditController {

    @Autowired
    EditService editService;

    @GetMapping("/edit/user/{UserID}")
    public List<Edit> getEditByUser(@PathVariable("UserID") Integer UserID){
        return editService.getEditByUser(UserID);
    }

    @PostMapping("/edit/get/{DocID}")
    public List<Edit> getEditList(@PathVariable Integer DocID){
        return editService.getEditByDoc(DocID);
    }
}
