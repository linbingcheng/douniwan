package com.modelsystem.controller.resources;

import com.modelsystem.controller.BaseController;
import com.modelsystem.model.po.resources.Property;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by bingchenglin.
 */
@Controller
@RequestMapping("resources/property")
public class PropertyController extends BaseController<Property> {

    private Logger log = Logger.getLogger(this.getClass());

}
