package com.example.test.controller;

import com.example.test.bean.CommonResult;
import com.example.test.bean.Edit;
import com.example.test.bean.EditShow;
import com.example.test.service.EditService;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EditController {

    @Autowired
    EditService editService;
    @Autowired
    UserService userService;

    @GetMapping("/edit/user/{UserID}")
    public List<Edit> getEditByUser(@PathVariable("UserID") Integer UserID){
        return editService.getEditByUser(UserID);
    }


    @GetMapping("/edit")
    public Edit insertEdit(Edit edit) {
        return editService.insertEdit(edit);
    }
        @PostMapping("/edit/get/{DocID}")
        public List<EditShow> getEditList(@PathVariable Integer DocID){
            List<Edit> edits=editService.getEditByDoc(DocID);
            List<EditShow> editShows=new ArrayList<>();
            for(Edit edit:edits){
                editShows.add(new EditShow(userService.getUserById(edit.getUserID()).getUserName(),edit.getDateTime()));
            }
            return editShows;
        }

        @PostMapping("/edit/add")
        public CommonResult addEdit(@RequestBody Edit edit){
            Edit result=editService.insertEdit(edit);
            return new CommonResult(200,null,result);
        }

    }
