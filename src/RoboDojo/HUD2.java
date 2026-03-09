package RoboDojo;
import java.awt.Color;
import java.awt.Graphics;

public class HUD2 {
	HUD2(){
	}
	public void draw(Graphics g, Player2 p1, Player2 p2,int min,int second,boolean zero) {
		g.setColor(Color.WHITE);
		g.drawString("Player 1", p1.getX(), p1.getY()-75);//name tags
		g.drawString("Player 2", p2.getX(), p2.getY()-75); 
		
		if(zero) {	g.drawString(min+":0"+second, 400, 90);//timer
		}else {g.drawString(min+":"+second, 400, 90);}
		
		g.setColor(Color.GREEN);
		g.fillRect(50, 50, p1.getHealth()*2, 30);//health bars
		g.fillRect(550, 50, p2.getHealth()*2, 30);
		
		g.setColor(Color.YELLOW);//attack cooldown bars
		g.fillRect(50, 90, p1.getCooldown()*4, 30);
		g.fillRect(550, 90, p2.getCooldown()*4, 30);
		
		g.setColor(Color.CYAN);//dodge cooldown bars
		g.fillRect(50, 130, p1.getCooldown2()*4, 30);
		g.fillRect(550, 130, p2.getCooldown2()*4, 30);
	}
	public void win(Graphics g, Player2 p1) {
		g.setColor(Color.WHITE);
		g.drawString("Player "+p1.getId()+" wins the game!",400, -50);
	}
	}

 