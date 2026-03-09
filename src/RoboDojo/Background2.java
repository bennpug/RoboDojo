package RoboDojo;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Background2 extends JPanel implements ActionListener, KeyListener {
    private Player2 p1;
    private Player2 p2;
    private Timer timer;
    private HUD2 hud;
    private int shakeFrame;
    private int frame;
    private int second = 0;
    private int min = 0;
    private boolean zero;
    private int shakeX = 0;
    private int shakeY = 0;

    Background2() {
        this.setFocusable(true); 
        this.addKeyListener(this); 
        p1 = new Player2(1, 100, 400);
        p2 = new Player2(2, 600, 400);
        timer = new Timer(16, this);
        timer.start();
        hud = new HUD2();
        // Image loading completely removed
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame++; 
        if (shakeFrame > 0) {
            shakeX = (int) (Math.random() * 30 - 15);
            shakeY = (int) (Math.random() * 30 - 15);
            shakeFrame--;
        } else {
            shakeX = 0;
            shakeY = 0;
        }
        if (frame == 60) {
            frame = 0;
            second++;
            if (second == 60) {
                second = 0;
                min++;
            }
            if (second < 10) {
                zero = true;
            } else {
                zero = false;
            }
        }
        p1.update();
        p2.update();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        p1.keyPressed(e.getKeyCode());
        p2.keyPressed(e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_F) {
            if (p1.attack(p2) == true) {
                shakeFrame += 10;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            if (p2.attack(p1) == true) {
                shakeFrame += 10;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        p1.keyReleased(e.getKeyCode());
        p2.keyReleased(e.getKeyCode());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(shakeX, shakeY); 
        
        // Removed the bgImage drawing line completely
        
        p1.draw(g, p1.getHitbox());
        p2.draw(g, p2.getHitbox());
        if (p1.getIsAttacking()) {
            p1.draw(g, p1.getAttack());
        }
        if (p2.getIsAttacking()) {
            p2.draw(g, p2.getAttack());
        }
        g2d.translate(-shakeX, -shakeY); 
        
        // draw floor
        g.setColor(Color.BLACK);
        g.fillRect(0, 500, 800, 400);
        hud.draw(g, p1, p2, min, second, zero); 
        
        if (p1.getHealth() <= 0) {
            hud.win(g, p2);
            timer.stop();
        }
        if (p2.getHealth() <= 0) {
            hud.win(g, p1);
            timer.stop();
        }
    }
}