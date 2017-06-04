package com.fuzhutech.service.auth.impl;


import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.entity.auth.User;
import com.fuzhutech.service.auth.UserService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    //当调用这个方法的时候，会从一个名叫 hour的缓存中查询，如果没有，则执行实际的方法（即查询数据库），并将执行的结果存入缓存中，否则返回缓存中的对象
    //@Cacheable(value = "hour", key = "#id")
    public User queryById(Integer id) {
        logger.info("UserServiceImpl queryById");
        return this.mapper.selectByPrimaryKey(id);
    }

    //@CacheEvict 注释来标记要清空缓存的方法，当这个方法被调用后，即会清空缓存。注意其中一个
    @CacheEvict(value = "hour", key = "#id")
    public Integer deleteById(Integer id) {
        return Integer.valueOf(this.mapper.deleteByPrimaryKey(id));
    }

    //@CachePut 注释，这个注释可以确保方法被执行，同时方法的返回值也被记录到缓存中，实现缓存与数据库的同步更新。
    @CachePut(value="accountCache",key="#record.getId()")// 更新accountCache 缓存
    public Integer update(User record) {
        return Integer.valueOf(this.mapper.updateByPrimaryKey(record));
    }

    @Override
    public String createToken(User user){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String token =  formatter.format(user.getLastLoginTime());
        token = user.getId() + "," + token;
        return Base64.encodeBase64String(org.apache.commons.codec.binary.StringUtils.getBytesUtf8(token));
    }

    @Override
    public boolean checkToken(String token) {
        if(StringUtils.isEmpty(token))
            return  false;

        String str = org.apache.commons.codec.binary.StringUtils.newStringUtf8(Base64.decodeBase64(token));
        int i = str.indexOf(',');
        String userId = str.substring(0, i);
        String loginTime = str.substring(i+1);

        if (!StringUtils.isNumeric(userId))
            return false;
        User record = new User();
        record.setId(Integer.parseInt(userId));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            record.setLastLoginTime(formatter.parse(loginTime));

            List<User> list = this.queryListByWhere(record);
            if(list.isEmpty())
                return false;
        } catch (ParseException e) {
            logger.error("验证token:[{}]过程中发生错误:{}",token,e.getMessage());
            return false;
        }

        return true;
    }

}
