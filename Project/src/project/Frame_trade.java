package project;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Frame_trade extends JFrame {

	JPanel tradePanel;
	
	JLabel tradeInfo;
	JLabel explanation;
	
	
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

		explanation = new JLabel("상인과의 흥정을 통해 재료를 저렴하게 구매할 수 있습니다!");
		explanation.setVerticalAlignment(SwingConstants.TOP);
		explanation.setHorizontalAlignment(SwingConstants.CENTER);
		explanation.setFont(explanation.getFont().deriveFont(12.0f)); // 폰트 사이즈 12
		explanation.setBounds(0, 70, Main.SCREEN_WIDTH, 30); // x좌표, y좌표, 너비, 높이
		tradePanel.add(explanation);
	}
	
	public void setJButton() {
		
	}
}
