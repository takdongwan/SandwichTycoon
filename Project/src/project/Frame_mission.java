package project;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Frame_mission extends JFrame {

	// 공통
	JPanel missionPanel;
	JLabel missionInfo;
	JLabel situation;
	JLabel explanation;
	int missionNumber;
	Random random = new Random();
	boolean isMissionLeaf = false;
	boolean isMissionBug = false;
	boolean isMissionUmbrella = false;

	// mission_leaf
	JLabel broom;
	JLabel[] leafList = new JLabel[13];
	int leafNumber;
	int leaf_xPosition;
	int leaf_yPosition;
	int leafRemovalCount;
	private ImageIcon broom_128 = new ImageIcon(Main.class.getResource("../images/broom_128.png"));
	private ImageIcon leaf_64 = new ImageIcon(Main.class.getResource("../images/leaf_64.png"));

	// mission_bug
	int bugNumber;
	int bug_xPosition;
	int bug_yPosition;
	int clickCount;
	JButton[] bugList = new JButton[13];
	private ImageIcon bug_64 = new ImageIcon(Main.class.getResource("../images/bee_64.png"));

	// mission_umbrella
	int mouseStart_xPosition;
	int mouseStart_yPosition;
	int mouseEnd_xPosition;
	int mouseEnd_yPosition;
	int stuffNumber;
	int stuff_xPosition;
	int stuff_yPosition;
	boolean isPressed;
	JButton umbrella;
	JLabel[] stuffList = new JLabel[100];
	private ImageIcon umbrella_64 = new ImageIcon(Main.class.getResource("../images/umbrella_64.png"));

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
			situation.setText("매장에 벌이 나타났습니다!!!");
			explanation.setText("벌을 클릭해서 모두 쫒아주세요!");
			mission_bug();
			repaint();

		}

		else if (missionNumber == 2) {
			situation.setText("매장에서 손님이 우산을 분실했습니다!!!");
			explanation.setText("물건들을 드래그해서 우산을 찾은 후 클릭해주세요!");
			mission_umbrella();
			repaint();

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
		missionPanel.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (isMissionLeaf == true) {
					int broom_xSpeed = 12;
					int broom_ySpeed = 12;

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

					checkCollision();
					repaint();

					System.out.println("빗자루 x위치: " + broom.getX());
					System.out.println("빗자루 y위치: " + broom.getY());
				}				
			}
			@Override
			public void keyTyped(KeyEvent e) {				
			}
			@Override
			public void keyReleased(KeyEvent e) {				
			}
		});
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
		explanation.setBounds(1, 660, Main.SCREEN_WIDTH / 2, 30); // x좌표, y좌표, 너비, 높이
		missionPanel.add(explanation);
	}

	public void mission_leaf() {

		isMissionLeaf = true; // 현재 보여지는 미션 정보
		isMissionBug = false;
		isMissionUmbrella = false;

		// 빗자루 이미지라벨 생성
		broom = new JLabel(broom_128);
		broom.setBounds(256, 260, 128, 128); // x좌표, y좌표, 너비, 높이
		missionPanel.add(broom);

		// 랜덤위치에 leafList 초기화
		for (leafNumber = 0; leafNumber < leafList.length; leafNumber++) {
			leaf_xPosition = random.nextInt(448) + 64;
			leaf_yPosition = random.nextInt(448) + 120;

			leafList[leafNumber] = new JLabel(leaf_64);
			leafList[leafNumber].setBounds(leaf_xPosition, leaf_yPosition, 64, 64);
			missionPanel.add(leafList[leafNumber]);
		}
	}

	public void checkCollision() {

		// 빗자루 이미지와 나뭇잎 이미지 충돌체크
		for (leafNumber = 0; leafNumber < leafList.length; leafNumber++) {
			Rectangle2D rectangleBroom = new Rectangle2D.Float(broom.getX() + 30, broom.getY() + 78, 20, 20);
			Rectangle2D rectangleLeaf = new Rectangle2D.Float(leafList[leafNumber].getX(), leafList[leafNumber].getY(),
					64, 64);

			if (rectangleBroom.intersects(rectangleLeaf)) {
				leafRemovalCount += 1;
				leafList[leafNumber].setLocation(-100, -100); // 충돌체크 중복을 막기 위해 rectangleLeaf 위치 변경

				System.out.println(leafNumber + " 제거됨 ---------------");
				System.out.println("leafRemoveCount: " + leafRemovalCount);

				if (leafRemovalCount == 13) {
					JOptionPane.showMessageDialog(null, "<html>모든 나뭇잎들을 치웠습니다.<br>OK 버튼을 누르면 게임으로 돌아갑니다.</html>",
							"처리 완료", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
			repaint();
		}
	}

	public void mission_bug() {

		isMissionLeaf = false;
		isMissionBug = true; // 현재 보여지는 미션 정보
		isMissionUmbrella = false;

		for (bugNumber = 0; bugNumber < bugList.length; bugNumber++) {
			bug_xPosition = random.nextInt(448) + 64;
			bug_yPosition = random.nextInt(448) + 120;

			bugList[bugNumber] = new JButton(bug_64);
			bugList[bugNumber].setBounds(bug_xPosition, bug_yPosition, 64, 64); // x좌표, y좌표, 너비, 높이
			bugList[bugNumber].setBorderPainted(false); // 버튼 외곽선 제거
			bugList[bugNumber].setFocusPainted(false); // 버튼 칠 제거
			bugList[bugNumber].setContentAreaFilled(false); // 버튼 칠 제거
			bugList[bugNumber].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					clickCount += 1;
					if (clickCount > 2) {
						missionPanel.remove(bugList[bugNumber]);
					}
					System.out.println(clickCount);					
				}
				
			});
			missionPanel.add(bugList[bugNumber]);
		}
	}

	public void mission_umbrella() {

		isMissionLeaf = false;
		isMissionBug = false;
		isMissionUmbrella = true; // 현재 보여지는 미션 정보

		// 드래그할 이미지 생성
		for (stuffNumber = 0; stuffNumber < stuffList.length; stuffNumber++) {
			stuff_xPosition = random.nextInt(448) + 64;
			stuff_yPosition = random.nextInt(448) + 120;

			// 인덱스가 짝수인 경우엔 나뭇잎 이미지,
			if (stuffNumber % 2 == 0) {
				stuffList[stuffNumber] = new JLabel(leaf_64);
			}
			// 인덱스가 홀수인 경우엔 벌레 이미지가 보여짐
			else {
				stuffList[stuffNumber] = new JLabel(bug_64);
			}
			stuffList[stuffNumber].setBounds(stuff_xPosition, stuff_yPosition, 64, 64); // x좌표, y좌표, 너비, 높이
			stuffList[stuffNumber].addMouseListener(new MouseListener() {

				@Override
				public void mousePressed(MouseEvent e) {
					if (e.getSource() == stuffList[stuffNumber]) {
						isPressed = true;
						mouseStart_xPosition = e.getX();
						mouseStart_yPosition = e.getY();
						System.out.println(stuffList[stuffNumber] + "눌림");
					}
				}
				@Override
				public void mouseReleased(MouseEvent e) {	
					isPressed = false;
				}
				@Override
				public void mouseClicked(MouseEvent e) {					
				}
				@Override
				public void mouseEntered(MouseEvent e) {					
				}
				@Override
				public void mouseExited(MouseEvent e) {					
				}				
			});
			stuffList[stuffNumber].addMouseMotionListener(new MouseMotionListener() {

				@Override
				public void mouseDragged(MouseEvent e) {
					if (isPressed == true) {
						mouseEnd_xPosition = e.getX();
						mouseEnd_yPosition = e.getY();
						stuffList[stuffNumber].setLocation(stuff_xPosition - mouseStart_xPosition - mouseEnd_xPosition, stuff_yPosition - mouseStart_yPosition - mouseEnd_yPosition);
						System.out.println("드래그됨");
					}					
				}
				@Override
				public void mouseMoved(MouseEvent e) {					
				}
				
			});
			missionPanel.add(stuffList[stuffNumber]);
		}

		// 우산 버튼 생성
		umbrella = new JButton(umbrella_64);
		umbrella.setBounds(stuff_xPosition, stuff_yPosition, 64, 64); // x좌표, y좌표, 너비, 높이
		umbrella.setBorderPainted(false); // 버튼 외곽선 제거
		umbrella.setFocusPainted(false); // 버튼 칠 제거
		umbrella.setContentAreaFilled(false); // 버튼 칠 제거
		umbrella.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "<html>손님의 우산을 찾았습니다.<br>OK 버튼을 누르면 게임으로 돌아갑니다.</html>",
						"처리 완료", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
			
		});
		missionPanel.add(umbrella);

	}
}
