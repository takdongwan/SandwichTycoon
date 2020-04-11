package project;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Frame_mission extends JFrame implements ActionListener, KeyListener, MouseListener {

	// 공통
	JPanel missionPanel;
	JLabel missionInfo;
	JLabel situation;
	JLabel explanation;
	int topMargin = 92;
	int bottomMargin = 464;
	int leftMargin = 64;
	int rightMargin = 436; // Main.SCREEN_WIDTH / 2 - leftMargin - imageWidth
	int missionNumber;
	Image screenImage;
	Graphics screenGraphic;
	Random random = new Random();

	// mission_leaf
	JLabel swapInfo;
	JLabel broom;
	JLabel[] leafList = new JLabel[13];
	int broom_xSpeed = 12;
	int broom_ySpeed = 12;
	int leaf_xPosition;
	int leaf_yPosition;
	private ImageIcon broom_128 = new ImageIcon(Main.class.getResource("../images/broom_128.png"));
	private ImageIcon leaf_64 = new ImageIcon(Main.class.getResource("../images/leaf_64.png"));

	// mission_bug
	int bug_xPosition;
	int bug_yPosition;
	int clickCount = 0;
	private ImageIcon bug_64 = new ImageIcon(Main.class.getResource("../images/bee_64.png"));
	JButton bug = new JButton(bug_64);
	JButton[] bugList = new JButton[13];

	public static void main(String[] args) {
		new Frame_mission(0);
	}

	public Frame_mission(int missionNumber) {
		setFrame();
		setPanel();
		setLabel();

		if (missionNumber == 0) {
			situation.setText("밖에서 나뭇잎이 들어와 바닥이 더러워졌습니다!!!");
			explanation.setText("키보드의 방향키를 이용해서 바닥의 나뭇잎들을 쓸어주세요!");
			mission_leaf();
			repaint();
		}

		else if (missionNumber == 1) {
			situation.setText("매장에 벌레가 나타났습니다!!!");
			explanation.setText("벌레를 모두 눌러 쫒아주세요!");
			mission_bug();
		}

		else if (missionNumber == 2) {
			situation.setText("매장에서 손님이 우산을 분실했습니다!!!");
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
		missionPanel.setFocusable(true);
		missionPanel.requestFocus();
		missionPanel.addKeyListener(this);
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

	public void mission_leaf() {
		broom = new JLabel(broom_128);
		broom.setBounds(256, 260, 128, 128); // x좌표, y좌표, 너비, 높이
		broom.addKeyListener(this);
		missionPanel.add(broom);

		for (int i = 0; i < leafList.length; i++) {
			leaf_xPosition = random.nextInt(448) + 64;
			leaf_yPosition = random.nextInt(448) + 200;

			leafList[i] = new JLabel(leaf_64);
			leafList[i].setBounds(leaf_xPosition, leaf_yPosition, 64, 64);
			missionPanel.add(leafList[i]);
			
			if ((leaf_xPosition < broom.getX() + 50) && (broom.getX() + 50 < leaf_xPosition + 64) && (leaf_yPosition < broom.getY() + 78) && (broom.getY() + 78 < leaf_yPosition + 64)) {
				missionPanel.remove(leafList[i]);
				System.out.println(leafList[i] + "제거됨");
			}
			repaint();
		}

	}
	
	public void checkCollision() {
		
	}

	public void mission_bug() {
		for (int i = 0; i < bugList.length; i++) {
			bug_xPosition = random.nextInt(448) + 64;
			bug_yPosition = random.nextInt(448) + 200;

			bugList[i] = new JButton(bug_64);
			bugList[i].setBounds(bug_xPosition, bug_yPosition, 64, 64); // x좌표, y좌표, 너비, 높이
			bugList[i].setBorderPainted(false); // 버튼 외곽선 제거
			bugList[i].setFocusPainted(false); // 버튼 칠 제거
			bugList[i].setContentAreaFilled(false); // 버튼 칠 제거
			bugList[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(clickCount);
					clickCount += 1;
					if (clickCount > 2) {
						// missionPanel.remove(bugList[i]);
					} else {

					}
				}

			});
			missionPanel.add(bugList[i]);
		}

	}

	public void mission_umbrella() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == e.VK_LEFT) {
			broom.setLocation(broom.getX() - broom_xSpeed, broom.getY());
			System.out.println("왼쪽으로 " + broom_xSpeed + "이동");
		}

		else if (e.getKeyCode() == e.VK_RIGHT) {
			broom.setLocation(broom.getX() + broom_xSpeed, broom.getY());
			System.out.println("오른쪽으로 " + broom_xSpeed + "이동");
		}

		else if (e.getKeyCode() == e.VK_UP) {
			broom.setLocation(broom.getX(), broom.getY() - broom_ySpeed);
			System.out.println("위쪽으로 " + broom_ySpeed + "이동");
		}

		else if (e.getKeyCode() == e.VK_DOWN) {
			broom.setLocation(broom.getX(), broom.getY() + broom_ySpeed);
			System.out.println("아래쪽으로 " + broom_ySpeed + "이동");
		}

		else {
			System.out.println("예외 발생");
			System.out.println(broom.getX());
			System.out.println(broom.getY());
		}
		
		repaint();
		System.out.println("repaint 메소드 실행");

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

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
	public void mouseEntered(MouseEvent e) {
		bug.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		bug.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

}
