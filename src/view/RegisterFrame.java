package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

import bean.User;
import dao.IUserDao;
import dao.impl.UserDaoImpl;
import until.StringUtils;


/**
 * @author Evil
 * @creatTime：2021年1月4日 下午9:49:58
 *
 */
public class RegisterFrame extends JFrame {

	private static final long serialVersionUID = 4302358276896999417L;

		// 表单注册按钮
		private JButton registerBtn;
		// 表单重置按钮
		private JButton resetBtn;
		// 年份下拉框对象
		private JComboBox<Integer> yearBox;
		// 月份下拉框对象
		private JComboBox<Integer> monthBox;
		// 天数拉框对象
		private JComboBox<Integer> dateBox;
		private int date;
		
		// 用户名输入框
		private JTextField usernameText;
		// 密码输入框
		private JPasswordField passwordText;
		// 单选按钮--男
		private JRadioButton maleBtn;
		// 单选按钮--女
		private JRadioButton femaleBtn;
		// 显示用户头像的下拉框对象
		private JComboBox<ImageIcon> headImageBox;
		// 显示备注信息的文本域对象
		private TextArea remarkText;
		// 复选框
		private JCheckBox readBox;
		// 复选框
		private JCheckBox lolBox;
		// 复选框
		private JCheckBox sportBox;
		// 复选框
		private JCheckBox sleepBox;

	/*
	 * 构造方法
	 */
	public RegisterFrame() {
		// 初始化窗口
		initFrame();

		// 初始化年月日下拉框
		initDateComboBox();

		// 注册监听器的方法
		event();
	}
	
	private void close() {
		this.dispose();
	}
	
	private void event() {
		
		// 给注册功能添加鼠标点击事件
		registerBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取输入的内容
				
				//用户名处理-----------------
				String username=usernameText.getText();
				//判断用户名是否为空
				if(StringUtils.isEmpty(username)) {
					//弹窗提示
					JOptionPane.showMessageDialog(null, "用户名不能为空");
					//还原输入框里面的内容
					usernameText.setText("");
					return;
				}
				//判断用户名是否超过6位
				if((username=username.trim()).length()<4) {
					//弹窗提示
					JOptionPane.showMessageDialog(null, "用户名长度需要超过6位");
					//还原输入框里面的内容
					usernameText.setText(username);
					return;
				}
				
				//密码处理---------------------
				char[] array=passwordText.getPassword();
				String password=new String(array);
				if(StringUtils.isEmpty(password)) {
					//弹窗提示
					JOptionPane.showMessageDialog(null, "密码不能为空");
					//还原输入框里面的内容
					passwordText.setText("");
					return;
				}
				
				//性别------------------------------
				char gender=maleBtn.isSelected()?'男':'女';
				
				//兴趣爱好----------------------------
				List<String > interestList=new ArrayList<String>();
				if(readBox.isSelected()) {
					interestList.add(readBox.getText());
				}
				if(lolBox.isSelected()) {
					interestList.add(lolBox.getText());
				}
				if(sportBox.isSelected()) {
					interestList.add(sportBox.getText());
				}
				if(sleepBox.isSelected()) {
					interestList.add(sleepBox.getText());
				}
				//将集合转成数组
				Object[] arr=interestList.toArray();
				String[] interest=new String[arr.length];
				for(int i=0;i<arr.length;i++) {
					interest[i]=(String) arr[i];
				}
				
				//出生日期----------------------
				String birthday=yearBox.getSelectedItem()+"-"+monthBox.getSelectedItem()+"-"+dateBox.getSelectedItem();
				
				//居住地址----------------------
				String addressString="湖南省长沙市";
				
				//用户头像----------------------
				ImageIcon headImageIcon=(ImageIcon) headImageBox.getSelectedItem();
				String headImageString=headImageIcon.toString();
				
				//其它信息----------------------
				String remark=remarkText.getText().trim();
				
				//构建User对象
				User user=new User(username, password, gender, interest, birthday, addressString, headImageString, remark);
				
				//保持用户注册数据
				IUserDao userDao=new UserDaoImpl();
				userDao.addUer(user);
				close();
				new loadFrame();
			}
		});
		
		//给重置功能添加鼠标点击事件
		resetBtn.addActionListener(new ActionListener() {
			
			@Override
			public void  actionPerformed(ActionEvent e) {
			close();
			new RegisterFrame();	
			}
		});

		//给年份和月份下拉框注册监听器
		yearBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// 通过年份或者月份下拉框的变化，来动态调整天数下拉框的变化
				formatDateComboxByYearOrMonth();
			}
		});
		monthBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				formatDateComboxByYearOrMonth();
			}
		});
	}
	
