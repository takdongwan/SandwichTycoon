package project;

import javax.swing.JFrame;

public class Frame_minigame extends JFrame {

	public Frame_minigame() {
		setJFrame();
		setJPanel();
		setJLabel();
		setJButton();
	}

	public void setJFrame() {
		setTitle("미니게임");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLayout(null);
		getContentPane().setLayout(null);
		setUndecorated(true); // 임의로 상점 창을 종료할 수 없도록 undecorated 설정
		setVisible(true);
	}

	public void setJPanel() {

	}

	public void setJLabel() {

	}

	public void setJButton() {

	}
}
