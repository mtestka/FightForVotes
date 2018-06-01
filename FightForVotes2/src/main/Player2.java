package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player2 extends GameObject {
	Random r = new Random();
	Handler handler;
	public static BufferedImage pl2;
	public static BufferedImage pl2right;
	public static BufferedImage hand1h;
	public static BufferedImage hand2h;
	public static BufferedImage hillarydead1;
	public static boolean clickedb = false;
	public static boolean hillarydead = false;
	public static float xtemp;
	public static float ytemp;
	public static boolean hillaryleft = true;
	

	public Player2(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	
	public Rectangle getBounds(){
		if(hillarydead)
			return new Rectangle((int)x,(int)y,245,74);
		else
			return new Rectangle((int)x,(int)y,51,193);
	}

	public void tick() {
		x+=velX;
		y+=velY;
		
		xtemp = x;
		ytemp = y;
		
		x = Game.clamp(x, 0, Game.WIDTH - 135);
		y = Game.clamp(y, 0, Game.HEIGHT - 61);
	}
	
	public void render(Graphics g) {
		BufferedImageLoader loader = new BufferedImageLoader();
		pl2 = loader.loadImage("/hillarystand2.png");
		pl2right = loader.loadImage("/hillarystand2right.png");
		hand1h = loader.loadImage("/hillaryhand1.png");
		hand2h = loader.loadImage("/hillaryhand2.png");
		hillarydead1 = loader.loadImage("/hillarydead.png");
		if(!hillarydead){
			if(hillaryleft){
				g.drawImage(pl2,(int)x, (int)y, null);
				if(clickedb){
					g.drawImage(hand2h, (int)x-10, (int)y+48, null);
				}else{
					g.drawImage(hand1h, (int)x+20, (int)y+48, null);
				}
			}else{
				g.drawImage(pl2right,(int)x, (int)y, null);
				if(clickedb){
					g.drawImage(hand2h, (int)x-10, (int)y+48, null);
				}else{
					g.drawImage(hand1h, (int)x+20, (int)y+48, null);
				}
			}
		}else{
			g.drawImage(hillarydead1, (int)x-150, (int)y+123, null);
		}
	}
	public float getY() {
		return y;
	}
	
	public float getX(){
		return x;
	}
}
