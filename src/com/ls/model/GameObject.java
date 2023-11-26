package com.ls.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.ls.controller.MainController;
import com.ls.util.Direction;

public abstract class GameObject {
	protected Random ran = new Random();
	protected int x;//绘制到面板上的水平坐标
	protected int y;
	protected int w;
	protected int h;
	protected int speed;
	protected BufferedImage image;
	protected int life;
	protected Direction d ;
	protected boolean dead = false;
	
	public GameObject(int x, int y, int w, int h,int speed) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.speed = speed;
		MainController.getCon().addObj(this);;
	}
	
	public abstract void paintSelf(Graphics2D g);
	public abstract void move();
	public abstract void checkBounds();
	
	public Rectangle getRect() {
		return new Rectangle(x, y, w, h);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public Direction getD() {
		return d;
	}
	public void setD(Direction d) {
		this.d = d;
	}
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
}
