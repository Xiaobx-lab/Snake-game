package com.ls.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ls.bean.Player;

import com.ls.controller.MainController;
import com.ls.util.Helper;
import com.ls.util.imgUtil;
import com.ls.util.startNew;

public class LoginFrame extends BasicFrame{
	private static final   long serialVersionUID = 1L;
	
	static BufferedImage bgImage;
	static BufferedImage loginImg;
	static BufferedImage registerImg;
	
	static {
		bgImage = imgUtil.getImg(Helper.bgPath);
		loginImg = imgUtil.getImg(Helper.loginImgPath);
		registerImg = imgUtil.getImg(Helper.registerImgPath);
	}
	
	MainPanel pan = new MainPanel();
	JLabel title = new JLabel("登录游戏");
	JLabel userName = new JLabel("用户名");
	JLabel password = new JLabel("密码");
	Font font = new Font("华光美黑_CNKI", Font.BOLD, 35);
	Font font2 = new Font("华光美黑_CNKI", Font.PLAIN, 30);
	JTextField usernameText = new JTextField();
	JPasswordField passText = new JPasswordField();
	JButton enter = new JButton();
	JButton register = new JButton();
	
	//初始化窗口
	public void init() {
		this.setTitle("贪吃蛇大作战");
		this.setIconImage(imgUtil.getImg(Helper.snakeIcon));
		super.init(Helper.width, Helper.height);
	}
	
	//传递用户名给控制层
	public void transferUser(String name) {
		MainController.getCon().setUniqueName(name);
	}
	
	@Override
	public void addPart() {
		pan.setPreferredSize(new Dimension(Helper.width, Helper.height));
		pan.add(title);
		title.setFont(font);
		title.setBounds(400, 30, Helper.width/5, 35);
		pan.add(userName);
		userName.setFont(font2);
		userName.setBounds(Helper.width/4 , 110, Helper.width/5, 30);
		pan.add(usernameText);
		usernameText.setFont(font2);
		usernameText.setBounds(Helper.width/35*17, 110, Helper.width/5*2, 30);
		usernameText.setOpaque(false);
		
		pan.add(password);
		password.setFont(font2);
		password.setBounds(Helper.width/4 , 170, Helper.width/5, 30);
		pan.add(passText);
		passText.setBounds(Helper.width/35*17, 170, Helper.width/5*2, 30);
		passText.setOpaque(false);
		
		pan.add(enter);
		enter.setBounds(Helper.width/3+10, Helper.height/5*3-15, 200 , 60);
		enter.setIcon(new ImageIcon(loginImg));
		enter.setBorderPainted(false);//不打印边框  
		enter.setBorder(null);//除去边框  
		enter.setFocusPainted(false);//除去焦点的框  
		enter.setContentAreaFilled(false);//除去默认的背景填充 
		
		pan.add(register);
		register.setBounds(Helper.width/3*2, Helper.height/5*3-13, 200, 60);
		register.setIcon(new ImageIcon(registerImg));
		register.setBorderPainted(false);//不打印边框  
		register.setBorder(null);//除去边框  
		register.setFocusPainted(false);//除去焦点的框  
		register.setContentAreaFilled(false);//除去默认的背景填充 
		this.add(pan);
	}

	@Override
	public void addlisten() {
		//当按下enter按钮时 把用户名和密码 传递给控制层去控制
		enter.addActionListener(e->{
			String user = usernameText.getText();
			String pass = new String(passText.getPassword());
			//登录：拿着用户名和密码 到数据库里查该用户名的所有信息 得到对象
			Player player = MainController.getCon().login(user,pass);
			if (player==null) {
				JOptionPane.showMessageDialog(pan, "用户名或密码错误！");
				usernameText.setText("");
				passText.setText("");
				usernameText.requestFocus();
			}else {
				JOptionPane.showMessageDialog(pan, "登陆成功，欢迎"+player.getPnick()+"进入游戏");
				this.dispose();
				transferUser(user);
				
				//开始游戏
				gameFrame go = new gameFrame();
				go.init();
			}
		});
			
		//点击注册按钮
		register.addActionListener(e->{
			startNew.startLogin(this);
		});
	}
}
