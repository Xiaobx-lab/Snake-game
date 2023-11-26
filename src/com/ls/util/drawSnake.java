package com.ls.util;

import java.awt.Graphics2D;
import java.awt.Image;
/**
 * 画蛇！
 * @author 不点
 *
 */
public class drawSnake {
	public static void drawSnake(Graphics2D g,Boolean isinitStarted,Image right,Image left,Image up,Image down,Image bodyImg,Direction d,int x,int y,int len,int[] snakeX,int[] snakeY) {
		if (isinitStarted) {
			//画蛇头
			if (d == Direction.R) {
				g.drawImage(right, x,y,50,50, null);	
			}else if (d == Direction.L) {
				g.drawImage(left,x,y,50,50, null);	
			}else if (d == Direction.U) {
				g.drawImage(up,x,y, 50,50,null);
			}else if (d == Direction.D) {
				g.drawImage(down,x,y,50,50, null);
			}
			//画身子
				for(int i = 1;i<len;i++) {
					if (d==Direction.R) {
						g.drawImage(bodyImg,snakeX[i]-5,snakeY[i]+12,Helper.snakeWidth,Helper.snakeHeight,null);			
					}else if (d==Direction.U) {
						g.drawImage(bodyImg,snakeX[i]+12,snakeY[i]+20,Helper.snakeWidth,Helper.snakeHeight,null);			
					}else if (d== Direction.L) {
						g.drawImage(bodyImg,snakeX[i]+30,snakeY[i]+12,Helper.snakeWidth,Helper.snakeHeight,null);			
					}else if (d==Direction.D) {
						g.drawImage(bodyImg,snakeX[i]+12,snakeY[i]-10,Helper.snakeWidth,Helper.snakeHeight,null);			
					}
				}
				
			}
	}
}
