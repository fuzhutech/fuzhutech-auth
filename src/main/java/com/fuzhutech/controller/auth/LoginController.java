package com.fuzhutech.controller.auth;

import com.fuzhutech.common.ResponseResult;
import com.fuzhutech.common.service.BaseService;
import com.fuzhutech.entity.auth.User;
import com.fuzhutech.service.auth.UserService;
import com.fuzhutech.util.auth.HashCalculator;
import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

// 文章评论
@RestController
//@RequestMapping("/login")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    protected UserService uerService;

    //登录
    @RequestMapping(value = "/login", method = RequestMethod.PUT)
    @ResponseBody
    private ResponseResult login(@RequestBody User model/*,HttpServletRequest request*/) {

        ResponseResult responseResult = new ResponseResult();
        try {
            if (model.getLoginName().isEmpty())
                return new ResponseResult(ResponseResult.FAILURE, null, "用户名不正确");
            if (model.getPassword().isEmpty())
                return new ResponseResult(ResponseResult.FAILURE, null, "密码不正确");

            model.setPassword(EncoderByMd5(model.getPassword()));
            logger.info("加密后密码：" + model.getPassword());

            List<User> userList = uerService.queryListByWhere(model);
            if (userList.size() == 0)
                return new ResponseResult(ResponseResult.FAILURE, null, "用户名或密码不正确");

            return new ResponseResult(ResponseResult.SUCCESS, userList.get(0));

        } catch (Exception ex) {
            return new ResponseResult(ResponseResult.FAILURE, null, ex.getMessage());
        }
    }

    @RequestMapping("/loginAdmin")
    public String login1(User user){
        Subject subject = SecurityUtils.getSubject() ;
        UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(),user.getPassword()) ;
        try {
            //token.setRememberMe(true);
            subject.login(token);
            return "admin" ;
        }catch (Exception e){
            //这里将异常打印关闭是因为如果登录失败的话会自动抛异常
            logger.info("用户名或密码错误");
            return "../../login" ;
        }
    }

    //对字符串md5加密
    private String EncoderByMd5(String value) throws Exception {
        try {
            //确定计算方法,生成一个MD5加密计算摘要
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            MessageDigest md = MessageDigest.getInstance("MD5");
            return Base64.encodeBase64String(md.digest(value.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("MD5加密出现错误");
        }
    }

    //退出
    @RequestMapping(value = "/logout", method = RequestMethod.PUT)
    @ResponseBody
    private ResponseResult logout(@RequestBody User model/*,HttpServletRequest request*/) {
        ResponseResult responseResult = new ResponseResult();
        try {
            //如果已经登录，则退出
            //uerService.update(model);
            return new ResponseResult(ResponseResult.SUCCESS);
        } catch (RuntimeException ex) {
            return new ResponseResult(ResponseResult.FAILURE, null, ex.getMessage());
        }
    }

    //修改密码
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    @ResponseBody
    private ResponseResult modifyPassword(@RequestBody User model/*,HttpServletRequest request*/) {
        ResponseResult responseResult = new ResponseResult();
        try {
            //如果已经登录，则允许修改

            model.setPassword(EncoderByMd5(model.getPassword()));
            logger.info("修改后加密密码：" + model.getPassword());

            uerService.update(model);
            return new ResponseResult(ResponseResult.SUCCESS);
        } catch (Exception ex) {
            return new ResponseResult(ResponseResult.FAILURE, null, ex.getMessage());
        }
    }

}
