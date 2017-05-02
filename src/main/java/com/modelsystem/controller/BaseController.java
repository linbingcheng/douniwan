package com.modelsystem.controller;

import com.modelsystem.framework.annotation.AddNowDate;
import com.modelsystem.framework.annotation.FileUrl;
import com.modelsystem.framework.annotation.UpdateDate;
import com.modelsystem.framework.bean.PageRusult;
import com.modelsystem.framework.exception.AddNowDateAnnotationUsedIllegalException;
import com.modelsystem.framework.exception.FileUrlAnnotationUsedIllegalException;
import com.modelsystem.framework.finals.AnnotationFinals;
import com.modelsystem.framework.util.FormatStringUtil;
import com.modelsystem.model.po.BaseModel;
import com.modelsystem.service.BaseService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public class BaseController<T extends BaseModel> {

    public static final String SUCCESS = "success";
    public static final String FAILURE = "faiulure";
    public static final String MESSAGE = "message";
    private Logger log = Logger.getLogger(BaseController.class);

    @Autowired
    protected BaseService<T> baseService;

    // 添加保存数据
    @RequestMapping(value = "save.action")
    public void save(T t, HttpServletRequest request,
                     HttpServletResponse response) throws IOException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {// 如果是文件上传请求的话
            try {
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                MultiValueMap<String, MultipartFile> multiValueMap = multipartRequest
                        .getMultiFileMap();// 获取所有的文件上传请求，对应上传的参数和相应的文件List
                Map<String, Field> fileFields = getField4FileMaps();// 实体类中的用来保存上传文件地址的成员属性

                for (String fileParameter : multiValueMap.keySet()) {
                    String filePath = null; // 上传文件路径
                    List<MultipartFile> files = multiValueMap
                            .get(fileParameter);// 通过form表单的参数获取对应上传的文件集
                    Field field = fileFields.get(fileParameter);// 获取参数对应的成员属性
                    FileUrl fileUrl = field.getAnnotation(FileUrl.class);// 从成员属性中获取FileUrl对象

                    String uploadPath = fileUrl.uploadPath().equals(
                            AnnotationFinals.FILE_DEDAULT_UPLOAD_PATH) ? request
                            .getSession()
                            .getServletContext()
                            .getRealPath(
                                    AnnotationFinals.FILE_DEDAULT_UPLOAD_PATH)
                            : fileUrl.uploadPath();// 判断赋予文件上传目录
                    File root = new File(uploadPath);
                    if (!root.exists()) {
                        root.mkdir();// 若目录不存在新建这个文件目录
                    }

                    if (fileUrl.isManyFileUpload()) {// 如果该属性对应的是多文件上传
                        List<String> filePaths = new ArrayList<String>();// 创建存放每个上传文件的路径的集合
                        for (MultipartFile file : files) {// 遍历文件集
                            String path = null;// 上传文件的路径
                            File uploadFile = null;

                            if (file != null && !file.isEmpty()) {// 判断文件是否为空
                                if (fileUrl.keepFileName()) {// 如果要保留原文件名
                                    path = uploadPath + File.separator
                                            + file.getOriginalFilename();
                                } else {
                                    String fileName = file
                                            .getOriginalFilename();

                                    path = uploadPath
                                            + File.separator
                                            + FormatStringUtil
                                            .getTimeAddRandomIDString()
                                            + fileName.substring(fileName
                                            .lastIndexOf("."));// 随机生成的上传文件名

                                }
                            }
                            if (path != null) {
                                uploadFile = new File(path);// 新建文件
                                file.transferTo(uploadFile);// 保存上传文件
                                filePaths.add(path);
                            }
                        }
                        filePath = JSONArray.fromObject(filePaths).toString();// 将上传文件的路径的集合转化为json字符串赋予filePath
                    } else {

                        MultipartFile file = files.get(0);// 获取第一个文件
                        if (file != null && !file.isEmpty()) {// 判断第一个文件是否为空
                            if (fileUrl.keepFileName()) {
                                filePath = uploadPath + File.separator
                                        + file.getOriginalFilename();

                            } else {
                                String fileName = file.getOriginalFilename();
                                filePath = uploadPath
                                        + File.separator
                                        + FormatStringUtil
                                        .getTimeAddRandomIDString()
                                        + fileName.substring(fileName
                                        .lastIndexOf("."));

                            }
                            if (filePath != null) {
                                File uploadFile = new File(filePath);// 新建文件
                                file.transferTo(uploadFile);// 保存上传文件
                            }
                        }

                    }
                    field.setAccessible(true);
                    field.set(t, filePath);// 将文件路径保存到对象中
                }
            } catch (Exception e) {
                log.error("文件上传处理异常", e);
            }
        }
        List<Field> addNewTimeFields = getField4NewdateLists();
        for (Field f : addNewTimeFields) {
            f.setAccessible(true);
            try {
                f.set(t, new Date());
            } catch (IllegalArgumentException e) {
                log.error(e);
            } catch (IllegalAccessException e) {
                log.error(e);

            }
        }
        try {
            Serializable id = null;
            response.setContentType("text/html; charset=utf-8");
            if ((id = baseService.save(t)) != null) {
                response.getWriter().print(
                        responseAfterSubmit(true, String.valueOf(id)));// 接收到请求并且保存成功就返回序列化对象id
            } else {
                response.getWriter().print(
                        responseAfterSubmit(false, "接收到请求但保存失败"));
            }
        } catch (IOException e) {
            log.error(e);
        }
    }

    // 编辑修改数据
    @RequestMapping(value = "update.action")
    public void update(T t, HttpServletRequest request,
                       HttpServletResponse response) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {// 如果是文件上传请求的话
            try {
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                MultiValueMap<String, MultipartFile> multiValueMap = multipartRequest
                        .getMultiFileMap();// 获取所有的文件上传请求，对应上传的参数和相应的文件List
                Map<String, Field> fileFields = getField4FileMaps();// 实体类中的用来保存上传文件地址的成员属性

                for (String fileParameter : multiValueMap.keySet()) {
                    String filePath = null; // 上传文件路径
                    List<MultipartFile> files = multiValueMap
                            .get(fileParameter);// 通过form表单的参数获取对应上传的文件集
                    Field field = fileFields.get(fileParameter);// 获取参数对应的成员属性
                    FileUrl fileUrl = field.getAnnotation(FileUrl.class);// 从成员属性中获取FileUrl对象

                    String uploadPath = fileUrl.uploadPath().equals(
                            AnnotationFinals.FILE_DEDAULT_UPLOAD_PATH) ? request
                            .getSession()
                            .getServletContext()
                            .getRealPath(
                                    AnnotationFinals.FILE_DEDAULT_UPLOAD_PATH)
                            : fileUrl.uploadPath();// 判断赋予文件上传目录
                    File root = new File(uploadPath);
                    if (!root.exists()) {
                        root.mkdir();// 若目录不存在新建这个文件目录
                    }

                    if (fileUrl.isManyFileUpload()) {// 如果该属性对应的是多文件上传
                        List<String> filePaths = new ArrayList<String>();// 创建存放每个上传文件的路径的集合
                        for (MultipartFile file : files) {// 遍历文件集
                            String path = null;// 上传文件的路径
                            File uploadFile = null;

                            if (file != null && !file.isEmpty()) {// 判断文件是否为空
                                if (fileUrl.keepFileName()) {// 如果要保留原文件名
                                    path = uploadPath + File.separator
                                            + file.getOriginalFilename();
                                } else {
                                    String fileName = file
                                            .getOriginalFilename();

                                    path = uploadPath
                                            + File.separator
                                            + FormatStringUtil
                                            .getTimeAddRandomIDString()
                                            + fileName.substring(fileName
                                            .lastIndexOf("."));// 随机生成的上传文件名

                                }
                            }
                            if (path != null) {
                                uploadFile = new File(path);// 新建文件
                                file.transferTo(uploadFile);// 保存上传文件
                                filePaths.add(path);
                            }
                        }
                        filePath = JSONArray.fromObject(filePaths).toString();// 将上传文件的路径的集合转化为json字符串赋予filePath
                    } else {

                        MultipartFile file = files.get(0);// 获取第一个文件
                        if (file != null && !file.isEmpty()) {// 判断第一个文件是否为空
                            if (fileUrl.keepFileName()) {
                                filePath = uploadPath + File.separator
                                        + file.getOriginalFilename();

                            } else {
                                String fileName = file.getOriginalFilename();
                                filePath = uploadPath
                                        + File.separator
                                        + FormatStringUtil
                                        .getTimeAddRandomIDString()
                                        + fileName.substring(fileName
                                        .lastIndexOf("."));

                            }
                            if (filePath != null) {
                                File uploadFile = new File(filePath);// 新建文件
                                file.transferTo(uploadFile);// 保存上传文件
                            }
                        }

                    }
                    field.setAccessible(true);
                    field.set(t, filePath);// 将文件路径保存到对象中
                }
            } catch (Exception e) {
                log.error("文件上传处理异常", e);
            }
        }
        try {
            List<Field> updateDateFields = getField4UpdateDate();
            for (Field f : updateDateFields) {
                f.setAccessible(true);
                try {
                    f.set(t, new Date());
                } catch (IllegalArgumentException e) {
                    log.error(e);
                } catch (IllegalAccessException e) {
                    log.error(e);

                }
            }
            if (baseService.update(t)) {
                response.getWriter().print(
                        responseAfterSubmit(true, "接收到请求并且修改成功"));
            } else {
                response.getWriter().print(
                        responseAfterSubmit(false, "接收到请求但修改失败"));
            }
        } catch (IOException e) {
            log.error(e);
        }

    }

    // 删除数据
    @RequestMapping(value = "delete.action")
    public void delete(T t, HttpServletRequest request,
                       HttpServletResponse response) {
        try {
            if (baseService.delete(t)) {
                response.getWriter().print(
                        responseAfterSubmit(true, "接收到请求并且删除成功"));
            } else {
                response.getWriter().print(
                        responseAfterSubmit(false, "接收到请求但删除失败"));
            }
        } catch (IOException e) {
            log.error(e);
        }
    }

    // 冻结数据
    @RequestMapping(value = "congeal.action")
    public void congeal(T t, HttpServletRequest request,
                        HttpServletResponse response) {// 冻结
        try {
            List<Field> updateDateFields = getField4UpdateDate();
            for (Field f : updateDateFields) {
                f.setAccessible(true);
                try {
                    f.set(t, new Date());
                } catch (IllegalArgumentException e) {
                    log.error(e);
                } catch (IllegalAccessException e) {
                    log.error(e);

                }
            }
            if (baseService.congeal(t)) {
                response.getWriter().print(
                        responseAfterSubmit(true, "接收到请求并且冻结成功"));
            } else {
                response.getWriter().print(
                        responseAfterSubmit(false, "接收到请求但冻结失败"));
            }
        } catch (IOException e) {
            log.error(e);
        }

    }

    // 解冻数据
    @RequestMapping(value = "thaw.action")
    public void thaw(T t, HttpServletRequest request,
                     HttpServletResponse response) {// 解冻
        try {
            List<Field> updateDateFields = getField4UpdateDate();
            for (Field f : updateDateFields) {
                f.setAccessible(true);
                try {
                    f.set(t, new Date());
                } catch (IllegalArgumentException e) {
                    log.error(e);
                } catch (IllegalAccessException e) {
                    log.error(e);

                }
            }
            if (baseService.thaw(t)) {
                response.getWriter().print(
                        responseAfterSubmit(true, "接收到请求并且解冻成功"));
            } else {
                response.getWriter().print(
                        responseAfterSubmit(false, "接收到请求但解冻失败"));
            }
        } catch (IOException e) {
            log.error(e);
        }
    }

    // 获取数据
    @RequestMapping(value = "get.action")
    @ResponseBody
    public T get(String id, HttpServletRequest request,
                 HttpServletResponse response) {
        return baseService.get(StringUtils.isNumeric(id)?Integer.valueOf(id):id);
    }

    // 查询数据，不带分页结果的
    @RequestMapping(value = "find.action")
    @ResponseBody
    public List<T> find(T t, HttpServletRequest request,
                        HttpServletResponse response) {
        return baseService.query(t, true);
    }

    // 查询数据，分页查询,框架那只支持ext,easyui
    @RequestMapping(value = "query.action")
    @ResponseBody
    public Map<String, Object> query(T t, HttpServletRequest request,
                                     HttpServletResponse response) {
        final PageRusult rusult = baseService.query(t);
        return new HashMap<String, Object>() {
            private static final long serialVersionUID = 975714782007778264L;
            {
                put("total", rusult.getTotal());
                put("rows", rusult.getRows());
            }
        };
    }

    /**
     * 设置页面不缓存
     */
    public void setResponseNoCache(HttpServletResponse response) {
        response.setHeader("progma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
    }

    /**
     * 提交操作数据后返回操作是否成功信息
     *
     * @param isSuccess
     *            是否保存成功
     * @param message
     *            返回的信息
     * @return null
     */
    public String responseAfterSubmit(boolean isSuccess, String message) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put(SUCCESS, isSuccess);
        jsonMap.put(MESSAGE, message);
        JSONObject jsonObject = JSONObject.fromObject(jsonMap);
        return jsonObject.toString();
    }

    /**
     * 获取在实体类保存上传文件地址的成员属性
     *
     * @return 实体类保存上传文件地址的成员属性
     */
    private Map<String, Field> getField4FileMaps() {
        Map<String, Field> fields = new HashMap<String, Field>();
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        try {
            Field[] fs = clazz.getDeclaredFields();
            for (Field field : fs) {
                if (field.isAnnotationPresent(FileUrl.class)) {
                    if (!field.getType().equals(String.class)) {
                        throw new FileUrlAnnotationUsedIllegalException(
                                clazz.getName() + "." + field.getName()
                                        + "属性不是String类型,不能使用@FileUrl注解");
                    }

                    FileUrl fileUrl = field.getAnnotation(FileUrl.class);
                    if (StringUtils.isEmpty(fileUrl.name())) {
                        throw new FileUrlAnnotationUsedIllegalException(
                                clazz.getName() + "." + field.getName()
                                        + "属性使用@FileUrl注解时，name不能设置为空");
                    }

                    if (StringUtils.isEmpty(fileUrl.uploadPath())) {
                        throw new FileUrlAnnotationUsedIllegalException(
                                clazz.getName() + "." + field.getName()
                                        + "属性使用@FileUrl注解时，uploadPath不能设置为空");
                    }

                    fields.put(fileUrl.name(), field);
                }
            }
        } catch (Exception e) {
            log.error("[获取在实体类保存上传文件地址的属性]异常", e);
        }
        return fields;
    }

    /**
     * 对实体注入当前系统时间
     */
    private List<Field> getField4NewdateLists() {
        List<Field> fields = new ArrayList<Field>();
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        try {
            Field[] fs = clazz.getDeclaredFields();
            for (Field field : fs) {
                if (field.isAnnotationPresent(AddNowDate.class)) {
                    if (!field.getType().equals(Date.class)) {
                        throw new AddNowDateAnnotationUsedIllegalException(
                                clazz.getName() + "." + field.getName()
                                        + "属性不是Date类型,不能使用@AddNowDate注解");
                    } else {
                        fields.add(field);
                    }
                }
            }
        } catch (Exception e) {
            log.error("[自动注入当前系统时间]异常", e);
        }
        return fields;
    }

    private List<Field> getField4UpdateDate() {
        List<Field> fields = new ArrayList<Field>();
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        try {
            Field[] fs = clazz.getDeclaredFields();
            for (Field field : fs) {
                if (field.isAnnotationPresent(UpdateDate.class)) {
                    if (!field.getType().equals(Date.class)) {
                        throw new AddNowDateAnnotationUsedIllegalException(
                                clazz.getName() + "." + field.getName()
                                        + "属性不是Date类型,不能使用@AddNowDate注解");
                    } else {
                        fields.add(field);
                    }
                }
            }
        } catch (Exception e) {
            log.error("[自动注入当前系统时间]异常", e);
        }
        return fields;
    }

    /**
     * 文件下载
     * @param fileName 需要下载的文件的名字（需要先转成ISO8859-1编码，防止乱码问题出现）
     * @param fileInputStream
     * @return
     */
    public String ajaxdownLoad(HttpServletResponse response,String fileName, InputStream fileInputStream) {
        try {
            response.setContentType( "application/octet-stream;charset=ISO8859-1");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setDateHeader("Expires", 0);
            //得到响应的输出流  即向客户端输出信息的输出流。
            ServletOutputStream servletOutputStream = response.getOutputStream();
            byte[] b = new byte[1024];
            int len;
            while((len=fileInputStream.read(b)) >0) {
                servletOutputStream.write(b,0,len);
            }
            response.setStatus(HttpServletResponse.SC_OK );
            response.flushBuffer();
            servletOutputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            log.error("[文件下载]异常信息 ：", e);
        }
        return null;
    }
}
