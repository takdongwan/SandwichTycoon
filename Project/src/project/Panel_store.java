package project;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Panel_store extends JPanel {
	
	
	private ImageIcon sandwich_64 = new ImageIcon(Main.class.getResource("../images/sandwich_64.png"));
	private ImageIcon sandwich_128 = new ImageIcon(Main.class.getResource("../images/sandwich_128.png"));

	
	// 이미지 포함된 버튼으로 해서 누를떄마다 재고에서 하나씩 깎이고, 버튼 누르면 해당 메뉴 제공되면서 돈 버는.. 시스템..
	public Panel_store() {
		setLayout(null);
		setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setBackground(new Color(255, 230, 0));
		add(this);
	}
	
	public void setJLabel() {
		
	}
	
	public void setJButton() {
		// 샌드위치 구매 버튼
		JButton sandwich = new JButton("샌드위치", sandwich_128);
		sandwich.setBounds(576, 300, 128, 160);
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
		this.add(sandwich);

		
		// 구매수량 초기화 버튼
		JButton init = new JButton("초기화하기");
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
		this.add(init);
	}
}
