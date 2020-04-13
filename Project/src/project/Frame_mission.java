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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;

public class Frame_mission extends JFrame {

	int missionNumber;
	int missionCount;
	int componentNumber;
	int amountOfComponent;
	int component_xPosition;
	int component_yPosition;
	int mouseStart_xPosition;
	int mouseStart_yPosition;
	int mouseEnd_xPosition;
	int mouseEnd_yPosition;

	boolean isPressed;
	boolean isMissionLeaf = false;
	boolean isMissionbee = false;
	boolean isMissionUmbrella = false;

	Random random = new Random();

	JPanel missionPanel;
	JLabel missionInfo;
	JLabel situation;
	JLabel explanation;
	JLabel leaf;
	JLabel broom;
	JLabel bee;
	JLabel[] leafList = new JLabel[13];
	JButton umbrella;

	private ImageIcon broom_128 = new ImageIcon(Main.class.getResource("../images/broom_128.png"));
	private ImageIcon leaf_64 = new ImageIcon(Main.class.getResource("../images/leaf_64.png"));
	private ImageIcon bee_64 = new ImageIcon(Main.class.getResource("../images/bee_64.png"));
	private ImageIcon umbrella_64 = new ImageIcon(Main.class.getResource("../images/umbrella_64.png"));

	public static void main(String[] args) {
		new Frame_mission(2);
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
			mission_bee();
			repaint();

		}

		else if (missionNumber == 2) {
			situation.setText("손님이 매장에서 우산을 분실했습니다!!!");
			explanation.setText("물건들을 드래그해서 우산을 찾아주세요!");
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
		isMissionbee = false;
		isMissionUmbrella = false;

		// 빗자루 이미지라벨 생성
		broom = new JLabel(broom_128);
		broom.setBounds(256, 260, 128, 128); // x좌표, y좌표, 너비, 높이
		missionPanel.add(broom);

		// 랜덤위치에 leafList 초기화
		for (componentNumber = 0; componentNumber < leafList.length; componentNumber++) {
			component_xPosition = random.nextInt(448) + 64;
			component_yPosition = random.nextInt(448) + 110;

			leafList[componentNumber] = new JLabel(leaf_64);
			leafList[componentNumber].setBounds(component_xPosition, component_yPosition, 64, 64);
			missionPanel.add(leafList[componentNumber]);
		}
	}

	public void checkCollision() {

		// 빗자루 이미지와 나뭇잎 이미지 충돌체크
		for (componentNumber = 0; componentNumber < leafList.length; componentNumber++) {
			Rectangle2D rectangleBroom = new Rectangle2D.Float(broom.getX() + 30, broom.getY() + 78, 20, 20);
			Rectangle2D rectangleLeaf = new Rectangle2D.Float(leafList[componentNumber].getX(),
					leafList[componentNumber].getY(), 64, 64);

			if (rectangleBroom.intersects(rectangleLeaf)) {
				missionCount += 1;
				leafList[componentNumber].setLocation(-100, -100); // 충돌체크 중복을 막기 위해 rectangleLeaf 위치 변경

				System.out.println(componentNumber + " 제거됨 ---------------");
				System.out.println("leafRemoveCount: " + missionCount);

				if (missionCount == 13) {
					JOptionPane.showMessageDialog(null, "<html>모든 나뭇잎들을 치웠습니다.<br>OK 버튼을 누르면 게임으로 돌아갑니다.</html>",
							"미션 완료", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
			repaint();
		}
	}

	public void mission_bee() {

		isMissionLeaf = false;
		isMissionbee = true; // 현재 보여지는 미션 정보
		isMissionUmbrella = false;

		amountOfComponent = 20;

		for (int i = 0; i < amountOfComponent; i++) {
			component_xPosition = random.nextInt(448) + 64;
			component_yPosition = random.nextInt(448) + 110;

			JButton beeButton = new JButton(bee_64);
			beeButton.setBounds(component_xPosition, component_yPosition, 64, 64); // x좌표, y좌표, 너비, 높이
			beeButton.setBorderPainted(false); // 버튼 외곽선 제거
			beeButton.setFocusPainted(false); // 버튼 칠 제거
			beeButton.setContentAreaFilled(false); // 버튼 칠 제거
			beeButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == beeButton) {
						missionCount += 1;
						missionPanel.remove(beeButton);
						System.out.println(missionCount);
						repaint();

						if (missionCount == amountOfComponent) {
							JOptionPane.showMessageDialog(null, "<html>모든 벌을 쫒았습니다.<br>OK 버튼을 누르면 게임으로 돌아갑니다.</html>",
									"미션 완료", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} else {
							System.out.println("남은 벌: " + (amountOfComponent - missionCount));
						}
					} else {
						System.out.println("예외 발생");
					}
				}

			});
			missionPanel.add(beeButton);
		}
	}

	public void mission_umbrella() {

		isMissionLeaf = false;
		isMissionbee = false;
		isMissionUmbrella = true; // 현재 보여지는 미션 정보

		// 드래그용 이미지 20개씩 생성 (나뭇잎)
		amountOfComponent = 30;
		
		for (componentNumber = 0; componentNumber < amountOfComponent; componentNumber++) {
			component_xPosition = random.nextInt(448) + 64;
			component_yPosition = random.nextInt(448) + 110;

			leaf = new JLabel(leaf_64);
			leaf.setName("leaf" + componentNumber);
			leaf.setBounds(component_xPosition, component_yPosition, 64, 64); // x좌표, y좌표, 너비, 높이
			missionPanel.add(leaf);
		}

		// 드래그용 이미지 20개씩 생성 (빗자루)
		for (componentNumber = 0; componentNumber < amountOfComponent; componentNumber++) {
			component_xPosition = random.nextInt(448) + 64;
			component_yPosition = random.nextInt(448) + 110;

			broom = new JLabel(broom_128);
			broom.setName("broom" + componentNumber);
			broom.setBounds(component_xPosition, component_yPosition, 128, 128);
			missionPanel.add(broom);
		}

		// 드래그용 이미지 20개씩 생성 (벌)
		for (componentNumber = 0; componentNumber < amountOfComponent; componentNumber++) {
			component_xPosition = random.nextInt(448) + 64;
			component_yPosition = random.nextInt(448) + 110;

			bee = new JLabel(bee_64);
			bee.setName("bee" + componentNumber);
			bee.setBounds(component_xPosition, component_yPosition, 64, 64);
			missionPanel.add(bee);
		}

		// 우산 버튼 생성
		umbrella = new JButton(umbrella_64);
		umbrella.setBounds(component_xPosition, component_yPosition, 64, 64); // x좌표, y좌표, 너비, 높이
		umbrella.setBorderPainted(false); // 버튼 외곽선 제거
		umbrella.setFocusPainted(false); // 버튼 칠 제거
		umbrella.setContentAreaFilled(false); // 버튼 칠 제거
		umbrella.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "<html>손님의 우산을 찾았습니다.<br>OK 버튼을 누르면 게임으로 돌아갑니다.</html>", "미 완료",
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}

		});
		missionPanel.add(umbrella);

	}
}
