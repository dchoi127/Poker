package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;


import model.ImageLoader;

public class PokerGUI extends JPanel {

	
	private Game game;
	private JButton nextStageButton = new JButton("Next Stage");
	private JButton addUCardButton = new JButton("Add User Card");
	private JButton addCCardButton = new JButton("Add Comm Card");
	private JSlider slider = new JSlider();
	private static final Color RED_COLOR = Color.ORANGE;
	private static final int TABLE_HEIGHT = 10;
	private static final int BUTTON_HEIGHT = 35;
	private static final int BUTTON_WIDTH = 95;
	private static final int TABLE_WIDTH = 20;
	private static final int CARD_WIDTH = 71;
	private static final int CARD_HEIGHT = 96;
	
	private static final int NUM_BUTTON_WIDTH = 35;
	private static final int H_DIST_NUM = 30;
	
	private static final int C_CARD_Y = 120;
	private static final int C_CARD_X = 200;
	private static final int SUIT_WIDTH = 70;
	private static final int SUIT_H_DIST = 65;

	private JButton aceButton = new JButton("A");
	private JButton twoButton = new JButton("2");
	private JButton	threeButton = new JButton("3");
	private JButton fourButton = new JButton("4");
	private JButton fiveButton = new JButton("5");
	private JButton sixButton = new JButton("6");
	private JButton sevenButton = new JButton("7");
	private JButton eightButton = new JButton("8");
	private JButton nineButton = new JButton("9");
	private JButton tenButton = new JButton("10");
	private JButton jButton = new JButton("J");
	private JButton qButton = new JButton("Q");
	private JButton kButton = new JButton("K");
	
	private JButton heartButton = new JButton("HEART");
	private JButton spadeButton = new JButton("SPADE");
	private JButton clubButton = new JButton("CLUBS");
	private JButton diaButton = new JButton("DIA");
	
	
	private int numUCards = 0;
	private int numCCards = 0;
	private int rankToAdd = -1;
	private int suitToAdd = -1;
	public static void main(String[] args) {
		new PokerGUI();

	}

	
	public PokerGUI() {
		game = new Game();
		setupWidgets();
		setupPanel();
		setupFrame();
		repaint();
		
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		int xVal = 280;
		for(int x = 0; x < 5; x++) {
			drawCard(g,xVal, C_CARD_Y);
			xVal += 20 + CARD_WIDTH;
		}
		
		xVal = 410;
		drawCard(g, xVal, 500);
		xVal += 20 + CARD_WIDTH;
		drawCard(g, xVal, 500);
		
	
	}
	private void setupFrame() {
		JFrame frame = new JFrame("Poker");
		JLabel uCards = new JLabel("Your Cards");
		JLabel cCards = new JLabel("Community Cards");
		cCards.setFont( new Font("Courier", Font.ITALIC, 16));
		uCards.setFont( new Font("Courier", Font.ITALIC, 16));
		uCards.setBounds(445, 400, 800, 100);
		cCards.setBounds(425, 20, 800, 100);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setContentPane(this);
		// frame.pack();
		frame.setSize(1000, 800);
		frame.setVisible(true);
		frame.add(uCards);
		frame.add(cCards);
		
	}

	private void setupPanel() {
		setLayout(null);
		add(nextStageButton);
		add(addUCardButton);
		add(addCCardButton);
		add(slider);
		add(aceButton);
		add(twoButton);
		add(threeButton);
		add(fourButton);
		add(fiveButton);
		add(sixButton);
		add(sevenButton);
		add(eightButton);
		add(nineButton);
		add(tenButton);
		add(jButton);
		add(qButton);
		add(kButton);
		
		add(heartButton);
		add(spadeButton);
		add(clubButton);
		add(diaButton);
		
		setBackground(RED_COLOR);
		
	}
	
