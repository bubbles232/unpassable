
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
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
//import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Font;
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
	private String[] answer;
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
	private String sub = "";
	private boolean answered;
	JFrame frame = new JFrame("");
	private MouseEvent r; 
	private int wr;
	private int wrongs; 
	private int score;
	private boolean gameOver;
	private Font l;
	private Font l2;

	GridLayout grid = new GridLayout();
	
	/**
	 * @throws FileNotFoundException  
	 * 
	 */
	public GUI() throws FileNotFoundException {
		x = 0;
		y = 0;
	l	= new Font("Serif",75,50);
		score = 0;
		startScreen = true;
		questionScreen = false;
		stop = true;
		chem = new Chemistry();
		psych = new Psych();
		mus = new Music();
		jap = new Japanese();
gameOver=false;
		answered = false;
		chem.scan();
		mus.scan();
		psych.scan();
		jap.scan();
	
		questionP = Toolkit.getDefaultToolkit().getImage("questions.png").getScaledInstance(500, 475,
				java.awt.Image.SCALE_SMOOTH);
		// this.setLayout(new GridLayout(4,1));
		
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
		
		System.out.println(chem.getCorrectAnsIn(2));
		
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));

		starting = Toolkit.getDefaultToolkit().getImage("start.gif");
		startButton = Toolkit.getDefaultToolkit().getImage("start button.jpg");
		right = Toolkit.getDefaultToolkit().getImage("");
		wrong = Toolkit.getDefaultToolkit().getImage("");

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

	public static void main(String[] args) throws FileNotFoundException {
		GUI g = new GUI();
		

	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponents(g);
		
		//System.out.println(questionScreen);
int add = 0;

			if (!answered) {
				removeAll();
			  g2.drawImage(starting, x, y, this);
			  g2.drawImage(startSubject, x, y, this);
			
			  if(questionScreen) {
				  removeAll();
			 
			  switch (sub) {
			  case "mus":
			  
			  if (count < mus.sortQs().size()-1) {
				  g2.drawImage(questionP, x, y, this);
				  if(mus.sortQs().get(count).length()<80) {
			  g2.drawString(mus.sortQs().get(count),25, 100);}
				  else {
				  g2.drawString(mus.sortQs().get(count).substring(0,mus.sortQs().get(count).length()/2) + "-",25, 100);
				  g2.drawString(mus.sortQs().get(count).substring(mus.sortQs().get(count).length()/2), 25, 115);}
			  for (int i = 0; i < 4; i++) { 
				  String[] answers = getNextAnswers(sub,count);
					g2.drawString(answers[i], 25, 150+add);
					add += 30;
				}
			  }
			  
			  break;
			  
				  case "jap":
			  
			  if (count < jap.sortQs().size()-1) {
				  g2.drawImage(questionP, x, y, this);
				  if(jap.sortQs().get(count).length()<80) {
					  g2.drawString(jap.sortQs().get(count),25, 100);}
						  else {
						  g2.drawString(jap.sortQs().get(count).substring(0,jap.sortQs().get(count).length()/2) + "-",25, 100);
						  g2.drawString(jap.sortQs().get(count).substring(jap.sortQs().get(count).length()/2), 25, 115);}
			  for (int i = 0; i < 4; i++) { 
				  String[] answers = getNextAnswers(sub,count);
					g2.drawString(answers[i], 25, 150+add);
					add += 30;
				}
			  }
		break;
			  case "chem":
			  
			  if (count < chem.sortQs().size()-1) {
				  g2.drawImage(questionP, x, y, this);
				  if(chem.sortQs().get(count).length()<80) {
					  g2.drawString(chem.sortQs().get(count),25, 100);}
						  else {
						  g2.drawString(chem.sortQs().get(count).substring(0,chem.sortQs().get(count).length()/2) + "-",25, 100);
						  g2.drawString(chem.sortQs().get(count).substring(chem.sortQs().get(count).length()/2), 25, 115);}
			  for (int i = 0; i < 4; i++) { 
				  String[] answers = getNextAnswers(sub,count);
					g2.drawString(answers[i], 25, 150+add);
					add += 30;
				}
			  }
			  break;
			default:
			  
			  if (count < psych.sortQs().size()-1) {
				  g2.drawImage(questionP, x, y, this);
				  if(psych.sortQs().get(count).length()<80) {
					  g2.drawString(psych.sortQs().get(count),25, 100);}
						  else {
						  g2.drawString(psych.sortQs().get(count).substring(0,psych.sortQs().get(count).length()/2) + "-",25, 100);
						  g2.drawString(psych.sortQs().get(count).substring(psych.sortQs().get(count).length()/2), 25, 115);}
			  for (int i = 0; i < 4; i++) { 
				  String[] answers = getNextAnswers(sub,count);
					g2.drawString(answers[i], 25, 150+add);
					add += 30;
				}
			  }
			  removeAll();
			  break;}
			  
			  
			
		
			
			  }
	
			}  
			
			if (answered) {
				System.out.println(ansChoice);
			if (wr==1) {
				g2.drawImage(wrong, x, y, this);
			}
			else if (wr==0) {
				g2.drawImage(right,  x,  y,  this);
			}
			
		
	
		
		
		}
			if(gameOver) {
				int total=0;
				switch (sub) {
				case "chem":
				total= chem.sortQs().size();
				break;
				case "mus":
					total= mus.sortQs().size();
					break;
				case "psych":
					total= psych.sortQs().size();
					break;
					default:
					
						total= jap.sortQs().size();
						break;
				}
				g.setFont(l);
				g.drawString(((double)score/(total-1))* 100 + " %",350,350); 
				l2 = new Font("Seirf", 20,20);
				g.setFont(l2);
				g.drawString("Play a different subject? Click here!",50 , 450);
				
			}
		
		update();}
	

	public void start() {
		gameOver= false;
		startScreen =true;
		chem.scan();
		psych.scan();
		jap.scan();
		mus.scan();
		starting = Toolkit.getDefaultToolkit().getImage("start.gif");
		start.setVisible(true);
		stop=true;
	}

	
	
	public Rectangle screen() {
		Rectangle screen;
		screen = new Rectangle(500,500);
		return screen;
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
			subBoarder = new Rectangle(21, 115 + ((200 - 115) * 3)+5, 488 - 21, 200 - 115);
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
		System.out.println(count);
		switch (sub) {
		case "chem":
			question=chem.sortQs().get(index);
			break;
		case "psych":
			question=psych.sortQs().get(index);
			break;
		case "mus":
			question=mus.sortQs().get(index);
			break;
		default:
			question=jap.sortQs().get(index);

		}
		return question;
	}
	
	public String[] getNextAnswers(String sub, int index) {
		String[] answer = new String[4];
		switch (sub) {
		case "chem":
			String[][] stored = new String[chem.sortQs().size()][4];
			stored = chem.sortAs();
			answer = stored[index];
			break;
		case "psych":
			String[][] storeds = new String[psych.sortQs().size()][4];
			storeds = psych.sortAs();
			answer = storeds[index];
			break;
		case "mus":
			String[][] storedss = new String[mus.sortQs().size()][4];
			storedss = mus.sortAs();
			answer = storedss[index];
			break;
		default:
			String[][] storedsss = new String[jap.sortQs().size()][4];
			storedsss = jap.sortAs();
			answer = storedsss[index];
			break;
		}
		
		return answer;
	
	}
	
	public int getNextAnswer(String sub, int index) throws FileNotFoundException {
		int an=0;
		switch (sub) {
		case "chem":
			an = chem.getCorrectAnsIn(index);
			break;
		case "psych":
			an = psych.getCorrectAnsIn(index);
			break;
		case "mus":
			an = mus.getCorrectAnsIn(index);
			break;
		default:
			an = jap.getCorrectAnsIn(index);
			break;
		}
		
		return an;
	
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
	
	public int getQuestion(String sub) {
		int finalled = 0; 
		if(sub.equals("chem")) {
			
			finalled = chem.sortQs().size();
		}
		if(sub.equals("mus")) {
			finalled = mus.sortQs().size();
			System.out.println(finalled + " actual " + mus.sortQs().size());
		}
		if(sub.equals("jap")) {
			finalled = jap.sortQs().size();
		}
		else {
			finalled = psych.sortQs().size();
		}
		return finalled;
	}

	public void mouseClicked(MouseEvent m) {
		int mouseX = m.getX();
		int mouseY = m.getY();
		System.out.println(mouseX + " " + mouseY);
		if (answered) {
			System.out.println("bubbles");
			answered = false; 
			this.repaint();
			revalidate();
			return;
		}
		if (!startScreen && !gameOver) {
			if (selectionOption("Music").contains(mouseX, mouseY) && stop) {
				starting = Toolkit.getDefaultToolkit().getImage("music history.png").getScaledInstance(500, 475,
						java.awt.Image.SCALE_SMOOTH);
				question= getNextQuestion("music", 0);
				String[][] stored = new String[mus.sortQs().size()][4];
				stored = mus.sortAs();
				answer = stored[count];
				stop = false;
				sub= "mus";
				questionScreen = true;
				System.out.println( " actual "+ getQuestion(sub));
			}
				
			else if (selectionOption("Psych").contains(mouseX, mouseY) && stop) {
				starting = Toolkit.getDefaultToolkit().getImage("psych.png").getScaledInstance(500, 475,
						java.awt.Image.SCALE_SMOOTH);
				questionScreen = true;
				sub="psych";
				question= getNextQuestion("psych", 0);
				String[][] stored = new String[psych.sortQs().size()][4];
				stored = psych.sortAs();
				answer = stored[count];
				stop = false;

			} else if (selectionOption("Japanese").contains(mouseX, mouseY) && stop) {
				starting = Toolkit.getDefaultToolkit().getImage("japanese.png").getScaledInstance(500, 475,
						java.awt.Image.SCALE_SMOOTH);
				questionScreen = true;
				question= getNextQuestion("jap", 0);
				String[][] stored = new String[jap.sortQs().size()][4];
				stored = jap.sortAs();
				answer = stored[count];
				sub="jap";
				stop = false;
				
			} else if (selectionOption("Chem").contains(mouseX, mouseY) && stop) {
				starting = Toolkit.getDefaultToolkit().getImage("chemistry.png").getScaledInstance(500, 475,
						java.awt.Image.SCALE_SMOOTH);
				questionScreen = true;
				question= getNextQuestion("chem", 0);
				String[][] stored = new String[chem.sortQs().size()][4];
				stored = chem.sortAs();
				answer = stored[count];
				sub="chem";
				stop = false;
			
		}
		else if (questionScreen && !stop && gameOver ==false) {
		System.out.println("length" + getQuestion(sub));
			if(sub.equals("chem")&&count>=chem.sortQs().size()-1) {
				if(win()==true) {
					questionScreen = false;
					starting = Toolkit.getDefaultToolkit().getImage("pass.gif").getScaledInstance(500, 475,
							java.awt.Image.SCALE_SMOOTH);
					
					gameOver=true;
				}
				
				questionScreen = false;
				starting = Toolkit.getDefaultToolkit().getImage("fail.gif").getScaledInstance(500, 475,
						java.awt.Image.SCALE_SMOOTH);
				gameOver=true;
			}
			else	if(sub.equals("psych")&&count>=psych.sortQs().size()-1) {
				if(win()==true) {
					questionScreen = false;
					starting = Toolkit.getDefaultToolkit().getImage("pass.gif").getScaledInstance(500, 475,
							java.awt.Image.SCALE_SMOOTH);
					
					gameOver=true;
				}else {
				
				questionScreen = false;
				
				starting = Toolkit.getDefaultToolkit().getImage("fail.gif").getScaledInstance(500, 475,
						java.awt.Image.SCALE_SMOOTH);
				gameOver=true;}
				}
			else if(sub.equals("mus")&&count>=mus.sortQs().size()-1) {
				if(win()==true) {
					questionScreen = false;
					starting = Toolkit.getDefaultToolkit().getImage("pass.gif").getScaledInstance(500, 475,
							java.awt.Image.SCALE_SMOOTH);
					
					gameOver=true;
				}
				questionScreen = false;
				starting = Toolkit.getDefaultToolkit().getImage("fail.gif").getScaledInstance(500, 475,
						java.awt.Image.SCALE_SMOOTH);
				gameOver=true;
			}
			else if(sub.equals("jap")&&count>=jap.sortQs().size()-1) {
				if(win()==true) {
					questionScreen = false;
					starting = Toolkit.getDefaultToolkit().getImage("pass.gif").getScaledInstance(500, 475,
							java.awt.Image.SCALE_SMOOTH);
					
					gameOver=true;
				}
				questionScreen = false;
				starting = Toolkit.getDefaultToolkit().getImage("fail.gif").getScaledInstance(500, 475,
						java.awt.Image.SCALE_SMOOTH);
				gameOver=true;
			}
				
				else {

					if (ansOption("a").contains(mouseX, mouseY)) {
					ansChoice=0;
					try {
						Answer(sub);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					determine =true;
					count++;
					
				} else if (ansOption("b").contains(mouseX, mouseY)) {
					ansChoice = 1;
					
						System.out.println(chem.getCorrectAnsIn(count));
					
						try {
							Answer(sub);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					determine=true;
					count++;
				} else if (ansOption("c").contains(mouseX, mouseY)) {
					ansChoice =2;
				try {
					Answer(sub);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				determine=true;
				count++;
				} else if (ansOption("d").contains(mouseX, mouseY)) {
					ansChoice = 3;
					try {
						Answer(sub);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					determine=true;
					count++;
			}
			this.repaint();
			revalidate();
			/*
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			//answered = false;
			this.repaint();
			revalidate();
		}}
		}
		else {
			start();
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
	
	
	public boolean win() {
		boolean won;
		if(sub.equals("chem")) {
			if ((double)score/(chem.sortQs().size()-1)>=0.9  ) {
				won =true;
				System.out.println("win");
				System.out.println(sub);
			}
			won=false;
		}
		else if (sub.equals("psych")) {
			if ((double)score/(psych.sortQs().size()-1)>=0.9  ) {
				won =true;
			}
			won=false;
		}
		else if (sub.equals("jap")) {
			if ((double)score/(jap.sortQs().size()-1)>=0.9  ) {
				won =true;
			}
			won=false;
		}
		else {
			if ((double)score/(mus.sortQs().size()-1)>=0.9  ) {
				won =true;
			}
			won=false;
		}
			return won;
	}


	public boolean rightAnswer(String sub)  {
	
		boolean result = false;
		//System.out.println(sub);
		
		switch(sub) {
			case "chem":
				if(count<chem.sortQs().size()) {
					if (chem.getCorrectAnsIn(count)==(ansChoice)) {
				result = true;
			System.out.println(result);
		}
				}
		
		break;
			case "psych":
		if(count<psych.sortQs().size()) {
			if (psych.getCorrectAnsIn(count)==(ansChoice)) {
				result = true;
			}}
		break;
			case "jap":
		if(count<jap.sortQs().size()) {
				if (jap.getCorrectAnsIn(count)==(ansChoice)) {
					result = true;
				}}
		break;
			default:
		if(count<mus.sortQs().size()) {
			if (mus.getCorrectAnsIn(count)==(ansChoice)) {
				result = true;
			}
			}
	//	break;
		
	}
		return result; 
		
	}
	/*
	public void theEnd() {
	
		if (wrongs <2 && Ending(sub)) {
			starting = Toolkit.getDefaultToolkit().getImage("pass.gif").getScaledInstance(500,475,java.awt.Image.SCALE_SMOOTH);
			//right = Toolkit.getDefaultToolkit().getImage("pass.gif").getScaledInstance(500,475,java.awt.Image.SCALE_SMOOTH);

		}
		else {
			starting = Toolkit.getDefaultToolkit().getImage("fail.gif").getScaledInstance(500,475,java.awt.Image.SCALE_SMOOTH);
		//	right = Toolkit.getDefaultToolkit().getImage("pass.gif").getScaledInstance(500,475,java.awt.Image.SCALE_SMOOTH);

		}
		
	}
	
	public boolean Ending(String sub) {
		boolean answer = false;
		switch (sub) {
		case "chem":
			if (count+2 == chem.sortQs().size()) {
				answer = true;
			}
			break;
		case "psych":
			if (count+2 == psych.sortQs().size()) {
				answer = true;
			}
			break;
		case "mus":
			if (count+2 == mus.sortQs().size()) {
				answer = true;
			}
			break;
		default:
			if (count+2 == jap.sortQs().size()) {
				answer = true;
			}
			break;
		}
		
		return answer;
	}
	*/
	
	
	public void Answer(String sub) throws FileNotFoundException {
	answered = true;
		if(rightAnswer(sub)) {
			right = Toolkit.getDefaultToolkit().getImage("right.gif").getScaledInstance(500,475,java.awt.Image.SCALE_SMOOTH);
			System.out.println(determine);
			wr=0;
			score++;
			//revalidate();
			//revert();
		}		
		else if (!rightAnswer(sub)){
			wrong = Toolkit.getDefaultToolkit().getImage("wrong.gif").getScaledInstance(500,475,java.awt.Image.SCALE_SMOOTH);
			wr=1;
			wrongs++;
		}
		
		
		
		
		/*
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
    }
	/*
	public void revert() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		frame.getContentPane().removeAll();
		Graphics2D g2 = (Graphics2D) g;
        super.paintComponents(g);

        g2.drawImage(starting, x, y, this);
        g2.drawImage(startSubject, x, y, this);
            g2.drawImage(questionP, x, y, this);
        g.setColor(Color.BLACK);
        g.drawString(question,25, 100);
        int add = 0; 
        for (int i = 0; i < 4; i++) {
            g.drawString(answer[i], 25+add, 200);
            add += 130;
        }
		
		
	}
	*/
	
}
