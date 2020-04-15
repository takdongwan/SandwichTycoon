package project;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//
public class Frame_sell extends JFrame  {
	Container contentPane;
	JPanel panel[];
	JPanel textview;
	JPanel imageview;
	JPanel selectview;
	
	JButton btn[];
	int imageWidth = 128;
	//초기 이미지 셋팅 
	private ImageIcon grilImage = new ImageIcon(Main.class.getResource("../images/gril.png"));
	private ImageIcon cokeBackgroundImage = new ImageIcon(Main.class.getResource("../images/cokeBackground.png"));
	//샌드위치 이미지 
	private ImageIcon breadOnTheGrilImage = new ImageIcon(Main.class.getResource("../images/breadOnTheGril.png"));
	private ImageIcon vegetableOnBreadImage = new ImageIcon(Main.class.getResource("../images/vegetablesOnBread.png"));
	private ImageIcon completeSandwichImage = new ImageIcon(Main.class.getResource("../images/completeSandwich.png"));
	//핫도그 이미지
	private ImageIcon hotdogBreadOnTheGrilImage = new ImageIcon(Main.class.getResource("../images/hotdogBreadOnTheGril.png"));
	private ImageIcon completeHotdogImage= new ImageIcon(Main.class.getResource("../images/completeHotdog.png"));
	private ImageIcon sausageInBreadImage= new ImageIcon(Main.class.getResource("../images/sausageInBread.png"));
	//콜라이미지
	private ImageIcon cupOfCokeImage= new ImageIcon(Main.class.getResource("../images/cupOfCoke.png"));
	private ImageIcon cokeIntoTheCupImage= new ImageIcon(Main.class.getResource("../images/cokeIntoTheCup.png"));
	private ImageIcon completeCokeImage= new ImageIcon(Main.class.getResource("../images/completeCoke.png"));

	Timer[] firstImageChangeTimer;
	Timer[] imageChangeTimer;
	
	int speed_time = 10000;
	JLabel totalAmountLabel; // 총 금액 
	JLabel sellListLabel;// 판매 목록 리스트 라벨
	JLabel cokeAmountLabel;
	JLabel hotdogAmountLabel;
	
	

	public Frame_sell(){
		sellmain();
		
	}
	
