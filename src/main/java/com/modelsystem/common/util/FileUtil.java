package com.modelsystem.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by bingchenglin on 2017/4/11.
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 写入文件内容
     * @param fileUrl
     * @param contents
     * @return
     */
    public static boolean writeFile(String fileUrl,String contents){
        File file = new File(fileUrl);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(contents);
            writer.flush();
            return true;
        }catch (Exception e){
            logger.error("写入文件内容失败",e);
            return false;
        }finally {
            if(null != writer){
                try {
                    writer.close();
                } catch (IOException e) {
                    logger.error("关闭文件Writer异常", e);
                }
            }
        }
    }

    /**
     * 获取文件内容
     * @param fileUrl
     * @return
     */
    public static String readFile(String fileUrl){
        String temp = "";
        String tempString = null;
        File file = new File(fileUrl);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((tempString = reader.readLine()) != null) {
                temp += tempString+"\n";
            }
            reader.close();
        }catch (Exception e){
            logger.error("获取文件内容失败",e);
        }finally {
            try {
                if(null != reader){
                    reader.close();
                }
            } catch (IOException e) {
                logger.error("关闭文件Reader异常",e);
            }
        }
        return temp;
    }

    /**
     * 创建文件夹
     * @param destDirName
     * @return
     */
    public static boolean createDir(String destDirName){
        File dir = new File(destDirName);
        if (dir.exists()) {
            logger.info("创建目录" + destDirName + "失败，目标目录已经存在");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            logger.info("创建目录" + destDirName + "成功！");
            return true;
        } else {
            logger.error("创建目录" + destDirName + "失败！");
            return false;
        }
    }

    /**
     * 创建新文件
     * @param destFileName
     * @return
     */
    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if(file.exists()) {
            logger.error("创建单个文件" + destFileName + "失败，目标文件已存在！");
            return false;
        }
        if (destFileName.endsWith(File.separator)) {
            logger.error("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
            return false;
        }
        //判断目标文件所在的目录是否存在
        if(!file.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            logger.error("目标文件所在目录不存在，准备创建它！");
            if(!file.getParentFile().mkdirs()) {
                System.out.println("创建目标文件所在目录失败！");
                return false;
            }
        }
        //创建目标文件
        try {
            if (file.createNewFile()) {
                logger.info("创建单个文件" + destFileName + "成功！");
                return true;
            } else {
                logger.error("创建单个文件" + destFileName + "失败！");
                return false;
            }
        } catch (IOException e) {
            logger.error("创建单个文件" + destFileName + "失败！" + e.getMessage());
            return false;
        }
    }


}
