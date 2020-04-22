package project;

import java.awt.Color;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
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
	JLabel obstacleEmpty;

	int score = 0;
	int randomObstacle;
	int catXspeed = 20;
	int catYspeed = 40;
	int obstacleNumber;
	int obstacle_yPosition;
	int obstacleXspeed;

	boolean isClear = false;
	boolean isAlive = true;

	Timer catTimer;
	Timer obstacleMover;
	Timer obstacleGenerator;
	Random random;

	ArrayList<JLabel> devilArrayList;
	ArrayList<JLabel> bombArrayList;
	ArrayList<JLabel> collisionArrayList;
	ArrayList<JLabel> emptyArrayList;

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

		moveCat();
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

	public void moveCat() {
		catTimer = new Timer(25, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// cat이 화면 하단에 위치할 경우
				if (cat.getY() >= Main.SCREEN_HEIGHT - cat.getHeight()) {
					cat.setLocation(cat.getX(), Main.SCREEN_HEIGHT - cat.getHeight());
				}
				// 그 외의 경우 y위치를 계속해서 +1 --- 서서히 떨어지는 움직임 구현
				else {
					cat.setLocation(cat.getX(), cat.getY() + 1);
				}
			}

		});
		catTimer.start();
		repaint();

	}

	public void moveObstacle() {

		Iterator<JLabel> devilIterator = devilArrayList.iterator();
		Iterator<JLabel> bombIterator = bombArrayList.iterator();
		Iterator<JLabel> collisionIterator = collisionArrayList.iterator();
		Iterator<JLabel> emptyIterator = emptyArrayList.iterator();

		while (devilIterator.hasNext()) {

			JLabel devilPosition = devilIterator.next();
			devilPosition.setLocation(devilPosition.getX() - 1, devilPosition.getY());

			if (devilPosition.getX() < -64) {
				devilIterator.remove();
			}
			else {
				
			}
		}
		
		while (bombIterator.hasNext()) {

			JLabel bombPosition = bombIterator.next();
			bombPosition.setLocation(bombPosition.getX() - 2, bombPosition.getY());

			if (bombPosition.getX() < -64) {
				bombIterator.remove();
			}
			else {
				
			}
		}
		
		while (collisionIterator.hasNext()) {

			JLabel collisionPosition = collisionIterator.next();
			collisionPosition.setLocation(collisionPosition.getX() - 3, collisionPosition.getY());

			if (collisionPosition.getX() < -64) {
				collisionIterator.remove();
			}
			else {
				
			}
		}
		
		while (emptyIterator.hasNext()) {

			JLabel emptyPosition = emptyIterator.next();
			emptyPosition.setLocation(emptyPosition.getX(), emptyPosition.getY());

			if (emptyPosition.getX() == Main.SCREEN_WIDTH) {
				emptyIterator.remove();
				System.out.println("emptyIterator 제거됨");
			}
			else {
				
			}
		}
		

		repaint();
	}

	public void generateObstacles() {

		devilArrayList = new ArrayList<JLabel>();
		bombArrayList = new ArrayList<JLabel>();
		collisionArrayList = new ArrayList<JLabel>();
		emptyArrayList = new ArrayList<JLabel>();
		
		// 2초에 한 번 장애물 생성
		obstacleGenerator = new Timer(25, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				
				// 랜덤한 장애물을 생성하기 위해 랜덤난수 생성
				random = new Random();
				randomObstacle = random.nextInt(200);

				// 랜덤난수가 0인 경우 - obstacleDevil 생성
				if (randomObstacle == 0) {

					// 랜덤한 y위치 지정
					random = new Random();
					obstacle_yPosition = random.nextInt(Main.SCREEN_HEIGHT - 64);

					obstacleDevil = new JLabel(devil_64);
					obstacleDevil.setBounds(Main.SCREEN_WIDTH, obstacle_yPosition, 64, 64); // x좌표, y좌표, 너비, 높이
					jumpGamePanel.add(obstacleDevil);
					devilArrayList.add(obstacleDevil);
					moveObstacle();

					System.out.println("devil 생성됨");

				}
				// 랜덤난수가 1인 경우 - obstacleBomb 생성
				else if (randomObstacle == 1) {

					// 랜덤한 y위치 지정
					random = new Random();
					obstacle_yPosition = random.nextInt(Main.SCREEN_HEIGHT - 64);

					obstacleBomb = new JLabel(bomb_64);
					obstacleBomb.setBounds(Main.SCREEN_WIDTH, obstacle_yPosition, 64, 64); // x좌표, y좌표, 너비, 높이
					jumpGamePanel.add(obstacleBomb);
					bombArrayList.add(obstacleBomb);
					moveObstacle();

					System.out.println("bomb 생성됨");

				}
				// 랜덤난수가 2인 경우 - obstacleCollision 생성
				else if (randomObstacle == 2) {

					// 랜덤한 y위치 지정
					random = new Random();
					obstacle_yPosition = random.nextInt(Main.SCREEN_HEIGHT - 64);

					obstacleCollision = new JLabel(collision_64);
					obstacleCollision.setBounds(Main.SCREEN_WIDTH, obstacle_yPosition, 64, 64); // x좌표, y좌표, 너비, 높이
					jumpGamePanel.add(obstacleCollision);
					collisionArrayList.add(obstacleCollision);
					moveObstacle();

					System.out.println("collision 생성됨");

				}
				else {
					obstacleEmpty = new JLabel("");
					obstacleEmpty.setBounds(Main.SCREEN_WIDTH, obstacle_yPosition, 64, 64); // x좌표, y좌표, 너비, 높이
					jumpGamePanel.add(obstacleEmpty);
					emptyArrayList.add(obstacleEmpty);
					moveObstacle();

					System.out.println("empty 생성됨");

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
