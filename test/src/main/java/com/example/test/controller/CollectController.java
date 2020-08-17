package com.example.test.controller;

import com.example.test.bean.Collect;
import com.example.test.bean.CommonResult;
import com.example.test.bean.Document;
import com.example.test.service.CollectService;
import com.example.test.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CollectController {

    @Autowired
    CollectService collectService;
    @Autowired
    DocService docService;

    @PostMapping("/collect/getCollect")
    public List<Document> getCollect(@RequestBody Integer UserID){
        List<Collect> collectList = collectService.getCollectByUser(UserID);
        List<Document> documentList = new ArrayList<Document>();
        if(collectList == null || collectList.size() == 0){
            return null;
        }
        for(Collect collect : collectList){
            Integer DocID = collect.getDocID();
            Document document = docService.getDocById(DocID);
            documentList.add(document);
        }
        return documentList;
    }

    @PostMapping("/collect/collected")
    public CommonResult collected(Integer DocID,Integer UserID){
        Collect collect = collectService.getCollectByDocAndUser(DocID, UserID);
        if(collect != null){
            return new CommonResult(200,"collected",null);
        }
        else{
            return new CommonResult(200,"not collected",null);
        }
    }

    @PostMapping("/collect/insertCollect")
    public CommonResult insertCollect(Integer DocID,Integer UserID){
        Collect collect = collectService.insertCollect(DocID, UserID);
        return new CommonResult(200,null,collect);
    }

    @PostMapping("/collect/deleteCollect")
    public CommonResult deleteCollect(@RequestBody Collect collect){
        collectService.deleteByDocAndUser(collect.getDocID(),collect.getUserID());
        return new CommonResult(200,null,null);
    }
}
