package com.ls.view;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import com.ls.util.Helper;

public class registerPanel extends JPanel{
	/**
	 * 注册面板
	 */
	private static final long serialVersionUID = 1L;

	public registerPanel() {
		this.setLayout(null);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gg = (Graphics2D) g;
		g.drawImage(LoginFrame.bgImage, 0, 0, Helper.width,Helper.height, null);
	}
}
