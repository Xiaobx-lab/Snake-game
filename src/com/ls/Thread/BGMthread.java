package com.ls.Thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class BGMthread extends Thread{
	
	Player p;
	boolean isRun = true;
	public BGMthread() {
	}
	
	@Override
	public void run() {
		while(isRun) {
			try {
				p = new Player(new FileInputStream(new File("MP3/bgm.mp3")));
				p.play();
			} catch (FileNotFoundException | JavaLayerException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void interrupt() {
		p.close();
		isRun = false;
	}
}
