package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class Frame_orderSequence extends JFrame {
	JPanel panel,sequenceNumber;//주문순서를 일렬로 나타냄 
	JLabel storeName,sequenceNumbers[],peopleLabel,productPriceLabel;
	JButton prev,next;
	boolean[] select;
	ArrayList<String> ticket;
	int member; 		// member는 하나씩 감소하면서 0이되면 주문순서가  다 선택된것임 초기에는 그전 화면에서 고른 인원수												

	
	//Frame_reservation에서 주문정보를  파라미터로 넘겨줌, reservationFrame은 Frame_reservation이다. 나중에 주문이 끝나면 두프레임다 종료 시키기위함이다. 
	public Frame_orderSequence(final String productName,int people,final int productPrice,final int productNum,final String time, final JFrame reservationFrame) {
		setSize(800,700);
		setTitle("sandwich");
		member = people;
		panel = new JPanel(null);
		
		storeName = new JLabel("sandwichTycoon");//가게이름 
		storeName.setBackground(Color.white);
		storeName.setOpaque(true);												
		
		prev = new JButton("PREV");
		next = new JButton("NEXT");
		ticket = new ArrayList<String>();// ticket.txt에서 가져온 정보를 저장하고 주문이 완료되면 지금 주문된 정보를 add할 것임
		sequenceNumber = new JPanel(new GridLayout(7, 7));	 		// 주문순서정보를 panel로 했는데 gridlayout으로 잡고 7x7 좌석을 만듬				
		sequenceNumbers = new JLabel[50];	// 주문넘버에 번호를 지정할 라벨임 때에따라서 background color을 바꿔서 좌석선택을 표시할것임											
		select = new boolean[50];// 현재 선택한 번호들 , 49번자리가 선택됫으면 select[49] = true임			
		

		sequenceNumber.setBackground(Color.white);
		sequenceNumber.setOpaque(true);// 이속성을 true로 해주면 background color가 적용됨
		
		for(int i=0;i<7;i++) 						//주문정보 초기화를 위한 반복문						
			for(int j=0;j<7;j++) {
				final int k =i*7+j;											
				sequenceNumbers[k] = new JLabel(Integer.toString(k+1)); 	//라벨 하나씩 주문번호로 초기화해줌			
				sequenceNumbers[k].setHorizontalAlignment(JLabel.CENTER); //가운데 정렬
				sequenceNumbers[k].addMouseListener(new MouseListener() {	
					
					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseClicked(MouseEvent arg0) { 						
						// TODO Auto-generated method stub
						if(sequenceNumbers[k].getBackground()==Color.pink) { 	//지금 선택한 좌석			
							sequenceNumbers[k].setBackground(Color.WHITE); 		//다시 누르면 흰색으로 배경 변환				
							select[k] = false;											
							member++;
						}
						else if(member>0 && sequenceNumbers[k].getBackground()!=Color.red){ 	//red 는 이전에 다른 사람이 주문한 부분 member 가 o 이면 선택 가능 함으로 pink 로 바궈줌
							sequenceNumbers[k].setBackground(Color.pink);						
							select[k] = true;						
							member--;	//자리하나를 차지했으니 -- 											
						}
						sequenceNumbers[k].setOpaque(true);	// 바뀐 배경색 적용									
					}
				});
				sequenceNumber.add(sequenceNumbers[k]);// 위와같이 초기화한sequenceNumber 라벨을 하나씩 gridlayout으로 세팅한 panel에 add함									
			}
		
		try {
			FileReader fileReader = new FileReader("ticket.txt");	//ticket.txt 를 읽어드림							
			BufferedReader bufferReader = new BufferedReader(fileReader);
			String str;
			while((str = bufferReader.readLine()) != null) { 	// 한줄단위로 읽어드림									
				ticket.add(str); 	// ticket arraylist에 add시킴													
				StringTokenizer st = new StringTokenizer(str);	// 이제 stringtokenize로 자름 						
				String name = st.nextToken("\t ");		// 여기서 필요한거는 주문한종류 ,주문시간 ,주문순서 정보							
				String str2 = st.nextToken("\t ");		//// 나머지는 필요없으므로 str2에 넣어둔다.							
				str2 = st.nextToken("\t ");
				String sequenceNumbers = st.nextToken("\t ");
				String tim = st.nextToken("\t ");
				StringTokenizer st2 = new StringTokenizer(sequenceNumbers,",");	// 이제  주문 순서 정보를 를 ","로  구분한다.				
				System.out.println(sequenceNumbers);
				if(productName.equals(name) && time.equals(tim)) {	// 이전에 지금 읽어드린 주문정보가 내가 주문할 정보랑 맞는지검사해서 맞으면 진행 			
					while(st2.hasMoreTokens()) {
						str2 = st2.nextToken();											
						int k = Integer.parseInt(str2)-1;//-1 해주지않으면 한칸씩 밀리면서  주문 순서가 밀린다.
						this.sequenceNumbers[k].setBackground(Color.GREEN);
						this.sequenceNumbers[k].setOpaque(true);
					}
				}
			}
			bufferReader.close();
			fileReader.close();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		prev.addActionListener(new ActionListener() {									
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				reservationFrame.setVisible(true);												
				Frame_orderSequence.this.dispose();											
			}
		});
		next.addActionListener(new ActionListener() {									
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(member>0) 
					new MessageBox(new JFrame("") ,null,null,"Please all choose ",null, false,null); 		// member가 0보다 크단것은 주문순서을 전부선택 하지 않았다는 뜻
				else {
					String msg = productName+"\t"+productNum+"\t"+productPrice;								// 그게아니면 주문정보를 아래와 같이 가공함
					String sequenceNumbers = "\t";
					String etc = "시    간 : "+time+"   가     격 : "+productPrice;
					for(int i=0;i<49;i++) {															// 해당하는 주문순서가 선택되면 true이기때문에
						if(select[i])																// true인지 검사하고 맞으면 sequenceNumbers에 추가해줌
							sequenceNumbers+=(i+1)+",";
					}
					sequenceNumbers = sequenceNumbers.substring(0, sequenceNumbers.length()-1);									// sequenceNumbers의 마지막 문자가 ","이기때문에 그것을 없애주기위함
					new MessageBox(new JFrame("") ,"error5 : "+productName,"error7 : "+productNum, etc,"error8: "+sequenceNumbers, false,Frame_orderSequence.this); // 가공한 정보를 msgbox로 띄움
					msg +=sequenceNumbers+"\t"+time;
					ticket.add(msg);																// 리스트에 역시 추가함
					try {
						FileWriter fw = new FileWriter("ticket.txt"); 								// 이제 주문를 했으니 ticket.txt에 쓸것임
						BufferedWriter bw = new BufferedWriter(fw);									// 지금 주문한것만 추가하고싶으나 그럴수없으므로 ticket에 있는 모든 정보를 다시 출력해줌									
						for(int i = 0 ; i<ticket.size();i++)										// 반복문을돌려서 모든 주문정보를 다시 출력해줌 									
							bw.write(ticket.get(i)+"\n");
						bw.close();
						fw.close();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				//
			}
		});
		 
		peopleLabel = new JLabel("인원       :     "+people+"명");
		productPriceLabel = new JLabel("가격       :     " +productPrice+"원");
		storeName.setBounds(30,20,430,30);
		storeName.setHorizontalAlignment(SwingConstants.CENTER);
		
		prev.setBounds(80, 320, 140, 30);															
		next.setBounds(280,320,140,30);
		sequenceNumber.setBounds(30,70,430,200);
		peopleLabel.setBounds(80,280,140,30);
		productPriceLabel.setBounds(280,280,140,30);
		
		panel.add(sequenceNumber);
		panel.add(storeName);
		panel.add(prev);
		panel.add(next);
		panel.add(peopleLabel);
		panel.add(productPriceLabel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);				
		add(panel);		
	}

}
