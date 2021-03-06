package project;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Frame_store extends JFrame implements ActionListener, MouseListener {

	JPanel storePanel;

	JLabel storeInfo;
	JLabel explanation;
	static JLabel amountOfSandwichInfo;
	static JLabel amountOfHotdogInfo;
	static JLabel amountOfCokeInfo;
	static JLabel currentMoneyInfo;
	static JLabel totalPriceInfo;

	JButton buySandwich;
	JButton buyHotdog;
	JButton buyCoke;
	JButton buy;
	JButton init;
	JButton backButton;
	JButton trade;
	JButton minigame;

	Frame_trade tradeFrame;
	Frame_minigame minigameFrame;

	static int totalAmount;
	static int sandwichPrice = 100;
	static int hotdogPrice = 100;
	static int cokePrice = 50;

	static int amountOfSandwich = 0;
	static int amountOfHotdog = 0;
	static int amountOfCoke = 0;

	static ImageIcon sandwich_128 = new ImageIcon(Main.class.getResource("../images/sandwich_128.png"));
	static ImageIcon coke_128 = new ImageIcon(Main.class.getResource("../images/coke_128.png"));
	static ImageIcon hotdog_128 = new ImageIcon(Main.class.getResource("../images/hotdog_128.png"));
	ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));

	public Frame_store() {
		setJFrame();
		setJPanel();
		setJLabel();
		setJButton();
	}

	public void setJFrame() {

		setTitle("재료상점");
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

		storePanel = new JPanel();
		storePanel.setLayout(null);
		storePanel.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		storePanel.setBackground(new Color(255, 230, 0));
		getContentPane().add(storePanel);
	}

	public void setJLabel() {

		storeInfo = new JLabel("재료상점");
		storeInfo.setVerticalAlignment(SwingConstants.TOP);
		storeInfo.setHorizontalAlignment(SwingConstants.CENTER);
		storeInfo.setFont(storeInfo.getFont().deriveFont(20.0f)); // 폰트 사이즈 20
		storeInfo.setBounds(0, 40, Main.SCREEN_WIDTH, 30); // x좌표, y좌표, 너비, 높이
		storePanel.add(storeInfo);

		explanation = new JLabel("구매를 원하는 만큼 각 재료를 클릭해주세요!");
		explanation.setVerticalAlignment(SwingConstants.TOP);
		explanation.setHorizontalAlignment(SwingConstants.CENTER);
		explanation.setFont(explanation.getFont().deriveFont(12.0f)); // 폰트 사이즈 12
		explanation.setBounds(0, 70, Main.SCREEN_WIDTH, 30); // x좌표, y좌표, 너비, 높이
		storePanel.add(explanation);

		// 샌드위치 개수 표시
		amountOfSandwichInfo = new JLabel(amountOfSandwich + "개");
		amountOfSandwichInfo.setVerticalAlignment(SwingConstants.TOP);
		amountOfSandwichInfo.setHorizontalAlignment(SwingConstants.CENTER);
		amountOfSandwichInfo.setFont(amountOfSandwichInfo.getFont().deriveFont(16.0f)); // 폰트 사이즈 16
		amountOfSandwichInfo.setBounds(106 + 320, 330, 128, 20); // x좌표, y좌표, 너비, 높이
		storePanel.add(amountOfSandwichInfo);

		// 핫도그 개수 표시
		amountOfHotdogInfo = new JLabel(amountOfHotdog + "개");
		amountOfHotdogInfo.setVerticalAlignment(SwingConstants.TOP);
		amountOfHotdogInfo.setHorizontalAlignment(SwingConstants.CENTER);
		amountOfHotdogInfo.setFont(amountOfHotdogInfo.getFont().deriveFont(16.0f)); // 폰트 사이즈 16
		amountOfHotdogInfo.setBounds(256 + 320, 330, 128, 20); // x좌표, y좌표, 너비, 높이
		storePanel.add(amountOfHotdogInfo);

		// 콜라 개수 표시
		amountOfCokeInfo = new JLabel(amountOfCoke + "개");
		amountOfCokeInfo.setVerticalAlignment(SwingConstants.TOP);
		amountOfCokeInfo.setHorizontalAlignment(SwingConstants.CENTER);
		amountOfCokeInfo.setFont(amountOfCokeInfo.getFont().deriveFont(16.0f)); // 폰트 사이즈 16
		amountOfCokeInfo.setBounds(406 + 320, 330, 128, 20); // x좌표, y좌표, 너비, 높이
		storePanel.add(amountOfCokeInfo);

		// 플레이어 보유 골드 표시
		currentMoneyInfo = new JLabel("보유 골드: " + Player.currentMoney + "골드");
		currentMoneyInfo.setVerticalAlignment(SwingConstants.TOP);
		currentMoneyInfo.setHorizontalAlignment(SwingConstants.CENTER);
		currentMoneyInfo.setFont(currentMoneyInfo.getFont().deriveFont(16.0f)); // 폰트 사이즈 12
		currentMoneyInfo.setBounds(0, 410, Main.SCREEN_WIDTH, 20); // x좌표, y좌표, 너비, 높이
		storePanel.add(currentMoneyInfo);

		// 선택한 재료의 총 가격 표시
		totalPriceInfo = new JLabel("총 가격: " + totalAmount + "골드");
		totalPriceInfo.setVerticalAlignment(SwingConstants.TOP);
		totalPriceInfo.setHorizontalAlignment(SwingConstants.CENTER);
		totalPriceInfo.setFont(totalPriceInfo.getFont().deriveFont(16.0f)); // 폰트 사이즈 16
		totalPriceInfo.setBounds(0, 430, Main.SCREEN_WIDTH, 20); // x좌표, y좌표, 너비, 높이
		storePanel.add(totalPriceInfo);
	}

	public void setJButton() {
		// 샌드위치 구매 버튼
		buySandwich = new JButton("샌드위치 / " + sandwichPrice + "골드", sandwich_128);
		buySandwich.setBounds(106 + 320, 150, 128, 160); // x좌표, y좌표, 너비, 높이
		buySandwich.setHorizontalTextPosition(SwingConstants.CENTER);
		buySandwich.setVerticalTextPosition(SwingConstants.BOTTOM);
		buySandwich.setBorderPainted(false); // 버튼 외곽선 제거
		buySandwich.setFocusPainted(false); // 버튼 칠 제거
		buySandwich.setContentAreaFilled(false); // 버튼 칠 제거
		buySandwich.addMouseListener(this);
		buySandwich.addActionListener(this);
		storePanel.add(buySandwich);

		// 핫도그 구매 버튼
		buyHotdog = new JButton(" 핫도그 / " + hotdogPrice + "골드", hotdog_128);
		buyHotdog.setBounds(256 + 320, 150, 128, 160); // x좌표, y좌표, 너비, 높이
		buyHotdog.setHorizontalTextPosition(SwingConstants.CENTER);
		buyHotdog.setVerticalTextPosition(SwingConstants.BOTTOM);
		buyHotdog.setBorderPainted(false); // 버튼 외곽선 제거
		buyHotdog.setFocusPainted(false); // 버튼 칠 제거
		buyHotdog.setContentAreaFilled(false); // 버튼 칠 제거
		buyHotdog.addMouseListener(this);
		buyHotdog.addActionListener(this);
		storePanel.add(buyHotdog);

		// 콜라 구매 버튼
		buyCoke = new JButton(" 콜라 / " + cokePrice + "골드", coke_128);
		buyCoke.setBounds(406 + 320, 150, 128, 160); // x좌표, y좌표, 너비, 높이
		buyCoke.setHorizontalTextPosition(SwingConstants.CENTER);
		buyCoke.setVerticalTextPosition(SwingConstants.BOTTOM);
		buyCoke.setBorderPainted(false); // 버튼 외곽선 제거
		buyCoke.setFocusPainted(false); // 버튼 칠 제거
		buyCoke.setContentAreaFilled(false); // 버튼 칠 제거
		buyCoke.addMouseListener(this);
		buyCoke.addActionListener(this);
		storePanel.add(buyCoke);

		// 구매하기 버튼
		buy = new JButton("구매하기");
		buy.setBounds(115 + 320, 530, 200, 60); // x좌표, y좌표, 너비, 높이
		buy.addMouseListener(this);
		buy.addActionListener(this);
		storePanel.add(buy);

		// 구매수량 초기화 버튼
		init = new JButton("재료선택 초기화하기");
		init.setBounds(325 + 320, 530, 200, 60); // x좌표, y좌표, 너비, 높이
		init.addMouseListener(this);
		init.addActionListener(this);
		storePanel.add(init);

		// 흥정하기 버튼
		trade = new JButton("흥정하기");
		trade.setBounds(115 + 320, 590, 200, 60); // x좌표, y좌표, 너비, 높이
		trade.addMouseListener(this);
		trade.addActionListener(this);
		storePanel.add(trade);

		// 미니게임 버튼
		minigame = new JButton("미니게임");
		minigame.setBounds(325 + 320, 590, 200, 60); // x좌표, y좌표, 너비, 높이
		minigame.addMouseListener(this);
		minigame.addActionListener(this);
		storePanel.add(minigame);

		// 되돌아가기 버튼
		backButton = new JButton(backButtonBasicImage);
		backButton.setBounds(20, 30, 60, 60); // x좌표, y좌표, 너비, 높이
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(this);
		backButton.addActionListener(this);
		storePanel.add(backButton);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		buySandwich.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buyHotdog.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buyCoke.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buy.setCursor(new Cursor(Cursor.HAND_CURSOR));
		init.setCursor(new Cursor(Cursor.HAND_CURSOR));
		trade.setCursor(new Cursor(Cursor.HAND_CURSOR));
		minigame.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

		backButton.setIcon(backButtonEnteredImage);

	}

	@Override
	public void mouseExited(MouseEvent e) {
		buySandwich.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		buyHotdog.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		buyCoke.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		buy.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		init.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		trade.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		minigame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

		backButton.setIcon(backButtonBasicImage);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// MouseListener implements시 무조건 포함해야하는 부분
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// MouseListener implements시 무조건 포함해야하는 부분
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// MouseListener implements시 무조건 포함해야하는 부분
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 샌드위치 버튼 클릭시
		if (e.getSource().equals(buySandwich)) {
			amountOfSandwich += 1;
			amountOfSandwichInfo.setText(Integer.toString(amountOfSandwich) + "개");

			totalAmount = sandwichPrice * amountOfSandwich + hotdogPrice * amountOfHotdog + cokePrice * amountOfCoke;
			totalPriceInfo.setText("총 가격: " + Integer.toString(totalAmount) + "골드");
		}

		// 핫도그 버튼 클릭시
		else if (e.getSource().equals(buyHotdog)) {
			amountOfHotdog += 1;
			amountOfHotdogInfo.setText(Integer.toString(amountOfHotdog) + "개");

			totalAmount = sandwichPrice * amountOfSandwich + hotdogPrice * amountOfHotdog + cokePrice * amountOfCoke;
			totalPriceInfo.setText("총 가격: " + Integer.toString(totalAmount) + "골드");
		}

		// 콜라 버튼 클릭시
		else if (e.getSource().equals(buyCoke)) {
			amountOfCoke += 1;
			amountOfCokeInfo.setText(Integer.toString(amountOfCoke) + "개");

			totalAmount = sandwichPrice * amountOfSandwich + hotdogPrice * amountOfHotdog + cokePrice * amountOfCoke;
			totalPriceInfo.setText("총 가격: " + Integer.toString(totalAmount) + "골드");
		}

		// 구매하기 버튼 클릭시
		else if (e.getSource().equals(buy)) {

			// 선택한 재료가 1개 이상일 경우
			if (amountOfSandwich + amountOfHotdog + amountOfCoke > 0) {

				// 플레이어의 현재 보유 금액이 총 가격보다 많을 경우
				if (Player.currentMoney >= totalAmount) {

					// 선택한 수량만큼 각 재료의 수량 +
					Player.amountOfSandwich += amountOfSandwich;
					Player.amountOfHotdog += amountOfHotdog;
					Player.amountOfCoke += amountOfCoke;

					// 선택한 수량의 총 가격만큼 플레이어의 보유금액 -
					Player.currentMoney -= totalAmount;

					// 구매 성공 팝업창
					JOptionPane.showMessageDialog(null, "<html>구매가 완료되었습니다.<br>OK 버튼을 누르면 게임으로 돌아갑니다.</html>", "구매 완료",
							JOptionPane.INFORMATION_MESSAGE);

					// 선택 초기화
					amountOfSandwich = 0;
					amountOfHotdog = 0;
					amountOfCoke = 0;
					amountOfSandwichInfo.setText(Integer.toString(amountOfSandwich) + "개");
					amountOfHotdogInfo.setText(Integer.toString(amountOfHotdog) + "개");
					amountOfCokeInfo.setText(Integer.toString(amountOfCoke) + "개");

					dispose();

					TycoonGame.backMain();

				}

				// 플레이어의 현재 보유 금액이 총 가격보다 적을 경우
				else {
					// 구매 실패 팝업창
					JOptionPane.showMessageDialog(null, "<html>보유 금액이 부족합니다.<br>재료를 다시 선택해주세요.</html>", "구매 실패",
							JOptionPane.ERROR_MESSAGE);

					// 선택 초기화
					amountOfSandwich = 0;
					amountOfHotdog = 0;
					amountOfCoke = 0;
					amountOfSandwichInfo.setText(Integer.toString(amountOfSandwich) + "개");
					amountOfHotdogInfo.setText(Integer.toString(amountOfHotdog) + "개");
					amountOfCokeInfo.setText(Integer.toString(amountOfCoke) + "개");

					totalAmount = sandwichPrice * amountOfSandwich + hotdogPrice * amountOfHotdog
							+ cokePrice * amountOfCoke;
					totalPriceInfo.setText("총 가격: " + Integer.toString(totalAmount) + "골드");
				}

				System.out.println(Player.currentMoney);
				System.out.println(totalAmount);

			}

			// 선택한 재료가 0개일 경우
			else {
				JOptionPane.showMessageDialog(null, "<html>선택한 재료가 없습니다.<br>구매할 재료를 선택한 후 구매하기 버튼을 눌러주세요.</html>",
						"구매 실패", JOptionPane.ERROR_MESSAGE);
			}
		}

		// 초기화하기 버튼 클릭시
		else if (e.getSource().equals(init)) {

			// 선택 초기화
			amountOfSandwich = 0;
			amountOfHotdog = 0;
			amountOfCoke = 0;
			amountOfSandwichInfo.setText(Integer.toString(amountOfSandwich) + "개");
			amountOfHotdogInfo.setText(Integer.toString(amountOfHotdog) + "개");
			amountOfCokeInfo.setText(Integer.toString(amountOfCoke) + "개");

			totalAmount = sandwichPrice * amountOfSandwich + hotdogPrice * amountOfHotdog + cokePrice * amountOfCoke;
			totalPriceInfo.setText("총 가격: " + Integer.toString(totalAmount) + "골드");

			System.out.println("선택 초기화");
			System.out.println(totalAmount);

		}

		// 되돌아가기 버튼 클릭시
		else if (e.getSource().equals(backButton)) {

			// 선택 초기화
			amountOfSandwich = 0;
			amountOfHotdog = 0;
			amountOfCoke = 0;
			amountOfSandwichInfo.setText(Integer.toString(amountOfSandwich) + "개");
			amountOfHotdogInfo.setText(Integer.toString(amountOfHotdog) + "개");
			amountOfCokeInfo.setText(Integer.toString(amountOfCoke) + "개");

			totalAmount = sandwichPrice * amountOfSandwich + hotdogPrice * amountOfHotdog + cokePrice * amountOfCoke;
			totalPriceInfo.setText("총 가격: " + Integer.toString(totalAmount) + "골드");

			// 창 종료
			dispose();
			// 게임 창 메인으로 변경
			TycoonGame.backMain();
		}

		// 흥정하기 버튼 클릭시
		else if (e.getSource().equals(trade)) {

			// 선택한 재료가 1개 이상일 경우
			if (amountOfSandwich + amountOfHotdog + amountOfCoke > 0) {
				
				// 플레이어의 현재 보유 금액이 총 가격보다 많을 경우
				if (Player.currentMoney >= totalAmount) {
					
					// 흥정하기 프레임 띄우기
					tradeFrame = new Frame_trade();
				}

				// 플레이어의 현재 보유 금액이 총 가격보다 적을 경우
				else {
					// 구매 실패 팝업창
					JOptionPane.showMessageDialog(null, "<html>보유 금액이 부족합니다.<br>재료를 다시 선택해주세요.</html>", "흥정하기 실패",
							JOptionPane.ERROR_MESSAGE);

					// 선택 초기화
					amountOfSandwich = 0;
					amountOfHotdog = 0;
					amountOfCoke = 0;
					amountOfSandwichInfo.setText(Integer.toString(amountOfSandwich) + "개");
					amountOfHotdogInfo.setText(Integer.toString(amountOfHotdog) + "개");
					amountOfCokeInfo.setText(Integer.toString(amountOfCoke) + "개");

					totalAmount = sandwichPrice * amountOfSandwich + hotdogPrice * amountOfHotdog
							+ cokePrice * amountOfCoke;
					totalPriceInfo.setText("총 가격: " + Integer.toString(totalAmount) + "골드");
				}
				
			}

			// 선택한 재료가 0개일 경우
			else {
				JOptionPane.showMessageDialog(null, "<html>선택한 재료가 없습니다.<br>구매할 재료를 선택한 후 흥정하기 버튼을 눌러주세요.</html>",
						"흥정하기 실패", JOptionPane.ERROR_MESSAGE);
			}
		}

		// 미니게임 버튼 클릭시
		else if (e.getSource().equals(minigame)) {

			// 미니게임 프레임 띄우기
			minigameFrame = new Frame_minigame();

		}

		else {
			System.out.println("예외 발생");
		}
	}
}