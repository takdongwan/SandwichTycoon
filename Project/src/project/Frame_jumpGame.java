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
	int catYspeed = 50;
	
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

		explanation = new JLabel("스페이바를 눌러서 다가오는 장애물을 피하세요!");
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
		cat.setBounds(200, 650, catImage.getIconWidth(), catImage.getIconHeight()); // x좌표, y좌표, 너비, 높이
		jumpGamePanel.add(cat);
		
	}
	
	public void catTimer() {
		catTimer = new Timer(25, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cat.setLocation(cat.getX(), cat.getY() + 1);
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
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			cat.setLocation(cat.getX(), cat.getY() - catYspeed);
			System.out.println(cat.getY());
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
