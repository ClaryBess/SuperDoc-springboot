package com.example.test.controller;

import com.example.test.bean.Collect;
import com.example.test.bean.CommonResult;
import com.example.test.bean.Document;
import com.example.test.service.CollectService;
import com.example.test.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CreationController {

    @Autowired
    DocService docService;
    @Autowired
    CollectService collectService;

//    需要一个接口 通过userID 得到(Document) List
    @PostMapping("/created/getDocument")
    public List<Document> getDocument(Integer UserID){
        return docService.getDocByUser(UserID);
    }

//    需要一个接口 通过DocID userID 判断该文档是否被收藏 *
    @PostMapping("/created/collected")
    public CommonResult collected(Integer DocID,Integer UserID){
        Collect collect = collectService.getCollectByDocAndUser(DocID, UserID);
        if(collect != null){
            return new CommonResult(200,"yes",null);
        }
        else{
            return new CommonResult(200,"no",null);
        }
    }

//    需要一个接口 通过DocID userID 修改该文档是否被收藏 *
    @PostMapping("/created/insertCollect")
    public CommonResult insertCollect(Integer DocID,Integer UserID){
        Collect collect = collectService.insertCollect(DocID, UserID);
        return new CommonResult(200,null,collect);
    }

    @PostMapping("/created/deleteCollect")
    public CommonResult deleteCollect(Integer DocID,Integer UserID){
        collectService.deleteByDocAndUser(DocID, UserID);
        return new CommonResult(200,null,null);
    }


//    需要一个接口 通过DocID userID 删除自己创建的文档
    @PostMapping("created/deleteDocument")
    public CommonResult deleteDocument(Integer DocID,Integer UserID){
        docService.deleteDocById(DocID,UserID);
        return new CommonResult(200,null,null);
    }
}
