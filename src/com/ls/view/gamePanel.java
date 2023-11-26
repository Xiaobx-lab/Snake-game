package com.ls.view;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.ls.controller.MainController;

public class gamePanel extends JPanel {

	/**
	 * 游戏面板
	 */
	private static final long serialVersionUID = 1L;
	
	public gamePanel() {
		this.setLayout(null);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gg = (Graphics2D) g;
		MainController.getCon().gameObjectPaint(gg);
	}
}
