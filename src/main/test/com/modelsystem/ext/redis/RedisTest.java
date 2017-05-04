package com.modelsystem.ext.redis;

import com.modelsystem.framework.enumtype.StatusType;
import com.modelsystem.model.po.workflow.PublicFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by bingchenglin on 2017/5/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-*.xml")
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testput(){
        redisUtil.put(RedisUtil.BASE_GROUP,"testkey","test}\'\"",null);
    }

    @Test
    public void testget(){
        System.out.println("++++++++");
        System.out.println(redisUtil.get(RedisUtil.BASE_GROUP, "testkey"));
    }


    @Test
    public void testPutObject(){
        PublicFile file = new PublicFile();
        file.setId(1);
        file.setStatus(StatusType.NORMAL);
        file.setCreate_date(new Date());
        file.setUpdate_date(new Date());
        file.setFile("ddddddd");
        redisUtil.put(RedisUtil.BASE_GROUP,"testobject",file,null);
    }

    @Test
    public void testGetObject(){
        PublicFile file = redisUtil.get(RedisUtil.BASE_GROUP,"testobject",PublicFile.class);
        System.out.println("获取到的信息为"+file.getId()+file.getFile()+file.getCreate_date());
    }




    public RedisUtil getRedisUtil() {
        return redisUtil;
    }


    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }
}
