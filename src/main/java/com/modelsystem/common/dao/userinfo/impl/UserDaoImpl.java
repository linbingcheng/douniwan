package com.modelsystem.common.dao.userinfo.impl;

import com.modelsystem.common.dao.BaseDaoImpl;
import com.modelsystem.common.dao.userinfo.UserDao;
import com.modelsystem.model.po.userinfo.User;
import org.springframework.stereotype.Repository;

/**
 * Created by bingchenglin on 2017/4/5.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements
        UserDao {
}
