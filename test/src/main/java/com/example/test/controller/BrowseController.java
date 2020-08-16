package com.example.test.controller;

import com.example.test.bean.Browse;
import com.example.test.bean.CommonResult;
import com.example.test.bean.Document;
import com.example.test.service.BrowseService;
import com.example.test.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BrowseController {

    @Autowired
    BrowseService browseService;
    @Autowired
    DocService docService;

    @PostMapping("/browse/getBrowse")
    public List<Document> getBrowse(@RequestBody Integer UserID){
        List<Document> documentList = new ArrayList<Document>();
        List<Browse> browseList = browseService.getBrowseByUser(UserID);
        if(browseList == null || browseList.size()==0){
            return null;
        }
        for(Browse browse : browseList){
            Integer DocID = browse.getDocID();
            Document document =docService.getDocById(DocID);
            documentList.add(document);
        }
        return documentList;
    }

    @PostMapping("/browse/insertBrowse")
    public CommonResult insertBrowse(@RequestBody Browse browse){
        browseService.insertBrowse(browse);
        Browse browse1 = browseService.getBrowseById(browse.getBrowseID());
        return new CommonResult(200,null,browse1);
    }

    @PostMapping("/browse/updateBrowse")
    public CommonResult updateBrowse(@RequestBody Integer BrowseID){
        browseService.updateDateTime(BrowseID);
        Browse browse = browseService.getBrowseById(BrowseID);
        return new CommonResult(200,null,browse);
    }

    @PostMapping("/browse/deleteBrowseById")
    public CommonResult deleteBrowseById(@RequestBody Integer BrowseID){
        browseService.deleteBrowseById(BrowseID);
        return new CommonResult(200,null,null);
    }

    @PostMapping("/browse/deleteBrowseByUser")
    public CommonResult deleteBrowseByUser(@RequestBody Integer UserID){
        browseService.deleteBrowseByUser(UserID);
        return new CommonResult(200,null,null);
    }

    @PostMapping("/browse/deleteBrowse")
    public CommonResult deleteBrowse(@RequestBody Integer DocID,@RequestBody Integer UserID){
        browseService.deleteBrowseByDocAndUser(DocID,UserID);
        return new CommonResult(200,null,null);
    }
}
