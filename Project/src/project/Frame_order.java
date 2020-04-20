package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Frame_order extends JFrame{
	

	public void Frame_order() {
		// TODO Auto-generated method stub
		setSize(800,700);
		setTitle("예약");
		JPanel panel = new JPanel(null);
		JLabel label = new JLabel("예약");
		label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 80));
		label.setForeground(Color.gray);											
		JButton button1 = new JButton("주문");
		JButton button2 = new JButton("주문확인");
		
		label.setBounds(260,160,200,80);											
		button1.setBounds(280, 340, 140, 30);
		button2.setBounds(280, 380, 140, 30);
		panel.add(label);
		panel.add(button1);
		panel.add(button2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);				 // ũ�� ���� �Ұ�
		add(panel);
		setVisible(true);
		button1.addActionListener(new ActionListener() { 						
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Frame_reservation().show(); 									
				Frame_order.this.dispose();										
			}
		});
		button2.addActionListener(new ActionListener() { 						
			//
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Frame_checkOrder().show(); 									
				Frame_order.this.dispose(); 									
			}
		});
	}

	
}
