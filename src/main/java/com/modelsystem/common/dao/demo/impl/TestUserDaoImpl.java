package com.modelsystem.common.dao.demo.impl;


import org.springframework.stereotype.Repository;

import com.modelsystem.common.dao.BaseDaoImpl;
import com.modelsystem.common.dao.demo.TestUserDao;
import com.modelsystem.model.po.demo.TestUser;

@Repository
public class TestUserDaoImpl extends BaseDaoImpl<TestUser> implements
		TestUserDao {

}
