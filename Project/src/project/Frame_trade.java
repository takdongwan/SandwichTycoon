package project;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;


import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
	JLabel tradeExplanation;
	JLabel merchant;

	JButton buySandwich;
	JButton buyHotdog;
	JButton buyCoke;
	JButton deal;
	JButton backButton;

	JTextField dealPriceText;

	int dealPrice;
	int merchantPrice;
	int randomNumber;

	Random random;

	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));

	public Frame_trade() {
		setJFrame();
		setJPanel();
		setJLabel();
		setJButton();
		setJTextField();
		setMerchantPrice();
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

		explanation = new JLabel("상인에게 가격을 제시해서 저렴한 가격으로 재료를 구매하세요!");
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
		amountOfSandwichInfo.setBounds(106 + 320, 330, 128, 20); // x좌표, y좌표, 너비, 높이
		tradePanel.add(amountOfSandwichInfo);

		// 핫도그 개수 표시
		amountOfHotdogInfo = new JLabel(Frame_store.amountOfHotdog + "개");
		amountOfHotdogInfo.setVerticalAlignment(SwingConstants.TOP);
		amountOfHotdogInfo.setHorizontalAlignment(SwingConstants.CENTER);
		amountOfHotdogInfo.setFont(amountOfHotdogInfo.getFont().deriveFont(16.0f)); // 폰트 사이즈 16
		amountOfHotdogInfo.setBounds(256 + 320, 330, 128, 20); // x좌표, y좌표, 너비, 높이
		tradePanel.add(amountOfHotdogInfo);

		// 콜라 개수 표시
		amountOfCokeInfo = new JLabel(Frame_store.amountOfCoke + "개");
		amountOfCokeInfo.setVerticalAlignment(SwingConstants.TOP);
		amountOfCokeInfo.setHorizontalAlignment(SwingConstants.CENTER);
		amountOfCokeInfo.setFont(amountOfCokeInfo.getFont().deriveFont(16.0f)); // 폰트 사이즈 16
		amountOfCokeInfo.setBounds(406 + 320, 330, 128, 20); // x좌표, y좌표, 너비, 높이
		tradePanel.add(amountOfCokeInfo);

		// 플레이어 보유 골드 표시
		currentMoneyInfo = new JLabel("보유 골드: " + Player.currentMoney + "골드");
		currentMoneyInfo.setVerticalAlignment(SwingConstants.TOP);
		currentMoneyInfo.setHorizontalAlignment(SwingConstants.CENTER);
		currentMoneyInfo.setFont(currentMoneyInfo.getFont().deriveFont(16.0f)); // 폰트 사이즈 12
		currentMoneyInfo.setBounds(0, 410, Main.SCREEN_WIDTH, 20); // x좌표, y좌표, 너비, 높이
		tradePanel.add(currentMoneyInfo);

		// 선택한 재료의 총 가격 표시
		totalPriceInfo = new JLabel("원가 합계: " + Frame_store.totalAmount + "골드");
		totalPriceInfo.setVerticalAlignment(SwingConstants.TOP);
		totalPriceInfo.setHorizontalAlignment(SwingConstants.CENTER);
		totalPriceInfo.setFont(totalPriceInfo.getFont().deriveFont(16.0f)); // 폰트 사이즈 16
		totalPriceInfo.setBounds(0, 430, Main.SCREEN_WIDTH, 20); // x좌표, y좌표, 너비, 높이
		tradePanel.add(totalPriceInfo);

		tradeExplanation = new JLabel("[흥정 가능 범위: " + Integer.toString(Frame_store.totalAmount - 100) + " ~ "
				+ Integer.toString(Frame_store.totalAmount - 10) + "골드]");
		tradeExplanation.setVerticalAlignment(SwingConstants.TOP);
		tradeExplanation.setHorizontalAlignment(SwingConstants.CENTER);
		tradeExplanation.setFont(explanation.getFont().deriveFont(12.0f)); // 폰트 사이즈 12
		tradeExplanation.setBounds(0, 500, Main.SCREEN_WIDTH, 30); // x좌표, y좌표, 너비, 높이
		tradePanel.add(tradeExplanation);
	}

	public void setJButton() {
		// 샌드위치 구매 버튼 - 버튼기능 X (상점에서 선택한 재료 개수를 보여주는 목적으로만 사용됨)
		buySandwich = new JButton("샌드위치" + Frame_store.sandwichPrice + "골드", Frame_store.sandwich_128);
		buySandwich.setBounds(106 + 320, 150, 128, 160); // x좌표, y좌표, 너비, 높이
		buySandwich.setHorizontalTextPosition(SwingConstants.CENTER);
		buySandwich.setVerticalTextPosition(SwingConstants.BOTTOM);
		buySandwich.setBorderPainted(false); // 버튼 외곽선 제거
		buySandwich.setFocusPainted(false); // 버튼 칠 제거
		buySandwich.setContentAreaFilled(false); // 버튼 칠 제거
		tradePanel.add(buySandwich);

		// 핫도그 구매 버튼 - 버튼기능 X (상점에서 선택한 재료 개수를 보여주는 목적으로만 사용됨)
		buyHotdog = new JButton(" 핫도그" + Frame_store.hotdogPrice + "골드", Frame_store.hotdog_128);
		buyHotdog.setBounds(256 + 320, 150, 128, 160); // x좌표, y좌표, 너비, 높이
		buyHotdog.setHorizontalTextPosition(SwingConstants.CENTER);
		buyHotdog.setVerticalTextPosition(SwingConstants.BOTTOM);
		buyHotdog.setBorderPainted(false); // 버튼 외곽선 제거
		buyHotdog.setFocusPainted(false); // 버튼 칠 제거
		buyHotdog.setContentAreaFilled(false); // 버튼 칠 제거
		tradePanel.add(buyHotdog);

		// 콜라 구매 버튼 - 버튼기능 X (상점에서 선택한 재료 개수를 보여주는 목적으로만 사용됨)
		buyCoke = new JButton(" 콜라" + Frame_store.cokePrice + "골드", Frame_store.coke_128);
		buyCoke.setBounds(406 + 320, 150, 128, 160); // x좌표, y좌표, 너비, 높이
		buyCoke.setHorizontalTextPosition(SwingConstants.CENTER);
		buyCoke.setVerticalTextPosition(SwingConstants.BOTTOM);
		buyCoke.setBorderPainted(false); // 버튼 외곽선 제거
		buyCoke.setFocusPainted(false); // 버튼 칠 제거
		buyCoke.setContentAreaFilled(false); // 버튼 칠 제거
		tradePanel.add(buyCoke);

		// 흥정하기 버튼
		deal = new JButton("가격 제시하기");
		deal.setBounds(540, 590, 200, 60); // x좌표, y좌표, 너비, 높이
		deal.addMouseListener(this);
		deal.addActionListener(this);
		tradePanel.add(deal);

		// 되돌아가기 버튼
		backButton = new JButton(backButtonBasicImage);
		backButton.setBounds(20, 30, 60, 60); // x좌표, y좌표, 너비, 높이
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(this);
		backButton.addActionListener(this);
		tradePanel.add(backButton);
	}

	public void setJTextField() {
		// 가격 제시 텍스트필드
		dealPriceText = new JTextField();
		dealPriceText.setBounds(535, 520, 210, 40); // x좌표, y좌표, 너비, 높이
		tradePanel.add(dealPriceText);
	}

	public void setMerchantPrice() {

		// 10 ~ 100까지의 랜덤난수 설정
		random = new Random();
		randomNumber = (random.nextInt(10) + 1) * 10;

		// 흥정가 = 원래 가격 - 랜덤난수
		merchantPrice = Frame_store.totalAmount - randomNumber;
		System.out.println(merchantPrice);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		deal.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backButton.setIcon(backButtonEnteredImage);

	}

	@Override
	public void mouseExited(MouseEvent e) {
		deal.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
		// 텍스트필드 값 입력
		if (e.getSource().equals(deal)) {
			// string -> integer 형변환
			dealPrice = Integer.parseInt(dealPriceText.getText());

			// 제시 가격이 상인 가격과 동일할 시
			if (dealPrice == merchantPrice) {
				tradeExplanation.setText("흥정 성공!");

				Player.amountOfSandwich += Frame_store.amountOfSandwich;
				Player.amountOfHotdog += Frame_store.amountOfHotdog;
				Player.amountOfCoke += Frame_store.amountOfCoke;

				// 선택한 수량의 제시 가격만큼 플레이어의 보유금액 -
				Player.currentMoney -= dealPrice;

				// 구매 성공 팝업창
				JOptionPane.showMessageDialog(null, "<html>흥정한 가격으로 구매가 완료되었습니다.<br>OK 버튼을 누르면 흥정하기 창이 닫힙니다.</html>",
						"흥정 성공", JOptionPane.INFORMATION_MESSAGE);

				// 선택 초기화
				Frame_store.amountOfSandwich = 0;
				Frame_store.amountOfHotdog = 0;
				Frame_store.amountOfCoke = 0;
				Frame_store.amountOfSandwichInfo.setText(Integer.toString(Frame_store.amountOfSandwich) + "개");
				Frame_store.amountOfHotdogInfo.setText(Integer.toString(Frame_store.amountOfHotdog) + "개");
				Frame_store.amountOfCokeInfo.setText(Integer.toString(Frame_store.amountOfCoke) + "개");

				Frame_store.totalAmount = Frame_store.sandwichPrice * Frame_store.amountOfSandwich
						+ Frame_store.hotdogPrice * Frame_store.amountOfHotdog
						+ Frame_store.cokePrice * Frame_store.amountOfCoke;
				Frame_store.totalPriceInfo.setText("총 가격: " + Integer.toString(Frame_store.totalAmount) + "골드");

				Frame_store.currentMoneyInfo.setText("보유 골드: " + Integer.toString(Player.currentMoney) + "골드");

				dispose();
			}
			// 제시 가격이 상인 가격보다 작을 시
			else if (dealPrice < merchantPrice) {
				tradeExplanation.setText("제시한 " + dealPrice + "골드는 상인이 원하는 금액보다 적습니다.");
			}

			// 제시 가격이 상인 가격보다 클 시
			else if (dealPrice > merchantPrice) {
				tradeExplanation.setText("제시한 " + dealPrice + "골드는 상인이 원하는 금액보다 많습니다.");
			}
			dealPriceText.setText("");
		}
		// 되돌아가기 버튼 클릭시
		else if (e.getSource().equals(backButton)) {

			dispose();
		}

		else {
			System.out.println("예외 발생");
		}
	}
}
