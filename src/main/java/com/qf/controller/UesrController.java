package com.qf.controller;

import com.qf.cogfig.R;
import com.qf.pojo.User;
import com.qf.service.UserService;
import com.qf.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UesrController {
    @Value("${qiniu.url}")
    private String url;
    @Autowired
    UploadUtils uploadUtils;
    @Autowired
    public UserService userService;
    @Autowired
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String MALL_SENDER;
    //测试发送简单的邮件
    @Autowired
    TemplateEngine templateEngine;
    //注册 邮箱
    @RequestMapping("/em")
    public Boolean emc(@RequestBody() String email) throws MessagingException {
        if (email!=null){
            Context context = new Context();
            context.setVariable("id", "1");
            String emailTemplate = templateEngine.process("emailTemplate", context);
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(MALL_SENDER);
            mimeMessageHelper.setTo(email);//qq自动拦截，无法验证可行性
            mimeMessageHelper.setSubject("测试激活邮件");
            mimeMessageHelper.setText(emailTemplate, true);
            javaMailSender.send(mimeMessage);
            return true;
        }else{
            return false;
        }

    }
    //注册  用户新增
    @RequestMapping("/ins")
    public User insert(@RequestBody User user){
        return userService.insert(user);
    }
   //登录验证   -------暂有问题
    @RequestMapping("/log")
    public R login(@RequestBody Integer phone, @RequestBody String pass, HttpSession session){
        User login = userService.login(phone,pass);
        if (login!=null){
            //success
            session.setAttribute("user",login);
            return R.ok();
        }
        return R.error();
    }
  /*  //手机查头像
    @RequestMapping("/pp")
    public ModelAndView findpp(@RequestBody(phone)Integer phone){
        int findpp = userService.findpp(phone);
        ModelAndView modelAndView = new ModelAndView("");
        modelAndView.addObject("findpp",findpp);
        return modelAndView;
    }*/
  //用户资料查询
  @RequestMapping("ufindAll")
  public List<User> ufindAll(){
      return userService.findAll();
  }
  //个人信息修改1
    @RequestMapping(value = "/uupdate1",method = RequestMethod.POST)
    public User uupdate1(@RequestBody User user){
        int uid = user.getUid();
        return userService.findById(uid);
    }
  //个人信息修改2
    @RequestMapping("/uupdate2")
    public String userup(User user, @RequestParam("file")MultipartFile mull){
            try{
                String upload = uploadUtils.upload(mull);
                user.setPhoto(url+upload);
                userService.userupdate(user);
                return "redirect:/findAll";
            }catch (Exception e){
                return "404";
            }
    }

}
