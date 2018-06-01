package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class TrumpFBI extends GameObject{
	
	Player player;
	
	private BufferedImage[] sprites;
	private Handler handler;
	private int licznik=0;
	private int delay = 0;
	
	public TrumpFBI(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	public void tick() {
		licznik++;
		delay = (int)((int)(licznik/18)%18);
	}

	public void render(Graphics g) {
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,78,78);
	}

}
