package com.ls.crash;

import java.util.Timer;
import java.util.TimerTask;

import com.ls.controller.MainController;
import com.ls.model.GameObject;
import com.ls.model.Snake;
import com.ls.model.food.food;
import com.ls.model.food.point3;
import com.ls.model.food.point4;
import com.ls.model.food.point5;

public class HeroFoodCrash {
	
	//吃buff碰撞
	public static boolean testCrash(GameObject o1, GameObject o2) {
		if (o1 instanceof Snake && o2 instanceof food) {
			if (o1.getRect().intersects(o2.getRect())) {
				MainController.getCon().removeObj(o2);
				return true;
			}
		}
			return false;
	}
	
	//longer
	public static boolean test1Crash(GameObject o1, GameObject o2) {
		if (o1 instanceof Snake && o2 instanceof point5) {
			if (o1.getRect().intersects(o2.getRect())) {
				MainController.getCon().removeObj(o2);
				return true;
			}
		}
			return false;
	}
	
	//换皮肤
	public static void test2Crash(GameObject o1, GameObject o2) {
		if (o1 instanceof Snake && o2 instanceof point4) {
			if (o1.getRect().intersects(o2.getRect())) {
				MainController.getCon().removeObj(o2);
				MainController.getCon().getSnake().setChangeShirt(true);
			}
		}
	}
	
	//保护罩
	public static void testRound(GameObject o1, GameObject o2) {
		class task extends TimerTask{
			//必须实现方法run()
			        public void run(){
			         //里面写你要在5秒后执行的代码。
			        	MainController.getCon().getSnake().setHaveRound(false);
			        }
			}
			if (o1 instanceof Snake && o2 instanceof point3) {
				if (o1.getRect().intersects(o2.getRect())) {
					//和相应的buff撞了以后 产生保护罩
				MainController.getCon().getSnake().setHaveRound(true);
					//五秒后将保护罩关掉 PERFECT!
				Timer t=new Timer();
				task tt=new task();
				t.schedule(tt,8000);//5000单位是毫秒=5秒
			}
		}
	}
}
