package com.example.test.controller;

import com.example.test.bean.CommonResult;
import com.example.test.bean.Document;
import com.example.test.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecycleController {

    @Autowired
    DocService docService;

    @PostMapping("/recycle/getRecycle")
    public List<Document> getRecycle(Integer UserID){
        return docService.getRecycleByUser(UserID);
    }

    @PostMapping("/recycle/recover")
    public CommonResult recover(Integer DocID,Integer UserID){
        int flag = docService.recoverDoc(DocID,UserID);
        Document document = docService.getDocById(DocID);
        if(flag == 1){
            return new CommonResult(200,"success",document);
        }
        else{
            return new CommonResult(200,"failure",document);
        }
    }

    @PostMapping("/recycle/delete")
    public CommonResult delete(Integer DocID,Integer UserID){
        int flag = docService.deleteDocById(DocID,UserID);
        if(flag == 0){
            return new CommonResult(400,"failure",null);
        }
        else{
            return new CommonResult(200,"success",null);
        }
    }

}
