package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class TrumpWall extends GameObject{
	
	Player player;
	
	private BufferedImage trumpw;
	private Handler handler;
	private int licznik=0;
	private int delay = 0;
	
	public TrumpWall(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	public void tick() {
		licznik++;
		if(licznik == 500){
			handler.removeObject(this);
		}
		collision();
	}
	
	public void collision(){
		/*for (int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.SausageHillary){
				if (getBounds().intersects(tempObject.getBounds())){
					SausageHillary.touched = true;
				}
			}
			
		}*/
	}

	public void render(Graphics g) {
		BufferedImageLoader loader = new BufferedImageLoader();
		trumpw = loader.loadImage("/walltrump.png");
		g.drawImage(trumpw, (int)x, (int)y,  null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,72,250);
	}

}
