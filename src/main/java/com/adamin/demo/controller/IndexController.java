package com.adamin.demo.controller;

import com.adamin.demo.lucence.ChineseSearch;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Classname IndexController
 * @Description TODO
 * @Date 2022/4/11 17:26
 * @Created by Adam(https://www.lixiaopeng.top)
 */
@Controller
public class IndexController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }
   @RequestMapping("/lucence")
    public String lucence(Model model){
       // 索引所在的目录
       String indexDir = "E:\\springboot\\demo\\lucence2";
     // 要查询的字符
       String q = "面积";
       try {
           List<String> list = ChineseSearch.search(indexDir, q);
           model.addAttribute("list", list);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return "lucence";
   }

}
