package com.example.test.controller;

import com.example.test.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class TemplateController {
    @Autowired
    TemplateService templateService;

    @PostMapping("/template/getContent")
    public String getContent(@RequestBody Integer TemplateID){
        return  templateService.getTemplateByTemplateID(TemplateID).getContent();
    }
}
