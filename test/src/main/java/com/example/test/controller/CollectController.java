package com.example.test.controller;

import com.example.test.bean.Collect;
import com.example.test.bean.CommonResult;
import com.example.test.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CollectController {

    @Autowired
    CollectService collectService;

    @GetMapping("/collect/getCollect")
    public List<Collect> getCollectByUser(@RequestBody Integer UserID){
        return collectService.getCollectByUser(UserID);
    }

    @PostMapping("/collect/insertCollect")
    public CommonResult insertCollect(@RequestBody Collect collect){
        Collect collect1 = collectService.insertCollect(collect);
        return new CommonResult(200,null,collect1);
    }

}
