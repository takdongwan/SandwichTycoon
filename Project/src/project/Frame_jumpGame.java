package project;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
	
	int score = 0;
	int catXspeed = 20;
	int catYspeed = 40;
	
	boolean isClear = false;
	boolean isAlive = true;
	
	Timer catTimer;

	ImageIcon catImage = new ImageIcon(new ImageIcon(Main.class.getResource("../images/nyanCat.png")).getImage().getScaledInstance(296, 69, Image.SCALE_SMOOTH));

	
	public static void main(String[] args) {

		new Frame_jumpGame();

	}
	
	public Frame_jumpGame() {
		setJFrame();
		setJPanel();
		setJLabel();
		catTimer();
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
				
				if(cat.getY() >= Main.SCREEN_HEIGHT - cat.getHeight()) {
					cat.setLocation(cat.getX(), Main.SCREEN_HEIGHT - cat.getHeight());
				}
				else {
					cat.setLocation(cat.getX(), cat.getY() + 1);
				}
			}
			
		});
		catTimer.start();
		repaint();

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
			} 
			else {
				cat.setLocation(cat.getX() - catXspeed, cat.getY());
			}
		}

		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			// 고양이 이미지가 우측 끝에 위치할 경우
			if (cat.getX() >= Main.SCREEN_WIDTH - cat.getWidth()) {
				cat.setLocation(cat.getX(), cat.getY());
			} 
			else {
				cat.setLocation(cat.getX() + catXspeed, cat.getY());
			}
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			// 고양이 이미지가 위쪽 끝에 위치할 경우
			if (cat.getY() <= 0) {
				cat.setLocation(cat.getX(), 0);
			} 
			else {
				cat.setLocation(cat.getX(), cat.getY() - catYspeed);
			}
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			// 고양이 이미지가 아래쪽 끝에 위치할 경우
			if (cat.getY() >= Main.SCREEN_HEIGHT - cat.getHeight()) {
				cat.setLocation(cat.getX(), Main.SCREEN_HEIGHT - cat.getHeight());
			} 
			else {
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
