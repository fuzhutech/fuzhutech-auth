package com.fuzhutech.security.auth.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 开发公司：SOJSON在线工具 <p>
 * 版权所有：© www.sojson.com<p>
 * 博客地址：http://www.sojson.com/blog/  <p>
 * <p>
 * <p>
 * shiro 回话 监听
 * <p>
 * <p>
 * <p>
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　周柏成　2016年6月2日 　<br/>
 *
 * @author zhou-baicheng
 * @version 1.0, 2016年6月2日 <br/>
 * @email so@sojson.com
 */
public class CustomSessionListener implements SessionListener {

    private static final Logger logger = LoggerFactory.getLogger(CustomSessionListener.class);

    /**
     * 一个回话的生命周期开始
     */
    @Override
    public void onStart(Session session) {
        logger.info("session start,id:{},host:{},startTimestamp:{}",session.getId(),session.getHost());
    }

    /**
     * 一个回话的生命周期结束
     */
    @Override
    public void onStop(Session session) {
        logger.info("session stop,id:{},host:{},startTimestamp:{}",session.getId(),session.getHost());
    }

    @Override
    public void onExpiration(Session session) {
        logger.info("session onExpiration,id:{},host:{},startTimestamp:{}",session.getId(),session.getHost());
    }

}
