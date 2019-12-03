package com.qf.controller;

import com.qf.pojo.Eduexper;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EduexperController {
    @Autowired
    private UserService userService;
    //简历信息  查询
    @RequestMapping("/efind")
    public List<Eduexper> efindAll(){
        return userService.EfindAll();
    }
    //修改
    @RequestMapping("/eupdate1")
    public Eduexper eupdate1(Eduexper eduexper){
        int id = eduexper.getId();
        return userService.findOne(id);
    }
    @RequestMapping("/eupdate2")
    public String eupdate2(Eduexper eduexper){
       userService.Eupdate(eduexper);
        return "redirect:/efind";
    }
}