	public void sellmain() {
		firstImageChangeTimer = new Timer[10];
		imageChangeTimer = new Timer[9];
		
		setTitle("tycoon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(6,1));
		panel = new JPanel[9];
		for(int i = 0 ; i < 9 ; i++)
		{
			panel[i] = new  JPanel();
			panel[i].setLayout(new FlowLayout());
		}
		
		class imageChangeIndex0 extends TimerTask{
			public void run(){
				if(btn[0].getIcon() == hotdogBreadOnTheGrilImage)
					btn[0].setIcon(sausageInBreadImage);
			}
		}
		class timageChangeIndex0 extends TimerTask{	
			public void run(){
				if(btn[0].getIcon() == sausageInBreadImage)
					btn[0].setIcon(completeHotdogImage);
			}
		}
		class imageChangeIndex1 extends TimerTask{
			public void run(){
				if(btn[1].getIcon() == hotdogBreadOnTheGrilImage)
					btn[1].setIcon(sausageInBreadImage);
			}
		}
		class timageChangeIndex1 extends TimerTask{
			public void run(){
				if(btn[1].getIcon() == sausageInBreadImage)
					btn[1].setIcon(completeHotdogImage);
			}
		}
		class imageChangeIndex2 extends TimerTask{
			public void run(){
				if(btn[2].getIcon() == hotdogBreadOnTheGrilImage)
					btn[2].setIcon(sausageInBreadImage);
			}
		}
		class timageChangeIndex2 extends TimerTask{
			public void run(){
				if(btn[2].getIcon() == sausageInBreadImage)
					btn[2].setIcon(completeHotdogImage);
			}
		}
		class imageChangeIndex3 extends TimerTask{
			public void run(){
				if(btn[3].getIcon() == breadOnTheGrilImage)
					btn[3].setIcon(vegetableOnBreadImage);
			}
		}
		class timageChangeIndex3 extends TimerTask{
			public void run(){
				if(btn[3].getIcon() == vegetableOnBreadImage)
					btn[3].setIcon(completeSandwichImage);
			}
		}
		class imageChangeIndex4 extends TimerTask{
			public void run(){
				if(btn[4].getIcon() == breadOnTheGrilImage)
					btn[4].setIcon(vegetableOnBreadImage);
			}
		}
		class timageChangeIndex4 extends TimerTask{
			public void run(){
				if(btn[4].getIcon() == vegetableOnBreadImage)
					btn[4].setIcon(completeSandwichImage);
			}
		}
		class imageChangeIndex5 extends TimerTask{
			public void run(){
				if(btn[5].getIcon() == breadOnTheGrilImage)
					btn[5].setIcon(vegetableOnBreadImage);
			}
		}
		class timageChangeIndex5 extends TimerTask{
			public void run(){
				if(btn[5].getIcon() == vegetableOnBreadImage)
					btn[5].setIcon(completeSandwichImage);
			}
		}
		class imageChangeIndex6 extends TimerTask{
			public void run(){
				if(btn[6].getIcon() == breadOnTheGrilImage)
					btn[6].setIcon(vegetableOnBreadImage);
			}
		}
		class timageChangeIndex6 extends TimerTask{
			public void run(){
				if(btn[6].getIcon() == vegetableOnBreadImage)
					btn[6].setIcon(completeSandwichImage);
			}
		}
		class imageChangeIndex7 extends TimerTask{
			public void run(){
				if(btn[7].getIcon() == breadOnTheGrilImage)
					btn[7].setIcon(vegetableOnBreadImage);
			}
		}
		class timageChangeIndex7 extends TimerTask{
			public void run(){
				if(btn[7].getIcon() == vegetableOnBreadImage)
					btn[7].setIcon(completeSandwichImage);
			}
		}
		class imageChangeIndex8 extends TimerTask{
			public void run(){
				if(btn[8].getIcon() == cupOfCokeImage)
					btn[8].setIcon(cokeIntoTheCupImage);
			}
		}
		class timageChangeIndex8 extends TimerTask{
			public void run(){
				if(btn[8].getIcon() == cokeIntoTheCupImage)
					btn[8].setIcon(completeCokeImage);
			}
		}
		class imageChangeIndex9 extends TimerTask{
			public void run(){
				if(btn[8].getIcon() == cokeIntoTheCupImage)
					speed_time -= 100;
			}
		}
		class timageChangeIndex9 extends TimerTask{
			public void run(){
				if(btn[9].getIcon() == vegetableOnBreadImage)
					btn[9].setIcon(completeSandwichImage);
			}
		}
		
		
		btn = new JButton[9];
		for ( int i = 0; i < 8; i++)
			btn[i] = new JButton("",grilImage);

		btn[8]= new JButton("",cokeBackgroundImage);
		
		firstImageChangeTimer[9] = new Timer();
		firstImageChangeTimer[9].schedule(new imageChangeIndex9(), 10000, 10000);
		
		btn[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if( Frame_store.amountOfHotdog>0){
						
					if(btn[0].getIcon() == grilImage)
					{
					
						firstImageChangeTimer[0] = new Timer();
						imageChangeTimer[0] = new Timer();
						btn[0].setIcon(hotdogBreadOnTheGrilImage);
						firstImageChangeTimer[0].schedule(new imageChangeIndex0(), speed_time);
						imageChangeTimer[0].schedule(new timageChangeIndex0(), speed_time*2);
						System.out.println("error check 2");
					}
					else 
					{
						if(btn[0].getIcon()  == hotdogBreadOnTheGrilImage ) {
							Player.currentMoney -= 100;
						}
						else if ( btn[0].getIcon() == completeHotdogImage) {
							if(Player.currentMoney>=6000) {
								JOptionPane.showMessageDialog(null, "<html>미션성공 .<br>게임을 종료합니다..</html>", "미션성공",
										JOptionPane.INFORMATION_MESSAGE);
								System.exit(0);
							}
							Player.currentMoney += 100;
							Frame_store.amountOfHotdog -=1;
						}else {
							//
						}//
						
						
						totalAmountLabel.setText("보유금액 :" +Integer.toString(Player.currentMoney));
						sellListLabel.setText("샌드위치 갯수 :" +Integer.toString(Frame_store.amountOfSandwich)+" 핫도그갯수 : "+Integer.toString(Frame_store.amountOfHotdog));
						cokeAmountLabel.setText("콜라 갯수 :" +Integer.toString(Frame_store.amountOfCoke));
			
						btn[0].setIcon(grilImage);
					}
				}else {
					JOptionPane.showMessageDialog(null, "<html>재고가 부족합니다..<br>재고를 구매하고 오세요.</html>", "미션실패",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
					}
		});
		btn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( Frame_store.amountOfHotdog>0){
				if(btn[1].getIcon() == grilImage)
				{
					firstImageChangeTimer[1] = new Timer();
					imageChangeTimer[1] = new Timer();
					btn[1].setIcon(hotdogBreadOnTheGrilImage);
					firstImageChangeTimer[1].schedule(new imageChangeIndex1(), speed_time-3000);
					imageChangeTimer[1].schedule(new timageChangeIndex1(), (speed_time-3000)*2);
					System.out.println("error check 3");
				}
				else 
				{
					if(btn[1].getIcon() == hotdogBreadOnTheGrilImage) {
						Player.currentMoney -= 100;
					}
					else if ( btn[1].getIcon() == completeHotdogImage) {
						if(Player.currentMoney>=6000) {
							JOptionPane.showMessageDialog(null, "<html>미션성공 .<br>게임을 종료합니다..</html>", "미션성공",
									JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}
						Player.currentMoney += 100;
						Frame_store.amountOfHotdog -=1;
					}else {
						//
					}
					
					

					totalAmountLabel.setText("보유금액 :" +Integer.toString(Player.currentMoney));
					sellListLabel.setText("샌드위치 갯수 :" +Integer.toString(Frame_store.amountOfSandwich)+" 핫도그갯수 : "+Integer.toString(Frame_store.amountOfHotdog));
					cokeAmountLabel.setText("콜라 갯수 :" +Integer.toString(Frame_store.amountOfCoke));
					btn[1].setIcon(grilImage);
				}
				}else {
					JOptionPane.showMessageDialog(null, "<html>재고가 부족합니다..<br>재고를 구매하고 오세요.</html>", "미션실패",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		});
		btn[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( Frame_store.amountOfHotdog>0){
				if(btn[2].getIcon() == grilImage)
				{
					firstImageChangeTimer[2] = new Timer();
					imageChangeTimer[2] = new Timer();
					btn[2].setIcon(hotdogBreadOnTheGrilImage);
					firstImageChangeTimer[2].schedule(new imageChangeIndex2(), speed_time);
					imageChangeTimer[2].schedule(new timageChangeIndex2(), speed_time*2);
					System.out.println("error check 4");
				}
				else 
				{
					if(btn[2].getIcon()  == hotdogBreadOnTheGrilImage ) {
						Player.currentMoney -= 100;
					}
					else if ( btn[2].getIcon() == completeHotdogImage) {
						Player.currentMoney += 100;
						Frame_store.amountOfHotdog -=1;
						if(Player.currentMoney>=6000) {
							JOptionPane.showMessageDialog(null, "<html>미션성공 .<br>게임을 종료합니다..</html>", "미션성공",
									JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}
					}else {
						//
					}

					totalAmountLabel.setText("보유금액 :" +Integer.toString(Player.currentMoney));
					sellListLabel.setText("샌드위치 갯수 :" +Integer.toString(Frame_store.amountOfSandwich)+" 핫도그갯수 : "+Integer.toString(Frame_store.amountOfHotdog));
					cokeAmountLabel.setText("콜라 갯수 :" +Integer.toString(Frame_store.amountOfCoke));
					btn[2].setIcon(grilImage);
				}
				}else {
					JOptionPane.showMessageDialog(null, "<html>재고가 부족합니다..<br>재고를 구매하고 오세요.</html>", "미션실패",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		});
		btn[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( Frame_store.amountOfSandwich>0){
				if(btn[3].getIcon() == grilImage)
				{
					firstImageChangeTimer[3] = new Timer();
					imageChangeTimer[3] = new Timer();
					btn[3].setIcon(breadOnTheGrilImage);
					firstImageChangeTimer[3].schedule(new imageChangeIndex3(), speed_time-3000);
					imageChangeTimer[3].schedule(new timageChangeIndex3(), (speed_time-3000)*2);
					System.out.println("error check 5");
				}
				else 
				{
					if(btn[3].getIcon()  == breadOnTheGrilImage ) {
						Player.currentMoney -= 100;
					}
					else if ( btn[3].getIcon() == completeSandwichImage) {
						Player.currentMoney += 100;
						Frame_store.amountOfSandwich-=1;
						if(Player.currentMoney>=6000) {
							JOptionPane.showMessageDialog(null, "<html>미션성공 .<br>게임을 종료합니다..</html>", "미션성공",
									JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}
					}else {
						//
					}
					
					

					totalAmountLabel.setText("보유금액 :" +Integer.toString(Player.currentMoney));
					sellListLabel.setText("샌드위치 갯수 :" +Integer.toString(Frame_store.amountOfSandwich)+" 핫도그갯수 : "+Integer.toString(Frame_store.amountOfHotdog));
					cokeAmountLabel.setText("콜라 갯수 :" +Integer.toString(Frame_store.amountOfCoke));
					btn[3].setIcon(grilImage);
				}
				}else {
					JOptionPane.showMessageDialog(null, "<html>재고가 부족합니다..<br>재고를 구매하고 오세요.</html>", "미션실패",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		});
		btn[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( Frame_store.amountOfSandwich>0){
				if(btn[4].getIcon() == grilImage)
				{
					firstImageChangeTimer[4] = new Timer();
					imageChangeTimer[4] = new Timer();
					btn[4].setIcon(breadOnTheGrilImage);
					firstImageChangeTimer[4].schedule(new imageChangeIndex4(), speed_time-5000);
					imageChangeTimer[4].schedule(new timageChangeIndex4(), (speed_time-5000)*2);
					System.out.println("error check6");
				}
				else 
				{
					if(btn[4].getIcon() == breadOnTheGrilImage ) {
						Player.currentMoney -= 100;
					}
					else if ( btn[4].getIcon() == completeSandwichImage) {
						if(Frame_store.amountOfSandwich>0) {
						Player.currentMoney += 100;
						Frame_store.amountOfSandwich-=1;
						if(Player.currentMoney>=6000) {
							JOptionPane.showMessageDialog(null, "<html>미션성공 .<br>게임을 종료합니다..</html>", "미션성공",
									JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}
						}else if(Frame_store.amountOfSandwich==0) {
							JOptionPane.showMessageDialog(null, "<html>선택한 재료가 없습니다.<br>구매할 재료를 선택한 후 다시판매하세요 .</html>", "판매 실패", JOptionPane.ERROR_MESSAGE);
							dispose (); 
						}
					}else {
						//
					}
					
					
					totalAmountLabel.setText("보유금액 :" +Integer.toString(Player.currentMoney));
					cokeAmountLabel.setText("콜라 갯수 :" +Integer.toString(Frame_store.amountOfCoke));
					sellListLabel.setText("샌드위치 갯수 :" +Integer.toString(Frame_store.amountOfSandwich)+" 핫도그갯수 : "+Integer.toString(Frame_store.amountOfHotdog));
					
					btn[4].setIcon(grilImage);
				}
				}else {
					JOptionPane.showMessageDialog(null, "<html>재고가 부족합니다..<br>재고를 구매하고 오세요.</html>", "미션실패",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		});
		btn[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( Frame_store.amountOfSandwich>0){
				if(btn[5].getIcon() == grilImage)
				{
					firstImageChangeTimer[5] = new Timer();
					imageChangeTimer[5] = new Timer();
					btn[5].setIcon(breadOnTheGrilImage);
					firstImageChangeTimer[5].schedule(new imageChangeIndex5(), speed_time-3000);
					imageChangeTimer[5].schedule(new timageChangeIndex5(), (speed_time-3000)*2);
					System.out.println("error check 6");
				}
				else 
				{
					if(btn[5].getIcon() == breadOnTheGrilImage ) {
						Player.currentMoney -= 100;
					}
					else if ( btn[5].getIcon() == completeSandwichImage) {
						Player.currentMoney += 100;
						Frame_store.amountOfSandwich-=1;
						if(Player.currentMoney>=6000) {
							JOptionPane.showMessageDialog(null, "<html>미션성공 .<br>게임을 종료합니다..</html>", "미션성공",
									JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}
					}else {
						//
					}
					
					
					totalAmountLabel.setText("보유금액 :" +Integer.toString(Player.currentMoney));
					sellListLabel.setText("샌드위치 갯수 :" +Integer.toString(Frame_store.amountOfSandwich)+" 핫도그갯수 : "+Integer.toString(Frame_store.amountOfHotdog));
					cokeAmountLabel.setText("콜라 갯수 :" +Integer.toString(Frame_store.amountOfCoke));
		
		
					btn[5].setIcon(grilImage);
				}
				}else {
					JOptionPane.showMessageDialog(null, "<html>재고가 부족합니다..<br>재고를 구매하고 오세요.</html>", "미션실패",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		});
		btn[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( Player.amountOfSandwich>0){
				if(btn[6].getIcon() == grilImage)
				{
					firstImageChangeTimer[6] = new Timer();
					imageChangeTimer[6] = new Timer();
					btn[6].setIcon(breadOnTheGrilImage);
					firstImageChangeTimer[6].schedule(new imageChangeIndex6(), speed_time);
					imageChangeTimer[6].schedule(new timageChangeIndex6(), speed_time*2);
					System.out.println("error check 7");
				}
				else 
				{
					if(btn[6].getIcon() == breadOnTheGrilImage ) {
						Player.currentMoney -= 100;
					}
					else if ( btn[6].getIcon() == completeSandwichImage) {
						Player.currentMoney += 100;
						Frame_store.amountOfSandwich-=1;
						if(Player.currentMoney>=6000) {
							JOptionPane.showMessageDialog(null, "<html>미션성공 .<br>게임을 종료합니다..</html>", "미션성공",
									JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}
					}else {
						//
					}
					
					totalAmountLabel.setText("보유금액 :" +Integer.toString(Player.currentMoney));
					sellListLabel.setText("샌드위치 갯수 :" +Integer.toString(Frame_store.amountOfSandwich)+" 핫도그갯수 : "+Integer.toString(Frame_store.amountOfHotdog));
					cokeAmountLabel.setText("콜라 갯수 :" +Integer.toString(Frame_store.amountOfCoke));
		
		
					
					btn[6].setIcon(grilImage);
				}
				}else {
					JOptionPane.showMessageDialog(null, "<html>재고가 부족합니다..<br>재고를 구매하고 오세요.</html>", "미션실패",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		});
		btn[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( Frame_store.amountOfSandwich>0){
				if(btn[7].getIcon() == grilImage)
				{
					firstImageChangeTimer[7] = new Timer();
					imageChangeTimer[7] = new Timer();
					btn[7].setIcon(breadOnTheGrilImage);
					firstImageChangeTimer[7].schedule(new imageChangeIndex7(), speed_time-3000);
					imageChangeTimer[7].schedule(new timageChangeIndex7(), (speed_time-3000)*2);
					System.out.println("error check8");
				}
				else 
				{
					if(btn[7].getIcon()  == breadOnTheGrilImage ) {
						Player.currentMoney -= 100;
					}
					else if ( btn[7].getIcon() == completeSandwichImage) {
						Player.currentMoney += 100;
						Frame_store.amountOfSandwich-=1;
						if(Player.currentMoney>=6000) {
							JOptionPane.showMessageDialog(null, "<html>미션성공 .<br>게임을 종료합니다..</html>", "미션성공",
									JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}
					}else {
						//
					}
					
					totalAmountLabel.setText("보유금액 :" +Integer.toString(Player.currentMoney));
					cokeAmountLabel.setText("콜라 갯수 :" +Integer.toString(Frame_store.amountOfCoke));
					sellListLabel.setText("샌드위치 갯수 :" +Integer.toString(Frame_store.amountOfSandwich)+" 핫도그갯수 : "+Integer.toString(Frame_store.amountOfHotdog));
		
					btn[7].setIcon(grilImage);
				}
				}else {
					JOptionPane.showMessageDialog(null, "<html>재고가 부족합니다..<br>재고를 구매하고 오세요.</html>", "미션실패",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		});
		btn[8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Frame_store.amountOfCoke>0){
				if(btn[8].getIcon() == cokeBackgroundImage)
				{
					firstImageChangeTimer[8] = new Timer();
					imageChangeTimer[8] = new Timer();
					btn[8].setIcon(cupOfCokeImage);
					firstImageChangeTimer[8].schedule(new imageChangeIndex8(), speed_time);
					imageChangeTimer[8].schedule(new timageChangeIndex8(), speed_time*2);
					System.out.println("error check 9");
				}
				else 
				{
					if(btn[8].getIcon() == cupOfCokeImage ) {
						Player.currentMoney -= 50;
					}
					else if ( btn[8].getIcon() == completeCokeImage) {
						Player.currentMoney += 50;
						Frame_store.amountOfCoke-=1;
						if(Player.currentMoney>=6000) {
							JOptionPane.showMessageDialog(null, "<html>미션성공 .<br>게임을 종료합니다..</html>", "미션성공",
									JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}
					}else {
						//
					}
					
					totalAmountLabel.setText("보유금액 :" +Integer.toString(Player.currentMoney));
					sellListLabel.setText("샌드위치 갯수 :" +Integer.toString(Frame_store.amountOfSandwich)+" 핫도그갯수 : "+Integer.toString(Frame_store.amountOfHotdog));
					cokeAmountLabel.setText("콜라 갯수 :" +Integer.toString(Frame_store.amountOfCoke));
		
		
					btn[8].setIcon(cokeBackgroundImage);
				}
			}else {
				JOptionPane.showMessageDialog(null, "<html>재고가 부족합니다..<br>재고를 구매하고 오세요.</html>", "미션실패",
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
			}
		});

		//contentPane.add(panel[0]);
		panel[1].add(btn[0]);
		panel[1].add(btn[1]);
		panel[1].add(btn[2]);
		contentPane.add(panel[1]);
		panel[2].add(btn[3]); 
		panel[2].add(btn[4]);
		panel[2].add(btn[5]);
		contentPane.add(panel[2]);
		panel[3].add(btn[6]);
		panel[3].add(btn[7]);
		panel[3].add(btn[8]);
		contentPane.add(panel[3]);
		
		
	
		for(int k = 0 ; k < 9 ; k++)   
			contentPane.add(btn[k]);
	
		setSize(Main.SCREEN_WIDTH / 2, Main.SCREEN_HEIGHT);
		setVisible(true);
		 
		totalAmountLabel = new JLabel("보유금액:" + Player.currentMoney );
		sellListLabel= new JLabel("샌드위치 갯수 : "+ Frame_store.amountOfSandwich+" 핫도그갯수 : "+ Frame_store.amountOfHotdog );
		cokeAmountLabel= new JLabel("콜라 갯수 "+ Frame_store.amountOfCoke);
		
		panel[5].add(sellListLabel);
		panel[4].add(totalAmountLabel);
		panel[6].add(cokeAmountLabel);
		contentPane.add(panel[4]);
		contentPane.add(panel[5]);
		contentPane.add(panel[6]);
		
	}



}