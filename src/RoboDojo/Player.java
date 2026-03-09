package RoboDojo;
import java.awt.*;
import java.awt.event.KeyEvent;

class Player2 {
	//player 
	private int id;
	private int health=100;
	private int width=50;
	private int height=100;
	//attacking
	private Rectangle attack = new Rectangle(0, 0, 0, 0);
	private boolean forward=true;
	private Rectangle hitbox;
	private int cooldown=0;
	private int cooldown2=0;
	private boolean isAttacking;
	//physics
	private int x;
	private int y;
	private int xVelocity=0;
	private int maxVelocity=15;
    private int yVelocity=0;
    private boolean onGround=true;
    private final int SPEED=5;       
    private final int JUMP_POWER=15; 
    //input
    private boolean right=false;
    private boolean left=false;
    private boolean jump=false;
    private boolean dodge=false;
    
	Player2(int id, int x, int y) {
		this.id=id;
		if(id==2) {forward=false;}
		this.x=x;
		this.y=y;
		this.hitbox=new Rectangle(this.x,this.y,width,height);
	}//positioning
	public void update() {
		if(x<=-55) {x=800;}
		if(x>=805) {x=-50;}
		if(left&&xVelocity>=((-1*maxVelocity))) {//walking
			xVelocity-=SPEED; 
			forward=false;
		}else if(right&&xVelocity<=maxVelocity) {
			xVelocity+=SPEED; 
			forward=true;
		}else {//standing
			xVelocity=0; 
			}
			x+=xVelocity;
		if(jump&&onGround) {//jumping
			yVelocity-=JUMP_POWER;
			onGround=false;
		}
    	if(onGround==false) {//falling
			yVelocity+=1;
		}
		y+=yVelocity;  
		if(y>400){//ground
			yVelocity=0;
			y=400;
			onGround=true;
		}
		if(dodge&& cooldown2==0) {
			if(forward) {
				//dash backwards in the opposite direction
				x-=50;
				cooldown2+=100;
				dodge=false;
			}else {
				x+=100;
				cooldown2+=50;
				dodge=false;
			}
		//move the hitbox
		}
		hitbox.setLocation(x, y);
		//cooldowns
		if(cooldown!=0) {
			cooldown-=1;}
		if(cooldown==45) {
				isAttacking=false;
		}
		if(cooldown==5) {
			isAttacking=false;
	}
		if(cooldown2!=0){
			cooldown2-=1;}
	}
	//punching, called in background
	public boolean attack(Player2 enemy){
		if(cooldown==0 && !isAttacking) {
			isAttacking=true;
			if(forward) {
				attack=new Rectangle(x+50,y+25,100,30);
				cooldown+=50;
			}else if(!forward){
				attack=new Rectangle(x-100,y+25,100,30);
				cooldown+=50;
			}
			if(attack.intersects(enemy.hitbox)) {
				enemy.setHealth(enemy.getHealth()-10);
				enemy.setCooldown(enemy.getCooldown()+10);//enemy paralyzed
				cooldown-=40;//lower cooldown since attack hit
				System.out.println("Player "+id+" hit Player "+enemy.id);
				if(forward) {
					enemy.setX(enemy.getX()+(5*(xVelocity)+25));//attacks have knockback
					enemy.setY(enemy.getY()-((yVelocity)+15));
					
				}else{
					enemy.setX(enemy.getX()-(5*(xVelocity)+25));
					enemy.setY(enemy.getY()-((yVelocity)+15));
				}
				return true;
			}
		}return false;
	}
	//bodies
	public void draw(Graphics g, Rectangle rect) {
		if(id==1) {
			g.setColor(Color.RED);
		}else {
			g.setColor(Color.BLUE);
		}
		g.fillRect(rect.x,rect.y, rect.width,rect.height );

		g.fillRect(rect.x,rect.y, rect.width,rect.height );
	}
	//controls
	public void keyPressed(int k) {
        if (id==1) {
            if(k == KeyEvent.VK_A) {left=true;}
            if(k == KeyEvent.VK_D) {right=true;}
            if(k == KeyEvent.VK_W) {jump=true;}
            if(k==KeyEvent.VK_S) {dodge=true;}
        } else{if (id==2) {
            if(k == KeyEvent.VK_LEFT) {left=true;}
            if(k == KeyEvent.VK_RIGHT) {right=true;}
            if(k == KeyEvent.VK_UP) {jump=true;}
            if(k==KeyEvent.VK_DOWN) {dodge=true;}}}}
	public void keyReleased(int k){
        if (id==1){
            if(k==KeyEvent.VK_A) {left=false;}
            if(k==KeyEvent.VK_D) {right=false;}
            if(k==KeyEvent.VK_W) {jump=false;}
            if(k==KeyEvent.VK_S) {dodge=false;}
            
        } else{if(id==2){
            if(k==KeyEvent.VK_LEFT) {left=false;}
            if(k==KeyEvent.VK_RIGHT) {right=false;}
            if(k==KeyEvent.VK_UP) {jump=false;}
            if(k==KeyEvent.VK_DOWN) {dodge=false;}}}}
    //getters
	public int getHealth() {
        return health;
    }
	public int getCooldown() {
        return cooldown;
    }
	public int getCooldown2() {
        return cooldown2;
    }
    public int getY() {
        return y;
    }
    public int getX() {
        return x;
    }
    public Rectangle getHitbox() {
        return hitbox;
    }
    public Rectangle getAttack() {
        return attack;
    }
    public int getId() {
        return id;
    }
    public boolean getIsAttacking() {
        return isAttacking;
    }
    /*public int getYVelo() {
        return yVelocity;
    }
    public int getXVelo() {
        return xVelocity;
    }*/
    //setters
    public void setHealth(int num) {
        health=num;
    }
    public void setX(int num) {
        x=num;
    }
    public void setY(int num) {
    	y=num;
    	onGround=false;
    }
    public void setCooldown(int num) {
        cooldown=num;
    }
    
		
}