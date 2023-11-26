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

import com.ls.controller.MainController;
import com.ls.util.Helper;
import com.ls.util.imgUtil;
import com.ls.util.startNew;

public class registerFrame extends BasicFrame{

	/**
	 * 注册窗口
	 */
	private static final long serialVersionUID = 1L;
	
	registerPanel pan = new registerPanel();
	
	Font font = new Font("华光美黑_CNKI", Font.BOLD, 35);
	Font font2 = new Font("华光美黑_CNKI", Font.PLAIN, 30);
	
	JLabel title = new JLabel("欢迎注册");
	JLabel username = new JLabel("用户名:");
	JLabel password = new JLabel("密码:");
	JLabel password1 = new JLabel("确认密码:");
	JLabel nick = new JLabel("昵称");
	JButton enter = new JButton();
	JButton back = new JButton();
	
	JTextField usertext = new JTextField();
	JTextField passtext = new JPasswordField();
	JTextField passtext1 = new JPasswordField();
	JTextField nickText = new JTextField(); 
	
	public void init() {
		this.setTitle("欢迎注册");
		this.setIconImage(imgUtil.getImg(Helper.snakeIcon));
		super.init(Helper.width, Helper.height);
	}
	
	@Override
	public void addPart() {
		pan.setPreferredSize(new Dimension(Helper.width, Helper.height));
		pan.add(title);
		title.setFont(font);
		title.setBounds(400, 30, Helper.width/5, 35);
		
		pan.add(username);
		username.setFont(font2);
		username.setBounds(Helper.width/4, 110, Helper.width/5, 30);
		
		pan.add(usertext);
		usertext.setFont(font2);
		usertext.setBounds(Helper.width/35*17, 110, Helper.width/5*2, 30);
		usertext.setOpaque(false);
		
		pan.add(password);
		password.setFont(font2);
		password.setBounds(Helper.width/4 , 160, Helper.width/5, 30);
		
		pan.add(passtext);
//		passtext.setFont(font2);
		passtext.setBounds(Helper.width/35*17, 160, Helper.width/5*2, 30);
		passtext.setOpaque(false);
		
		pan.add(password1);
		password1.setFont(font2);
		password1.setBounds(Helper.width/4 , 210, Helper.width/5, 30);
		
		pan.add(passtext1);
//		passtext1.setFont(font2);
		passtext1.setBounds(Helper.width/35*17, 210, Helper.width/5*2, 30);
		passtext1.setOpaque(false);
		
		pan.add(nick);
		nick.setFont(font2);
		nick.setBounds(Helper.width/4 , 260, Helper.width/5, 30);
		
		pan.add(nickText);
		nickText.setFont(font2);
		nickText.setBounds(Helper.width/35*17, 260, Helper.width/5*2, 30);
		nickText.setOpaque(false);
		
		pan.add(enter);
		enter.setBounds(Helper.width/3+20, 330, 200 , 55);
		enter.setIcon(new ImageIcon(LoginFrame.registerImg));
		enter.setBorderPainted(false);//不打印边框  
		enter.setBorder(null);//除去边框  
		enter.setFocusPainted(false);//除去焦点的框  
		enter.setContentAreaFilled(false);//除去默认的背景填充 
		
		pan.add(back);
		back.setBounds(Helper.width/3+300, 330, 200 , 55);
		back.setIcon(new ImageIcon(LoginFrame.loginImg));
		back.setBorderPainted(false);//不打印边框  
		back.setBorder(null);//除去边框  
		back.setFocusPainted(false);//除去焦点的框  
		back.setContentAreaFilled(false);//除去默认的背景填充 
		this.add(pan);
	}

	@Override
	public void addlisten() {
		enter.addActionListener(e->{
			String user = usertext.getText();
			String nickName = nickText.getText();
			if(!passtext.getText().equals(passtext1.getText())) {
				JOptionPane.showMessageDialog(pan,"密码不一致，请重新输入！");
				passtext.setText("");
				passtext1.setText("");
				passtext.requestFocus();
			}
			
			if (usertext.getText().equals("")||passtext.getText().equals("")||passtext1.getText().equals("")||nickText.getText().equals("")) {
				System.out.println("null!");
				JOptionPane.showMessageDialog(pan, "您的资料未填写完整，请返回继续填写！");
			}else {
			String pass = passtext.getText();
			int rows = MainController.getCon().register(user,nickName,pass);
			
			if (rows!=1045) {
				JOptionPane.showMessageDialog(pan, "恭喜你，注册成功！即将返回登录界面");
				startNew.stratLogin(this);
				System.out.println(rows+"行受到影响！");
			}else if (rows==1045) {
				JOptionPane.showMessageDialog(pan, rows+":您的用户名已经被占用，请重新输入：");
				nickText.setText("");
				nickText.requestFocus();
			}
			
			}
		});
		
		
		
		back.addActionListener(e->{
			startNew.stratLogin(this);
		});
	}

}
