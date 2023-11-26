package com.ls.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * 读取一张图片
 * @author 不点
 *
 */
public  class imgUtil {
	public static BufferedImage getImg(String path) {
		try {
			BufferedImage image = ImageIO.read(new File(path));
			return image;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
