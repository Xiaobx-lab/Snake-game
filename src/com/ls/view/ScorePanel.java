package com.ls.view;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.ls.util.Helper;
import com.ls.util.imgUtil;

public class ScorePanel extends JPanel{

	/**
	 * 分数面板
	 */
	private static final long serialVersionUID = 1L;
	public ScorePanel() {
		this.setLayout(null);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gg = (Graphics2D) g;
		gg.drawImage(imgUtil.getImg(Helper.pointBGPath), 0, 0, null);
	}
}
