package com.example.test.controller;

import com.example.test.bean.CommonResult;
import com.example.test.bean.User;
import com.example.test.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {
    //注解自动获取环境变量，图片上传目录
    @Value("${UPLOADFILE_PATH}")
    String UPLOADFILE_PATH;
    @Autowired
    UserMapper userMapper;
    @GetMapping("/user/{UserID}")
    public User getUserById(@PathVariable("UserID") Integer UserID){
        return userMapper.getUserById(UserID);
    }

    @GetMapping("/user")
    public User insertUser(User user){
        userMapper.insertUser(user);
        return user;
    }

    @PostMapping("/user/regisiter")
    public CommonResult register(@RequestBody User user){
        if (user.getGender()==null){
            user.setGender("");
        }
        if (user.getBirthday()==null){
            user.setBirthday(new Date());
        }
        if (user.getProfileUrl()==null){
            user.setProfileUrl("");
        }
        //System.out.println("UserName="+user.getUserName());
        User user1=userMapper.getUserByEmail(user.getEmail());
        if(user1!=null){
            return new CommonResult(500,"email already exists!",null);
        }
        User user2=userMapper.getUserByName(user.getUserName());
        if(user2!=null){
            return new CommonResult(400,"username already exists!",null);
        }
        User result =insertUser(user);
        return new CommonResult(200,null,result);
    }

    @RequestMapping(value="/user/login",method = RequestMethod.POST)
    public CommonResult login(@RequestBody User user){
        User user1=userMapper.getUserByName(user.getUserName());
        //需要先判断user1 是否为null
        System.out.println("UserName="+user.getUserName());
        System.out.println("Password="+user.getPassword());
        System.out.println("Password2="+user1.getPassword());
        if(user1!=null&&user.getPassword().equals(user1.getPassword())){
            return new CommonResult(200,"success",user);
        }
        else {
            return new CommonResult(500,"failure",null);
        }
    }

    @RequestMapping(value="user/register2")
    public CommonResult register2(@RequestBody User user){
        userMapper.updateBir(user);
        userMapper.updateGen(user);
        userMapper.updatePro(user);
        return new CommonResult(200,null,user);
    }
    /**
     * 保存 图片
     */
    @RequestMapping("user/save")
    public Map<String, Object> materialPictureAndText(HttpServletRequest request,
                                                      @RequestParam(value = "imgTitle", required = false) String imgTitle,
                                                      @RequestParam(value = "file", required = false) MultipartFile file) {

        if (file.isEmpty()) {
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("msg", "请上传图片");
            return resultMap;
        } else {
            String fileName = file.getOriginalFilename();  // 文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));

            fileName = UUID.randomUUID() + suffixName; // 新文件名
            File dest = new File(UPLOADFILE_PATH + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            HashMap map = new HashMap();

            map.put("picture_url","/file/"+fileName);
            return map;//这里就是上传图片返回的信息，成功失败异常等，前端根据字段接收就是了
        }
    }

}
