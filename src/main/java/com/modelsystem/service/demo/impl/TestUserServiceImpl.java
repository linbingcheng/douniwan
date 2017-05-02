package com.modelsystem.service.demo.impl;


import org.springframework.stereotype.Service;

import com.modelsystem.model.po.demo.TestUser;
import com.modelsystem.service.BaseServiceImpl;
import com.modelsystem.service.demo.TestUserService;

@Service
public class TestUserServiceImpl extends BaseServiceImpl<TestUser> implements
		TestUserService {
	
}
