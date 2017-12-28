package myPongGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import javax.swing.JPanel;

public class Gestion extends JPanel implements KeyListener {

	private final int largeurRaquette = 10, hauteurRaquette = 100;
	private int xSpeed = 5, ySpeed = 5;

	private int p1Score = 0, p2Score = 0;

	Point raquette1 = new Point(1, 20);
	Point raquette2 = new Point(Launcher.Largeur - 17, 20);
	Point balle;

	// Refresh
	private Timer looper = new Timer(1000 / 50, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			balleMove();
			repaint();
		}
	});

	// Initialisation
	public Gestion() {
		ballePosition();
		looper.start();
	}

	// Position balle
	private void ballePosition() {
		balle = new Point(Launcher.Largeur / 2, (int) (Math.random() * Launcher.Hauteur / 2));
		xSpeed = -xSpeed;
		//ySpeed = -ySpeed;
	}

	// Win
	private void win() {
		if (p1Score == 5) {
			repaint();
			looper.stop();
			javax.swing.JOptionPane.showMessageDialog(null, "Player 1 win !");
			System.exit(1);
		} else if (p2Score == 5){
			repaint();
			looper.stop();
			javax.swing.JOptionPane.showMessageDialog(null, "Player 2 win !");
			System.exit(1);
		}
	}
	
	// Mouvement balle
	private void balleMove() {
		balle.x += xSpeed;
		balle.y += ySpeed;

		if (balle.x < -10) {
			p2Score++;
			win();
			ballePosition();
		} else if (balle.x > Launcher.Largeur) {
			p1Score++;
			win();
			ballePosition();
		}
		if (balle.y < 0 || balle.y > Launcher.Hauteur - 45) {
			ySpeed = -ySpeed;
		}

		if (balle.x + 7 >= raquette2.x && (balle.y > raquette2.y && balle.y < raquette2.y + hauteurRaquette)) {
			xSpeed = -xSpeed;
		}
		if (balle.x <= raquette1.x + 8 && (balle.y > raquette1.y && balle.y < raquette1.y + hauteurRaquette)) {
			xSpeed = -xSpeed;
		}
	}

	// Gestion graphique
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		// Backgroud
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, Launcher.Largeur, Launcher.Hauteur);

		// Raquette 1
		g2d.setColor(Color.WHITE);
		g2d.fillRect(raquette1.x, raquette1.y, largeurRaquette, hauteurRaquette);

		// Raquette 2
		g2d.setColor(Color.WHITE);
		g2d.fillRect(raquette2.x, raquette2.y, largeurRaquette, hauteurRaquette);

		// Balle
		g2d.setColor(Color.YELLOW);
		g2d.fillOval(balle.x, balle.y, 10, 10);

		// Score
		g2d.setColor(Color.WHITE);
		g2d.drawString(p1Score + " | " + p2Score, Launcher.Largeur / 2, 10);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Joueur 1
		if (e.getKeyCode() == KeyEvent.VK_UP && e.getKeyCode() != KeyEvent.VK_DOWN) {
			if (raquette1.y >= 10) {
				raquette1.y -= 10;
			} else {
				raquette1.y = 0;
			}
		} else if (e.getKeyCode() != KeyEvent.VK_UP && e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (raquette1.y < 500) {
				raquette1.y += 10;
				repaint();
			} else {
				raquette1.y = 500;
			}
		}
		repaint();

		// Joueur 2
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD8 && e.getKeyCode() != KeyEvent.VK_NUMPAD2) {
			if (raquette2.y >= 10) {
				raquette2.y -= 10;
			} else {
				raquette2.y = 0;
			}
		} else if (e.getKeyCode() != KeyEvent.VK_NUMPAD8 && e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
			if (raquette2.y < 500) {
				raquette2.y += 10;
				repaint();
			} else {
				raquette2.y = 500;
			}
		}
		repaint();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
