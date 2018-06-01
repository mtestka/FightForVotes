package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import main.Game.STATE;

public class SausageHillary extends GameObject{
	
	Player player;
	HUD hud;
	
	private BufferedImage spriter;
	private Handler handler;
	private int licznik=0;
	private int delay = 0;
	public static boolean touched = false;
	
	public SausageHillary(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	public void tick() {
		x+=velX;
		y+=velY;
		licznik+=10;
		delay = (int)((int)(licznik/18)%18);
		collision();
		velX = -20;
	}
	
	public void collision(){
		for (int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.Player){
				if (getBounds().intersects(tempObject.getBounds())){
					HUD.TrumpVotes1 = 100;
					HUD.dubel++;
					handler.removeObject(this);
					touched = true;
				}
			}
			if (tempObject.getID() == ID.Wall){
				if (getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);
					touched = true;
					System.out.println("2 touched");
				}
			}
		}
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		try{
			BufferedImageLoader loader = new BufferedImageLoader();
			spriter = loader.loadImage("/kielbasawyb1.png");
		}catch(Exception e){
			e.printStackTrace();
		}
		g2d.drawImage(spriter, (int)x, (int)y, null);
		//g2d.rotate(Math.toRadians(licznik));
	}
	
	public boolean isTouched(){
		return touched;
	}
	
	public void setTouched(boolean touch){
		this.touched = touch;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,78,78);
	}

}
