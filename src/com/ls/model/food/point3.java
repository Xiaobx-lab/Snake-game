package com.ls.model.food;

import java.awt.Graphics2D;

import com.ls.util.Helper;
import com.ls.util.imgUtil;

public class point3 extends food{

	public point3() {
		super(0, 0, 0, 0,5);
		//食物的坐标随机
		x = ran.nextInt(Helper.width-50);
		y = ran.nextInt(Helper.height-50);
		image = imgUtil.getImg(Helper.food4Path);
		w = image.getWidth();
		h = image.getHeight();
	}

	@Override
	public void paintSelf(Graphics2D g) {
		g.drawImage(image, x, y, null);
	}

	@Override
	public void move() {
		
	}

	@Override
	public void checkBounds() {
		
	}

}
