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
	int orderNumber,select;
	public static int sandwichNum=0;
	public static int hotdogNum=0;
	public static int cokeNum=0;
	public Frame_reservation(){
		setSize(800,700);
		setTitle("Sandwich order");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel(null);
		label = new JLabel(" 예약");
		name = new JLabel("Sale List : ");
		time = new JLabel("sale time : ");
		adult = new JLabel("Buyer : ");
		//child = new JLabel(" Student : ");
		
		orderNumber = (int) (Math.random()*9999);	// 주문 번호를 random으로 선택함
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
		//childTicket=new Choice();			
		
		salesLisetName.add("Sandwich");                        
		salesLisetName.add("Hotdog");
		salesLisetName.add("Coke");
	
		salesLisetName.addItemListener(this);
	
		for(int i=0; i < 11; i++) {                 //인원수 최대 10명
			adultTicket.add(String.valueOf(i));
		//	childTicket.add(String.valueOf(i));
		}
		
		label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 80));
		label.setForeground(Color.gray);
		
		label.setBounds(300,60,200,80);	// 절대좌표 지정
		name.setBounds(130,160,70,30);//Sale List
		salesLisetName.setBounds(210,163,150,30);//구매하고자 하는 물건 목록 
		time.setBounds(130,200,70,30);//sale time 
		selectTime.setBounds(210,203,150,30);//시간 Choice 박스
		adult.setBounds(130, 240, 70, 30);// Adult
		adultTicket.setBounds(210,243,70,30);// adult choice box
		next.setBounds(210, 500, 140, 30);
		home.setBounds(410,500,140,30);
		
		panel.add(label);
		panel.add(name);
		panel.add(salesLisetName);
		panel.add(time);
		panel.add(selectTime);
		panel.add(adult);
		panel.add(adultTicket);
		//panel.add(child);
		//panel.add(childTicket);
		panel.add(next);
		panel.add(home);
		setResizable(false);				 // 크기 조절 불가
		add(panel);
		
	}
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		int index = salesLisetName.getSelectedIndex();		//선택번호를 index에 입력
		System.out.println(index);
		selectTime.removeAll();
		if(index == 0)							
		{					
			selectTime.add("09:00~11:20(sale)");// 
			selectTime.add("11:30~13:5(sale)");
			selectTime.add("--:--~--:--()");
			selectTime.add("16:30~18:50(sale)");
			selectTime.add("19:00~17:20(sale)");
			selectTime.add("--:--~--:--()");
			selectTime.add("22:00~24:20(sale)");
		}
		else if(index==1)
		{					
			selectTime.add("09:00~11:20(sale)");// 
			selectTime.add("--:--~--:--()");
			selectTime.add("14:00~16:20(sale)");
			selectTime.add("16:30~18:50(sale)");
			selectTime.add("--:--~--:--()");
			selectTime.add("17:30~19:50(sale)");
			selectTime.add("--:--~--:--()");
		}
		else if(index==2)
		{					
			selectTime.add("09:00~11:20(sale)");// 
			selectTime.add("11:30~13:50(sale)");
			selectTime.add("14:00~16:20(sale)");
			selectTime.add("16:30~18:50(sale)");
			selectTime.add("--:--~--:--()");
			selectTime.add("17:30~19:50(sale)");
			selectTime.add("--:--~--:--()");
		}
		else if(index==3)
		{					
			selectTime.add("09:00~11:20(sale)");// 
			selectTime.add("--:--~--:--()");
			selectTime.add("14:00~16:20(sale)");
			selectTime.add("--:--~--:--()");
			selectTime.add("19:00~17:20(sale)");
			selectTime.add("17:30~19:50(sale)");
			selectTime.add("--:--~--:--()");
		}

		select = index;
	}
	public void actionPerformed(ActionEvent e){

		// 버튼에 적힌 문자열을 읽어온다.

			int storeOpenTime = selectTime.getSelectedIndex();		//각 아이템에서 index 를 얻어옴
			String stringStoreOpenTime = selectTime.getSelectedItem();
			int buyerOrder = adultTicket.getSelectedIndex();
			//int studentOrder = childTicket.getSelectedIndex();

			String all ="";
			
			int buyerPrice = 100;					//가격설정 
			//int studentPrice = 100;
			int totalPrice = 0;
			
		
			//구매자가 0 이거나 판매시간이 아닌경우  판매시간이아니라는 팝업 창 띄움.
			if((buyerOrder == 0 ) || stringStoreOpenTime.equals("--:--~--:--()")||stringStoreOpenTime.equals(null))
			{
				new MessageBox(new JFrame("") ,null,null ,"Wrong information.",null, false,null);
			}
			else 
			{
				if((storeOpenTime == 0 || storeOpenTime == 1|| storeOpenTime ==2|| storeOpenTime == 3|| storeOpenTime ==4
						|| storeOpenTime ==5|| storeOpenTime == 6 ) && !(salesLisetName.getSelectedIndex()==2)
						&& !stringStoreOpenTime.equals("--:--~--:--()"))// 선택한 시간과 "--:--~--:--() string , !(salesLisetName.getSelectedIndex()==2)이아니라면 할인이 들어감.
				{
					buyerPrice -= 20;
					//studentPrice -= 20;
				}else if( salesLisetName.getSelectedIndex()==2 ) {
					
					buyerPrice -= 60; // 콜라 가격은 50 원 , 10원 할인 한다는 생각으로  , 설정값 100에서 60 을 빼게함. 
					//studentPrice -= 60;// 콜라 가격은 50 원 , 10원 할인 한다는 생각으로  , 설정값 100에서 60 을 빼게함. 
				}
				
				if(buyerOrder != 0)
				{
					totalPrice += buyerOrder*buyerPrice; 
				
				}
			
				//각각 예약받은 물건의 갯수들 
				if(salesLisetName.getSelectedIndex()==0) {
					sandwichNum= buyerOrder;//샌드위치의 갯수
				}else if(salesLisetName.getSelectedIndex()==1) {
					hotdogNum=buyerOrder;//핫도그의 갯수
				}else if(salesLisetName.getSelectedIndex()==2) {
					 cokeNum= buyerOrder;//콜라의 갯수
				}else {
					new MessageBox(new JFrame("") ,null,null ,"Wrong information.",null, false,null);
				}
				
			
				
				/*if(studentOrder != 0)
				{
					totalPrice += studentOrder*studentPrice;
				}*/
				
				all += String.valueOf(totalPrice)+"   ";
				
				
				Frame_reservation.this.setVisible(false); // 현재 주문화면은 visivle false로 해줌 dispose 안한것은 orderseQuence에서 prev버튼 처리를 쉽게하기 위함임 dispose해주면 사용자가 입력한 모든 정보가 날라가기 때문
				
				
				//지금 선택한  모든 정보를Frame_orderseQuence에 넘겨줌Frame_orderseQuenc에선 모든 좌석을 선택하면   주문이 완료 됨.
				Frame_orderSequence sequenceNumber = new Frame_orderSequence(salesLisetName.getItem(select),buyerOrder,totalPrice,orderNumber,stringStoreOpenTime,Frame_reservation.this);
				sequenceNumber.show();							// 좌석 선택 화면 show해줌
				
			}

	  }
	
}
