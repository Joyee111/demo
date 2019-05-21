package com.example.demo;


import com.example.demo.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import javax.xml.ws.Response;

@Controller
@RequestMapping("/")
public class GreetingController {
    @Autowired
    RedisUtils redisUtils;
    @RequestMapping("/greeting")
    public String greet(@RequestParam(value = "name",defaultValue="World")String name, Model model){
        System.out.println(name.indexOf("index,Search"));
        model.addAttribute("xname",name);
        if("index,Search".indexOf(name)>=0){
            redisUtils.hincr("userName",name,1);
            redisUtils.expire("userName",10);
            return "user/"+name;
        }else{
            return "user/Error";
        }
    }
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String search (@RequestParam(value = "name")String name , Model model ){
        System.out.println("查询结果"+name);
        model.addAttribute("xname",name) ;
        return "user/index";
    }
}
