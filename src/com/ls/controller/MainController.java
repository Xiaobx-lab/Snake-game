package com.ls.controller;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.druid.sql.visitor.functions.If;
import com.ls.Thread.BGMthread;
import com.ls.Thread.DispatcherThread;
import com.ls.Thread.repaintThread;
import com.ls.bean.Player;
import com.ls.bean.Result;
import com.ls.crash.HeroFoodCrash;
import com.ls.dao.playDao;
import com.ls.model.BG;
import com.ls.model.GameObject;
import com.ls.model.Snake;
import com.ls.model.food.point;
import com.ls.util.Direction;
import com.ls.view.gamePanel;

public class MainController {
	//关联Dao层
	private BG bg;
	private String uniqueName;
	private point buff;
	private Date nowDate;
	private repaintThread rpTH;
	private DispatcherThread disTH;
	private Snake snake ;
	private BGMthread bgm;
	playDao pd = new playDao();
	int clickCount;
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	private static MainController gameController = new MainController();
	//控制层关联所有的游戏物体
	List<GameObject> objs = new ArrayList<GameObject>();
	public static MainController getCon() {
		return gameController;
	}
	
	private  MainController() {
		
	}
	
	//获取当前时间
	public String getNowDay() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
       return df.format(new Date());
	}
	
	//登录
	public Player login(String user, String pass) {
		Player p = new Player();
		p.setPname(user);
		p.setPass(pass);
		return pd.login(p);
	}
	
	//注册
	public int register(String user, String nickName, String pass) {
		Player player = new Player();
		player.setPname(user);
		player.setPnick(nickName);
		player.setPass(pass);
		return pd.register(player);
	} 
	
	//查询玩家当前id
	public Player getID(String user) {
		Player player = new Player();
		player.setPname(user);
		return pd.searchID(player);
	}
	
	//添加游戏分数到数据库
	public int inPoint(int pid) {
		Result result = new Result();
		result.setPid(pid);
		return pd.addPoint(result);
	}
	
	//让所有游戏物体绘制自己到面板上
	public void gameObjectPaint(Graphics2D g) {
		for(int i = 0;i<objs.size();i++) {
			GameObject o1 = objs.get(i);
				o1.paintSelf(g);
				for(int j=0;j<objs.size();j++) {
					GameObject o2 = objs.get(j);
					HeroFoodCrash.testRound(o1, o2);
					HeroFoodCrash.test2Crash(o1, o2);//检测换皮肤
					if(HeroFoodCrash.testCrash(o1, o2)) {//检测加尾巴
						if (HeroFoodCrash.test1Crash(o1, o2)) {
							snake.setLen(snake.getLen()+3);
						}
					snake.setLen(snake.getLen()+1);
				}
			}
		}
	}
	
	public Snake getSnake() {
		return snake;
	}
	

	public BGMthread getBgm() {
		return bgm;
	}

	public void setBgm(BGMthread bgm) {
		this.bgm = bgm;
	}

	public repaintThread getRpTH() {
		return rpTH;
	}

	public void setRpTH(repaintThread rpTH) {
		this.rpTH = rpTH;
	}
	
	public Date getNowDate() {
		return nowDate;
	}

	public void setNowDate(Date nowDate) {
		this.nowDate = nowDate;
	}

	//添加游戏物体
	public void addObj(GameObject object) {
		objs.add(object);
	}
	
	//移除游戏物体
	public void removeObj(GameObject object) {
		objs.remove(object);
	}
	
	//初始化基本物体 背景
	public void init(gamePanel panel ) {
	//	clickCount;
		bg = new BG();
		snake = new Snake();
		rpTH = new repaintThread(panel);
		rpTH.start();
		bgm = new BGMthread();
		bgm.start();
		disTH = new DispatcherThread();
		//disTH.start();
	}
	
	//关闭线程
	public void shutDownThread() {
		rpTH.interrupt();
		bgm.interrupt();
		disTH.interrupt();
	}
	
	//按
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
//		case KeyEvent.VK_J:
//			snake.setLen(snake.getLen()+1);
//			break;
		case KeyEvent.VK_SPACE :
			//按空格键暂停
			snake.setStarted(!snake.isStarted());
			break;
		default:
			break;
		}
		tellSnakeDirection();
	}
	
	//释放
	public void keyRealeased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_W:
			up=false;
			break;
		case KeyEvent.VK_S:
			down=false;
			break;
		case KeyEvent.VK_A:
			left=false;
			break;
		case KeyEvent.VK_D:
			right=false;
			break;
		default:
			break;
		}
		tellSnakeDirection();
	}
	
	//点击
	public void mouseclick(MouseEvent e) {
		clickCount = e.getClickCount();
		tellSnakeClickCount();
	}
	
	//告诉蛇点击次数
	public void tellSnakeClickCount() {
		snake.setClickCount(clickCount);
	}
	
	//告诉蛇方向
	public void tellSnakeDirection() {
		 
		if (left&&!right&&!up&&!down) {
			snake.setD(Direction.L);
		}else if(!left&&right&&!up&&!down){
			snake.setD(Direction.R);
		}else if(!left&&!right&&up&&!down){
			snake.setD(Direction.U);
		}else if(!left&&!right&&!up&&down){
			snake.setD(Direction.D);
		}
	}

	public point getBuff() {
		return buff;
	}
	
	
	public String getUniqueName() {
		return uniqueName;
	}

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}
	
	public DispatcherThread getDisTH() {
		return disTH;
	}

	public void setDisTH(DispatcherThread disTH) {
		this.disTH = disTH;
	}

	//判断贪吃蛇头和身体是否相碰
	public void isFailed() {
		for(int i = 1;i<snake.getLen();i++) {
			if (snake.getSnakeX()[i]==snake.getSnakeX()[0]&&snake.getSnakeY()[i]==snake.getSnakeY()[0]) {
			//	System.out.println("hahaha 你死了！");
				if (!snake.isHaveRound()) {
					snake.setFailed(true);					
				}
			}
		}
	
	}
}
