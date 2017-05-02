package com.modelsystem.model.util;

import com.modelsystem.common.util.ClassUtil;
import com.modelsystem.common.util.FileUtil;
import com.modelsystem.common.util.Underline2Camel;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by bingchenglin on 2017/4/10.
 */
public class GenerateCodeByTemplate {

    private static final String MODEL_PACKAGE = "com.modelsystem.model.po";
    private static final String TEMPLATES_DIR = "K:/douniwan/src/main/resources/template";
    private static final String DAO_PACKAGE = "K:/douniwan/src/main/java/com/modelsystem/common/dao";
    private static final String SERVICE_PACKAGE = "K:/douniwan/src/main/java/com/modelsystem/service";
    private static final String CONTROLLER_PACKAGE = "K:/douniwan/src/main/java/com/modelsystem/controller";
    private static final String VIEW_PACKAGE = "K:/douniwan/src/main/webapp/resource/view";
    private static final String[][] FileNameInfos = getFileNameInfo();

    public static String[][] getFileNameInfo(){
        Set<Class<?>> calssSet =  ClassUtil.getClassSet("com.modelsystem.model.po");
        Iterator<Class<?>> iterable = calssSet.iterator();
        String[][] infos = new String[calssSet.size()-2][2];
        int i = 0;
        while (iterable.hasNext()){
            Class clazz = iterable.next();
            String className = clazz.getName();
            if((!className.equals("com.modelsystem.model.po.BaseModel"))&&(!className.equals("com.modelsystem.model.po.TreeModel"))){
                String[] nameInfos = className.split("\\.");
                infos[i][0] = nameInfos[4];
                infos[i][1] = nameInfos[5];
                i++;
            }
        }
        return infos;
    }

    public static void generateDaoCode(){
        for (String[] fileInfo : FileNameInfos){
            String fileUrl = DAO_PACKAGE+"/"+fileInfo[0]+"/"+fileInfo[1]+"Dao.java";
            String templeFileUrl = TEMPLATES_DIR + "/Dao.tpl";
            String contents = FileUtil.readFile(templeFileUrl).replace("{1}",fileInfo[0]).replace("{2}",fileInfo[1]);
            if (FileUtil.createFile(fileUrl)){
                FileUtil.writeFile(fileUrl,contents);
            }
        }

    }

    public static void generateDaoImplCode(){
        for (String[] fileInfo : FileNameInfos){
            String fileUrl = DAO_PACKAGE+"/"+fileInfo[0]+"/impl/"+fileInfo[1]+"DaoImpl.java";
            String templeFileUrl = TEMPLATES_DIR + "/DaoImpl.tpl";
            String contents = FileUtil.readFile(templeFileUrl).replace("{1}",fileInfo[0]).replace("{2}",fileInfo[1]);
            if (FileUtil.createFile(fileUrl)){
                FileUtil.writeFile(fileUrl,contents);
            }
        }
    }

    public static void generateServiceCode(){
        for (String[] fileInfo : FileNameInfos){
            String fileUrl = SERVICE_PACKAGE+"/"+fileInfo[0]+"/"+fileInfo[1]+"Service.java";
            String templeFileUrl = TEMPLATES_DIR + "/Service.tpl";
            String contents = FileUtil.readFile(templeFileUrl).replace("{1}",fileInfo[0]).replace("{2}",fileInfo[1]);
            if (FileUtil.createFile(fileUrl)){
                FileUtil.writeFile(fileUrl,contents);
            }
        }
    }

    public static void generateServiceImplCode(){
        for (String[] fileInfo : FileNameInfos){
            String fileUrl = SERVICE_PACKAGE+"/"+fileInfo[0]+"/impl/"+fileInfo[1]+"ServiceImpl.java";
            String templeFileUrl = TEMPLATES_DIR + "/ServiceImpl.tpl";
            String contents = FileUtil.readFile(templeFileUrl).replace("{1}",fileInfo[0]).replace("{2}",fileInfo[1]);
            if (FileUtil.createFile(fileUrl)){
                FileUtil.writeFile(fileUrl,contents);
            }
        }
    }

    public static void generateControllerCode(){
        for (String[] fileInfo : FileNameInfos){
            String fileUrl = CONTROLLER_PACKAGE+"/"+fileInfo[0]+"/"+fileInfo[1]+"Controller.java";
            String templeFileUrl = TEMPLATES_DIR + "/Controller.tpl";
            String contents = FileUtil.readFile(templeFileUrl).replace("{1}",fileInfo[0]).replace("{2}",fileInfo[1]).replace("{3}",Underline2Camel.camel2Underline(fileInfo[1]).toLowerCase());
            if (FileUtil.createFile(fileUrl)){
                FileUtil.writeFile(fileUrl,contents);
            }
        }
    }

    public static void generateJspCode(){
        for (String[] fileInfo : FileNameInfos){
            String fileUrl = VIEW_PACKAGE+"/"+Underline2Camel.camel2Underline(fileInfo[0]).toLowerCase()+"/"+Underline2Camel.camel2Underline(fileInfo[1]).toLowerCase()+".jsp";
            String templeFileUrl = TEMPLATES_DIR + "/Jsp.tpl";
            String contents = FileUtil.readFile(templeFileUrl).replace("{1}",Underline2Camel.camel2Underline(fileInfo[0]).toLowerCase()).replace("{2}", Underline2Camel.camel2Underline(fileInfo[1]).toLowerCase());
            if (FileUtil.createFile(fileUrl)){
                FileUtil.writeFile(fileUrl,contents);
            }
        }
    }


}
