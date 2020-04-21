package project;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Frame_minigame extends JFrame implements ActionListener, MouseListener, KeyListener {

	JPanel minigamePanel;

	JLabel minigameInfo;
	JLabel explanation;
	JLabel ball;
	JLabel slider;
	JLabel leftedTimeInfo;
	JLabel scoreInfo;
	JLabel moneyArray[][];

	JButton backButton;

	Timer timer;
	Timer moveBall;

	int score = 0;
	int leftedTime;
	int limitTime = 30;
	int ballXspeed = 5;
	int ballYspeed = 5;
	int leftMargin = 240;
	int rightMargin = 1040;
	int moneyRow = 3;
	int moneyColumn = 11;

	boolean isClear = false;
	boolean isAlive = true;
	boolean isMinigame = true;
	
	Rectangle2D moneyRectangle;
	Rectangle2D ballRectangle;

	private ImageIcon ball_64 = new ImageIcon(Main.class.getResource("../images/ball_64.png"));
	private ImageIcon slider_128 = new ImageIcon(Main.class.getResource("../images/slider_128.png"));
	private ImageIcon money_64 = new ImageIcon(Main.class.getResource("../images/money_64.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));

	public Frame_minigame() {

		setJFrame();
		setJPanel();
		setJLabel();
		setJButton();
		generateMoney();

		timer();
		moveBall();
		repaint();
	}

	public void setJFrame() {

		setTitle("미니게임");
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

		minigamePanel = new JPanel();
		minigamePanel.setLayout(null);
		minigamePanel.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		minigamePanel.setBackground(new Color(255, 230, 0));
		minigamePanel.addKeyListener(this);
		minigamePanel.setFocusable(true);
		getContentPane().add(minigamePanel);
	}

	public void setJLabel() {

		minigameInfo = new JLabel("미니게임");
		minigameInfo.setVerticalAlignment(SwingConstants.TOP);
		minigameInfo.setHorizontalAlignment(SwingConstants.CENTER);
		minigameInfo.setFont(minigameInfo.getFont().deriveFont(20.0f)); // 폰트 사이즈 20
		minigameInfo.setBounds(0, 40, Main.SCREEN_WIDTH, 30); // x좌표, y좌표, 너비, 높이
		minigamePanel.add(minigameInfo);

		explanation = new JLabel("키보드의 좌우 방향키를 이용해 돈을 맞추세요!");
		explanation.setVerticalAlignment(SwingConstants.TOP);
		explanation.setHorizontalAlignment(SwingConstants.CENTER);
		explanation.setFont(explanation.getFont().deriveFont(12.0f)); // 폰트 사이즈 12
		explanation.setBounds(0, 70, Main.SCREEN_WIDTH, 30); // x좌표, y좌표, 너비, 높이
		minigamePanel.add(explanation);

		ball = new JLabel(ball_64);
		ball.setVerticalAlignment(SwingConstants.TOP);
		ball.setHorizontalAlignment(SwingConstants.CENTER);
		ball.setBounds(360, 300, 64, 64); // x좌표, y좌표, 너비, 높이
		minigamePanel.add(ball);

		slider = new JLabel(slider_128);
		slider.setVerticalAlignment(SwingConstants.TOP);
		slider.setHorizontalAlignment(SwingConstants.CENTER);
		slider.setBounds(576, 540, 128, 128); // x좌표, y좌표, 너비, 높이
		minigamePanel.add(slider);

		leftedTimeInfo = new JLabel("- 제한시간 -");
		leftedTimeInfo.setVerticalAlignment(SwingConstants.TOP);
		leftedTimeInfo.setHorizontalAlignment(SwingConstants.CENTER);
		leftedTimeInfo.setFont(leftedTimeInfo.getFont().deriveFont(20.0f)); // 폰트 사이즈 20
		leftedTimeInfo.setBounds(0, 620, Main.SCREEN_WIDTH, 30); // x좌표, y좌표, 너비, 높이
		minigamePanel.add(leftedTimeInfo);

		scoreInfo = new JLabel("- 현재 점수 -");
		scoreInfo.setVerticalAlignment(SwingConstants.TOP);
		scoreInfo.setHorizontalAlignment(SwingConstants.CENTER);
		scoreInfo.setFont(explanation.getFont().deriveFont(12.0f)); // 폰트 사이즈 12
		scoreInfo.setBounds(0, 650, Main.SCREEN_WIDTH, 30); // x좌표, y좌표, 너비, 높이
		minigamePanel.add(scoreInfo);
	}

	public void setJButton() {
		// 되돌아가기 버튼
		backButton = new JButton(backButtonBasicImage);
		backButton.setBounds(20, 30, 60, 60); // x좌표, y좌표, 너비, 높이
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(this);
		backButton.addActionListener(this);
		minigamePanel.add(backButton);
	}

	public void generateMoney() {

		moneyArray = new JLabel[moneyRow][moneyColumn];

		for (int i = 0; i < moneyRow; i++) {
			for (int j = 0; j < moneyColumn; j++) {

				moneyArray[i][j] = new JLabel(money_64);
				moneyArray[i][j].setBounds(leftMargin + 48 + 64 * j, 100 + 64 * i, 64, 64); // x좌표, y좌표, 너비, 높이
				minigamePanel.add(moneyArray[i][j]);
			}
		}
	}

	public void checkCollision() {
		for (int i = 0; i < moneyRow; i++) {
			for (int j = 0; j < moneyColumn; j++) {

				moneyRectangle = new Rectangle2D.Double(moneyArray[i][j].getX(), moneyArray[i][j].getY(), moneyArray[i][j].getWidth(), moneyArray[i][j].getHeight());
				ballRectangle = new Rectangle2D.Double(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
				
				
				if (ballRectangle.intersects(moneyRectangle)) {
					score += 100;
					moneyArray[i][j].setLocation(-100, -100);
										
					if (score >= 3300) {
						isClear = true;
					}
					else {
						
					}
				}
				else {
					
				}
				repaint();
				
//				moneyArray[i][j] = new JLabel(money_64);
//				moneyArray[i][j].setBounds(leftMargin + 16 + 64 * j, 100 + 64 * i, 64, 64); // x좌표, y좌표, 너비, 높이
//				minigamePanel.add(moneyArray[i][j]);
			}
		}
	}

	public void timer() {

		leftedTime = limitTime;
		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (isMinigame == true) {
					// 제한시간이 0 이하일 경우 || 제한시간 내에 게임을 클리어한 경우
					if (leftedTime < 0  || isClear == true) {
						timer.stop();

						// 미니게임 종료 팝업창 생성
						JOptionPane.showMessageDialog(null, "<html>GAME CLEAR!<br>OK 버튼을 누르면 상점으로 돌아갑니다.</html>",
								"미니게임 종료", JOptionPane.ERROR_MESSAGE);

						// 미니게임 창 종료
						dispose();
						Player.currentMoney += score;
					}

//					// 시간 내에 게임을 완료했을 경우
//					else if (isClear == true) {
//						timer.stop();
//
//						// 미니게임 종료 팝업창 생성
//						JOptionPane.showMessageDialog(null, "<html>GAME OVER 2<br>OK 버튼을 누르면 상점으로 돌아갑니다.</html>",
//								"미니게임 종료", JOptionPane.ERROR_MESSAGE);
//
//						// 미니게임 창 종료
//						dispose();
//						Player.currentMoney += score;
//					}
					
					else if (isAlive == false) {
						timer.stop();

						// 미니게임 종료 팝업창 생성
						JOptionPane.showMessageDialog(null, "<html>GAME OVER<br>OK 버튼을 누르면 상점으로 돌아갑니다.</html>",
								"미니게임 종료", JOptionPane.ERROR_MESSAGE);

						// 미니게임 창 종료
						dispose();
						Player.currentMoney += score;
					}

					else {
						leftedTimeInfo.setText(leftedTime + "초");
						leftedTime--;
					}
				} 
				else if (isMinigame == false) {
					timer.stop();
					leftedTime = limitTime;
				}
			}

		});
		timer.start();
	}

	public void moveBall() {
		moveBall = new Timer(20, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (isMinigame == true) {
					ball.setLocation(ball.getX() + ballXspeed, ball.getY() + ballYspeed);

					// 공이 좌측 끝 마진에 위치할 경우
					if (ball.getX() < leftMargin) {
						ballXspeed = -ballXspeed;
					}
					// 공이 우측 끝 마진에 위치할 경우
					else if (ball.getX() > rightMargin - ball.getWidth()) {
						ballXspeed = -ballXspeed;
					}
					// 공이 위쪽 끝 마진에 위치할 경우
					else if (ball.getY() < 0) {
						ballYspeed = -ballYspeed;
					}
					// 공이 화면 밖으로 벗어난 경우
					else if (ball.getY() > Main.SCREEN_HEIGHT) {
						isAlive = false;
						moveBall.stop();
					}
					// 공과 슬라이더의 y좌표가 동일할 경우
					else if (ball.getY() == 540) {
						
						// 공과 슬라이더가 충돌했을 경우
						if ((slider.getX() <= ball.getX() + 32) && (ball.getX() +32 <= slider.getX() + slider.getWidth())) {
							ballYspeed = -ballYspeed;
						} 
						else {

						}
					}
					checkCollision();
					repaint();
				} 
				else if (isMinigame == false) {
					moveBall.stop();
				}
			}

		});
		moveBall.start();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(backButton)) {

			isMinigame = false;
			dispose();
		}

		else {

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			// 슬라이더가 좌측 끝 마진에 위치할 경우
			if (slider.getX() <= leftMargin) {
				slider.setLocation(slider.getX(), slider.getY());
			} else {
				slider.setLocation(slider.getX() - 20, slider.getY());
			}
		}

		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			// 슬라이더가 우측 끝 마진에 위치할 경우
			if (slider.getX() >= rightMargin - slider.getWidth()) {
				slider.setLocation(slider.getX(), slider.getY());
			} else {
				slider.setLocation(slider.getX() + 20, slider.getY());
			}
		}

		else {

		}

		System.out.println(slider.getX());
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
