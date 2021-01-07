package dao.impl;

import java.util.ArrayList;
import java.util.List;

import bean.User;
import dao.IUserDao;



/**
 * 定义用户信息相关操作的接口实现类
 * 
 * @author Evil
 * @creatTime：2021年1月5日  下午5:01:46
 *
 */
public class UserDaoImpl  implements IUserDao{
//定义一个可以存放多个User对象的集合对象
	private static List<User> users=new ArrayList<User>();

	@Override
	public boolean addUer(User user) {
		users.add(user);
		return true;
	}

	@Override
	public List<User> selectAll() {
		
		return users;
	}

	@Override
	public User selectByUsernameAndPassword(String username, String password) {
		for (User user : users) {
			if(user.getUsername().equals(username)&&user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}
	
	
}
