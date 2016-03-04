package com.qylm.logic.company;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qylm.dao.UserDao;
import com.qylm.dao.UserRoleDao;
import com.qylm.entity.User;
import com.qylm.entity.UserRole;

public class UserLogic {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	public void saveUser(User user, List<UserRole> userRoleList) {
		userDao.saveEntity(user);
		if (userRoleList != null && !userRoleList.isEmpty()) {
			userRoleDao.saveEntityAll(userRoleList);
		}
	}

	public void updateUser(User user, List<UserRole> userRoleList) {
		userDao.updateEntity(user);
		String sql = "delete from user_role where userId={0}";
		userRoleDao.bulkSQLUpdate(MessageFormat.format(sql, user.getId()));
		userRoleDao.saveEntityAll(userRoleList);
	}
}
