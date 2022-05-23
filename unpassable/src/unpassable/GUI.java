package unpassable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
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

		private AffineTransform tx;private int x;
	private int y;
private JButton start;
private Image startSubject;
//FlowLayout f = new FlowLayout();
GridLayout grid = new GridLayout();

	/** 
	 * 
	 */
	public GUI() {
		x = 0;
		y = 0;
		
		
		//this.setLayout(new GridLayout(4,1));
		JFrame frame = new JFrame("");
		frame.setSize(500, 500);
		JPanel southPanel = new JPanel();
		
		        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));

		    	starting = Toolkit.getDefaultToolkit().getImage("start.gif");
				startButton = Toolkit.getDefaultToolkit().getImage("start button.jpg");
		
		ImageIcon 	b=new ImageIcon(startButton);
		Image image = b.getImage(); // transform it 
		Image newimg = image.getScaledInstance(475, 120,  java.awt.Image.SCALE_SMOOTH);
		b.setImage(newimg);
start = new JButton();
start.setAlignmentY(CENTER_ALIGNMENT);
start.setMaximumSize(new Dimension(b.getIconWidth(),b.getIconHeight()));


	
		
		frame.add(southPanel,BorderLayout.SOUTH);
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y);

	
		
		start.setIcon(b);
		
		
		southPanel.setBackground(Color.black);
	start.setBackground(Color.BLACK);
		
		
		
		
		
		frame.setResizable(false);
	
		frame.add(this);
		frame.setVisible(true);
		
	
		//southPanel.add(Box.createHorizontalGlue());
		southPanel.add(start);
		start.addActionListener(this);
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
		g2.drawImage(startSubject,x,y,this);
		
		


		update();
	}

	public void start() {

	}

	public void gameOver() {

	}


	
public Rectangle selectionOption(String sub) {
		
		//if(sub.equals("Music")) {
		return new Rectangle(21,202, 239-202,488-21);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==start) {
			starting = Toolkit.getDefaultToolkit().getImage("subjects.gif").getScaledInstance(500,475,java.awt.Image.SCALE_SMOOTH);;
			start.setVisible(false);
		}
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
