package com.ls.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import com.ls.util.Helper;

public class MainPanel extends JPanel{
	/**
	 * 登录面板
	 */
	private static final long serialVersionUID = 1L;
	public MainPanel() {
		this.setLayout(null);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gg = (Graphics2D) g;
		g.drawImage(LoginFrame.bgImage, 0, 0, Helper.width,Helper.height, null);
	}
}
