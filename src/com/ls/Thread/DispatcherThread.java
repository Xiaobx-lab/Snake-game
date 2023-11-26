package com.ls.Thread;
/**
 * 游戏中贪吃蛇食物分发线程 根据每一关的配置文件 在制定的时间内创建出一个指定的游戏物体
 * @author 不点
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.ls.controller.MainController;
import com.ls.util.Helper;

public class DispatcherThread extends Thread{
	private List<String> config = new ArrayList<>();
	Boolean isRun = true;
	int times = 0;
	public DispatcherThread() {
		//source folder 里的文件没有方法使用newfile直接获取，可以使用类加载器读文件读成流的方式
		//类加载器是用来加载类的字节码文件 进内存的对象 可以读取到类路径里的资源（文件）
		InputStream is = DispatcherThread.class.getClassLoader().getResourceAsStream(Helper.LEVEL+".e65");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		try {
			while((line = br.readLine())!=null) {
				config.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while(isRun) {
		//	System.out.println(MainController.getCon().getSnake().isInitStarted());
		//	MainController.getCon().getSnake().isInitStarted();
		//	System.out.println("hhhh");
		//	if (MainController.getCon().getSnake().isInitStarted()==true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				times++;
				for(int i=0;i<config.size();i++) {
					String[]datas = config.get(i).split("=");
					int t = Integer.parseInt(datas[0]);
					if (times==t) {
						int count = Integer.parseInt(datas[2]);
						for(int j = 1;j<=count;j++) {
							//使用反射技术用字符串形式的类名的到这个类名创建的一个对象
							try {
								Class.forName(datas[1]).newInstance();
							} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
								e.printStackTrace();
							}
						}
					}
				}
		}
	}
	@Override
	public void interrupt() {
		isRun = false;
	}
}
