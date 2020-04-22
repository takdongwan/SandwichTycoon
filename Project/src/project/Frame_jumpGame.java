package project;

import java.awt.Color;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Frame_jumpGame extends JFrame implements KeyListener {

	JPanel jumpGamePanel;

	JLabel jumpGameInfo;
	JLabel explanation;
	JLabel scoreInfo;
	JLabel cat;

	JLabel obstacleDevil;
	JLabel obstacleBomb;
	JLabel obstacleCollision;

	int score = 0;
	int delayTime;
	int randomObstacle;
	int catXspeed = 20;
	int catYspeed = 40;
	int obstacleNumber;
	int obstacle_yPosition;
	int obstacleXspeed = 30;

	boolean isClear = false;
	boolean isAlive = true;

	Timer catTimer;
	Timer obstacleMover;
	Timer obstacleGenerator;
	Random random;
	
	ArrayList<JLabel> obstacleArrayList;
	
	private ImageIcon devil_64 = new ImageIcon(Main.class.getResource("../images/devil_64.png"));
	private ImageIcon bomb_64 = new ImageIcon(Main.class.getResource("../images/bomb_64.png"));
	private ImageIcon collision_64 = new ImageIcon(Main.class.getResource("../images/collision_64.png"));
	ImageIcon catImage = new ImageIcon(new ImageIcon(Main.class.getResource("../images/nyanCat.png")).getImage()
			.getScaledInstance(296, 69, Image.SCALE_SMOOTH));

	
	public static void main(String[] args) {

		new Frame_jumpGame();

	}

	public Frame_jumpGame() {
		setJFrame();
		setJPanel();
		setJLabel();

		catTimer();
		
		generateObstacles();
	}

	public void setJFrame() {

		setTitle("점프게임..");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLayout(null);
		getContentPane().setLayout(null);
		setUndecorated(true); // 임의로 미니게임 창을 종료할 수 없도록 undecorated 설정
		setVisible(true);
	}

	public void setJPanel() {

		jumpGamePanel = new JPanel();
		jumpGamePanel.setLayout(null);
		jumpGamePanel.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		jumpGamePanel.setBackground(new Color(255, 230, 0));
		jumpGamePanel.setFocusable(true);
		jumpGamePanel.addKeyListener(this);
		getContentPane().add(jumpGamePanel);
	}

	public void setJLabel() {

		jumpGameInfo = new JLabel("장애물 피하기");
		jumpGameInfo.setVerticalAlignment(SwingConstants.TOP);
		jumpGameInfo.setHorizontalAlignment(SwingConstants.CENTER);
		jumpGameInfo.setFont(jumpGameInfo.getFont().deriveFont(20.0f)); // 폰트 사이즈 20
		jumpGameInfo.setBounds(0, 40, Main.SCREEN_WIDTH, 30); // x좌표, y좌표, 너비, 높이
		jumpGamePanel.add(jumpGameInfo);

		explanation = new JLabel("키보드의 방향키를 이용해서 다가오는 장애물들을 피하세요!");
		explanation.setVerticalAlignment(SwingConstants.TOP);
		explanation.setHorizontalAlignment(SwingConstants.CENTER);
		explanation.setFont(explanation.getFont().deriveFont(12.0f)); // 폰트 사이즈 12
		explanation.setBounds(0, 70, Main.SCREEN_WIDTH, 30); // x좌표, y좌표, 너비, 높이
		jumpGamePanel.add(explanation);

		scoreInfo = new JLabel("현재 점수: " + score);
		scoreInfo.setVerticalAlignment(SwingConstants.TOP);
		scoreInfo.setHorizontalAlignment(SwingConstants.CENTER);
		scoreInfo.setFont(explanation.getFont().deriveFont(12.0f)); // 폰트 사이즈 12
		scoreInfo.setBounds(0, 650, Main.SCREEN_WIDTH, 30); // x좌표, y좌표, 너비, 높이
		jumpGamePanel.add(scoreInfo);

		cat = new JLabel(catImage);
		cat.setVerticalAlignment(SwingConstants.TOP);
		cat.setHorizontalAlignment(SwingConstants.CENTER);
		cat.setBounds(200, 300, catImage.getIconWidth(), catImage.getIconHeight()); // x좌표, y좌표, 너비, 높이
		jumpGamePanel.add(cat);

	}

	public void catTimer() {
		catTimer = new Timer(25, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (cat.getY() >= Main.SCREEN_HEIGHT - cat.getHeight()) {
					cat.setLocation(cat.getX(), Main.SCREEN_HEIGHT - cat.getHeight());
				} else {
					cat.setLocation(cat.getX(), cat.getY() + 1);
				}
			}

		});
		catTimer.start();
		repaint();

	}

	public void moveObstacles() {
		
		obstacleMover = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

//				if (devilArray[obstacleNumber].getX() > -64) {
//					devilArray[obstacleNumber].setLocation(devilArray[obstacleNumber].getX() - obstacleXspeed, devilArray[obstacleNumber].getY());
//				}
//				else {
//		//			devilArray[].remove(obstacleNumber);
//				}
			}

		});

	}

	public void generateObstacles() {

		obstacleArrayList = new ArrayList<JLabel>();
		
		random = new Random();
		delayTime = random.nextInt(6);
		obstacleGenerator = new Timer(1500 * delayTime, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				random = new Random();
				randomObstacle = random.nextInt(3);
				
				if (randomObstacle == 0) {
					
					random = new Random();
					obstacle_yPosition = random.nextInt(Main.SCREEN_HEIGHT - 64);

					obstacleDevil = new JLabel(devil_64);
					obstacleDevil.setBounds(100, obstacle_yPosition, 64, 64); // x좌표, y좌표, 너비, 높이
					jumpGamePanel.add(obstacleDevil);
					
					obstacleArrayList.add(obstacleDevil);
					System.out.println("devil 생성됨");
					
				}
				else if (randomObstacle == 1) {
					
					random = new Random();
					obstacle_yPosition = random.nextInt(Main.SCREEN_HEIGHT - 64);

					obstacleBomb = new JLabel(bomb_64);
					obstacleBomb.setBounds(100, obstacle_yPosition, 64, 64); // x좌표, y좌표, 너비, 높이
					jumpGamePanel.add(obstacleBomb);
					
					obstacleArrayList.add(obstacleBomb);
					System.out.println("bomb 생성됨");

				}
				else if (randomObstacle == 2) {
					
					random = new Random();
					obstacle_yPosition = random.nextInt(Main.SCREEN_HEIGHT - 64);

					obstacleCollision = new JLabel(collision_64);
					obstacleCollision.setBounds(100, obstacle_yPosition, 64, 64); // x좌표, y좌표, 너비, 높이
					jumpGamePanel.add(obstacleCollision);
					
					obstacleArrayList.add(obstacleCollision);
					System.out.println("collision 생성됨");

				}
				repaint();
			}

		});
		obstacleGenerator.start();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			// 고양이 이미지가 좌측 끝에 위치할 경우
			if (cat.getX() <= -100) {
				cat.setLocation(-100, cat.getY());
			} else {
				cat.setLocation(cat.getX() - catXspeed, cat.getY());
			}
		}

		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			// 고양이 이미지가 우측 끝에 위치할 경우
			if (cat.getX() >= Main.SCREEN_WIDTH - cat.getWidth()) {
				cat.setLocation(cat.getX(), cat.getY());
			} else {
				cat.setLocation(cat.getX() + catXspeed, cat.getY());
			}
		}

		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			// 고양이 이미지가 위쪽 끝에 위치할 경우
			if (cat.getY() <= 0) {
				cat.setLocation(cat.getX(), 0);
			} else {
				cat.setLocation(cat.getX(), cat.getY() - catYspeed);
			}
		}

		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			// 고양이 이미지가 아래쪽 끝에 위치할 경우
			if (cat.getY() >= Main.SCREEN_HEIGHT - cat.getHeight()) {
				cat.setLocation(cat.getX(), Main.SCREEN_HEIGHT - cat.getHeight());
			} else {
				cat.setLocation(cat.getX(), cat.getY() + catYspeed);
			}
		}

		else {

		}

		repaint();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
