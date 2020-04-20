package project;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Frame_trade extends JFrame implements ActionListener, MouseListener {

	JPanel tradePanel;

	JLabel tradeInfo;
	JLabel explanation;
	JLabel amountOfSandwichInfo;
	JLabel amountOfHotdogInfo;
	JLabel amountOfCokeInfo;
	JLabel currentMoneyInfo;
	JLabel totalPriceInfo;
	JLabel merchant;

	JButton buySandwich;
	JButton buyHotdog;
	JButton buyCoke;
	JButton backButton;

	private ImageIcon merchant_128 = new ImageIcon(Main.class.getResource("../images/merchant_128.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));

	public Frame_trade() {
		setJFrame();
		setJPanel();
		setJLabel();
		setJButton();
	}

	public void setJFrame() {

		setTitle("흥정하기");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLayout(null);
		getContentPane().setLayout(null);
		setUndecorated(true); // 임의로 흥정하기 창을 종료할 수 없도록 undecorated 설정
		setVisible(true);
	}

	public void setJPanel() {

		tradePanel = new JPanel();
		tradePanel.setLayout(null);
		tradePanel.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		tradePanel.setBackground(new Color(255, 230, 0));
		getContentPane().add(tradePanel);
	}

	public void setJLabel() {

		tradeInfo = new JLabel("흥정하기");
		tradeInfo.setVerticalAlignment(SwingConstants.TOP);
		tradeInfo.setHorizontalAlignment(SwingConstants.CENTER);
		tradeInfo.setFont(tradeInfo.getFont().deriveFont(20.0f)); // 폰트 사이즈 20
		tradeInfo.setBounds(0, 40, Main.SCREEN_WIDTH, 30); // x좌표, y좌표, 너비, 높이
		tradePanel.add(tradeInfo);

		explanation = new JLabel("흥정할 물품을 선택해주세요!");
		explanation.setVerticalAlignment(SwingConstants.TOP);
		explanation.setHorizontalAlignment(SwingConstants.CENTER);
		explanation.setFont(explanation.getFont().deriveFont(12.0f)); // 폰트 사이즈 12
		explanation.setBounds(0, 70, Main.SCREEN_WIDTH, 30); // x좌표, y좌표, 너비, 높이
		tradePanel.add(explanation);

		// 샌드위치 개수 표시
		amountOfSandwichInfo = new JLabel(Frame_store.amountOfSandwich + "개");
		amountOfSandwichInfo.setVerticalAlignment(SwingConstants.TOP);
		amountOfSandwichInfo.setHorizontalAlignment(SwingConstants.CENTER);
		amountOfSandwichInfo.setFont(amountOfSandwichInfo.getFont().deriveFont(16.0f)); // 폰트 사이즈 16
		amountOfSandwichInfo.setBounds(106 + 320, 360, 128, 20); // x좌표, y좌표, 너비, 높이
		tradePanel.add(amountOfSandwichInfo);

		// 핫도그 개수 표시
		amountOfHotdogInfo = new JLabel(Frame_store.amountOfHotdog + "개");
		amountOfHotdogInfo.setVerticalAlignment(SwingConstants.TOP);
		amountOfHotdogInfo.setHorizontalAlignment(SwingConstants.CENTER);
		amountOfHotdogInfo.setFont(amountOfHotdogInfo.getFont().deriveFont(16.0f)); // 폰트 사이즈 16
		amountOfHotdogInfo.setBounds(256 + 320, 360, 128, 20); // x좌표, y좌표, 너비, 높이
		tradePanel.add(amountOfHotdogInfo);

		// 콜라 개수 표시
		amountOfCokeInfo = new JLabel(Frame_store.amountOfCoke + "개");
		amountOfCokeInfo.setVerticalAlignment(SwingConstants.TOP);
		amountOfCokeInfo.setHorizontalAlignment(SwingConstants.CENTER);
		amountOfCokeInfo.setFont(amountOfCokeInfo.getFont().deriveFont(16.0f)); // 폰트 사이즈 16
		amountOfCokeInfo.setBounds(406 + 320, 360, 128, 20); // x좌표, y좌표, 너비, 높이
		tradePanel.add(amountOfCokeInfo);

		// 플레이어 보유 골드 표시
		currentMoneyInfo = new JLabel("보유 골드: " + Player.currentMoney + "골드");
		currentMoneyInfo.setVerticalAlignment(SwingConstants.TOP);
		currentMoneyInfo.setHorizontalAlignment(SwingConstants.CENTER);
		currentMoneyInfo.setFont(currentMoneyInfo.getFont().deriveFont(16.0f)); // 폰트 사이즈 12
		currentMoneyInfo.setBounds(0, 440, Main.SCREEN_WIDTH, 20); // x좌표, y좌표, 너비, 높이
		tradePanel.add(currentMoneyInfo);

		// 선택한 재료의 총 가격 표시
		totalPriceInfo = new JLabel("원가 합계: " + Frame_store.totalAmount + "골드");
		totalPriceInfo.setVerticalAlignment(SwingConstants.TOP);
		totalPriceInfo.setHorizontalAlignment(SwingConstants.CENTER);
		totalPriceInfo.setFont(totalPriceInfo.getFont().deriveFont(16.0f)); // 폰트 사이즈 16
		totalPriceInfo.setBounds(0, 460, Main.SCREEN_WIDTH, 20); // x좌표, y좌표, 너비, 높이
		tradePanel.add(totalPriceInfo);
		
		merchant = new JLabel(merchant_128);
		merchant.setVerticalAlignment(SwingConstants.TOP);
		merchant.setHorizontalAlignment(SwingConstants.CENTER);
		merchant.setFont(explanation.getFont().deriveFont(12.0f)); // 폰트 사이즈 12
		merchant.setBounds(300, 200, 128, 128); // x좌표, y좌표, 너비, 높이
		tradePanel.add(merchant);
	}

	public void setJButton() {
		// 샌드위치 구매 버튼
		buySandwich = new JButton("샌드위치" + Frame_store.sandwichPrice + "골드", Frame_store.sandwich_128);
		buySandwich.setBounds(106 + 320, 180, 128, 160); // x좌표, y좌표, 너비, 높이
		buySandwich.setHorizontalTextPosition(SwingConstants.CENTER);
		buySandwich.setVerticalTextPosition(SwingConstants.BOTTOM);
		buySandwich.setBorderPainted(false); // 버튼 외곽선 제거
		buySandwich.setFocusPainted(false); // 버튼 칠 제거
		buySandwich.setContentAreaFilled(false); // 버튼 칠 제거
		buySandwich.addMouseListener(this);
		buySandwich.addActionListener(this);
		tradePanel.add(buySandwich);

		// 핫도그 구매 버튼
		buyHotdog = new JButton(" 핫도그" + Frame_store.hotdogPrice + "골드", Frame_store.hotdog_128);
		buyHotdog.setBounds(256 + 320, 180, 128, 160); // x좌표, y좌표, 너비, 높이
		buyHotdog.setHorizontalTextPosition(SwingConstants.CENTER);
		buyHotdog.setVerticalTextPosition(SwingConstants.BOTTOM);
		buyHotdog.setBorderPainted(false); // 버튼 외곽선 제거
		buyHotdog.setFocusPainted(false); // 버튼 칠 제거
		buyHotdog.setContentAreaFilled(false); // 버튼 칠 제거
		buyHotdog.addMouseListener(this);
		buyHotdog.addActionListener(this);
		tradePanel.add(buyHotdog);

		// 콜라 구매 버튼
		buyCoke = new JButton(" 콜라" + Frame_store.cokePrice + "골드", Frame_store.coke_128);
		buyCoke.setBounds(406 + 320, 180, 128, 160); // x좌표, y좌표, 너비, 높이
		buyCoke.setHorizontalTextPosition(SwingConstants.CENTER);
		buyCoke.setVerticalTextPosition(SwingConstants.BOTTOM);
		buyCoke.setBorderPainted(false); // 버튼 외곽선 제거
		buyCoke.setFocusPainted(false); // 버튼 칠 제거
		buyCoke.setContentAreaFilled(false); // 버튼 칠 제거
		buyCoke.addMouseListener(this);
		buyCoke.addActionListener(this);
		tradePanel.add(buyCoke);

		// 되돌아가기 버튼
		backButton = new JButton(backButtonBasicImage);
		backButton.setBounds(20, 30, 60, 60); // x좌표, y좌표, 너비, 높이
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(this);
		backButton.addActionListener(this);
		tradePanel.add(backButton);
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
			System.out.println("예외 발생");
		}
	}
}
