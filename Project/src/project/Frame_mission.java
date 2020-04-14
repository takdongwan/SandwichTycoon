package project;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class Frame_mission extends JFrame implements KeyListener, MouseListener, ActionListener {

	int leftedTime;
	int limitTime = 30;
	int missionCount;
	int componentNumber;
	int amountOfComponent;
	int component_xPosition;
	int component_yPosition;

	boolean isTimerStarted = false;
	boolean isMissionLeaf = false;
	boolean isMissionBee = false;
	boolean isMissionUmbrella = false;

	Random random = new Random();

	JPanel missionPanel;
	JLabel missionInfo;
	JLabel situation;
	JLabel explanation;
	JLabel leftedTimeInfo;
	JLabel leaf;
	JLabel broom;
	JLabel bee;
	JLabel[] leafList = new JLabel[13];
	JButton beeButton;
	JButton umbrellaButton;

	private ImageIcon broom_128 = new ImageIcon(Main.class.getResource("../images/broom_128.png"));
	private ImageIcon leaf_64 = new ImageIcon(Main.class.getResource("../images/leaf_64.png"));
	private ImageIcon bee_64 = new ImageIcon(Main.class.getResource("../images/bee_64.png"));
	private ImageIcon umbrella_64 = new ImageIcon(Main.class.getResource("../images/umbrella_64.png"));

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//
//			@Override
//			public void run() {
//				new Frame_mission(2);				
//			}
//			
//		});
//	}

	// 생성자
	public Frame_mission(int missionNumber) {

		setFrame();
		setPanel();
		setLabel();

		// GenerateMission 클레스에서 생성되는 랜덤난수에 따라 미션 내용 할당
		if (missionNumber == 0) { // mission_leaf
			situation.setText("밖에서 나뭇잎이 들어와 바닥이 더러워졌습니다!!!");
			explanation.setText("키보드의 방향키를 이용해서 바닥의 나뭇잎들을 쓸어주세요!");
			mission_leaf();
		}

		else if (missionNumber == 1) { // mission_bee
			situation.setText("매장에 벌이 나타났습니다!!!");
			explanation.setText("벌을 클릭해서 모두 쫒아주세요!");
			mission_bee();
		}

		else if (missionNumber == 2) { // mission_umbrella
			situation.setText("손님이 매장에서 우산을 분실했습니다!!!");
			explanation.setText("물건들을 드래그해서 우산을 찾아주세요!");
			mission_umbrella();
		}

		else {
			System.out.println("constructor - 예외 발생");
		}
		timer();
		repaint();
	}

	public void setFrame() {
		setTitle("돌발 상황 발생");
		setSize(Main.SCREEN_WIDTH / 2, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLayout(null);
		getContentPane().setLayout(null);
		setUndecorated(true); // 임의로 미션 창을 종료할 수 없도록 undecorated 설정
		setVisible(true);
	}

	public void setPanel() {
		missionPanel = new JPanel();
		missionPanel.setLayout(null);
		missionPanel.setBounds(0, 0, Main.SCREEN_WIDTH / 2, Main.SCREEN_HEIGHT);
		missionPanel.setBackground(new Color(255, 230, 0));
		missionPanel.setFocusable(true); // 키 이벤트의 포커스 설정
		missionPanel.requestFocus(true); // 키 이벤트의 포커스 설정
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
		situation.setBounds(0, 70, Main.SCREEN_WIDTH / 2, 30); // x좌표, y좌표, 너비, 높이
		missionPanel.add(situation);

		explanation = new JLabel("- 플레이어가 해야할 미션 행동 설명 -");
		explanation.setVerticalAlignment(SwingConstants.TOP);
		explanation.setHorizontalAlignment(SwingConstants.CENTER);
		explanation.setFont(situation.getFont().deriveFont(12.0f)); // 폰트 사이즈 12
		explanation.setBounds(0, 660, Main.SCREEN_WIDTH / 2, 30); // x좌표, y좌표, 너비, 높이
		missionPanel.add(explanation);

		leftedTimeInfo = new JLabel("- 제한시간 -");
		leftedTimeInfo.setVerticalAlignment(SwingConstants.TOP);
		leftedTimeInfo.setHorizontalAlignment(SwingConstants.LEFT);
		leftedTimeInfo.setFont(situation.getFont().deriveFont(12.0f)); // 폰트 사이즈 12
		leftedTimeInfo.setBounds(50, 100, Main.SCREEN_WIDTH / 2, 30); // x좌표, y좌표, 너비, 높이
		missionPanel.add(leftedTimeInfo);
	}

	public void mission_leaf() {

		isTimerStarted = true; // 타이머 시작

		isMissionLeaf = true; // 현재 보여지는 미션 정보
		isMissionBee = false;
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

			// 빗자루 이미지와 나뭇잎 이미지가 충돌했을 경우
			if (rectangleBroom.intersects(rectangleLeaf)) {
				missionCount += 1;
				leafList[componentNumber].setLocation(-100, -100); // 충돌체크 중복을 막기 위해 rectangleLeaf 위치 변경
				System.out.println(componentNumber + " 제거됨 / leafRemovalCount: " + missionCount);

				// 모든 나뭇잎 이미지와 충돌했을 경우
				if (missionCount == leafList.length) {
					// 미션완료 JOptionPane 창 생성
					JOptionPane.showMessageDialog(null, "<html>모든 나뭇잎들을 치웠습니다.<br>OK 버튼을 누르면 게임으로 돌아갑니다.</html>",
							"미션 완료", JOptionPane.INFORMATION_MESSAGE);
					// 현재 미션 창 종료
					dispose();
				} 
				else {
					System.out.println("남은 나뭇잎: " + (leafList.length - missionCount));
				}
			} 
			else {
				System.out.println("충돌 X");
			}
			repaint();
		}
	}

	public void mission_bee() {

		isTimerStarted = true; // 타이머 시작

		isMissionLeaf = false;
		isMissionBee = true; // 현재 보여지는 미션 정보
		isMissionUmbrella = false;

		// 생성할 버튼 컴포넌트 개수
		amountOfComponent = 20;
		
		for (componentNumber = 0; componentNumber < amountOfComponent; componentNumber++) {
			component_xPosition = random.nextInt(448) + 64;
			component_yPosition = random.nextInt(448) + 110;

			beeButton = new JButton(bee_64);
			beeButton.setBounds(component_xPosition, component_yPosition, 64, 64); // x좌표, y좌표, 너비, 높이
			beeButton.setBorderPainted(false); // 버튼 외곽선 제거
			beeButton.setFocusPainted(false); // 버튼 칠 제거
			beeButton.setContentAreaFilled(false); // 버튼 칠 제거
			beeButton.addActionListener(this);
			beeButton.addMouseListener(this);
			missionPanel.add(beeButton);
		}
	}

	public void mission_umbrella() {

		isTimerStarted = true; // 타이머 시작

		isMissionLeaf = false;
		isMissionBee = false;
		isMissionUmbrella = true; // 현재 보여지는 미션 정보

		// 생성할 라벨 컴포넌트 개수 (각 40개씩)
		amountOfComponent = 40;

		// 드래그용 이미지 40개 생성 (나뭇잎)
		for (componentNumber = 0; componentNumber < amountOfComponent; componentNumber++) {
			component_xPosition = random.nextInt(448) + 64;
			component_yPosition = random.nextInt(448) + 110;

			leaf = new JLabel(leaf_64);
			leaf.setName("leaf" + componentNumber);
			leaf.setBounds(component_xPosition, component_yPosition, 64, 64); // x좌표, y좌표, 너비, 높이
			missionPanel.add(leaf);
		}

		// 드래그용 이미지 40개 생성 (빗자루)
		for (componentNumber = 0; componentNumber < amountOfComponent; componentNumber++) {
			component_xPosition = random.nextInt(448) + 64;
			component_yPosition = random.nextInt(448) + 110;

			broom = new JLabel(broom_128);
			broom.setName("broom" + componentNumber);
			broom.setBounds(component_xPosition, component_yPosition, 128, 128);
			missionPanel.add(broom);
		}

		// 드래그용 이미지 40개 생성 (벌)
		for (componentNumber = 0; componentNumber < amountOfComponent; componentNumber++) {
			component_xPosition = random.nextInt(448) + 64;
			component_yPosition = random.nextInt(448) + 110;

			bee = new JLabel(bee_64);
			bee.setName("bee" + componentNumber);
			bee.setBounds(component_xPosition, component_yPosition, 64, 64);
			missionPanel.add(bee);
		}

		// 컴포넌트 드래그 클래스
		DragImage drag = new DragImage(missionPanel.getComponents());

		// 우산 버튼 생성
		umbrellaButton = new JButton(umbrella_64);
		umbrellaButton.setBounds(component_xPosition, component_yPosition, 64, 64); // x좌표, y좌표, 너비, 높이
		umbrellaButton.setBorderPainted(false); // 버튼 외곽선 제거
		umbrellaButton.setFocusPainted(false); // 버튼 칠 제거
		umbrellaButton.setContentAreaFilled(false); // 버튼 칠 제거
		umbrellaButton.addActionListener(this);
		umbrellaButton.addMouseListener(this);
		missionPanel.add(umbrellaButton);

	}

	public void timer() {
		System.out.println("타이머 실행됨");

		SwingWorker<Boolean, Void> timer = new SwingWorker<Boolean, Void>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				for (leftedTime = limitTime; leftedTime >= 0; leftedTime--) {
					leftedTimeInfo.setText(Integer.toString(leftedTime) + "초");
					System.out.println(leftedTime + "초");
					Thread.sleep(1000);
				}
				return true;
			}

			protected void done() {
				Boolean status;

				if (status = true) {
					JOptionPane.showMessageDialog(null, "<html>미션을 완료하지 못했습니다.<br>OK 버튼을 누르면 게임으로 돌아갑니다.</html>",
							"미션 실패", JOptionPane.ERROR_MESSAGE);
					dispose();

					leftedTime = limitTime;
					leftedTimeInfo.setText(Integer.toString(leftedTime) + "초");
				} else {
					System.out.println("미션 강제종료");
					leftedTime = limitTime;
					leftedTimeInfo.setText(Integer.toString(leftedTime) + "초");
				}
			}

		};
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		beeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		umbrellaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		beeButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		umbrellaButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (isMissionLeaf == true) {
			int broom_xSpeed = 12;
			int broom_ySpeed = 12;

			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				broom.setLocation(broom.getX() - broom_xSpeed, broom.getY());
				System.out.println("왼쪽으로 " + broom_xSpeed + "이동");
			}

			else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				broom.setLocation(broom.getX() + broom_xSpeed, broom.getY());
				System.out.println("오른쪽으로 " + broom_xSpeed + "이동");
			}

			else if (e.getKeyCode() == KeyEvent.VK_UP) {
				broom.setLocation(broom.getX(), broom.getY() - broom_ySpeed);
				System.out.println("위쪽으로 " + broom_ySpeed + "이동");
			}

			else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				broom.setLocation(broom.getX(), broom.getY() + broom_ySpeed);
				System.out.println("아래쪽으로 " + broom_ySpeed + "이동");
			}

			else {
				System.out.println("예외 발생");
				System.out.println(broom.getX());
				System.out.println(broom.getY());
			}

			// 빗자루와 나뭇잎 이미지의 충돌 체크
			checkCollision();
			repaint();

			System.out.println("빗자루 x위치: " + broom.getX());
			System.out.println("빗자루 y위치: " + broom.getY());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// mission_bee 에서
		if (isMissionBee == true) {
			
			//  beeButton 클릭했을 경우
			if (e.getSource() == beeButton) {
				missionCount += 1;
				missionPanel.remove(beeButton);
				System.out.println(missionCount);
				repaint();

				// 모든 버튼 클릭했을 경우 (미션완료)
				if (missionCount == amountOfComponent) {
					
					// 미션완료 JOptionPane 창 생성
					JOptionPane.showMessageDialog(null, "<html>모든 벌을 쫒았습니다.<br>OK 버튼을 누르면 게임으로 돌아갑니다.</html>", "미션 완료",
							JOptionPane.INFORMATION_MESSAGE);
					// 현재 미션 창 종료
					dispose();
				}

				else {
					System.out.println("남은 벌: " + (amountOfComponent - missionCount));
				}
			} 
			
			else {
			}
		}

		// mission_umbrella 에서 
		else if (isMissionUmbrella == true) {
			
			// umbrellaButton 클릭했을 경우 (미션완료)
			if (e.getSource() == umbrellaButton) {
				
				// 미션완료 JOptionPane 창 생성
				JOptionPane.showMessageDialog(null, "<html>손님의 우산을 찾았습니다.<br>OK 버튼을 누르면 게임으로 돌아갑니다.</html>", "미션 완료",
						JOptionPane.INFORMATION_MESSAGE);
				// 현재 미션 창 종료
				dispose();
			}
			else {
				
			}

		}
		else {
			
		}
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