/*
 * 初始化年月日下拉框
 */
	private void initDateComboBox() {
		//得到当前的年月日
		Calendar c=Calendar.getInstance();
		//得到当前日期的年份
		int year=c.get(Calendar.YEAR);
		//得到当前日期的月份
		int month=c.get(Calendar.MONTH)+1;
		//得到当前日期的天数
		date=c.get(Calendar.DATE);

		//循环给年份下拉框赋值
		for(int i=1998;i<=year;i++) {
			yearBox.addItem(i);
		}
		//设置选中的年份为当前年份year
		yearBox.setSelectedItem(year);
		
		//循环给月份下拉框赋值
		for(int i=1;i<=12;i++) {
			monthBox.addItem(i);
		}
		//设置选中的月份为当前月份month
		monthBox.setSelectedItem(month);
		
		//得到当前年月对应的最大的天数
		initDateComboBox(year,month,date);
	}

	/*
	 * 初始化天数下拉框
	 */
	private void initDateComboBox(int year, int month, int date) {
	//得到最大的天数
		int maxDays=getMaxDays(year,month);
		
	//清除之前下拉框里面的内容
		dateBox.removeAllItems();
		
		//循环给天数赋值
		for(int i=1;i<=maxDays;i++) {
			dateBox.addItem(i);
		}
		//设置当前日期天数为选中天数
		dateBox.setSelectedIndex(date);
}

	/*
	 * 得到指定年月对应的最大天数
	 */
	private int getMaxDays(int year, int month) {
		int maxDays=0;
		switch (month) {
		case 2:
			maxDays=(isLeapYear(year)?29:28);
		break;
		case 4:
		case 6:
		case 9:
		case 11:
			maxDays=30;
			break;
			
		default:
			maxDays=31;
			break;
		}
		return maxDays;
	}

	/*
	 * 判断是否为闰年
	 */
	private boolean isLeapYear(int year) {
		return (year%4==0 && year%100!=0) || (year%400==0);
	}

	/*
	 *通过年份或者是月份下拉框的变化，来动态调整天数下拉框的变化 
	 */
	private void formatDateComboxByYearOrMonth() {
		//得到选中的年份
		Integer year=(Integer) yearBox.getSelectedItem();
		//得到选中的月份
		Integer month=(Integer) monthBox.getSelectedItem();
		//初始化天数的下拉框
		initDateComboBox(year,month,date);
	}
	
	/*
	 * 初始化窗口
	 */
	private void initFrame() {

		// 设置窗口的标题
		this.setTitle("注册窗口");
		// 设置窗口的大小
		this.setSize(320, 550);
		// 窗口居中
		this.setLocationRelativeTo(null);
		// 设置窗口大小不可变
		this.setResizable(false);
		// 设置窗口退出方式
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置布局为空布局【默认为流式布局】
		this.setLayout(null);

		// 构建一个用户标签
		JLabel usernameJLabel = new JLabel("用户名:  ");
		// 设置字体和大小
		usernameJLabel.setFont(new Font("楷体", Font.BOLD, 18));
		// 设置这个标签的位置和大小
		usernameJLabel.setBounds(30, 20, 90, 40);
		// 设置对齐方式
		usernameJLabel.setHorizontalAlignment(JLabel.RIGHT);
		usernameJLabel.setVerticalAlignment(JLabel.CENTER);
		// 添加到窗口
		this.add(usernameJLabel);
		// 构建一个用户名输入框
		 usernameText = new JTextField();
		// 设置大小
		usernameText.setBounds(120, 30, 150, 20);
		this.add(usernameText);

		// 构建一个用户密码标签
		JLabel passwordLabel = new JLabel("密码:  ");
		// 设置字体和大小
		passwordLabel.setFont(new Font("楷体", Font.BOLD, 18));
		// 设置标签的位置和大小
		passwordLabel.setBounds(30, 60, 90, 40);
		// 设置对齐方式
		passwordLabel.setHorizontalAlignment(JLabel.RIGHT);
		passwordLabel.setVerticalAlignment(JLabel.CENTER);
		// 添加到窗口
		this.add(passwordLabel);
		// 构建一个密码输入框
		 passwordText = new JPasswordField();
		// 设置标签的位置和大小
		passwordText.setBounds(120, 70, 150, 20);
		// 添加到窗口上
		this.add(passwordText);

		// 构建一个性别标签
		JLabel genderLabel = new JLabel("性别:  ");
		// 设置字体和大小
		genderLabel.setFont(new Font("楷体", Font.BOLD, 18));
		// 设置标签的位置和大小
		genderLabel.setBounds(40, 100, 80, 40);
		// 设置对齐方式
		genderLabel.setHorizontalAlignment(JLabel.RIGHT);
		genderLabel.setVerticalAlignment(JLabel.CENTER);
		// 添加到窗口
		this.add(genderLabel);
		// 构建一个单选框
		 maleBtn = new JRadioButton("男");
		// 设置标签位置和大小
		maleBtn.setBounds(120, 100, 50, 40);
		// 添加到窗口
		this.add(maleBtn);
		// 构建一个单选框
		 femaleBtn = new JRadioButton("女");
		// 设置标签位置和大小
		femaleBtn.setBounds(170, 100, 50, 40);
		// 添加到窗口
		this.add(femaleBtn);
		// 实现单选功能
		ButtonGroup group = new ButtonGroup();
		group.add(femaleBtn);
		group.add(maleBtn);
		// 默认选中"男"
		maleBtn.setSelected(true);

		// 构建一个兴趣标签
		JLabel interestLabel = new JLabel("兴趣爱好:  ");
		// 设置字体和大小
		interestLabel.setFont(new Font("楷体", Font.BOLD, 18));
		// 设置标签位置和大小
		interestLabel.setBounds(-10, 140, 130, 60);
		// 设置对齐方式
		interestLabel.setHorizontalAlignment(JLabel.RIGHT);
		interestLabel.setVerticalAlignment(JLabel.CENTER);
		// 添加到窗口
		this.add(interestLabel);
		// 复选框
		 readBox = new JCheckBox("学习");
		 lolBox = new JCheckBox("LOL");
		 sportBox = new JCheckBox("运动");
		 sleepBox = new JCheckBox("睡觉");
		// 设置标签位置和大小
		readBox.setBounds(120, 140, 60, 30);
		lolBox.setBounds(200, 140, 60, 30);
		sportBox.setBounds(120, 170, 60, 30);
		sleepBox.setBounds(200, 170, 60, 30);
		// 添加到窗口
		this.add(readBox);
		this.add(lolBox);
		this.add(sportBox);
		this.add(sleepBox);

		// 构建一个出生日期标签
		JLabel birthdayJLabel = new JLabel("出生日期:  ");
		// 设置字体和大小
		birthdayJLabel.setFont(new Font("楷体", Font.BOLD, 18));
		// 设置标签位置和大小
		birthdayJLabel.setBounds(10, 205, 110, 30);
		// 设置对齐方式
		birthdayJLabel.setHorizontalAlignment(JLabel.RIGHT);
		birthdayJLabel.setVerticalAlignment(JLabel.CENTER);
		// 添加到窗口
		this.add(birthdayJLabel);
		// 年份下拉框
		yearBox = new JComboBox<Integer>();
		yearBox.setFont(new Font("宋体", Font.PLAIN, 10));
		yearBox.setBounds(120, 210, 60, 20);
		this.add(yearBox);
		// 月份下拉框
		monthBox = new JComboBox<Integer>();
		monthBox.setFont(new Font("宋体", Font.PLAIN, 10));
		monthBox.setBounds(180, 210, 60, 20);
		this.add(monthBox);
		// 天数下拉框
		dateBox = new JComboBox<Integer>();
		dateBox.setFont(new Font("宋体", Font.PLAIN, 10));
		dateBox.setBounds(240, 210, 60, 20);
		this.add(dateBox);

		// 构建一个居住地址标签
		JLabel addressLabel = new JLabel("居住地址:  ");
		// 设置字体和大小
		addressLabel.setFont(new Font("楷体", Font.BOLD, 18));
		// 设置标签位置和大小
		addressLabel.setBounds(10, 240, 110, 40);
		// 设置对齐方式
		addressLabel.setHorizontalAlignment(JLabel.RIGHT);
		addressLabel.setVerticalAlignment(JLabel.CENTER);
		// 添加到窗口
		this.add(addressLabel);
		// 创建一个省份下拉框
		JComboBox<String> provinceBox = new JComboBox<String>();
		provinceBox.setFont(new Font("宋体", Font.PLAIN, 10));
		provinceBox.addItem("河南省");
		provinceBox.addItem("河北省");
		provinceBox.addItem("湖南省");
		provinceBox.addItem("湖北省");
		provinceBox.setBounds(120, 250, 80, 20);
		this.add(provinceBox);
		// 创建一个城市下拉框
		JComboBox<String> cityBox = new JComboBox<String>();
		cityBox.setBounds(200, 250, 80, 20);
		this.add(cityBox);

		// 创建一个用户头像标签
		JLabel headImageLabel = new JLabel("用户头像:");
		// 设置字体和大小
		headImageLabel.setFont(new Font("楷体", Font.BOLD, 18));
		// 设置标签位置和大小
		headImageLabel.setBounds(-10, 280, 110, 40);
		// 设置对齐方式
		headImageLabel.setHorizontalAlignment(JLabel.RIGHT);
		headImageLabel.setVerticalAlignment(JLabel.CENTER);
		// 添加到窗口
		this.add(headImageLabel);
		// 创建一个用户头像下拉框
		 headImageBox = new JComboBox<ImageIcon>();
		// 设置标签位置和大小
		headImageBox.setBounds(120, 280, 100, 40);
		for (int i = 1; i <= 6; i++) {
			headImageBox.addItem(new ImageIcon("images/0" + i + ".jpg"));
		}
		//添加到窗口
		this.add(headImageBox);
		
		//构建一个其它信息标签
		JLabel remarkLabel=new JLabel("其它信息:  ");
		//设置字体和大小
		remarkLabel.setFont(new Font("楷体",Font.BOLD,18));
		//设置标签位置和大小
		remarkLabel.setBounds(10, 320, 110, 40);
		//设置对齐方式
		remarkLabel.setHorizontalAlignment(JLabel.RIGHT);
		remarkLabel.setVerticalAlignment(JLabel.CENTER);
		//添加到窗口
		this.add(remarkLabel);
		//滚动条
		JScrollBar scrollBar=new JScrollBar();
		//文本域
		 remarkText=new TextArea(5,10);
		scrollBar.add(remarkText);
		remarkText.setBounds(120, 330, 150, 100);
		//添加到窗口
		this.add(remarkText);
		
		//表单注册按钮
		registerBtn=new JButton("注册");
		registerBtn.setBackground(Color.PINK);
		registerBtn.setForeground(Color.BLUE);
		registerBtn.setFont(new Font("楷体",Font.BOLD,20));
		registerBtn.setBounds(40, 450, 100, 30);
		this.add(registerBtn);
		
		//表单重置按钮
		 resetBtn=new JButton("重置");
		resetBtn.setBackground(Color.PINK);
		resetBtn.setForeground(Color.BLUE);
		resetBtn.setFont(new Font("楷体",Font.BOLD,20));
		resetBtn.setBounds(160,450,100,30);
		//添加到窗口上
		this.add(resetBtn);
		
		// 窗口可见
		this.setVisible(true);
	}
}


