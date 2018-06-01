package main;


import java.awt.Graphics;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Player extends GameObject {
	
	private ArrayList<SausageTrump> firedSausages = new ArrayList<SausageTrump>();
	Random r = new Random();
	Handler handler;
	private HUD hud;
	public static BufferedImage pl1;
	public static BufferedImage pl1right;
	public static BufferedImage hand1;
	public static BufferedImage hand2;
	public static BufferedImage hand3;
	public static BufferedImage hand4;
	public static boolean clicked = false;
	public static int timer1 = 0;
	public static float xtemp;
	public static float ytemp;
	public static boolean trumpright = true;
	public static boolean jump = false;
	public static boolean jumped = false;
	public static double timer2 = 0;
	public static final double g = 10;
	public static double a = 0;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,77,219);
	}

	public void tick() {
		
		a = (g*Math.pow(timer2, 2))/2;
		y=(float)(355 - velY*timer2 + a);
		x+=velX;
		//y+=velY;
		if(jump){
			timer2 += 0.2;
			/*if(timer2 == 10){
				System.out.println(timer2 + " " + y);
			}*/
		}
		if(timer2 == 14.199999999999982){
			jump = false;
			timer2 = 0;
		}
		velY = 70;
		/*if(jump){
			if(timer1 == 0){
				velY=-15;
			}
			timer1++;
			if(timer1 == 20){
				velY = 15;
				jump = false;
				timer1 = 0;
			}
		}*/
		xtemp = x;
		ytemp = y;
		
		x = Game.clamp(x, 0, Game.WIDTH - 135);
		//y = Game.clamp(y, 0, 355);
		collision();
	}
	
	public void collision(){
		/*for (int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.SausageHillary){
				if (getBounds().intersects(tempObject.getBounds())){
					HUD.TrumpVotes1 = 100;
				}
			}
		}*/
	}
	
	public void render(Graphics g) {
		BufferedImageLoader loader = new BufferedImageLoader();
		pl1 = loader.loadImage("/trumpstand2.png");
		pl1right = loader.loadImage("/trumpstand2left.png");
		hand1 = loader.loadImage("/trumphand.png");
		hand2 = loader.loadImage("/trumphandattack.png");
		hand3 = loader.loadImage("/trumphand2.png");
		hand4 = loader.loadImage("/trumphandattack2.png");
		if(trumpright){
			g.drawImage(pl1,(int)x, (int)y, null);
			if(clicked){
				g.drawImage(hand2, (int)x, (int)y+48, null);
			}else{
				g.drawImage(hand1, (int)x, (int)y+48, null);
			}
		}else{
			g.drawImage(pl1right,(int)x, (int)y, null);
			if(clicked){
				g.drawImage(hand4, (int)x-45, (int)y+48, null);
			}else{
				g.drawImage(hand3, (int)x+35, (int)y+48, null);
			}
		}
	}
	public float getY() {
		return y;
	}
	
	public float getX(){
		return x;
	}
}
