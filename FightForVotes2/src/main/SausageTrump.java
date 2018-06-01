package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class SausageTrump extends GameObject{
	
	Player player;
	Player2 player2;
	
	private BufferedImage sprites;
	private Handler handler;
	private int licznik=0;
	private int delay = 0;
	private boolean touched = false;
	public static boolean exists = false;
	public int  i = 0;
	
	public SausageTrump(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	public void tick() {
		x+=velX;
		y+=velY;
		licznik+=10;
		i+=10;
		delay = (int)((int)(licznik/18)%18);
		collision();
		if(Player.trumpright){
			velX =20;
		}else if(!Player.trumpright && !exists){
			velX = -20;
		}
	}
	
	public void collision(){
		for (int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.Player2){
				if (getBounds().intersects(tempObject.getBounds())){
					HUD.HillaryVotes3 = 100;
					HUD.dubelh++;
					exists = false;
					handler.removeObject(this);
					touched = true;
				}
			}
			if (tempObject.getID() == ID.SausageTrump){
				if (getBounds().intersects(tempObject.getBounds())){
					if(tempObject.getX() < 0 || tempObject.getX() > 1280){
						handler.removeObject(this);
					}
				}
			}
		}
	}

	public void render(Graphics g) {
		try{
			BufferedImageLoader loader = new BufferedImageLoader();
			sprites = loader.loadImage("/kielbasawyb1.png");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		AffineTransform at = AffineTransform.getTranslateInstance(x, y);
		at.rotate(Math.toRadians(i), sprites.getWidth()/2, sprites.getHeight()/2);
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(sprites, at, null);
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
