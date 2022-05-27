
package unpassable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
//import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author 1861267
 *
 */
public class GUI extends JPanel implements ActionListener, MouseListener {
	private Image starting;
	private Image startButton;
	private Image questionP;
	private AffineTransform tx;
	private int x;
	private int y;
	private JButton start;
	private Image startSubject;
	private boolean startScreen;
	private boolean questionScreen;
	private boolean stop;
	private boolean determine;
	private Chemistry chem;
	private Psych psych;
	private Music mus;
	private Japanese jap;
	private JLabel questionL;
	private JPanel questionBox;
	private int ansChoice;
	private JPanel southPanel;
	private String question;
	private int count; 
	//private Timer t; 
	private int valMusic = 0;
	private int valChem = 0;
	private int valPsych = 0;
	private int valJap = 0;
	private int countdown = 3000;
	private Rectangle screen;
	public Image right;
	public Image wrong;
	
//FlowLayout f = new FlowLayout();
	GridLayout grid = new GridLayout();

	/** 
	 * 
	 */
	public GUI() {
		x = 0;
		y = 0;
		startScreen = true;
		questionScreen = false;
		stop = true;
		chem = new Chemistry();
		psych = new Psych();
		mus = new Music();
		jap = new Japanese();
		chem.scan();
		mus.scan();
		psych.scan();
		jap.scan();
		questionP = Toolkit.getDefaultToolkit().getImage("questions.png").getScaledInstance(500, 475,
				java.awt.Image.SCALE_SMOOTH);
		// this.setLayout(new GridLayout(4,1));
		JFrame frame = new JFrame("");
		determine =false;
		frame.setSize(500, 500);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		
		questionBox = new JPanel();
		southPanel = new JPanel();
		question = "";
		questionBox.setLayout(new BoxLayout(questionBox, BoxLayout.PAGE_AXIS));
		//questionBox.add(questionL);
		questionBox.setBackground(Color.black);
		questionBox.setPreferredSize(new Dimension(200, 100));
		//questionL.setVisible(true);
		questionBox.setVisible(true);
		frame.add(questionBox, BorderLayout.CENTER);
		frame.add(southPanel, BorderLayout.SOUTH);
		
		System.out.println(getNextQuestion("music",0));
		System.out.println(mus.sortQs().get(0));
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));

		starting = Toolkit.getDefaultToolkit().getImage("start.gif");
		startButton = Toolkit.getDefaultToolkit().getImage("start button.jpg");
		right = Toolkit.getDefaultToolkit().getImage("right.gif").getScaledInstance(500,475,java.awt.Image.SCALE_SMOOTH);
		wrong = Toolkit.getDefaultToolkit().getImage("wrong.gif").getScaledInstance(500,475,java.awt.Image.SCALE_SMOOTH);

		ImageIcon b = new ImageIcon(startButton);
		Image image = b.getImage(); // transform it
		Image newimg = image.getScaledInstance(475, 120, java.awt.Image.SCALE_SMOOTH);
		b.setImage(newimg);
		start = new JButton();
		start.setAlignmentY(CENTER_ALIGNMENT);
		start.setMaximumSize(new Dimension(b.getIconWidth(), b.getIconHeight()));

		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y);

		start.setIcon(b);

		southPanel.setBackground(Color.black);
		start.setBackground(Color.BLACK);

		frame.setResizable(false);

		frame.add(this);
		frame.setVisible(true);
		
	
		
		

		this.addMouseListener(this);
		// southPanel.add(Box.createHorizontalGlue());
		southPanel.add(start);

		start.addActionListener(this);
		count=0;
		this.repaint();
		revalidate();
		
		System.out.println(mus.getCorrectAnsIn(3));
		

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
	
		if (questionScreen &&determine ==false) {
			g2.drawImage(questionP, x, y, this);
		g.setColor(Color.BLACK);
		g.drawString(question,25, 100);
		}
		
		update();
	}

	public void start() {

	}

	public void gameOver() {

	}

	public Rectangle selectionOption(String sub) {
		Rectangle subBoarder;
		switch (sub) {
		case "Music":
			subBoarder = new Rectangle(21, 115, (488 - 21), (200 - 115));
			break;
		case "Japanese":
			subBoarder = new Rectangle(21, 115 + (200 - 115) + 5, (488 - 21), (200 - 115));
			break;
		case "Psych":
			subBoarder = new Rectangle(21, 115 + ((200 - 115) * 2) + 5, (488 - 21), (200 - 115));
			break;
		default:
			subBoarder = new Rectangle(21, 115 + ((200 - 115) * 3), 488 - 21, 200 - 115);
			break;
//		default:
//			subBoarder = new Rectangle(21, 115 + ((200 - 115) * 3) + 5, (488 - 21), (200 - 115));
//			break;
		}
		return subBoarder;
	}

	public Rectangle ansOption(String ans) {
		Rectangle ansBoarder;
		switch (ans) {
		case "a":
			ansBoarder = new Rectangle(15, 280, (127 - 10), (340 - 280));
			break;
		case "b":
			ansBoarder = new Rectangle(15 + ((127 - 10)) + 3, 280, (120 - 10), (350 - 280));
			break;
		case "c":
			ansBoarder = new Rectangle(15 + ((127 - 10) * 2) + 3, 280, (120 - 10), (350 - 280));
			break;
		default:
			ansBoarder = new Rectangle(15 + ((127 - 10) * 3) + 3, 280, (120 - 10), (350 - 280));

		}
		return ansBoarder;
	}

	public String getNextQuestion(String sub, int index) {
		String question = "";
		switch (sub) {
		case "chem":
			question=chem.sortQs().get(0);
			break;
		case "psych":
			question=psych.sortQs().get(index);
			break;
		case "music":
			question=mus.sortQs().get(index);
			break;
		default:
			question=jap.sortQs().get(0);

		}
		return question;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == start) {
			starting = Toolkit.getDefaultToolkit().getImage("subjects.gif").getScaledInstance(500, 475,
					java.awt.Image.SCALE_SMOOTH);
			start.setVisible(false);
			startScreen = false;
		}
	}

	public void mouseClicked(MouseEvent m) {
		int mouseX = m.getX();
		int mouseY = m.getY();
		String sub="";
		System.out.println(mouseX + " " + mouseY);
		if (!startScreen) {
			if (selectionOption("Music").contains(mouseX, mouseY) && stop) {
				starting = Toolkit.getDefaultToolkit().getImage("music history.png").getScaledInstance(500, 475,
						java.awt.Image.SCALE_SMOOTH);
				question= getNextQuestion("music", 1);
				System.out.println(question);
				stop = false;
				sub= "mus";
				questionScreen = true;
				valMusic++;
				
			} else if (selectionOption("Psych").contains(mouseX, mouseY) && stop) {
				starting = Toolkit.getDefaultToolkit().getImage("psych.png").getScaledInstance(500, 475,
						java.awt.Image.SCALE_SMOOTH);
				questionScreen = true;
				sub="psych";
				question= getNextQuestion("psych", 0);
				stop = false;
				valPsych++;

			} else if (selectionOption("Japanese").contains(mouseX, mouseY) && stop) {
				starting = Toolkit.getDefaultToolkit().getImage("japanese.png").getScaledInstance(500, 475,
						java.awt.Image.SCALE_SMOOTH);
				questionScreen = true;
				question= getNextQuestion("jap", 0);
				sub="jap";
				stop = false;
				valJap++;

			} else if (selectionOption("Chem").contains(mouseX, mouseY) && stop) {
				starting = Toolkit.getDefaultToolkit().getImage("chemistry.png").getScaledInstance(500, 475,
						java.awt.Image.SCALE_SMOOTH);
				questionScreen = true;
				sub="chem";
				question= getNextQuestion("chem", 0);
				stop = false;
				valChem++;
				

			
		}
		else if (questionScreen && !stop && !determine) {
			if (ansOption("a").contains(mouseX, mouseY)) {
				ansChoice = 0;
				count++;
				Answer(m,sub);
				determine =true;
			} else if (ansOption("b").contains(mouseX, mouseY)) {
				ansChoice = 1;
				count++;
				Answer(m,sub);
				determine=true;
			} else if (ansOption("c").contains(mouseX, mouseY)) {
				ansChoice = 2;
				count++;
			Answer(m,sub);
			determine=true;
			} else {
				ansChoice = 3;
				count++;
				Answer(m,sub);
				determine=true;
			}
		}}
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
	
	public boolean rightAnswer(String sub) {
		boolean result = false;
		switch(sub) {
			case "chem":
		if(count<chem.sortQs().size()) {
		if (chem.getCorrectAnsIn(count) == ansChoice) {
			result = true;
		}}
		break;
			case "psych":
		if(count<psych.sortQs().size()) {
			if (psych.getCorrectAnsIn(count) == ansChoice) {
				result = true;
			}}
		break;
			case "jap":
		if(count<jap.sortQs().size()) {
				if (jap.getCorrectAnsIn(count) == ansChoice) {
					result = true;
				}}
		break;
			default:
		if(count<mus.sortQs().size()) {
			if (mus.getCorrectAnsIn(count) == ansChoice) {
				result = true;
			}}
		break;}
		return result; 
	}
	
	public void revert() {
		if (valChem != 0) {
			starting = Toolkit.getDefaultToolkit().getImage("chemistry.png").getScaledInstance(500,475,java.awt.Image.SCALE_SMOOTH);
		}
		
		if (valPsych != 0) {
			starting = Toolkit.getDefaultToolkit().getImage("psych.png").getScaledInstance(500,475,java.awt.Image.SCALE_SMOOTH);
		}
		
		if (valMusic != 0) {
			starting = Toolkit.getDefaultToolkit().getImage("music history.png").getScaledInstance(500,475,java.awt.Image.SCALE_SMOOTH);
		}
		
		if (valJap != 0) {
			starting = Toolkit.getDefaultToolkit().getImage("japanese.png").getScaledInstance(500,475,java.awt.Image.SCALE_SMOOTH);
		}
		
	}
	
	
	
	public void Answer(MouseEvent r, String sub) {
		int mouseX = r.getX();
		int mouseY = r.getY();
		boolean result = false;
		if(rightAnswer(sub) && !result) {
			result = true;
			starting = Toolkit.getDefaultToolkit().getImage("right.gif").getScaledInstance(500,475,java.awt.Image.SCALE_SMOOTH);
			}
		else if (!result && !rightAnswer(sub)) {
			starting = Toolkit.getDefaultToolkit().getImage("wrong.gif").getScaledInstance(500,475,java.awt.Image.SCALE_SMOOTH);
			result = true;
		}
}
}
