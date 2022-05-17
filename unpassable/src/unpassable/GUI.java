package unpassable;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame {
	private JFrame frame;
	private JPanel panel;
	private Image starting;

	private AffineTransform tx;
	private int x;
	private int y;

	public GUI() {
		x = 110;
		y = 110;
		frame = new JFrame();
		panel = new JPanel();
		frame.add(panel);
		frame.setSize(500, 500);

		starting = Toolkit.getDefaultToolkit().getImage("start.gif");
		frame.setResizable(false);
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y);
		panel.setVisible(true);
		frame.setVisible(true);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = GUI.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	private void update() {
		tx.setToTranslation(x, y); // randomizing x value
		tx.scale(1, 1);
		frame.repaint();
		panel.repaint();
	}

	public static void main(String[] args) {
		GUI g = new GUI();

	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponents(g);
		
		
		g2.drawImage(starting, x, y, this);
		System.out.println("paints");

		update();
	}

	public void start() {

	}

	public void gameOver() {

	}
}
