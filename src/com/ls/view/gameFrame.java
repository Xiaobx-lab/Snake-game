package com.ls.view;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.imageio.ImageIO;

import com.ls.controller.MainController;
import com.ls.util.Helper;
import com.ls.util.imgUtil;

public class gameFrame extends BasicFrame{

	/**
	 * 游戏窗口
	 */
	private static final long serialVersionUID = 1L;
	gamePanel pan = new gamePanel();
	public void init() {
		this.setTitle("贪吃蛇大作战");
		this.setIconImage(imgUtil.getImg(Helper.snakeIcon));
		super.init(Helper.width, Helper.height);
	}
	@Override
	public void addPart() {
		MainController.getCon().init(pan);
		pan.setPreferredSize(new Dimension(Helper.width, Helper.height));
		pan.setFocusable(true);
		this.add(pan);
	}

	@Override
	public void addlisten() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MainController.getCon().shutDownThread();
			}
		});
		
		pan.addKeyListener(new KeyAdapter() {
			//通过控制层把按键情况转给贪吃蛇
			@Override
			public void keyPressed(KeyEvent e) {
				MainController.getCon().keyPressed(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
		//		System.out.println("jjjjj");
				MainController.getCon().keyRealeased(e);
			}
		});
		
		pan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			MainController.getCon().mouseclick(e);
			}
		});
	}

}
