package bean;

import java.util.Arrays;

/**
 * @author Evil
 * @creatTime：2021年1月5日  下午4:44:52
 *
 */
public class User {

	private String username; //用户名
	private String password; //密码
	private char gender; //性别
	private String[] interest;//兴趣
	private String birthday; //出生日期
	private String address; //居住地址
	private String headImage; //用户头像
	private String remark; //其它信息
	
	/**
	 * @param username
	 * @param password
	 * @param gender
	 * @param interest
	 * @param birthday
	 * @param address
	 * @param headImage
	 * @param remark
	 */
	public User(String username, String password, char gender, String[] interest, String birthday, String address,
			String headImage, String remark) {
		super();
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.interest = interest;
		this.birthday = birthday;
		this.address = address;
		this.headImage = headImage;
		this.remark = remark;
	}

	public User() {
		
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", gender=" + gender + ", interest="
				+ Arrays.toString(interest) + ", birthday=" + birthday + ", address=" + address + ", headImage="
				+ headImage + ", remark=" + remark + "]";
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the gender
	 */
	public char getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}

	/**
	 * @return the interest
	 */
	public String[] getInterest() {
		return interest;
	}

	/**
	 * @param interest the interest to set
	 */
	public void setInterest(String[] interest) {
		this.interest = interest;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the headImage
	 */
	public String getHeadImage() {
		return headImage;
	}

	/**
	 * @param headImage the headImage to set
	 */
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
