package com.example.test.controller;

import com.example.test.bean.CommonResult;
import com.example.test.bean.Edit;
import com.example.test.bean.EditShow;
import com.example.test.bean.UEditorFile;
import com.example.test.service.EditService;
import com.example.test.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class EditController {
    @Value("${UPLOADFILE_PATH}")
   static String UPLOADFILE_PATH;
    @Autowired
    EditService editService;
    @Autowired
    UserService userService;

    @GetMapping("/edit/user/{UserID}")
    public List<Edit> getEditByUser(@PathVariable("UserID") Integer UserID){
        return editService.getEditByUser(UserID);
    }
    /**
     * 上传配置：即不走config.json，模拟config.json里的内容，解决后端配置项不正确，无法上传的问题
     * @return
     */
    @RequestMapping("/ueditor/configs")
    public String uploadConfig(String action, MultipartFile upfile) {
        System.out.println(action);
        if (action.equals("config")){
            String s = "{\n" +
                    "            \"imageActionName\": \"uploadimage\",\n" +
                    "                \"imageFieldName\": \"upfile\", \n" +
                    "                \"imageMaxSize\": 2048000, \n" +
                    "                \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], \n" +
                    "                \"imageCompressEnable\": true, \n" +
                    "                \"imageCompressBorder\": 1600, \n" +
                    "                \"imageInsertAlign\": \"none\", \n" +
                    "                \"imageUrlPrefix\": \"http://localhost:8081\",\n" +
                    "                \"imagePathFormat\": \"/file/{rand:6}\" }";
            return s;
        }else if (action.equals("uploadimage")){
            try {
                UEditorFile file=uploadImage(upfile);
                JSONObject object=new JSONObject(file);
                System.out.println(object.toString());
                return  object.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
     return null;
    }

    public static UEditorFile uploadImage(MultipartFile file) throws IOException {
         String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();

        String fileName = file.getOriginalFilename();  //获取文件名
        //Ueditor的config.json规定的返回路径格式
        String returnPath = "/file/"+fileName;
        File saveFile = new File("D:/Java/uploadFiles/"+new Date().getTime()+"/"+fileName);
        System.out.println(saveFile.getPath());
        if (!saveFile.exists()){
            saveFile.mkdirs();
        }
        file.transferTo(saveFile);  //将临时文件移动到保存路径

        UEditorFile uEditorFile = new UEditorFile();
        uEditorFile.setState("SUCCESS");
        uEditorFile.setUrl(returnPath);  //访问URL
        uEditorFile.setTitle(fileName);
        uEditorFile.setOriginal(fileName);
        return uEditorFile;
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
