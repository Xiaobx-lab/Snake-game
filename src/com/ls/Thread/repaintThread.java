package com.ls.Thread;

import com.ls.view.gamePanel;

public class repaintThread extends Thread{
	private gamePanel panel;
	boolean isRun = true;
	
	public repaintThread(gamePanel panel) {
		this.panel = panel;
	}
	
	@Override
	public void run() {
		while(isRun) {
			panel.repaint();
			try {
				Thread.sleep(41);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void interrupt() {
		isRun = false;
	}
}
