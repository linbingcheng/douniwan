package com.modelsystem.model.util;

import com.modelsystem.common.util.ClassUtil;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by bingchenglin on 2017/4/10.
 */
public class ClassUtilTestCase {

    public void getClassName(){
        Set<Class<?>> calssSet =  ClassUtil.getClassSet("com.modelsystem.model.po");
        Iterator<Class<?>> iterable = calssSet.iterator();
        while (iterable.hasNext()){
            Class clazz = iterable.next();
            System.out.println(clazz.getName());
        }
    }



}
