package com.modelsystem.controller.workflow;

import com.modelsystem.controller.BaseController;
import com.modelsystem.model.po.workflow.PublicFile;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by bingchenglin.
 */
@Controller
@RequestMapping("workflow/public_file")
public class PublicFileController extends BaseController<PublicFile> {

    private Logger log = Logger.getLogger(this.getClass());


    // 获取数据
    @RequestMapping(value = "download.action")
    @ResponseBody
    public void download(String id, HttpServletRequest request,
                 HttpServletResponse response) throws FileNotFoundException {
        PublicFile publicFile = baseService.get(StringUtils.isNumeric(id)?Integer.valueOf(id):id);
        File file = new File(publicFile.getFile());
        FileInputStream fileInputStream = new FileInputStream(file);
        String fileName = publicFile.getFile().substring( publicFile.getFile().indexOf("upload")+7);
        ajaxdownLoad(response,fileName,fileInputStream);
    }


}
