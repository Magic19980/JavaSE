package dao;



import java.util.List;

import bean.User;

/**
 * 定义用户信息相关操作的接口
 * 
 * @author Evil
 * @creatTime：2021年1月5日  下午4:52:41
 *
 */
public interface IUserDao {
	
	/**
	 * 添加用户信息
	 * @param user
	 * @return
	 */
boolean addUer(User user);

/**
 * 查询所有的用户信息
 * 
 * @return
 */
List<User> selectAll();

/**
 * 通过用户名和密码查询用户信息
 * 
 * @param username
 * @param password
 * @return
 */
User selectByUsernameAndPassword(String username,String password); 
}
