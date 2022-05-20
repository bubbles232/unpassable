package unpassable;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
private Image startSubject;
private boolean hello = true;

		private AffineTransform tx;private int x;
	private int y;
private JButton start;

//FlowLayout f = new FlowLayout();
GridLayout grid = new GridLayout();
	/**
	 * 
	 */
	public GUI() {
		x = 0;
		y = 0;
		
		
		//this.setLayout(new GridLayout(4,1));
		JPanel southPanel = new JPanel();
		        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));

		JFrame frame = new JFrame("");
		frame.setSize(500, 500);
		start = new JButton();
		starting = Toolkit.getDefaultToolkit().getImage("start.gif");
		startButton = Toolkit.getDefaultToolkit().getImage("start button.jpg");
		
		frame.add(southPanel,BorderLayout.SOUTH);


		ImageIcon 	b=new ImageIcon(startButton);
		
		start.setIcon(b);
		
	
		
		southPanel.setSize(2, 0);
		southPanel.setMaximumSize(getMaximumSize());
		
		System.out.println(start.getSize());
		frame.setResizable(false);
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y);
		frame.add(this);
		if(hello)
		frame.setVisible(true);
		southPanel.add(Box.createHorizontalGlue());
		southPanel.add(start);
		
		repaint();
		
		
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
		
	}

	public static void main(String[] args) {
		GUI g = new GUI();

	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponents(g);
		
		
		g2.drawImage(starting, x, y, this);
		g2.drawImage(startSubject, x, y, this);

		
		


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
	
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		int x2 =(int) start.getLocationOnScreen().getX();
		int y2 = (int) start.getLocationOnScreen().getX();
		int width = (int) start.getWidth();
		int height = (int) start.getHeight();
		System.out.println(width);
		if (x>=x2 && x<=x2+width && y>=y2 && y<=y2+width) {
			hello = false;
			
		
		
	}
}
}
