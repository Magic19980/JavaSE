package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bean.User;
import dao.IUserDao;
import dao.impl.UserDaoImpl;
import until.StringUtils;




/**
 * @author Evil
 * @creatTime：2021年1月5日 下午5:57:41
 *
 */
public class loadFrame extends JFrame {
	
	private static final long serialVersionUID = -2986246242406175994L;
	private JButton loadButton;  //登录按钮
	private JButton regiserButton; //注册按钮
	private  JTextField usernameField; //用户文本
	private JPasswordField passwordField; //密码文本
	
	public static void main(String[] args) {
		new loadFrame();
	}
	
	public loadFrame() {
		//初始化窗口
		initFrame();
		
		//注册监听器方法
		event();
	}
	
	/**
	 * 初始化窗口
	 */
	private void initFrame() {
		//设置窗体标题
		this.setTitle("登录界面");
		//设置窗体大小
		this.setSize(400,320);
		//设置窗体在屏幕居中
		this.setLocationRelativeTo(null);
		//设置窗体不可变
		this.setResizable(false);
		//设置窗口退出方式
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置布局
		this.setLayout(null);
		
		//用户标签
		JLabel usernameLabel=new JLabel("用户名:  ");
		usernameLabel.setFont(new Font("楷体",Font.BOLD,18));
		usernameLabel.setBounds(40, 80, 100, 30);
		usernameLabel.setHorizontalAlignment(JLabel.RIGHT);
		usernameLabel.setVerticalAlignment(JLabel.CENTER);
		this.add(usernameLabel);
		usernameField =new JTextField();
		usernameField.setBounds(150, 80, 150, 30);
		this.add(usernameField);
		
		//密码标签
		JLabel passwordLabel=new JLabel("密码:  ");
		passwordLabel.setFont(new Font("楷体",Font.BOLD,18));
		passwordLabel.setBounds(40, 120, 100, 30);
		passwordLabel.setHorizontalAlignment(JLabel.RIGHT);
		passwordLabel.setVerticalAlignment(JLabel.CENTER);
		this.add(passwordLabel);
		passwordField=new JPasswordField();
		passwordField.setBounds(150, 120, 150, 30);
		this.add(passwordField);
		
		loadButton =new JButton("登录");
		loadButton.setBounds(110, 180, 70, 30);
		loadButton.setBackground(Color.GRAY);
		loadButton.setForeground(Color.BLUE);
		loadButton.setFont(new Font("楷体",Font.BOLD,15));
		this.add(loadButton);
		
		regiserButton=new JButton("注册");
		regiserButton.setBounds(220, 180, 70, 30);
		regiserButton.setBackground(Color.GRAY);
		regiserButton.setForeground(Color.BLUE);
		regiserButton.setFont(new Font("楷体",Font.BOLD,15));
		this.add(regiserButton);
		
		//设置窗体可见
		this.setVisible(true);
	}
	
	//窗体关闭
	private void close() {
		this.dispose();
	}
	
	/**
	 * 注册监听器方法
	 */
	private void event() {// TODO Auto-generated method stub
		
		//给登录功能添加鼠标点击事件
		loadButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//获取输入内容
				String usernameString=usernameField.getText();
				if(StringUtils.isEmpty(usernameString)) {
					JOptionPane.showMessageDialog(null, "用户名不能为空");
					usernameField.setText("");
					return;
				}
				
				char[] arr=passwordField.getPassword();
				String passwordString=new String(arr);
				if(StringUtils.isEmpty(passwordString)) {
					JOptionPane.showMessageDialog(null, "密码不能为空");
				}
				
				IUserDao userDao=new UserDaoImpl();
				User user= userDao.selectByUsernameAndPassword(usernameString, passwordString);
				if(user!=null) {
					JOptionPane.showMessageDialog(null, "登录成功！");
				}else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误！");
				}
			}
		});
		
		//给注册功能添加鼠标事件
		regiserButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
				new RegisterFrame();
			}
		});
	}

	

	
	
}
