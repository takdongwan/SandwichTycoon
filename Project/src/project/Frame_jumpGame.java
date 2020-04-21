package project;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Frame_jumpGame extends JFrame {

	JPanel jumpGamePanel;
	
	JLabel jumpGameInfo;
	JLabel explanation;
	JLabel scoreInfo;
	
	int score = 0;

	
	public Frame_jumpGame() {
		setJFrame();
		setJPanel();
		setJLabel();
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

		jumpGamePanel = new JPanel();
		jumpGamePanel.setLayout(null);
		jumpGamePanel.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		jumpGamePanel.setBackground(new Color(255, 230, 0));
		getContentPane().add(jumpGamePanel);
	}

	public void setJLabel() {

		jumpGameInfo = new JLabel("재료상점");
		jumpGameInfo.setVerticalAlignment(SwingConstants.TOP);
		jumpGameInfo.setHorizontalAlignment(SwingConstants.CENTER);
		jumpGameInfo.setFont(jumpGameInfo.getFont().deriveFont(20.0f)); // 폰트 사이즈 20
		jumpGameInfo.setBounds(0, 40, Main.SCREEN_WIDTH, 30); // x좌표, y좌표, 너비, 높이
		jumpGamePanel.add(jumpGameInfo);

		explanation = new JLabel("구매를 원하는 만큼 각 재료를 클릭해주세요!");
		explanation.setVerticalAlignment(SwingConstants.TOP);
		explanation.setHorizontalAlignment(SwingConstants.CENTER);
		explanation.setFont(explanation.getFont().deriveFont(12.0f)); // 폰트 사이즈 12
		explanation.setBounds(0, 70, Main.SCREEN_WIDTH, 30); // x좌표, y좌표, 너비, 높이
		jumpGamePanel.add(explanation);
		
		scoreInfo = new JLabel("획득 골드: " + score);
		scoreInfo.setVerticalAlignment(SwingConstants.TOP);
		scoreInfo.setHorizontalAlignment(SwingConstants.CENTER);
		scoreInfo.setFont(explanation.getFont().deriveFont(12.0f)); // 폰트 사이즈 12
		scoreInfo.setBounds(0, 650, Main.SCREEN_WIDTH, 30); // x좌표, y좌표, 너비, 높이
		jumpGamePanel.add(scoreInfo);
		
	}
	
}
