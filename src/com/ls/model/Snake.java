package com.ls.model;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.ls.controller.MainController;
import com.ls.util.Direction;
import com.ls.util.Helper;
import com.ls.util.drawSnake;
import com.ls.util.imgUtil;
import com.ls.view.Score;
import com.mysql.cj.util.Util;

public class Snake extends GameObject{
	
	int len = 3;//初始长度为3
	boolean isFailed;
	int score;
	int ClickCount;
	boolean isStarted = true;
	boolean initStarted = false;//初始图片添加
	boolean haveRound = false;  //有无保护罩
	boolean changeShirt = false;
	
	//给蛇搞一个集合
	int snakeX[] = new int[800];
	int snakeY[] = new int[800];
	static BufferedImage right= null;
	static BufferedImage right1= null;
	static BufferedImage right2= null;
	static BufferedImage left= null;
	static BufferedImage left1= null;
	static BufferedImage left2= null;
	static BufferedImage up= null;
	static BufferedImage up1= null;
	static BufferedImage up2= null;
	static BufferedImage down= null;
	static BufferedImage down1= null;
	static BufferedImage down2= null;
	BufferedImage image1;
	BufferedImage image2;
	
	static {//加载四个头
		right = imgUtil.getImg(Helper.snakeHeadPath);
		left = imgUtil.getImg(Helper.snakeHeadLeftPath);
		up = imgUtil.getImg(Helper.snakeHeadUpPath);
		down = imgUtil.getImg(Helper.snakeHeadDownPath);
		right1 = imgUtil.getImg("img/right1.png");
		left1 = imgUtil.getImg("img/left1.png");
		up1 = imgUtil.getImg("img/up1.png");
		down1 = imgUtil.getImg("img/down1.png");
		right2 = imgUtil.getImg("img/right2.png");
		left2 = imgUtil.getImg("img/left2.png");
		up2 = imgUtil.getImg("img/up2.png");
		down2 = imgUtil.getImg("img/down2.png");
		Helper.bodyWidth = right.getWidth();
		Helper.bodyHeight = right.getHeight();
		Helper.headheight = right.getHeight();
		Helper.headWidth = right.getWidth();
	}
	
	public Snake() {
		super(0, 0, 0, 0, 5);
		initSnake();	
		image = imgUtil.getImg(Helper.snakeBodyPath);//蛇身
		image1 = imgUtil.getImg("img/snake_body1.png");
		image2 = imgUtil.getImg("img/body.png");
		Helper.snakeWidth = image.getWidth();
		Helper.snakeHeight = image.getHeight();
		w = Helper.headWidth;
		h = Helper.headheight;
		speed=5;
	}

	@Override
	public void paintSelf(Graphics2D g) {
			//面板左上角显示时间和蛇的长度
			g.setFont(new Font("华光美黑_CNKI", Font.BOLD, 20));
			g.drawString("蛇的长度：", 5, 30);
			g.drawString(len+"", 100, 30);
			g.drawString("当前分数：", 5, 55);
			g.drawString(len*3+"", 100, 55);
			//鼠标单击  画初始图片
			if(initStarted == false) {
			drawInitPicture(g);
			g.drawImage(imgUtil.getImg(Helper.initStartPath), 0, 0,Helper.width,Helper.height, null);
			}else {	//启动绘制buff的线程
				if (!MainController.getCon().getDisTH().isAlive()) {
					MainController.getCon().getDisTH().start();
				}
			}
			move();
			//换装画蛇
			if (changeShirt==false&&len<60) {
				drawSnake.drawSnake(g, isInitStarted(), right, left, up, down, image, d, snakeX[0], snakeY[0], len, snakeX, snakeY);
			}else if(changeShirt==true&&len<60){
				drawSnake.drawSnake(g, isInitStarted(), right1, left1, up1, down1, image1, d, snakeX[0], snakeY[0], len, snakeX, snakeY);
			}else if (len>=60) {
				drawSnake.drawSnake(g, isInitStarted(), right2, left2, up2, down2, image2, d, snakeX[0], snakeY[0], len, snakeX, snakeY);
			}
				if (haveRound) {
					//如果有保护罩
					g.drawImage(imgUtil.getImg(Helper.RoundPath), snakeX[0]-60, snakeY[0]-60, 170, 170, null);
				}
			

			//判断撞墙
			checkBounds();
			//判断咬到身体
			MainController.getCon().isFailed();
			//游戏结束
			showOver(g);
	}

