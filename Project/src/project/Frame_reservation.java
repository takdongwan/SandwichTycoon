package project;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Frame_reservation extends JFrame implements ItemListener, ActionListener{
	Choice salesLisetName;             // 판매하는 물품 목록 
	Choice selectTime; 		
	Choice adultTicket;	//성인전용 
	Choice childTicket;	//학생전용 
	JPanel panel;
	JLabel label,name,time,adult,child;
	JButton next,home;
	int ticketNum,select;
	public Frame_reservation(){
		setSize(800,700);
		setTitle("sandwich order");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel(null);
		label = new JLabel(" 예약");
		name = new JLabel("sandwich name : ");
		time = new JLabel("sell : ");
		adult = new JLabel("어   른 : ");
		child = new JLabel("학   생 : ");
		
		ticketNum = (int) (Math.random()*9999);	// 주문 번호를 random으로 선택함
		next = new JButton("NEXT");
		home = new JButton("HOME");
		
		next.addActionListener(this);// next 이벤트 리스너
		
		home.addActionListener(new ActionListener() { // home 버튼 이벤트 리스너
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Frame_reservation.this.dispose();
				new Frame_order().show();
			}
		});
		
		salesLisetName=new Choice();   // 초이스 객체를 생성한다.
		selectTime=new Choice(); 
		
		selectTime.add("09:00~11:20(sale)");  
		selectTime.add("11:30~13:50(sale)");
		selectTime.add("14:00~16:20(sale)");
		selectTime.add("16:30~18:50");
		selectTime.add("19:00~17:20");
		selectTime.add("17:30~19:50(sale)");
		selectTime.add("22:00~24:20(sale)");
		
		adultTicket=new Choice();			
		childTicket=new Choice();			
		
		salesLisetName.add("sandwich");                        
		salesLisetName.add("hotdog");
		salesLisetName.add("coke");
	
		salesLisetName.addItemListener(this);
	
		for(int i=0; i < 11; i++) {                 //인원수 최대 10명
			adultTicket.add(String.valueOf(i));
			childTicket.add(String.valueOf(i));
		}
		
		label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 80));
		label.setForeground(Color.gray);
		
		label.setBounds(160,60,200,80);	// 절대좌표 지정
		name.setBounds(20,160,70,30);
		salesLisetName.setBounds(90,163,150,30);
		time.setBounds(20,200,70,30);
		selectTime.setBounds(90,203,150,30);
		adult.setBounds(270, 160, 70, 30);
		adultTicket.setBounds(350,163,70,30);
		child.setBounds(270, 200, 70, 30);
		childTicket.setBounds(350,203,70,30);
		next.setBounds(80, 300, 140, 30);
		home.setBounds(280,300,140,30);
		
		panel.add(label);
		panel.add(name);
		panel.add(salesLisetName);
		panel.add(time);
		panel.add(selectTime);
		panel.add(adult);
		panel.add(adultTicket);
		panel.add(child);
		panel.add(childTicket);
		panel.add(next);
		panel.add(home);
		setResizable(false);				 // 크기 조절 불가
		add(panel);
		
	}
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		int index = salesLisetName.getSelectedIndex();		//ch1의 선택번호를 index에 입력
		System.out.println(index);
		selectTime.removeAll();
		if(index == 0)							
		{					
			selectTime.add("09:00~11:20(sale)");// 
			selectTime.add("11:30~13:50");
			selectTime.add("14:00~16:20");
			selectTime.add("16:30~18:50");
			selectTime.add("19:00~17:20");
			selectTime.add("--:--~--:--(sale)");
			selectTime.add("22:00~24:20(sale)");
		}
		else if(index==1)
		{					
			selectTime.add("09:00~11:20(sale)");// 
			selectTime.add("--:--~--:--(sale)");
			selectTime.add("14:00~16:20");
			selectTime.add("16:30~18:50");
			selectTime.add("19:00~17:20");
			selectTime.add("17:30~19:50");
			selectTime.add("22:00~24:20(sale)");
		}
		else if(index==2)
		{					
			selectTime.add("09:00~11:20(sale)");// 
			selectTime.add("11:30~13:50");
			selectTime.add("14:00~16:20");
			selectTime.add("16:30~18:50");
			selectTime.add("--:--~--:--(sale)");
			selectTime.add("17:30~19:50");
			selectTime.add("22:00~24:20(sale)");
		}
		else if(index==3)
		{					
			selectTime.add("09:00~11:20(sale)");// 
			selectTime.add("11:30~13:50");
			selectTime.add("14:00~16:20");
			selectTime.add("16:30~18:50");
			selectTime.add("19:00~17:20");
			selectTime.add("17:30~19:50");
			selectTime.add("22:00~24:20(sale)");
		}
		else if(index==4)
		{					
			selectTime.add("09:00~11:20(sale)");// 
			selectTime.add("11:30~13:50");
			selectTime.add("14:00~16:20");
			selectTime.add("16:30~18:50");
			selectTime.add("19:00~17:20");
			selectTime.add("17:30~19:50");
			selectTime.add("--:--~--:--(sale)");
		}
		else if(index==5)
		{					
			selectTime.add("--:--~--:--(sale)");// 
			selectTime.add("11:30~13:50");
			selectTime.add("14:00~16:20");
			selectTime.add("16:30~18:50");
			selectTime.add("19:00~17:20");
			selectTime.add("17:30~19:50");
			selectTime.add("22:00~24:20(sale)");
		}
		select = index;
	}
	public void actionPerformed(ActionEvent e){

		// 버튼에 적힌 문자열을 읽어온다.

			int storeOpenTime = selectTime.getSelectedIndex();		//각 아이템에서 index 를 얻어옴
			String stringStoreOpenTime = selectTime.getSelectedItem();
			int adultOrder = adultTicket.getSelectedIndex();
			int studentOrder = childTicket.getSelectedIndex();


			String all ="";
			int adulltPrice = 100;					//가격설정 
			int studentPrice = 100;
			int totalPrice = 0;
			
			//구매자가 0 이거나 판매시간이 아닌경우  판매시간이아니라는 팝업 창 띄움.
			if((adultOrder == 0 && studentOrder == 0) || stringStoreOpenTime.equals("--:--~--:--(sale)") || stringStoreOpenTime.equals("--:--~--:--(sale)")|| stringStoreOpenTime.equals("--:--~--:--(sale)")||stringStoreOpenTime.equals(null))
			{
				new MessageBox(new JFrame("") ,null,null ,"Wrong information.",null, false,null);
			}
			else
			{
				if(storeOpenTime == 0 && !stringStoreOpenTime.equals("--:--~--:--(sale)"))
				{
					adulltPrice -= 20;
					studentPrice -= 20;
				}
				else if(storeOpenTime == 6 && !stringStoreOpenTime.equals("--:--~--:--(sale)"))
				{
					adulltPrice -= 10;
					studentPrice -= 10;
				}
				
				if(adultOrder != 0)
				{
					totalPrice += adultOrder*adulltPrice; 
				}
				
				if(studentOrder != 0)
				{
					totalPrice += studentOrder*studentPrice;
				}
				
				all += String.valueOf(totalPrice)+"   ";
				
				
				Frame_reservation.this.setVisible(false); // 현재 주문화면은 visivle false로 해줌 dispose 안한것은 orderseQuence에서 prev버튼 처리를 쉽게하기 위함임 dispose해주면 사용자가 입력한 모든 정보가 날라가기 때문
				
				// 아래 개 발 더 해야함 .
				//지금 선택한  의 모든 정보를Frame_orderseQuence에 넘겨줌Frame_orderseQuenc에선 모든 좌석을 선택하면   주문이 완료 됨.
			}

	  }
	
}
