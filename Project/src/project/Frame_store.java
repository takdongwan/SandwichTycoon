package project;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Frame_store {
	
	JFrame storeFrame;
	JPanel storePanel;
	JLabel explanation;
	JButton sandwich;
	JButton init;
	
	private ImageIcon sandwich_64 = new ImageIcon(Main.class.getResource("../images/sandwich_64.png"));
	private ImageIcon sandwich_128 = new ImageIcon(Main.class.getResource("../images/sandwich_128.png"));

	public static void main(String[] args) {
		new Frame_store();
	}
	
	// 이미지 포함된 버튼으로 해서 누를떄마다 재고에서 하나씩 깎이고, 버튼 누르면 해당 메뉴 제공되면서 돈 버는.. 시스템..
	public Frame_store() {
		setJFrame();
		setJPanel();
		setJLabel();
		setJButton();
	}
	
	public void setJFrame() {
		storeFrame = new JFrame();
		storeFrame.setTitle("재료상점");
		storeFrame.setSize(Main.SCREEN_WIDTH/2, Main.SCREEN_HEIGHT);
		storeFrame.setResizable(false);
		storeFrame.setLocationRelativeTo(null);
		storeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		storeFrame.setLayout(null);
		storeFrame.setVisible(true);
	}
	
	public void setJPanel() {
		storePanel = new JPanel();
		storePanel.setLayout(null);
		storePanel.setBounds(0, 0, Main.SCREEN_WIDTH/2, Main.SCREEN_HEIGHT);
		storePanel.setBackground(new Color(255, 230, 0));
		storeFrame.add(storePanel);
	}
	
	public void setJLabel() {
		explanation = new JLabel("구매를 원하는 만큼 재료를 클릭해주세요!");
		explanation.setVerticalAlignment(SwingConstants.TOP);
		explanation.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public void setJButton() {
		// 샌드위치 구매 버튼
		sandwich = new JButton("샌드위치", sandwich_128);
		sandwich.setBounds(256, 300, 128, 160);
		sandwich.setHorizontalTextPosition(SwingConstants.CENTER);
		sandwich.setVerticalTextPosition(SwingConstants.BOTTOM);
		sandwich.setBorderPainted(false);
		sandwich.setFocusPainted(false);
		sandwich.setContentAreaFilled(false);
		sandwich.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				sandwich.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//		sandwich.setFont(new Font("", Font.BOLD, 16));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sandwich.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		//		sandwich.setFont(new Font("", Font.PLAIN, 16));
			}
		});
		sandwich.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		storePanel.add(sandwich);

		
		// 구매수량 초기화 버튼
		init = new JButton("초기화하기");
		init.setBounds(1010, 610, 200, 60);
		init.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseEntered(MouseEvent e) {
				init.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//		init.setFont(new Font("", Font.BOLD, 16));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				init.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		//		init.setFont(new Font("", Font.PLAIN, 16));
			}

		});
		init.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		storePanel.add(init);
	}
}