	@Override
	public void move() {
		//重绘x,y坐标 
		x = snakeX[0]; y = snakeY[0];
		//按下空格键暂停
		if (isStarted&&!isFailed&&initStarted) {
			for(int i = len-1; i>0;i--) {
				snakeX[i] = snakeX[i-1];
				snakeY[i] = snakeY[i-1];
			}
			//旋转前进方向 前进 冲冲冲！
			if (d == Direction.R) {
				snakeX[0] +=Helper.snakeWidth;
			}else if (d == Direction.L) {
				snakeX[0] -=Helper.snakeWidth;
			}else if (d == Direction.U) {
				snakeY[0] -=Helper.snakeHeight;
			}else if (d == Direction.D) {
				snakeY[0] +=Helper.snakeHeight;
			}
		}
	}
		
	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}
	
	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}
	
	public boolean isChangeShirt() {
		return changeShirt;
	}

	public void setChangeShirt(boolean changeShirt) {
		this.changeShirt = changeShirt;
	}

	@Override
	public void checkBounds() {
		if (haveRound == false) {
			if (snakeX[0]>Helper.width-Helper.headWidth||snakeX[0]<=0||snakeY[0]<=0||snakeY[0]>=Helper.height-Helper.snakeHeight) {
				isFailed = true;
			}	
		}else {//haveRound == true
			if (snakeX[0]>Helper.width-Helper.headWidth) {
				snakeX[0]=Helper.width-Helper.headWidth;
			}else if (snakeX[0]<=0) {
				snakeX[0] = 0;
			}else if (snakeY[0]<=0) {
				snakeY[0] = 0;
			}else if (snakeY[0]>=Helper.height-Helper.snakeHeight) {
				snakeY[0] = Helper.height-Helper.snakeHeight;
			}
		}
	}
	//画加载图片
	public void drawInitPicture(Graphics2D gg) {
		if (ClickCount>0) {
		//	gg.drawImage(imgUtil.getImg(Helper.initStartPath), 0, 0, null);
			initStarted = true;
		}
	}
	//给蛇进行初始化
	public void initSnake() {
		d = Direction.R;
		snakeX[0] = 300;
		snakeY[0] = 300;
		snakeX[1] = snakeX[0]-Helper.bodyWidth;
		snakeY[1] = snakeY[0];
		snakeX[2] = snakeX[1]-Helper.bodyHeight;
		snakeY[2] = snakeY[0];
	}
	public void showOver(Graphics2D g) {
		if (isFailed==true) {
			g.drawImage(imgUtil.getImg("img/over.png"), 200, 80,600,400, null);
			MainController.getCon().getRpTH().interrupt();
			MainController.getCon().getBgm().interrupt();
			//向数据库内添加玩家分数！
			MainController.getCon().inPoint(MainController.getCon().getID(MainController.getCon().getUniqueName()).getPid());	
			//打开分数窗口
			Score score = new Score();
			score.init();
		}
	}

	public int[] getSnakeX() {
		return snakeX;
	}

	public void setSnakeX(int[] snakeX) {
		this.snakeX = snakeX;
	}
	

	public boolean isInitStarted() {
		return initStarted;
	}

	public void setInitStarted(boolean initStarted) {
		this.initStarted = initStarted;
	}

	public int[] getSnakeY() {
		return snakeY;
	}

	public void setSnakeY(int[] snakeY) {
		this.snakeY = snakeY;
	}

	public boolean isFailed() {
		return isFailed;
	}

	public void setFailed(boolean isFailed) {
		this.isFailed = isFailed;
	}

	public int getClickCount() {
		return ClickCount;
	}

	public void setClickCount(int clickCount) {
		ClickCount = clickCount;
	}

	public boolean isHaveRound() {
		return haveRound;
	}

	public void setHaveRound(boolean haveRound) {
		this.haveRound = haveRound;
	}
	
}
