package com.ls.view;

import javax.swing.JFrame;

public abstract class BasicFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	protected int width;
	protected int height;
	
	public void init(int width,int height) {
		this.width = width;
		this.height = height;
		this.setSize(width,height);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addPart();
		this.addlisten();
		this.pack();
		this.setVisible(true);
		
	}
	public abstract void addPart();
	public abstract void addlisten();
}
