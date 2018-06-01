package main;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class HUD {
	
	SausageHillary sh;
	
	public static double TrumpVotes = 1000;
	public static double TrumpVotes1 = 0;
	public static double HillaryVotes = 1000;
	public static double HillaryVotes3 = 0;
	public static double TrumpSausagescost = 10;
	public static double HillarySausagescost = 10;
	public static int dubel = 0;
	public static int dubelh = 0;
	public static double MoneyTrump = 100;
	public double MoneyHillary = 0;
	public static double HillaryVotes1 = 1000;
	private int level = 1;
	public static BufferedImage vote1;
	public static BufferedImage headhillary;
	public static BufferedImage headtrump;
	private Font font;
	public boolean nt2 = false;
	public static boolean empty = false;
	
	public void tick(){
		MoneyTrump+=0.1;
		TrumpVotes = 1000 - dubel*TrumpVotes1;
		HillaryVotes1 = 1000 - dubelh*HillaryVotes3;
		HillaryVotes = 1000 + dubelh*HillaryVotes3;
		/*TrumpVotes--;
		HillaryVotes++;
		if(HillaryVotes > 2000){
			HillaryVotes = 2000;
		}
		HillaryVotes1--;*/
		if(HillaryVotes > 2000){
			HillaryVotes = 2000;
		}
		if(TrumpVotes<0){
			TrumpVotes = 0;
		}
		if(HillaryVotes1<0){
			HillaryVotes1 = 0;
		}else if(HillaryVotes1 == 0){
			Player2.hillarydead = true;
		}
		if(TrumpSausagescost < 0){
			TrumpSausagescost = 0;
		}
		if(HillarySausagescost < 0){
			HillarySausagescost = 0;
		}
		MoneyHillary+=0.1;
	}
	
	public void render(Graphics g){
		BufferedImageLoader loader = new BufferedImageLoader();
		headtrump = loader.loadImage("/trumpheadsmall.png");
		headhillary = loader.loadImage("/hillaryheadsmall.png");
		g.drawImage(headhillary, 1173, 0, null);
		g.drawImage(headtrump, 15, 0, null);
		g.setColor(Color.white);
		font = new Font ("Arial", Font.PLAIN, 40);
		g.setFont(font);
		g.drawString("Trump Money: "+(int)MoneyTrump, 31, 280);
		g.setColor(Color.red);
		g.fillRect(70, 131, (int) (0.3*TrumpVotes), 78);
		g.fillRect((int)(0.3*HillaryVotes) + 612, 131, (int) (0.3*HillaryVotes1), 78);
		g.fillOval((int)(0.3*TrumpVotes)+31, 131, 78, 78);
		g.fillOval((int)(0.3*HillaryVotes) + 573, 131, 78, 78);
		vote1 = loader.loadImage("/vote1.png");
		g.drawImage(vote1, 31, 131, null);
		g.drawImage(vote1, 1173, 131, null);
		
		if(empty){
			g.setColor(Color.red);
			g.drawString("Not enough money", 30, 245);
		}
	}
	
	public void setTrumpMoney(double MoneyTrump){
		this.MoneyTrump=MoneyTrump;
	}
	
	public void setTrumpVotes(double TrumpVotes){
		this.TrumpVotes = TrumpVotes;
	}
	
	public void setHillaryVotes(double HillaryVotes){
		this.HillaryVotes = HillaryVotes;
	}
	
	public double getTrumpVotes(){
		return TrumpVotes;
	}
	
	public double getHillaryVotes(){
		return HillaryVotes;
	}
	
	public double getTrumpMoney(){
		return MoneyTrump;
	}
	
	public void setHillaryMoney(double MoneyHillary){
		this.MoneyHillary=MoneyHillary;
	}
	
	public double getHillaryMoney(){
		return MoneyHillary;
	}
	
	public int getLevel(){
		return level;
	}
	
	public void setLevel(int level){
		this.level=level;
	}
	
}
