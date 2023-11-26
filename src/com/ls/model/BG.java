package com.ls.model;

import java.awt.Graphics2D;

import com.ls.util.Helper;
import com.ls.util.imgUtil;

public class BG extends GameObject{

	public BG() {
		super(0, 0, Helper.width, Helper.height, 0);
	}

	@Override
	public void paintSelf(Graphics2D g) {
		g.drawImage(imgUtil.getImg(Helper.gameBG), 0, 0, Helper.width, Helper.height,null);
		
	}

	@Override
	public void move() {
		
	}

	@Override
	public void checkBounds() {
		
	}

}
