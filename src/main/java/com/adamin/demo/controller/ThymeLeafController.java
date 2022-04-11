package com.adamin.demo.controller;

import com.adamin.demo.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Classname ThymeLeafController
 * @Description ThymeLeaf相关的控制器
 * @Date 2022/4/10 16:55
 * @Created by Adam(https://www.lixiaopeng.top)
 */
@Controller
@RequestMapping("/th")
public class ThymeLeafController {
    //故意制造404页面
    @RequestMapping("/test404")
    public String test404(){
        return  "adam";
    }
    @RequestMapping("/test500")
    public String test500(){
        int a=10/0;
        return  "hello";
    }
    @RequestMapping("/user")
    public String testUser(Model model){
        User user=new User("eve",18,2,22000,10f);
        model.addAttribute("user",user);
        return  "user/user";

    }


}
