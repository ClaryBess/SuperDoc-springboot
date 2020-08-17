package com.example.test.controller;

import com.example.test.bean.CommonResult;
import com.example.test.bean.Document;
import com.example.test.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecycleController {

    @Autowired
    DocService docService;

    @PostMapping("/recycle/getRecycle")
    public List<Document> getRecycle(@RequestBody Integer UserID){
        return docService.getRecycleByUser(UserID);
    }

    @PostMapping("/recycle/recover")
    public CommonResult recover(@RequestBody Document document){
        int flag = docService.recoverDoc(document.DocID,document.UserID);
        Document document1 = docService.getDocById(document.DocID);
        if(flag == 1){
            return new CommonResult(200,"recover success",document1);
        }
        else{
            return new CommonResult(200,"recover failure",document1);
        }
    }

    @PostMapping("/recycle/delete")
    public CommonResult delete(@RequestBody Document document){
        int flag = docService.deleteDocById(document.DocID,document.UserID);
        if(flag == 0){
            return new CommonResult(400,"delete failure",null);
        }
        else{
            return new CommonResult(200,"delete success",null);
        }
    }

}
