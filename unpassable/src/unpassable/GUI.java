

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
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	private Chemistry chem;
	private Psych psych;
	private Music mus;
	private Japanese jap;
	private JLabel questionL;
	private JPanel questionBox;
	private int ansChoice;
	private JPanel southPanel;
	private String question;
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

		frame.setSize(500, 500);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		questionL = new JLabel("hello");
		questionBox = new JPanel();
		southPanel = new JPanel();

		questionBox.setLayout(new BoxLayout(questionBox, BoxLayout.PAGE_AXIS));
		questionBox.add(questionL);
		questionBox.setBackground(Color.black);
		questionBox.setPreferredSize(new Dimension(200, 100));
		questionL.setVisible(true);
		questionBox.setVisible(true);
		frame.add(questionBox, BorderLayout.CENTER);
		frame.add(southPanel, BorderLayout.SOUTH);



		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));

		starting = Toolkit.getDefaultToolkit().getImage("start.gif");
		startButton = Toolkit.getDefaultToolkit().getImage("start button.jpg");

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

		this.repaint();
		revalidate();

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

		if (questionScreen) {
			g2.drawImage(questionP, x, y, this);
		g.setColor(Color.BLACK);
g.drawString(question,200, 200);
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
		case "Chem":
			subBoarder = new Rectangle(21, 115 + ((200 - 115) * 3), 488 - 21, 200 - 115);
		default:
			subBoarder = new Rectangle(21, 115 + ((200 - 115) * 3) + 5, (488 - 21), (200 - 115));
			break;
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
			chem.sortQs().get(index);
		case "psych":
			psych.sortQs().get(index);
		case "music":
			mus.sortQs().get(index);
		default:
			jap.sortQs().get(index);

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
		int count = 0;
		System.out.println(mouseX + " " + mouseY);
		if (startScreen == false) {
			if (selectionOption("Music").contains(mouseX, mouseY) && stop) {
				starting = Toolkit.getDefaultToolkit().getImage("music history.png").getScaledInstance(500, 475,
						java.awt.Image.SCALE_SMOOTH);
				questionScreen = true;
				
				question= getNextQuestion("music", 0);
				repaint();
				System.out.println(getNextQuestion("music", 0));
				stop = false;
			} else if (selectionOption("Psych").contains(mouseX, mouseY) && stop) {
				starting = Toolkit.getDefaultToolkit().getImage("psych.png").getScaledInstance(500, 475,
						java.awt.Image.SCALE_SMOOTH);
				questionScreen = true;
				question= getNextQuestion("psych", 0);
				stop = false;

			} else if (selectionOption("Japanese").contains(mouseX, mouseY) && stop) {
				starting = Toolkit.getDefaultToolkit().getImage("japanese.png").getScaledInstance(500, 475,
						java.awt.Image.SCALE_SMOOTH);
				questionScreen = true;
				question= getNextQuestion("jap", 0);
				stop = false;

			} else if (selectionOption("Chem").contains(mouseX, mouseY) && stop) {
				starting = Toolkit.getDefaultToolkit().getImage("chemistry.png").getScaledInstance(500, 475,
						java.awt.Image.SCALE_SMOOTH);
				questionScreen = true;
				question= getNextQuestion("chem", 0);
				stop = false;

			} else if (selectionOption("").contains(mouseX, mouseY)) {
				System.out.println("mouse works");
				questionScreen = true;
			}
		}
		if (questionScreen && !stop) {
			if (ansOption("a").contains(mouseX, mouseY)) {
				ansChoice = 0;
				count++;
			} else if (ansOption("b").contains(mouseX, mouseY)) {
				ansChoice = 1;
				count++;
			} else if (ansOption("c").contains(mouseX, mouseY)) {
				ansChoice = 2;
				count++;
			} else {
				ansChoice = 3;
				count++;
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
	
	public boolean rightAnswer() {
		boolean result = false;
		for (int i = 0; i < chem.getQLength(); i++) {
		if (chem.getCorrectAnsIn(i) == ansChoice) {
			result = true;
		}
		}
		
		for (int i = 0; i < psych.sortQs().size(); i++) {
		if (psych.getCorrectAnsIn(i) == ansChoice) {
			result = true;
		}
	}
		for (int i = 0; i < jap.getQLength(); i++) {
			if (jap.getCorrectAnsIn(i) == ansChoice) {
				result = true;
			}
		}
		
		for (int i = 0; i < mus.sortQs().size(); i++) {
			if (mus.getCorrectAnsIn(i) == ansChoice) {
				result = true;
			}
		}
		
		return result; 
	}
	
	public void Answer(MouseEvent r) {
		if(rightAnswer()) {
			starting = Toolkit.getDefaultToolkit().getImage("right.gif").getScaledInstance(500,475,java.awt.Image.SCALE_SMOOTH);
		}
		else {
			starting = Toolkit.getDefaultToolkit().getImage("wrong.gif").getScaledInstance(500,475,java.awt.Image.SCALE_SMOOTH);
		}
	}
