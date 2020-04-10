package project;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Frame_mission extends JFrame implements ActionListener, KeyListener {

	JPanel missionPanel;
	JLabel missionInfo;
	JLabel situation;
	JLabel explanation;

	int missionNumber;
	
	private ImageIcon sponge_64 = new ImageIcon(Main.class.getResource("../images/sponge_64.png"));

//	public static void main(String[] args) {
//	new Frame_mission();
//}
	
	public Frame_mission(int missionNumber) {
		setFrame();
		setPanel();
		setLabel();

		if (missionNumber == 0) {
			situation.setText("손님이 실수로 콜라를 바닥에 쏟았습니다");
			explanation.setText("키보드의 좌우 방향키를 이용해서 바닥을 10번 닦아주세요!");
			mission_coke();
		}
		else if (missionNumber == 1) {
			situation.setText("매장에 벌레가 나타났습니다");
			explanation.setText("벌레를 모두 눌러 쫒아주세요!");
			mission_bug();
		} 
		else if (missionNumber == 2) {
			situation.setText("매장에서 손님이 우산을 분실했습니다");
			explanation.setText("물건들을 드래그해서 우산을 찾아주세요!");
			mission_umbrella();
		} 
		else {
			System.out.println("예외 발생");
		}
	}

	public void setFrame() {
		setTitle("돌발 상황 발생");
		setSize(Main.SCREEN_WIDTH / 2, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLayout(null);
		getContentPane().setLayout(null);
		setUndecorated(true);
		setVisible(true);
	}

	public void setPanel() {
		missionPanel = new JPanel();
		missionPanel.setLayout(null);
		missionPanel.setBounds(0, 0, Main.SCREEN_WIDTH / 2, Main.SCREEN_HEIGHT);
		missionPanel.setBackground(new Color(255, 230, 0));
		getContentPane().add(missionPanel);
	}

	public void setLabel() {
		missionInfo = new JLabel("돌발 상황 발생");
		missionInfo.setVerticalAlignment(SwingConstants.TOP);
		missionInfo.setHorizontalAlignment(SwingConstants.CENTER);
		missionInfo.setFont(missionInfo.getFont().deriveFont(20.0f)); // 폰트 사이즈 20
		missionInfo.setBounds(0, 40, Main.SCREEN_WIDTH / 2, 30); // x좌표, y좌표, 너비, 높이
		missionPanel.add(missionInfo);
		
		situation = new JLabel("- 미션 상황 설명 -");
		situation.setVerticalAlignment(SwingConstants.TOP);
		situation.setHorizontalAlignment(SwingConstants.CENTER);
		situation.setFont(situation.getFont().deriveFont(12.0f)); // 폰트 사이즈 12
		situation.setBounds(1, 70, Main.SCREEN_WIDTH / 2, 30); // x좌표, y좌표, 너비, 높이
		missionPanel.add(situation);
		
		explanation = new JLabel("- 플레이어가 해야할 미션 행동 설명 -");
		explanation.setVerticalAlignment(SwingConstants.TOP);
		explanation.setHorizontalAlignment(SwingConstants.CENTER);
		explanation.setFont(situation.getFont().deriveFont(12.0f)); // 폰트 사이즈 12
		explanation.setBounds(1, 630, Main.SCREEN_WIDTH / 2, 30); // x좌표, y좌표, 너비, 높이
		missionPanel.add(explanation);
	}

	public void mission_coke() {
		
	}
	
	public void mission_bug() {
		
	}
	
	public void mission_umbrella() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
