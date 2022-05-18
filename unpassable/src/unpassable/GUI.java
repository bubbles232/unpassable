package unpassable;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author 1861267
 *
 */
public class GUI extends JPanel implements ActionListener {
	private Image starting;
private Image startButton;

		private AffineTransform tx;private int x;
	private int y;
private JButton start;
FlowLayout f = new FlowLayout();
	/**
	 * 
	 */
	public GUI() {
		x = 0;
		y = 0;
		JFrame frame = new JFrame("");
		frame.setSize(500, 500);
start = new JButton();
		starting = Toolkit.getDefaultToolkit().getImage("start.gif");
		startButton = Toolkit.getDefaultToolkit().getImage("start button.jpg");
		f.addLayoutComponent("button1", start);
		
		ImageIcon b = new ImageIcon(startButton);
		start.setIcon(b);
	//	start.setBounds(0, 0,200, 100);
		f.setAlignment(f.CENTER);
		
		frame.setResizable(false);
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y);
		frame.add(this);
		frame.setVisible(true);
		this.add(start);
		start.repaint();
		this.repaint();
		
		
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
		tx.setToTranslation(x, y); 
		tx.scale(1, 1);
		repaint();
		//start.prepareImage(startButton, null);
		//start.setBounds(500, 300, 200, 100);
		//start.setLocation(200, 200);
	}

	public static void main(String[] args) {
		GUI g = new GUI();

	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponents(g);
		
		
		g2.drawImage(starting, x, y, this);
		System.out.println("paints");
		
start.paint(g);

		update();
	}

	public void start() {

	}

	public void gameOver() {

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
