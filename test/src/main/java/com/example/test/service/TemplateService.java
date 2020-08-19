package com.example.test.service;

import com.example.test.bean.Template;
import com.example.test.mapper.TemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class TemplateService {
    @Autowired
    TemplateMapper templateMapper;

    public Template getTemplateByTemplateID(Integer TemplateID){
        return templateMapper.getTemplateByTemplateID(TemplateID);
    }
}
