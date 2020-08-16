package com.example.test.controller;

import com.example.test.bean.Browse;
import com.example.test.bean.CommonResult;
import com.example.test.service.BrowseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BrowseController {

    @Autowired
    BrowseService browseService;

    @GetMapping("/browse/getBrowse")
    public List<Browse> getBrowse(@RequestBody Integer UserID){
        return browseService.getBrowseByUser(UserID);
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
}
