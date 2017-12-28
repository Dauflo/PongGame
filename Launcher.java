package myPongGame;

import javax.swing.JFrame;

public class Launcher extends JFrame{

	public static final int Hauteur = 630, Largeur = 1000;
	
	private Gestion game;
	
	private Launcher() {
		setTitle("Pong Game");
		setSize(Largeur, Hauteur);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		game = new Gestion();
		
		add(game);
		addKeyListener(game);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Launcher();
	}
	
}
