package project;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Frame_jumpGame extends JFrame implements KeyListener, MouseListener, ActionListener {

	JPanel jumpGamePanel;

	JLabel background;
	JLabel jumpGameInfo;
	JLabel explanation;
	JLabel scoreInfo;
	JLabel cat;
	JLabel lifeInfo;

	JLabel obstacleMoney;
	JLabel obstacleDevil;
	JLabel obstacleBomb;
	JLabel obstacleCollision;
	JLabel obstacleEmpty;

	JButton backButton;

	int score = 0;
	int life = 3;
	int randomObstacle;
	int catXspeed = 20;
	int catYspeed = 20;
	int obstacleNumber;
	int obstacle_yPosition;
	int obstacleXspeed;

	boolean isClear = false;
	boolean isAlive = true;

	Timer catTimer;
	Timer obstacleGenerator;
	Random random;

	Rectangle catRect;
	Rectangle moneyRect;
	Rectangle devilRect;
	Rectangle bombRect;
	Rectangle collisionRect;

	ArrayList<JLabel> moneyArrayList;
	ArrayList<JLabel> devilArrayList;
	ArrayList<JLabel> bombArrayList;
	ArrayList<JLabel> collisionArrayList;
	ArrayList<JLabel> emptyArrayList;

	private ImageIcon money_64 = new ImageIcon(Main.class.getResource("../images/moneyWithWing_64.png"));
	private ImageIcon devil_64 = new ImageIcon(Main.class.getResource("../images/devil_64.png"));
	private ImageIcon bomb_64 = new ImageIcon(Main.class.getResource("../images/bomb_64.png"));
	private ImageIcon collision_64 = new ImageIcon(Main.class.getResource("../images/collision_64.png"));
	ImageIcon catImage = new ImageIcon(new ImageIcon(Main.class.getResource("../images/nyanCat.png")).getImage()
			.getScaledInstance(296, 69, Image.SCALE_SMOOTH));
	private ImageIcon backgroundImage = new ImageIcon(Main.class.getResource("../images/minigameBackground.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));

	
//	public static void main(String[] args) {
//
//		new Frame_jumpGame();
//
//	}

	public Frame_jumpGame() {
		setJFrame();
		setJPanel();
		setJLabel();
		setJButton();

		moveCat();
		generateObstacle();

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
		jumpGamePanel.setOpaque(false);
		jumpGamePanel.setFocusable(true);
		jumpGamePanel.addKeyListener(this);
		getContentPane().add(jumpGamePanel);

		// 배경 이미지 JLabel
		background = new JLabel(backgroundImage);
		background.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight()); // x좌표, y좌표, 너비, 높이
		getContentPane().add(background);
	}

	public void setJLabel() {
		
		jumpGameInfo = new JLabel("장애물 피하기");
		jumpGameInfo.setVerticalAlignment(SwingConstants.TOP);
		jumpGameInfo.setHorizontalAlignment(SwingConstants.CENTER);
		jumpGameInfo.setFont(jumpGameInfo.getFont().deriveFont(20.0f)); // 폰트 사이즈 20
		jumpGameInfo.setBounds(0, 40, Main.SCREEN_WIDTH, 30); // x좌표, y좌표, 너비, 높이
		jumpGameInfo.setForeground(Color.white);
		jumpGamePanel.add(jumpGameInfo);

		explanation = new JLabel("키보드의 방향키를 이용해서 다가오는 장애물들을 피하고, 돈을 먹어 점수를 획득하세요!");
		explanation.setVerticalAlignment(SwingConstants.TOP);
		explanation.setHorizontalAlignment(SwingConstants.CENTER);
		explanation.setFont(explanation.getFont().deriveFont(12.0f)); // 폰트 사이즈 12
		explanation.setBounds(0, 70, Main.SCREEN_WIDTH, 30); // x좌표, y좌표, 너비, 높이
		explanation.setForeground(Color.white);
		jumpGamePanel.add(explanation);

		scoreInfo = new JLabel("SCORE: " + score);
		scoreInfo.setVerticalAlignment(SwingConstants.TOP);
		scoreInfo.setHorizontalAlignment(SwingConstants.CENTER);
		scoreInfo.setFont(scoreInfo.getFont().deriveFont(12.0f)); // 폰트 사이즈 12
		scoreInfo.setBounds(0, 650, Main.SCREEN_WIDTH, 30); // x좌표, y좌표, 너비, 높이
		scoreInfo.setForeground(Color.white);
		jumpGamePanel.add(scoreInfo);

		cat = new JLabel(catImage);
		cat.setVerticalAlignment(SwingConstants.TOP);
		cat.setHorizontalAlignment(SwingConstants.CENTER);
		cat.setBounds(200, 300, catImage.getIconWidth(), catImage.getIconHeight()); // x좌표, y좌표, 너비, 높이
		cat.setForeground(Color.white);
		jumpGamePanel.add(cat);

		lifeInfo = new JLabel("LIFE: " + life);
		lifeInfo.setVerticalAlignment(SwingConstants.TOP);
		lifeInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lifeInfo.setFont(lifeInfo.getFont().deriveFont(12.0f)); // 폰트 사이즈 12
		lifeInfo.setBounds(0, 630, Main.SCREEN_WIDTH, 30); // x좌표, y좌표, 너비, 높이
		lifeInfo.setForeground(Color.white);
		jumpGamePanel.add(lifeInfo);
	
	}

	public void setJButton() {

		// 되돌아가기 버튼
		backButton = new JButton(backButtonBasicImage);
		backButton.setBounds(20, 30, 60, 60); // x좌표, y좌표, 너비, 높이
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(this);
		backButton.addActionListener(this);
		jumpGamePanel.add(backButton);
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

		checkCollision();
		repaint();

	}

	public void moveObstacle() {

		Iterator<JLabel> devilIterator = devilArrayList.iterator();
		Iterator<JLabel> bombIterator = bombArrayList.iterator();
		Iterator<JLabel> collisionIterator = collisionArrayList.iterator();
		Iterator<JLabel> moneyIterator = moneyArrayList.iterator();
		Iterator<JLabel> emptyIterator = emptyArrayList.iterator();

		// devilArrayList 움직임 구현
		while (devilIterator.hasNext()) {

			JLabel devilPosition = devilIterator.next();

			// 장애물이 화면 외 범위로 이동을 완료했을 경우
			if (devilPosition.getX() < -64) {
				devilIterator.remove();
			} else {
				devilPosition.setLocation(devilPosition.getX() - 1, devilPosition.getY());
			}
		}
		// bombArrayList 움직임 구현
		while (bombIterator.hasNext()) {

			JLabel bombPosition = bombIterator.next();

			// 장애물이 화면 외 범위로 이동을 완료했을 경우
			if (bombPosition.getX() < -64) {
				bombIterator.remove();
			} else {
				bombPosition.setLocation(bombPosition.getX() - 2, bombPosition.getY());
			}
		}
		// collisionArrayList 움직임 구현
		while (collisionIterator.hasNext()) {

			JLabel collisionPosition = collisionIterator.next();

			// 장애물이 화면 외 범위로 이동을 완료했을 경우
			if (collisionPosition.getX() < -64) {
				collisionIterator.remove();
			} else {
				collisionPosition.setLocation(collisionPosition.getX() - 3, collisionPosition.getY());
			}
		}
		// moneyArrayList 움직임 구현
		while (moneyIterator.hasNext()) {

			JLabel moneyPosition = moneyIterator.next();

			// 장애물이 화면 외 범위로 이동을 완료했을 경우
			if (moneyPosition.getX() < -64) {
				moneyIterator.remove();
			} else {
				moneyPosition.setLocation(moneyPosition.getX() - 4, moneyPosition.getY());
			}
		}
		// 연속적인 화면 움직임을 위한 emptyArrayList
		while (emptyIterator.hasNext()) {

			JLabel emptyPosition = emptyIterator.next();

			// 생성과 동시에 제거
			if (emptyPosition.getX() == Main.SCREEN_WIDTH) {
				emptyIterator.remove();
			} else {
				emptyPosition.setLocation(emptyPosition.getX(), emptyPosition.getY());
			}
		}

		checkCollision();
		repaint();
	}

	public void generateObstacle() {

		moneyArrayList = new ArrayList<JLabel>();
		devilArrayList = new ArrayList<JLabel>();
		bombArrayList = new ArrayList<JLabel>();
		collisionArrayList = new ArrayList<JLabel>();
		emptyArrayList = new ArrayList<JLabel>();

		obstacleGenerator = new Timer(25, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// 랜덤한 타이밍에 랜덤한 장애물을 생성하기 위해 랜덤난수 생성
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
				// 랜덤난수가 3인 경우 - obstacleMoney 생성
				else if (randomObstacle == 3) {

					// 랜덤한 y위치 지정
					random = new Random();
					obstacle_yPosition = random.nextInt(Main.SCREEN_HEIGHT - 64);

					obstacleMoney = new JLabel(money_64);
					obstacleMoney.setBounds(Main.SCREEN_WIDTH, obstacle_yPosition, 64, 64); // x좌표, y좌표, 너비, 높이
					jumpGamePanel.add(obstacleMoney);
					moneyArrayList.add(obstacleMoney);
					moveObstacle();

					System.out.println("money 생성됨");

				}
				// 랜덤난수가 0, 1, 2, 3이 아닐 경우 빈 JLabel 객체 생성
				else {
					obstacleEmpty = new JLabel("");
					obstacleEmpty.setBounds(Main.SCREEN_WIDTH, obstacle_yPosition, 64, 64); // x좌표, y좌표, 너비, 높이
					jumpGamePanel.add(obstacleEmpty);
					emptyArrayList.add(obstacleEmpty);
					moveObstacle();

				}
				repaint();
			}

		});
		obstacleGenerator.start();
	}

	public void checkCollision() {

		catRect = new Rectangle(cat.getX() + 230, cat.getY(), cat.getWidth() - 230, cat.getHeight()); // x좌표, y좌표, 너비, 높이

		// ArrayList가 null이 아닐 경우
		if ((devilArrayList != null) || (bombArrayList != null) || (collisionArrayList != null)) {

			// devilArrayList의 obstacleRect 생성
			for (int i = 0; i < devilArrayList.size(); i++) {
				devilRect = new Rectangle(devilArrayList.get(i).getX() + 10, devilArrayList.get(i).getY() + 10,
						devilArrayList.get(i).getWidth() - 20, devilArrayList.get(i).getHeight() - 20); // x좌표, y좌표, 너비, 높이

				// catRect과 obstacleRect이 충돌했을 경우
				if (catRect.intersects(devilRect)) {

					// life 1 차감
					life -= 1;
					lifeInfo.setText("LIFE: " + life);

					// 이미 충돌했으므로 추가적인 충돌을 막기 위해 화면 외 범위로 위치 변경
					devilArrayList.get(i).setLocation(-100, -100);
					devilArrayList.remove(i);
					System.out.println("devilArrayList 충돌함");
				} else {
				}
			}

			// bombArrayList의 obstacleRect 생성
			for (int i = 0; i < bombArrayList.size(); i++) {
				bombRect = new Rectangle(bombArrayList.get(i).getX() + 10, bombArrayList.get(i).getY() + 10,
						bombArrayList.get(i).getWidth() - 20, bombArrayList.get(i).getHeight() - 20); // x좌표, y좌표, 너비,
																										// 높이
				// catRect과 obstacleRect이 충돌했을 경우
				if (catRect.intersects(bombRect)) {

					// life 1 차감
					life -= 1;
					lifeInfo.setText("LIFE: " + life);

					// 이미 충돌했으므로 추가적인 충돌을 막기 위해 화면 외 범위로 위치 변경
					bombArrayList.get(i).setLocation(-100, -100);
					bombArrayList.remove(i);
					System.out.println("bombArrayList 충돌함");

				} else {
				}
			}

			// collisionArrayList의 obstacleRect 생성
			for (int i = 0; i < collisionArrayList.size(); i++) {
				collisionRect = new Rectangle(collisionArrayList.get(i).getX() + 10,
						collisionArrayList.get(i).getY() + 10, collisionArrayList.get(i).getWidth() - 20,
						collisionArrayList.get(i).getHeight() - 20); // x좌표, y좌표, 너비, 높이

				// catRect과 obstacleRect이 충돌했을 경우
				if (catRect.intersects(collisionRect)) {

					// life 1 차감
					life -= 1;
					lifeInfo.setText("LIFE: " + life);

					// 이미 충돌했으므로 추가적인 충돌을 막기 위해 화면 외 범위로 위치 변경
					collisionArrayList.get(i).setLocation(-100, -100);
					collisionArrayList.remove(i);
					System.out.println("collisionArrayList 충돌함");

				} else {
				}
			}
			// devilArrayList의 obstacleRect 생성
			for (int i = 0; i < moneyArrayList.size(); i++) {
				moneyRect = new Rectangle(moneyArrayList.get(i).getX() + 10, moneyArrayList.get(i).getY() + 10,
						moneyArrayList.get(i).getWidth() - 20, moneyArrayList.get(i).getHeight() - 20); // x좌표, y좌표, 너비, 높이

				// catRect과 obstacleRect이 충돌했을 경우
				if (catRect.intersects(moneyRect)) {

					// life 1 차감
					score += 100;
					scoreInfo.setText("SCORE: " + score);

					// 이미 충돌했으므로 추가적인 충돌을 막기 위해 화면 외 범위로 위치 변경
					moneyArrayList.get(i).setLocation(-100, -100);
					moneyArrayList.remove(i);
					System.out.println("moneyArrayList 충돌함");
				} else {
				}
			}
		}

		else {
		}

		checkGameOver();
	}

	public void checkGameOver() {
		
		if (life <= 0) {

			catTimer.stop();
			obstacleGenerator.stop();
			score = 0;
			life = 3;
			// 미니게임 종료 팝업창 생성
			JOptionPane.showMessageDialog(null, "<html>GAME OVER!<br>OK 버튼을 누르면 메인 화면으로 돌아갑니다.</html>", "[GAME OVER]",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();

		} else {

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

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

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// 되돌아가기 버튼 클릭했을 경우
		if (e.getSource().equals(backButton)) {

			catTimer.stop();
			obstacleGenerator.stop();
			score = 0;
			life = 3;
			dispose();

		}

		else {

		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backButton.setIcon(backButtonEnteredImage);

	}

	@Override
	public void mouseExited(MouseEvent e) {
		backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		backButton.setIcon(backButtonBasicImage);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
