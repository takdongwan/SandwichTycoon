package project;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Frame_minigame extends JFrame implements ActionListener, MouseListener, KeyListener {

	JPanel minigamePanel;

	JLabel minigameInfo;
	JLabel explanation;
	
	JButton backButton;
	
	Timer timer;
	
	int delay = 10;
	int playerX = 310;
	int ballX = 120;
	int ballY = 350;
	int ballXdir = -1;
	int ballYdir = -2;
	int score = 0;
	boolean play = false;
	
	
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	

	public Frame_minigame() {
		
		setJFrame();
		setJPanel();
		setJLabel();
		setJButton();
		setTimer();
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

		explanation = new JLabel("- 미니게임 설명 -");
		explanation.setVerticalAlignment(SwingConstants.TOP);
		explanation.setHorizontalAlignment(SwingConstants.CENTER);
		explanation.setFont(explanation.getFont().deriveFont(12.0f)); // 폰트 사이즈 12
		explanation.setBounds(0, 70, Main.SCREEN_WIDTH, 30); // x좌표, y좌표, 너비, 높이
		minigamePanel.add(explanation);
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
	
	public void setTimer() {
		timer = new Timer(delay, this);
		timer.start();
		repaint();
	}
	
	public void paint(Graphics g) {
		//background
		g.setColor(new Color(255, 230, 0));
		g.fillRect(0, 0, 692, 592);
		
		// paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		
		// ball
		g.setColor(Color.black);
		g.fillOval(ballX, ballY, 20, 20);
		
		// string
		g.drawString("키보드의 좌우 방향키를 이용해 모든 벽돌을 부수세요!", 200, 600);
		
		g.dispose();
		
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
			
			dispose();
		}
		
		else {
	//		System.out.println("actionPerformed - 예외 발생");
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 600) {
				playerX = 600;
			}
			else {
				play = true;
				playerX += 20;			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX < 10) {
				playerX = 10;
			}
			else {
				play = true;
				playerX -= 20;
			}
		}
		else {
			
		}
		System.out.println(playerX);
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
