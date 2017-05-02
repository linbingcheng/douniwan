package com.modelsystem.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @project_name:     [ douniwan ]
 * @package_name:     [ com.modelsystem.controller ]
 * @author:           [ LinBingcheng ]
 * @email:            [ 494410479@qq.com ]
 * @create_time       [ 2015-2-4下午9:14:56]
 * @description:      [ TODO ]
 * @version           [ v1.0 ]
 * @last_update_user: [ LinBingcheng ]
 * @last_update_time: [ 2015-2-4下午9:14:56 ]
 */

@Controller
@RequestMapping("test")
public class TestController {

	@RequestMapping(value = "test.action")
	public void test(String t, HttpServletResponse response){
		try {
			response.getWriter().print("**********s"+t);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
