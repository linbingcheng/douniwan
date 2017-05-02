package com.modelsystem.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by bingchenglin on 2017/4/11.
 */
public class PropertiesUtil {

    private final static Map<String, Properties> propMap = new HashMap<String, Properties>();
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);


    /**
     * 获取configFile配置文件下的proKey对应值
     *
     * @param configFile
     * @param proKey
     * @return
     */
    public static String getProperty(String configFile, String proKey) {
        Properties prop = propMap.get(configFile);
        if (prop != null) {
            return prop.getProperty(proKey);
        }
        InputStream in = null;
        try {
            in = PropertiesUtil.class.getResourceAsStream(configFile);
            prop = new Properties();
            prop.load(in);
            propMap.put(configFile, prop);
            return prop.getProperty(proKey);
        } catch (Exception e) {
            logger.error("读取配置文件内容失败！", e);
            return null;
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 获取configFile配置文件下的KeySet对应值
     * @param configFile
     * @return
     */
    public static Set getProKeySet(String configFile) {
        Properties prop = propMap.get(configFile);
        if (prop != null) {
            return prop.keySet();
        }
        InputStream in = null;
        try {
            in = PropertiesUtil.class.getResourceAsStream(configFile);
            prop = new Properties();
            prop.load(in);
            propMap.put(configFile, prop);
            return prop.keySet();
        } catch (Exception e) {
            logger.error("读取配置文件内容失败！",e);
            return null;
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
