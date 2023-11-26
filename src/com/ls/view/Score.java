package com.ls.view;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import com.ls.bean.Result;
import com.ls.controller.MainController;
import com.ls.util.Helper;
import com.ls.util.imgUtil;

public class Score extends BasicFrame{
	
	/**
	 * 游戏窗口
	 */
	private static final long serialVersionUID = 1L;
	ScorePanel pan = new ScorePanel();
//	String finallength = MainController.getCon().getSnake().getLen()+"";
	JLabel ID = new JLabel("玩家ID                                "+MainController.getCon().getID(MainController.getCon().getUniqueName()).getPid());
	JLabel length = new JLabel("蛇的长度:                            "+(MainController.getCon().getSnake().getLen()+""));
	JLabel result = new JLabel("你的分数:                            "+(MainController.getCon().getSnake().getLen()*3+""));
	JLabel title = new JLabel("游戏结束");
	Result grade;
	Font font1 = new Font("华光美黑_CNKI", Font.BOLD, 35);
	Font font = new Font("华光美黑_CNKI", Font.PLAIN, 30);
	
	public void init() {
		this.setTitle("游戏成绩:");
		this.setIconImage(imgUtil.getImg(Helper.snakeIcon));
		super.init(500, 400);
	}
	@Override
	public void addPart() {
		pan.setPreferredSize(new Dimension(500, 400));
		pan.add(title);
		title.setFont(font1);
		title.setBounds(180, 50, 150, 35);
		
		pan.add(ID);
		ID.setFont(font);
		ID.setBounds(74, 100, 500, 30);
		
		pan.add(length);
		length.setFont(font);
		length.setBounds(75, 190, 500, 30);
		
		pan.add(result);
		result.setFont(font);
		result.setBounds(75, 250, 500, 30);
		
		this.add(pan);
	}

	@Override
	public void addlisten() {
		
		
	}
		
}
