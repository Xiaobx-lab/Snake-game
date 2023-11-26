package com.ls.model.food;

import java.awt.Graphics2D;

import com.ls.util.Helper;
import com.ls.util.imgUtil;

public class point1 extends food{

	public point1() {
		super(0, 0, 0, 0,5);
		//食物的坐标随机
		x = ran.nextInt(Helper.width-50);
		y = ran.nextInt(Helper.height-50);
		image = imgUtil.getImg(Helper.food2Path);
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