	private void drawCard(Graphics g, int x, int y) {
		g.drawImage(ImageLoader.getImage("images/b2fv.gif"), x, y, this);
	}
	private void setupWidgets() {
		nextStageButton.setBounds(450, 700, BUTTON_WIDTH, BUTTON_HEIGHT);
		addUCardButton.setBounds(370, 400, BUTTON_WIDTH + 20, BUTTON_HEIGHT);
		addCCardButton.setBounds(525, 400, BUTTON_WIDTH + 20, BUTTON_HEIGHT);
		aceButton.setBounds(160, 300, NUM_BUTTON_WIDTH, BUTTON_HEIGHT);
		twoButton.setBounds(160 + H_DIST_NUM,300, NUM_BUTTON_WIDTH, BUTTON_HEIGHT);
		threeButton.setBounds(160 + H_DIST_NUM*2,300, NUM_BUTTON_WIDTH, BUTTON_HEIGHT);
		fourButton.setBounds(160 + H_DIST_NUM*3,300, NUM_BUTTON_WIDTH, BUTTON_HEIGHT);
		fiveButton.setBounds(160 + H_DIST_NUM*4,300, NUM_BUTTON_WIDTH, BUTTON_HEIGHT);
		sixButton.setBounds(160 + H_DIST_NUM*5,300, NUM_BUTTON_WIDTH, BUTTON_HEIGHT);
		sevenButton.setBounds(160 + H_DIST_NUM*6,300, NUM_BUTTON_WIDTH, BUTTON_HEIGHT);
		eightButton.setBounds(160 + H_DIST_NUM*7,300, NUM_BUTTON_WIDTH, BUTTON_HEIGHT);
		nineButton.setBounds(160 + H_DIST_NUM*8,300, NUM_BUTTON_WIDTH, BUTTON_HEIGHT);
		tenButton.setBounds(160 + H_DIST_NUM*9,300, NUM_BUTTON_WIDTH, BUTTON_HEIGHT);
		jButton.setBounds(160 + H_DIST_NUM*10,300, NUM_BUTTON_WIDTH, BUTTON_HEIGHT);
		qButton.setBounds(160 + H_DIST_NUM*11,300, NUM_BUTTON_WIDTH, BUTTON_HEIGHT);
		kButton.setBounds(160 + H_DIST_NUM*12,300, NUM_BUTTON_WIDTH, BUTTON_HEIGHT);
		
		
		heartButton.setBounds(600, 300, SUIT_WIDTH, BUTTON_HEIGHT);
		spadeButton.setBounds(600+SUIT_H_DIST, 300, SUIT_WIDTH, BUTTON_HEIGHT);
		clubButton.setBounds(600 + SUIT_H_DIST*2, 300, SUIT_WIDTH, BUTTON_HEIGHT);
		diaButton.setBounds(600 + SUIT_H_DIST*3, 300, SUIT_WIDTH, BUTTON_HEIGHT);
		
		
	}
	
	
	private void attachListeners() {
		aceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rankToAdd = 1;
			}	
		});
		
		twoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rankToAdd = 2;
			}	
		});
		
		threeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rankToAdd = 3;
			}	
		});
		
		fourButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rankToAdd = 4;
			}	
		});
		
		fiveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rankToAdd = 5;
			}	
		});
		sixButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rankToAdd = 6;
			}	
		});
		sevenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rankToAdd = 7;
			}	
		});
		eightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rankToAdd = 8;
			}	
		});
		nineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rankToAdd = 9;
			}	
		});
		tenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rankToAdd = 10;
			}	
		});
		jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rankToAdd = 11;
			}	
		});
		qButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rankToAdd = 12;
			}	
		});
		kButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rankToAdd = 13;
			}	
		});
		
		heartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suitToAdd = Card.HEARTS;
			}
		});
		spadeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suitToAdd = Card.SPADE;
			}
		});
		clubButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suitToAdd = Card.CLUBS;
			}
		});
		diaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suitToAdd = Card.DIA;
			}
		});
		
		
		addUCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rankToAdd != -1 && suitToAdd != -1) {
					game.readInGUI(rankToAdd, suitToAdd);
					numUCards ++;
				}
				
				rankToAdd = -1;
				suitToAdd = -1;
			}
		});
		
		addUCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rankToAdd != -1 && suitToAdd != -1) {
					game.readInGUI(rankToAdd, suitToAdd);
					numCCards ++;
				}
				
				rankToAdd = -1;
				suitToAdd = -1;
			}
		});
		
		
	}
}
