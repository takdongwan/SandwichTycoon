package project;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Frame_store implements ActionListener, MouseListener {

	JFrame storeFrame;
	JPanel storePanel;

	JLabel storeInfo;
	JLabel explanation;
	JLabel currentMoneyInfo;
	JLabel amountOfSandwich;
	JLabel amountOfHotdog;
	JLabel amountOfCoke;

	JButton buySandwich;
	JButton buyHotdog;
	JButton buyCoke;
	JButton buy;
	JButton init;
	
	int sandwichPrice = 100;
	int hotdogPrice = 100;
	int cokePrice = 50;
	
	int sandwich = 0;
	int hotdog = 0;
	int coke = 0;
	
	int totalAmount = sandwichPrice * sandwich + hotdogPrice * hotdog + cokePrice + coke;
	
	int imageWidth = 128;

	private ImageIcon sandwich_64 = new ImageIcon(Main.class.getResource("../images/sandwich_64.png"));
	private ImageIcon sandwich_128 = new ImageIcon(Main.class.getResource("../images/sandwich_128.png"));
	private ImageIcon coke_64 = new ImageIcon(Main.class.getResource("../images/coke_64.png"));
	private ImageIcon coke_128 = new ImageIcon(Main.class.getResource("../images/coke_128.png"));
	private ImageIcon hotdog_64 = new ImageIcon(Main.class.getResource("../images/hotdog_64.png"));
	private ImageIcon hotdog_128 = new ImageIcon(Main.class.getResource("../images/hotdog_128.png"));

	public static void main(String[] args) {
		new Frame_store();
	}

	public Frame_store() {
		setJFrame();
		setJPanel();
		setJLabel();
		setJButton();
	}

	public void setJFrame() {
		storeFrame = new JFrame();
		storeFrame.setTitle("재료상점");
		storeFrame.setSize(Main.SCREEN_WIDTH / 2, Main.SCREEN_HEIGHT);
		storeFrame.setResizable(false);
		storeFrame.setLocationRelativeTo(null);
		storeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		storeFrame.setLayout(null);
		storeFrame.setVisible(true);
	}

	public void setJPanel() {
		storePanel = new JPanel();
		storePanel.setLayout(null);
		storePanel.setBounds(0, 0, Main.SCREEN_WIDTH / 2, Main.SCREEN_HEIGHT);
		storePanel.setBackground(new Color(255, 230, 0));
		storeFrame.add(storePanel);
	}

	public void setJLabel() {
		storeInfo = new JLabel("재료상점");
		storeInfo.setVerticalAlignment(SwingConstants.TOP);
		storeInfo.setHorizontalAlignment(SwingConstants.CENTER);
		storeInfo.setFont(storeInfo.getFont().deriveFont(20.0f)); // 폰트 사이즈 20
		storeInfo.setBounds(0, 50, Main.SCREEN_WIDTH/2, 30); // x좌표, y좌표, 너비, 높이
		storePanel.add(storeInfo);

		explanation = new JLabel("구매를 원하는 만큼 각 재료를 클릭해주세요!");
		explanation.setVerticalAlignment(SwingConstants.TOP);
		explanation.setHorizontalAlignment(SwingConstants.CENTER);
		explanation.setFont(explanation.getFont().deriveFont(12.0f)); // 폰트 사이즈 12
		explanation.setBounds(0, 80, Main.SCREEN_WIDTH/2, 30); // x좌표, y좌표, 너비, 높이
		storePanel.add(explanation);
		
		currentMoneyInfo = new JLabel("보유 골드: " + Player.currentMoney + "골드");
		currentMoneyInfo.setVerticalAlignment(SwingConstants.TOP);
		currentMoneyInfo.setHorizontalAlignment(SwingConstants.LEFT);
		currentMoneyInfo.setFont(currentMoneyInfo.getFont().deriveFont(12.0f)); // 폰트 사이즈 12
		currentMoneyInfo.setBounds(40, 120, 240, 30); // x좌표, y좌표, 너비, 높이
		storePanel.add(currentMoneyInfo);

		amountOfSandwich = new JLabel("0개");
		amountOfSandwich.setVerticalAlignment(SwingConstants.TOP);
		amountOfSandwich.setHorizontalAlignment(SwingConstants.CENTER);
		amountOfSandwich.setFont(amountOfSandwich.getFont().deriveFont(16.0f)); // 폰트 사이즈 16
		amountOfSandwich.setBounds(106, 420, imageWidth, 20); // x좌표, y좌표, 너비, 높이
		storePanel.add(amountOfSandwich);

		amountOfHotdog = new JLabel("0개");
		amountOfHotdog.setVerticalAlignment(SwingConstants.TOP);
		amountOfHotdog.setHorizontalAlignment(SwingConstants.CENTER);
		amountOfHotdog.setFont(amountOfHotdog.getFont().deriveFont(16.0f)); // 폰트 사이즈 16
		amountOfHotdog.setBounds(256, 420, imageWidth, 20); // x좌표, y좌표, 너비, 높이
		storePanel.add(amountOfHotdog);

		amountOfCoke = new JLabel("0개");
		amountOfCoke.setVerticalAlignment(SwingConstants.TOP);
		amountOfCoke.setHorizontalAlignment(SwingConstants.CENTER);
		amountOfCoke.setFont(amountOfCoke.getFont().deriveFont(16.0f)); // 폰트 사이즈 16
		amountOfCoke.setBounds(406, 420, imageWidth, 20); // x좌표, y좌표, 너비, 높이
		storePanel.add(amountOfCoke);
	}

	public void setJButton() {
		// 샌드위치 구매 버튼
		buySandwich = new JButton("샌드위치 / " + sandwichPrice + "골드", sandwich_128);
		buySandwich.setBounds(106, 240, imageWidth, 160); // x좌표, y좌표, 너비, 높이
		buySandwich.setHorizontalTextPosition(SwingConstants.CENTER);
		buySandwich.setVerticalTextPosition(SwingConstants.BOTTOM);
		buySandwich.setBorderPainted(false); // 버튼 외곽선 및 칠 제거
		buySandwich.addMouseListener(this);
		buySandwich.addActionListener(this);
		storePanel.add(buySandwich);

		// 핫도그 구매 버튼
		buyHotdog = new JButton(" 핫도그 / " + hotdogPrice + "골드", hotdog_128);
		buyHotdog.setBounds(256, 240, imageWidth, 160); // x좌표, y좌표, 너비, 높이
		buyHotdog.setHorizontalTextPosition(SwingConstants.CENTER);
		buyHotdog.setVerticalTextPosition(SwingConstants.BOTTOM);
		buyHotdog.setBorderPainted(false); // 버튼 외곽선 및 칠 제거
		buyHotdog.addMouseListener(this);
		buyHotdog.addActionListener(this);
		storePanel.add(buyHotdog);

		// 콜라 구매 버튼
		buyCoke = new JButton(" 콜라 / " + cokePrice + "골드", coke_128);
		buyCoke.setBounds(406, 240, imageWidth, 160); // x좌표, y좌표, 너비, 높이
		buyCoke.setHorizontalTextPosition(SwingConstants.CENTER);
		buyCoke.setVerticalTextPosition(SwingConstants.BOTTOM);
		buyCoke.setBorderPainted(false); // 버튼 외곽선 및 칠 제거
		buyCoke.addMouseListener(this);
		buyCoke.addActionListener(this);
		storePanel.add(buyCoke);

		// 구매하기 버튼
		buy = new JButton("구매하기");
		buy.setBounds(115, 540, 200, 60); // x좌표, y좌표, 너비, 높이
		buy.addMouseListener(this);
		buy.addActionListener(this);
		storePanel.add(buy);

		// 구매수량 초기화 버튼
		init = new JButton("초기화하기");
		init.setBounds(325, 540, 200, 60); // x좌표, y좌표, 너비, 높이
		init.addMouseListener(this);
		init.addActionListener(this);
		storePanel.add(init);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(buySandwich)) {
			sandwich += 1; 
			amountOfSandwich.setText(Integer.toString(sandwich) + "개");
			System.out.println(sandwich);
		}
		else if (e.getSource().equals(buyHotdog)) {
			hotdog += 1; 
			amountOfHotdog.setText(Integer.toString(hotdog) + "개");
			System.out.println(hotdog);
		}
		else if (e.getSource().equals(buyCoke)) {
			coke += 1; 
			amountOfCoke.setText(Integer.toString(coke) + "개");
			System.out.println(coke);
		}
		else if (e.getSource().equals(buy)) {
			if (Player.currentMoney >= totalAmount) {
				Player.currentMoney -= totalAmount;
			}
			else {
				sandwich = 0;
				hotdog = 0;
				coke = 0;
				amountOfSandwich.setText(Integer.toString(sandwich) + "개");
				amountOfHotdog.setText(Integer.toString(hotdog) + "개");
				amountOfCoke.setText(Integer.toString(coke) + "개");
				System.out.println("잔액 부족");
			}
			System.out.println(Player.currentMoney);
		}
		else if (e.getSource().equals(init)) {
			sandwich = 0;
			hotdog = 0;
			coke = 0;
			amountOfSandwich.setText(Integer.toString(sandwich) + "개");
			amountOfHotdog.setText(Integer.toString(hotdog) + "개");
			amountOfCoke.setText(Integer.toString(coke) + "개");
			System.out.println("선택 초기화");
		}
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		buySandwich.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buyHotdog.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buyCoke.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buy.setCursor(new Cursor(Cursor.HAND_CURSOR));
		init.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		buySandwich.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		buyHotdog.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		buyCoke.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		buy.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		init.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// MouseListener implements 시 무조건 포함해야하는 부분
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// MouseListener implements 시 무조건 포함해야하는 부분
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// MouseListener implements 시 무조건 포함해야하는 부분
	}

}
