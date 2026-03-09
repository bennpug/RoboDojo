package RoboDojo;

import javax.swing.JFrame;

public class GameLauncher2{
 
    public static void main(String[] args) {
    	//create window
        JFrame window = new JFrame("Robo Dojo");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit
        window.setSize(800, 600); // The size of screen
        window.setResizable(false); 

        //trigger Combat in the window
        Background2 gamePanel = new Background2();
        window.add(gamePanel);
        window.setVisible(true);
    }
} 