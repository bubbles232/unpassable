package unpassable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
public class GUI extends JPanel implements ActionListener, MouseListener{
	private Image starting;
private Image startButton;
private Image questionP;
		private AffineTransform tx;private int x;
	private int y;
private JButton start;
private Image startSubject;
private boolean startScreen;
private boolean questionScreen;
//FlowLayout f = new FlowLayout();
GridLayout grid = new GridLayout();

	/** 
	 * 
	 */
	public GUI() {
		x = 0;
		y = 0;
startScreen=true;
questionScreen=false;
		questionP= Toolkit.getDefaultToolkit().getImage("questions.png").getScaledInstance(500,475,java.awt.Image.SCALE_SMOOTH);
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
		
	this.addMouseListener(this);
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
		if(questionScreen) {
		g2.drawImage(questionP,x,y,this);}


		update();
	}

	public void start() {

	}

	public void gameOver() {

	}


	
public Rectangle selectionOption(String sub) {
	Rectangle subBoarder;
		switch(sub) {
		case "Music":
			subBoarder= new Rectangle(21,115,(488-21) ,(200-115));
			
			break;
		case "Japanese":
			subBoarder= new Rectangle(21,115 + (200-115) + 5,(488-21) ,(200-115));
			break;
		case "Psych":
			subBoarder=  new Rectangle(21,115 + ((200-115)*2) + 5,(488-21) ,(200-115));
			break;
			default :
				subBoarder= new Rectangle(21,115 + ((200-115)*3) + 5,(488-21) ,(200-115));
				break;
		}
		return subBoarder;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==start) {
			starting = Toolkit.getDefaultToolkit().getImage("subjects.gif").getScaledInstance(500,475,java.awt.Image.SCALE_SMOOTH);
			start.setVisible(false);
			startScreen=false;
		}
	}
	public void mouseClicked(MouseEvent m) {
		int mouseX=m.getX();
		int mouseY=m.getY();
		System.out.println(mouseX + " " + mouseY);
		if(startScreen==false) {
		if (selectionOption("Music").contains(mouseX, mouseY)) {
			starting = Toolkit.getDefaultToolkit().getImage("music history.png").getScaledInstance(500,475,java.awt.Image.SCALE_SMOOTH);
			questionScreen=true;
		}
		else if (selectionOption("Psych").contains(mouseX, mouseY)) {
			starting = Toolkit.getDefaultToolkit().getImage("music history.png").getScaledInstance(500,475,java.awt.Image.SCALE_SMOOTH);
			questionScreen=true;
		}
		else if (selectionOption("Japenese").contains(mouseX, mouseY)) {
			starting = Toolkit.getDefaultToolkit().getImage("japanese.png").getScaledInstance(500,475,java.awt.Image.SCALE_SMOOTH);
			questionScreen=true;
		}
		else if (selectionOption("").contains(mouseX, mouseY)) {
			System.out.println("mouse works");
			questionScreen=true;
		}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
