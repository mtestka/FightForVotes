package main;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = -6058755522142820984L;

	public static final int SCALE = 1;
	public static final int WIDTH = 1280/SCALE , HEIGHT = 720/SCALE;
	
	private Font font;
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private HUD hud;
	private KeyInput keyinput;
	private static BufferedImage bg;
	private static BufferedImage bgHELP1;
	private static BufferedImage bgHELP2;
	private static BufferedImage bggame;
	private static BufferedImage skilltrump1;
	private static BufferedImage skilltrump2;
	private static BufferedImage skilltrump3;
	private static BufferedImage skilltrump11;
	private static BufferedImage skilltrump12;
	private static BufferedImage skilltrump13;
	private static BufferedImage skillhillary1;
	private static BufferedImage skillhillary2;
	private static BufferedImage skillhillary3;
	private static BufferedImage skillhillary11;
	private static BufferedImage skillhillary12;
	private static BufferedImage skillhillary13;
	private BufferedImage[] spriter;
	
	public boolean clickedz = false;
	public boolean clickedx = false;
	public boolean clickedc = false;
	public boolean clickedz2 = false;
	public boolean clickedx2 = false;
	public boolean clickedc2 = false;
	public boolean clickedz1 = false;
	public boolean clickedx1 = false;
	public boolean clickedc1 = false;
	
	public AudioPlayer bgMusic;
	public AudioPlayer bgZ;
	public AudioPlayer bgX;
	public AudioPlayer bgC;
	public AudioPlayer bgQ;
	public AudioPlayer bgW;
	public AudioPlayer bgE;
	
	int currentChoice = 0;
	
	String[] options = {
			"Start",
			"Help",
			"Quit"
	};
	
	public enum STATE{
		Menu,
		LEVEL1,
		HELP1,
		HELP2,
		NotAlive;
	};
	
	public static STATE gameState = STATE.Menu;
	
	public static BufferedImage sheet1;
	public static BufferedImage bck2;
	
	public Game(){
		if(gameState == STATE.Menu){
			BufferedImageLoader loader = new BufferedImageLoader();
			bg = loader.loadImage("/main1.png");
			bgHELP1 = loader.loadImage("/bgood3.png");
			bgHELP2 = loader.loadImage("/bgood2.png");
			bggame = loader.loadImage("/tlo1.png");
			skilltrump1 = loader.loadImage("/Trumpskillone.png");
			skilltrump2 = loader.loadImage("/Trumpskilltwo.png");
			skilltrump3 = loader.loadImage("/Trumpskillthreep.png");
			skilltrump11 = loader.loadImage("/Trumpskilloneclick.png");
			skilltrump12 = loader.loadImage("/Trumpskilltwoclick.png");
			skilltrump13 = loader.loadImage("/Trumpskillthreepclick.png");
			skillhillary1 = loader.loadImage("/Hillaryskillone.png");
			skillhillary2 = loader.loadImage("/Hillaryskilltwo.png");
			skillhillary3 = loader.loadImage("/Hillaryskillthree.png");
			skillhillary11 = loader.loadImage("/Hillaryskilloneclick.png");
			skillhillary12 = loader.loadImage("/Hillaryskilltwoclick.png");
			skillhillary13 = loader.loadImage("/Hillaryskillthreeclick.png");
			spriter = new BufferedImage[18];
				
			spriter[0] = loader.loadImage("/kielbasawyb1.png");
			spriter[1] = loader.loadImage("/kielbasawyb2.png");
			spriter[2] = loader.loadImage("/kielbasawyb3.png");
			spriter[3] = loader.loadImage("/kielbasawyb4.png");
			spriter[4] = loader.loadImage("/kielbasawyb5.png");
			spriter[5] = loader.loadImage("/kielbasawyb6.png");
			spriter[6] = loader.loadImage("/kielbasawyb7.png");
			spriter[7] = loader.loadImage("/kielbasawyb8.png");
			spriter[8] = loader.loadImage("/kielbasawyb9.png");
			spriter[9] = loader.loadImage("/kielbasawyb10.png");
			spriter[10] = loader.loadImage("/kielbasawyb11.png");
			spriter[11] = loader.loadImage("/kielbasawyb12.png");
			spriter[12] = loader.loadImage("/kielbasawyb13.png");
			spriter[13] = loader.loadImage("/kielbasawyb14.png");
			spriter[14] = loader.loadImage("/kielbasawyb15.png");
			spriter[15] = loader.loadImage("/kielbasawyb16.png");
			spriter[16] = loader.loadImage("/kielbasawyb17.png");
			spriter[17] = loader.loadImage("/kielbasawyb18.png");
		}
		bgMusic = new AudioPlayer("/sounds/yankee.mp3");
		bgZ= new AudioPlayer("/sounds/walltrump.mp3");
		bgX= new AudioPlayer("/sounds/taxestrump.wav");
		bgC= new AudioPlayer("/sounds/trumpfbi.wav");
		bgQ= new AudioPlayer("/sounds/trumpphonecall.wav");
		bgW= new AudioPlayer("/sounds/hillaryhurtstrumpwithtaxes.wav");
		bgE= new AudioPlayer("/sounds/hillarysupport.wav");
		bgMusic.loop();
		font = new Font ("Arial", Font.PLAIN, 60);
		handler = new Handler();
		hud = new HUD();
		
		this.addKeyListener(new KeyInput(handler, this));
		
		new Window(WIDTH, HEIGHT, "Fight for votes", this);
		handler.addObject(new Player2(1187,400,ID.Player2, handler));
		handler.addObject(new Player(31,355,ID.Player, handler));
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1){
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println(System.nanoTime());
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick(){
		handler.tick();
		if(gameState == STATE.LEVEL1){
			hud.tick();
			bgMusic.stop();
			if(clickedz){
				bgZ.play();
			}
			if(clickedz2){
				clickedz2 = false;
			}
			if(clickedx){
				bgX.play();
			}
			if(clickedc){
				bgC.play();
			}
			if(clickedc2){
				hud.setTrumpMoney(hud.getTrumpMoney()-300);
				clickedc2 = false;
			}
			if(clickedz1){
				bgQ.play();
			}
			if(clickedx1){
				bgW.play();
			}
			if(clickedc1){
				bgE.play();
			}
			/*if(keyinput.ist){
				handler.addObject(new SausageHillary(1100,450, ID.SausageHillary, handler));
			}*/
		}
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		if(gameState == STATE.Menu){
			g.drawImage(bg, 0, 0, null);
			g.setFont(font);
			for(int i=0; i<options.length;i++){
				if(i==currentChoice){
					g.setColor(Color.BLACK);
				}else{
					g.setColor(Color.red);
				}
				g.drawString(options[i], 600, 400 + i*80);
			}
		}
		if(gameState == STATE.HELP1){
			g.drawImage(bgHELP1, 0, 0, null);
		}
		if(gameState == STATE.HELP2){
			g.drawImage(bgHELP2, 0, 0, null);
		}
		if(gameState == STATE.LEVEL1){
			g.drawImage(bggame, 0, 0, null);
			handler.render(g);
			hud.render(g);
			g.drawImage(skilltrump1, 30, 580, null);
			g.drawImage(skilltrump2, 125, 580, null);
			g.drawImage(skilltrump3, 220, 580, null);
			g.drawImage(skillhillary1, 984, 580, null);
			g.drawImage(skillhillary2, 1079, 580, null);
			g.drawImage(skillhillary3, 1174, 580, null);
			if(clickedz){
				g.drawImage(bggame, 0, 0, null);
				handler.render(g);
				hud.render(g);
				g.drawImage(skilltrump11, 30, 580, null);
				g.drawImage(skilltrump2, 125, 580, null);
				g.drawImage(skilltrump3, 220, 580, null);
				g.drawImage(skillhillary1, 984, 580, null);
				g.drawImage(skillhillary2, 1079, 580, null);
				g.drawImage(skillhillary3, 1174, 580, null);
			}
			if(clickedx){
				g.drawImage(bggame, 0, 0, null);
				handler.render(g);
				hud.render(g);
				g.drawImage(skilltrump1, 30, 580, null);
				g.drawImage(skilltrump12, 125, 580, null);
				g.drawImage(skilltrump3, 220, 580, null);
				g.drawImage(skillhillary1, 984, 580, null);
				g.drawImage(skillhillary2, 1079, 580, null);
				g.drawImage(skillhillary3, 1174, 580, null);
			}
			if(clickedc){
				g.drawImage(bggame, 0, 0, null);
				handler.render(g);
				hud.render(g);
				g.drawImage(skilltrump1, 30, 580, null);
				g.drawImage(skilltrump2, 125, 580, null);
				g.drawImage(skilltrump13, 220, 580, null);
				g.drawImage(skillhillary1, 984, 580, null);
				g.drawImage(skillhillary2, 1079, 580, null);
				g.drawImage(skillhillary3, 1174, 580, null);
			}
			if(clickedz1){
				g.drawImage(bggame, 0, 0, null);
				handler.render(g);
				hud.render(g);
				g.drawImage(skilltrump1, 30, 580, null);
				g.drawImage(skilltrump2, 125, 580, null);
				g.drawImage(skilltrump3, 220, 580, null);
				g.drawImage(skillhillary11, 984, 580, null);
				g.drawImage(skillhillary2, 1079, 580, null);
				g.drawImage(skillhillary3, 1174, 580, null);
			}
			if(clickedx1){
				g.drawImage(bggame, 0, 0, null);
				handler.render(g);
				hud.render(g);
				g.drawImage(skilltrump1, 30, 580, null);
				g.drawImage(skilltrump2, 125, 580, null);
				g.drawImage(skilltrump3, 220, 580, null);
				g.drawImage(skillhillary1, 984, 580, null);
				g.drawImage(skillhillary12, 1079, 580, null);
				g.drawImage(skillhillary3, 1174, 580, null);
			}
			if(clickedc1){
				g.drawImage(bggame, 0, 0, null);
				handler.render(g);
				hud.render(g);
				g.drawImage(skilltrump1, 30, 580, null);
				g.drawImage(skilltrump2, 125, 580, null);
				g.drawImage(skilltrump3, 220, 580, null);
				g.drawImage(skillhillary1, 984, 580, null);
				g.drawImage(skillhillary2, 1079, 580, null);
				g.drawImage(skillhillary13, 1174, 580, null);
			}
		}
		g.dispose();
		bs.show();
		//render menu i game
		/*handler.render(g);
		if(paused){
			g.setColor(Color.white);
			g.drawString("PAUSED", 100, 100);
		}
		if(gameState==STATE.Game){
			hud.render(g);
		}else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Help || gameState == STATE.Select){
			menu.render(g);
		}
		g.dispose();
		bs.show();*/
	}
	
	void select(){
		if(currentChoice == 0){
			//start
			gameState = STATE.LEVEL1;
		}
		if(currentChoice == 1){
			//help
			gameState = STATE.HELP1;
		}
		if(currentChoice == 2){
			//quit
			System.exit(0);
		}
	}
	
	public BufferedImage[] getSpriter(){
		return spriter;
	}
	
	public static float clamp(float var, float min, float max){
		if(var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else 
			return var;
	}
	public static void main (String args[]){
		new Game();
	}

}
