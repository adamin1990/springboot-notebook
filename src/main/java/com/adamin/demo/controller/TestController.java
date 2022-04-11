package com.adamin.demo.controller;

import com.adamin.demo.bean.User;
import com.adamin.demo.entity.Box;
import com.adamin.demo.intercepter.UnInterceptor;
import com.adamin.demo.listener.event.BoxEvent;
import com.adamin.demo.service.BoxService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Classname TestController
 * @Description TODO
 * @Date 2022/4/10 10:56
 * @Created by Adam(https://www.lixiaopeng.top)
 */
@Api("测试相关口")
@RestController
public class TestController {
     @Resource
    private BoxService boxService;
     @Resource
     private RedisTemplate<String,Object> redisTemplate;
     @Resource
     private ApplicationContext context;
    @Operation(summary = "获取用户信息")
     @GetMapping("/user")
    public User getUser(){
         System.out.println("true = " + true);
        return  new User(null,18,1,null,null);
    }
    @RequestMapping("/getbox/{id}")
    public Box getBox(@PathVariable("id") Integer id){

        return  boxService.getBox(id);
    }
    @RequestMapping("/getboxById/{id}")
    public Box getBoxById( @PathVariable("id") Integer id){
        return  boxService.getBoxById(id);
    };
     @RequestMapping("/getallBox")
    public List<Box> getAllBox(){
        return  boxService.getAll();
    }

    /**
     * 从AppListener中设置的缓存中拉取数据
     * @param request
     * @return
     */
    @RequestMapping("/cachedBox")
    public Box getFromCache(HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        return (Box) servletContext.getAttribute("box2");

    }

    /**
     * 测试自定义监听时间BoxListener
     * @return
     */
    @RequestMapping("/customListener")
    @UnInterceptor
    public Box listenerBox(){
        Box box=new Box(10,1,1,"你好java",2);
        BoxEvent boxEvent=new BoxEvent(this,box);
        try {
            redisTemplate.opsForValue().set("adam",new ObjectMapper()
            .writeValueAsString(box));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        context.publishEvent(boxEvent);
        return  box;

    }




}
