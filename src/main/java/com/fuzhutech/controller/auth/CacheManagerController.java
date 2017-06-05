package com.fuzhutech.controller.auth;

import com.fuzhutech.common.ResponseResult;
import com.fuzhutech.common.controller.RestfulController;
import com.fuzhutech.model.auth.FzCache;
import com.fuzhutech.model.auth.FzElement;
import com.fuzhutech.security.auth.shiro.cache.CacheManagerService;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/caches")
public class CacheManagerController {
    private static Logger logger = LoggerFactory.getLogger(RestfulController.class);

    @Autowired
    protected CacheManagerService service;

    //获取列表.
    @RequestMapping(method = RequestMethod.GET)
    public List<FzCache> getList(HttpServletRequest request, HttpServletResponse response) {
        //HashMap<String,Object> result=new HashMap<String,Object>();
        List<FzCache> caches = new ArrayList<FzCache>();
        List<Ehcache> ehcacheList = service.getEhCaches();

        for (Ehcache ehcache : ehcacheList) {
            FzCache fzCache = new FzCache();
            fzCache.setAverageGetTime(ehcache.getAverageGetTime());
            fzCache.setAverageSearchTime(ehcache.getAverageSearchTime());
            fzCache.setDiskStoreSize(ehcache.getDiskStoreSize());
            fzCache.setMemoryStoreSize(ehcache.getMemoryStoreSize());
            fzCache.setName(ehcache.getName());
            fzCache.setStatus(ehcache.getStatus().toString());
            caches.add(fzCache);
        }

        return caches;
    }

    //获取单条记录
    @RequestMapping(value = "/{cacheName}", method = RequestMethod.GET)
    public FzCache getSingle(HttpServletRequest request, HttpServletResponse response, @PathVariable("cacheName") String cacheName) {

        FzCache fzCache = null;
        Ehcache ehcache = service.getEhcache(cacheName);
        if (ehcache != null) {
            fzCache = new FzCache();
            fzCache.setAverageGetTime(ehcache.getAverageGetTime());
            fzCache.setAverageSearchTime(ehcache.getAverageSearchTime());
            fzCache.setDiskStoreSize(ehcache.getDiskStoreSize());
            fzCache.setMemoryStoreSize(ehcache.getMemoryStoreSize());
            fzCache.setName(ehcache.getName());
            fzCache.setStatus(ehcache.getStatus().toString());
        }

        return fzCache;
    }

    //响应新增请求
    @RequestMapping(method = RequestMethod.POST)
    public ResponseResult add(HttpServletRequest request, HttpServletResponse response) {
        try {
            //service.ehCacheManager.getCacheManager().addCache();

            //将id返回给请求
            return new ResponseResult(ResponseResult.SUCCESS);
        } catch (RuntimeException ex) {
            logger.error("新增失败：{}", ex);
            return new ResponseResult(ResponseResult.FAILURE, ex.getMessage());
        }
    }

    /*更新
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseResult edit(HttpServletRequest request, HttpServletResponse response, @RequestBody T model) {
        return editInternal(request, response, model);
    }*/

    //响应删除请求.   清空缓存
    @RequestMapping(value = "/{cacheName}", method = RequestMethod.DELETE)
    public ResponseResult delete(HttpServletRequest request, HttpServletResponse response,
                                 @PathVariable("cacheName") String cacheName) {
        try {
            Ehcache ehcache = service.getEhcache(cacheName);
            if(ehcache != null){
                ehcache.removeAll();
            }

            return new ResponseResult(ResponseResult.SUCCESS);
        } catch (RuntimeException ex) {
            logger.error("删除失败：{}", ex);
            return new ResponseResult(ResponseResult.FAILURE, null, ex.getMessage());
        }
    }

    @RequestMapping(value = "/{cacheName}/elements", method = RequestMethod.GET)
    public List<FzElement> getElementList(HttpServletRequest request, HttpServletResponse response, @PathVariable("cacheName") String cacheName) {
        logger.info("list:{}", cacheName);

        List<FzElement> elements = new ArrayList<FzElement>();
        Ehcache ehcache = service.getEhcache(cacheName);
        if (ehcache == null)
            return null;

        List keys = ehcache.getKeys();
        for (Object key : keys) {
            Element element = ehcache.getQuiet(key);
            if (element == null)
                continue;

            FzElement fzElement = new FzElement();
            fzElement.setKey(element.getObjectKey());
            fzElement.setValue(element.getObjectValue());
            fzElement.setHitCount(element.getHitCount());
            fzElement.setLastUpdateTime(element.getLastUpdateTime());
            fzElement.setTimeToIdle(element.getTimeToIdle()); //空闲时间
            fzElement.setTimeToLive(element.getTimeToLive()); //存活时间
            fzElement.setVersion(element.getVersion());
            fzElement.setCreationTime(element.getCreationTime());  //创建时间
            fzElement.setLastAccessTime(element.getLastAccessTime()); //最后访问时间

            elements.add(fzElement);
        }

        return elements;
    }

    //在@RequestMapping的value中使用SpEL来表示,解决因@PathVariable出现点号"."时导致路径参数截断获取不全
    @RequestMapping(value = "/{cacheName}/elements/{key:.+}", method = RequestMethod.GET)
    public FzElement getElementSingle(HttpServletRequest request, HttpServletResponse response,
                                            @PathVariable("cacheName") String cacheName,
                                            @PathVariable("key") String key) {
        Ehcache ehcache = service.getEhcache(cacheName);
        if (ehcache == null)
            return null;

        if (StringUtils.isEmpty(key))
            return null;


        Element element = ehcache.getQuiet(key);
        if (element == null)
            return null;

        FzElement fzElement = new FzElement();
        fzElement.setKey(element.getObjectKey());
        fzElement.setValue(element.getObjectValue());
        fzElement.setHitCount(element.getHitCount());
        fzElement.setLastUpdateTime(element.getLastUpdateTime());
        fzElement.setTimeToIdle(element.getTimeToIdle()); //空闲时间
        fzElement.setTimeToLive(element.getTimeToLive()); //存活时间
        fzElement.setVersion(element.getVersion());
        fzElement.setCreationTime(element.getCreationTime());  //创建时间
        fzElement.setLastAccessTime(element.getLastAccessTime()); //最后访问时间

        return fzElement;
    }

    //响应删除请求.
    @RequestMapping(value = "/{cacheName}/elements/{key:.+}", method = RequestMethod.DELETE)
    public ResponseResult deleteElement(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable("cacheName") String cacheName,
                                        @PathVariable("key") String key) {
        logger.info("cacheName:{},key:{}", cacheName, key);

        try {
            Ehcache ehcache = service.getEhcache(cacheName);
            if (ehcache != null) {
                boolean bool = ehcache.remove(key);
                logger.info("删除状态:{}", bool);
                if(!bool){
                    return new ResponseResult(ResponseResult.FAILURE,null,"成功调用，但删除状态为false");
                }

            }

            return new ResponseResult(ResponseResult.SUCCESS);
        } catch (RuntimeException ex) {
            logger.error("删除失败：{}", ex);
            return new ResponseResult(ResponseResult.FAILURE, null, ex.getMessage());
        }
    }

}
