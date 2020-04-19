package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class Frame_checkOrder extends JFrame{
	ArrayList<String> ticket; 	// text파일에서 예매된 정보를 가져와서 arraylist에 넣어줌										
	JPanel panel;
	JLabel label,numLabel;
	JButton next,home;
	JTextField numText;
	public Frame_checkOrder(){
		setSize(800,700);
		setTitle("예약");
		panel = new JPanel(null);
		label = new JLabel("예약");
		label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 40));
		label.setForeground(Color.gray);
		 next = new JButton("next");
		home = new JButton("Home");
		
		numLabel = new JLabel("판매 번호 : ");
		numText = new JTextField(10);
		ticket = new ArrayList<String>(); 		// arraylist 초기화						
		try {
			FileReader fileReader = new FileReader("ticket.txt");				// ticket.txt 파일에서 예매 정보를 가져옴
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String str;
			while((str = bufferedReader.readLine()) != null) { 						// 한줄단위로읽어서 arraylist에 넣어줌
				ticket.add(str);
			}
			bufferedReader.close();
			fileReader.close();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		home.addActionListener(new ActionListener() { 	// home 버튼 이벤트 리스너				
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Frame_checkOrder.this.dispose(); 	// 현재 frame을 dispose 해줌					
				new Frame_order().show(); 							// mainframe을 show 해줌	
			}
		});
		
		next.addActionListener(new ActionListener() { 		// 주문정보확인 버튼 이벤트 리스너			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				boolean numError = false;
				for(int i=0;i<ticket.size();i++) {						
					
					StringTokenizer st = new StringTokenizer(ticket.get(i).toString());// text에서 읽어온 주문정보 .
					String name = st.nextToken("\t ");
					String num = st.nextToken("\t ");
					if(num.equals(numText.getText().toString())) {
						String price = st.nextToken("\t ");
						String seats = st.nextToken("\t ");
						String time = st.nextToken("\t");
						String etc = "Time : "+time+"   PRICE : "+price;
						new MessageBox(new JFrame("") ,"sandwich name: "+name," productNumber  : "+num, etc," sell  : "+seats, false,null);
						numError =true;
					}
				}
				if(!numError) {
					new MessageBox(new JFrame(""),null,null,"check the number",null,false,null);
				}
					
			}
		});
		next.setBounds(80, 300, 140, 30);
		home.setBounds(280,300,140,30);
		label.setBounds(160,60,200,80);
		numLabel.setBounds(120, 200, 140, 30);
		numText.setBounds(220, 200, 140, 30);
		
		panel.add(label);
		panel.add(numLabel);
		panel.add(numText);
		panel.add(next);
		panel.add(home);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);				
		add(panel);
		
		
	}
}

