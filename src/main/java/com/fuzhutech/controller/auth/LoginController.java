package com.fuzhutech.controller.auth;

import com.fuzhutech.common.ResponseResult;
import com.fuzhutech.entity.auth.User;
import com.fuzhutech.service.auth.UserService;
import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
                return new ResponseResult(ResponseResult.FAILURE, "用户名不正确");
            if (model.getPassword().isEmpty())
                return new ResponseResult(ResponseResult.FAILURE, "密码不正确");

            //model.setPassword(EncoderByMd5(model.getPassword()));
            logger.info("password:{}", model.getPassword());

            //采用shiro登录
            //AuthenticationToken 实例中包含终端用户的Principals(身份信息)和Credentials(凭证信息)
            UsernamePasswordToken authenticationToken = new UsernamePasswordToken(model.getLoginName(), model.getPassword());
            //authenticationToken.setRememberMe(true);
            logger.info("userName:{},Password:{}", authenticationToken.getPrincipal(), authenticationToken.getCredentials());

            //获取当前的用户  subject DelegatingSubject
            Subject subject = SecurityUtils.getSubject();
            //subject.isAuthenticated()
            subject.login(authenticationToken);

            String userID = (String) authenticationToken.getPrincipal();
            logger.info("User [" + userID + "] logged in successfully.");

            responseResult.setStatus(ResponseResult.SUCCESS);
            responseResult.putData("user",subject.getPrincipals().getPrimaryPrincipal());
            return responseResult;

        } catch (UnknownAccountException ex) {
            return new ResponseResult(ResponseResult.FAILURE, "未知账户");
        } catch (IncorrectCredentialsException ex) {
            return new ResponseResult(ResponseResult.FAILURE, "用户名密码不匹配");
        }catch(LockedAccountException lae){
            return new ResponseResult(ResponseResult.FAILURE, "账户已锁定");
        }catch(ExcessiveAttemptsException eae){
            return new ResponseResult(ResponseResult.FAILURE, "错误次数大于5次,账户已锁定");
        }catch (DisabledAccountException sae){
            return new ResponseResult(ResponseResult.FAILURE, "帐号已经禁止登录");
        }
        catch (AuthenticationException ex) {
            return new ResponseResult(ResponseResult.FAILURE, "其他的登录错误," + ex.getMessage());
        } catch (Exception ex) {
            logger.info("用户名或密码错误");
            return new ResponseResult(ResponseResult.FAILURE, ex.getMessage());
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
            Subject subject = SecurityUtils.getSubject();
            subject.logout(); //removes all identifying information and invalidates their session too.
            /**
             * 当你调用logout，任何现有的Session 都将会失效，而且任何身份都将会失去关联（例如，在Web 应用程序中，RememberMe cookie 也将被删除）。
             * 在Subject 注销后，该Subject 的实例被再次认为是匿名的。

             由于在Web 应用程序记住身份往往是依靠Cookies，然而Cookies 只能在Response 被committed 之前被删除，所以强烈建议在调用subject.logout()后立即将终端用户重定向
             到一个新的视图或页面。这样能够保证任何与安全相关的Cookies都能像预期的一样被删除。这是HTTP cookies 的功能限制，而不是Shiro的问题。
             */


            return new ResponseResult(ResponseResult.SUCCESS);
        } catch (RuntimeException ex) {
            return new ResponseResult(ResponseResult.FAILURE,  ex.getMessage());
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
            return new ResponseResult(ResponseResult.FAILURE,  ex.getMessage());
        }
    }

    /**
     * 登录认证异常
     */
    /*@ExceptionHandler({ UnauthenticatedException.class, AuthenticationException.class })
    public ResponseResult authenticationException(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseResult(ResponseResult.FAILURE, null,"未登录");
    }*/

    /**
     * 权限异常
     */
    /*@ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public ResponseResult authorizationException(HttpServletRequest request, HttpServletResponse response) {

        return new ResponseResult(ResponseResult.FAILURE, null,"无权限");
    }*/

}
