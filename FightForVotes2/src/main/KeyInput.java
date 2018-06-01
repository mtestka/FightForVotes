package main;


import java.awt.RenderingHints.Key;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.Game.STATE;

public class KeyInput extends KeyAdapter {
	
	SausageHillary sh;
	SausageTrump st;
	Player player1;
	Player2 player2;
	HUD hud;
	public AudioPlayer bgMusic;
	private Handler handler;
	private boolean [] keyDown = new boolean[3];
	public static boolean is = true;
	public static boolean ist = true;
	private int pos = 10;
	private GameObject player;
	
	Game game;
	
	public KeyInput(Handler handler, Game game){
		this.handler = handler;
		this.game = game;
		
		for(int i = 0; i < handler.object.size() ; i++){
			if (handler.object.get(i).getID() == ID.Player){
				player = handler.object.get(i);
			}
		}
	}
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(game.gameState == STATE.Menu){
			if(key == KeyEvent.VK_ENTER){
				game.select();
			}
			if(key == KeyEvent.VK_UP){
				game.currentChoice--;
				if(game.currentChoice == -1){
					game.currentChoice = game.options.length - 1;
				}
			}
			if(key == KeyEvent.VK_DOWN){
				game.currentChoice++;
				if(game.currentChoice == game.options.length){
					game.currentChoice = 0;
				}
			}
		}
		if(game.gameState == STATE.HELP1){
			if(key == KeyEvent.VK_RIGHT){
				game.gameState = STATE.HELP2;
			}
			if(key == KeyEvent.VK_BACK_SPACE){
				game.gameState = STATE.Menu;
			}
		}
		if(game.gameState == STATE.HELP2){
			if(key == KeyEvent.VK_LEFT){
				game.gameState = STATE.HELP1;
			}
			if(key == KeyEvent.VK_BACK_SPACE){
				game.gameState = STATE.Menu;
			}
		}
		for (int i = 0; i < handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			if(game.gameState == STATE.LEVEL1 && tempObject.getID() == ID.Player){
				if(key == KeyEvent.VK_A){
					tempObject.setVelX(-5);
					Player.trumpright = false;
				}
				if(key == KeyEvent.VK_D){
					tempObject.setVelX(5);
					Player.trumpright = true;
				}
			}
			if(game.gameState == STATE.LEVEL1 && tempObject.getID() == ID.Player2){
				if(!Player2.hillarydead){
				if(key == KeyEvent.VK_LEFT){
					tempObject.setVelX(-5);
					Player2.hillaryleft = true;
				}
				if(key == KeyEvent.VK_RIGHT){
					tempObject.setVelX(5);
					Player2.hillaryleft = false;
				}}
			}
		}
		if(game.gameState == STATE.LEVEL1){
			if(key == KeyEvent.VK_Z){
				if(HUD.MoneyTrump >= 50){
					handler.addObject(new TrumpWall(409,338, ID.Wall, handler));
					HUD.MoneyTrump -= 50;
				}else{
					HUD.empty = true;
				}
				game.clickedz = true;
			}
			if(key == KeyEvent.VK_X){
				game.clickedx = true;
			}
			if(key == KeyEvent.VK_P){
				if(!Player2.hillarydead){
					Player2.hillarydead = true;
				}else{
					Player2.hillarydead = false;
				}
			}
			if(key == KeyEvent.VK_C){
				game.clickedc = true;
			}
			if(key == KeyEvent.VK_COMMA){
				game.clickedz1 = true;
			}
			if(key == KeyEvent.VK_PERIOD){
				game.clickedx1 = true;
			}
			if(key == KeyEvent.VK_SLASH){
				game.clickedc1 = true;
			}
			if(key == KeyEvent.VK_SPACE){
				if(HUD.TrumpSausagescost <= HUD.MoneyTrump){
					player1.clicked = true;
					if(is){
						handler.addObject(new SausageTrump(Player.xtemp + 10,Player.ytemp + 40, ID.SausageTrump, handler));
						if(Player.trumpright){
							SausageTrump.exists = true;
						}else{
							SausageTrump.exists = false;
						}
						is=false;
						HUD.MoneyTrump -= 10;
					}
				}else{
					HUD.empty = true;
				}
			}
			if(key == KeyEvent.VK_CONTROL){
				player2.clickedb = true;
				if(ist){
					ist=false;
					handler.addObject(new SausageHillary(Player2.xtemp,Player2.ytemp, ID.SausageHillary, handler));
				}
			}
			if(key == KeyEvent.VK_W){
				Player.jump = true;
			}
		}
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			if(game.gameState == STATE.LEVEL1 && tempObject.getID() == ID.Player){
				if(key == KeyEvent.VK_A){
					tempObject.setVelX(0);;
				}
				if(key == KeyEvent.VK_D){
					tempObject.setVelX(0);;
				}
			}
			if(game.gameState == STATE.LEVEL1 && tempObject.getID() == ID.Player2){
				if(key == KeyEvent.VK_LEFT){
					tempObject.setVelX(0);
				}
				if(key == KeyEvent.VK_RIGHT){
					tempObject.setVelX(0);
				}
			}
		}
		if(game.gameState == STATE.LEVEL1){
			if(key == KeyEvent.VK_Z){
				game.clickedz = false;
				game.clickedz2 = true;
				HUD.empty = false;
			}
			if(key == KeyEvent.VK_X){
				game.clickedx = false;
			}
			if(key == KeyEvent.VK_C){
				game.clickedc = false;
			}
			if(key == KeyEvent.VK_COMMA){
				game.clickedz1 = false;
			}
			if(key == KeyEvent.VK_PERIOD){
				game.clickedx1 = false;
			}
			if(key == KeyEvent.VK_SLASH){
				game.clickedc1 = false;
			}
			if(key == KeyEvent.VK_SPACE){
				player1.clicked = false;
				is=true;
				HUD.empty = false;
			}
			if(key == KeyEvent.VK_CONTROL){
				player2.clickedb = false;
				ist = true;
			}
		}
	}
}
	
