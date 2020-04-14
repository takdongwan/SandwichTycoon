package project;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Frame_sell extends JFrame  {
	Container contentPane;
	JPanel panel[];
	JPanel textview;
	JPanel imageview;
	JPanel selectview;
	
	JButton btn[];

	private ImageIcon grilImage = new ImageIcon(Main.class.getResource("../images/gril.png"));
	private ImageIcon breadOnTheGrilImage = new ImageIcon(Main.class.getResource("../images/breadOnTheGril.png"));
	private ImageIcon vegetableOnBreadImage = new ImageIcon(Main.class.getResource("../images/vegetablesOnBread.png"));
	private ImageIcon completeSandwichImage = new ImageIcon(Main.class.getResource("../images/completeSandwich.png"));
	
	
	int score = 0;
	Timer[] timer;
	Timer[] ttimer;
	
	int speed_time = 10000;
	JLabel textLabel; // 샌드위치의 갯수?? 로 변경예
	JLabel countLabel;
	

	public Frame_sell(){
		sellmain();
		new GenerateMission().run();
	}
	
	public void sellmain() {
		timer = new Timer[10];
		ttimer = new Timer[9];
		
		setTitle("tycoon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(6,1));//행과열  상하 간격 .: 2차원 그리드로서 n x n 으로 설정해주며 왼쪽에서 오른쪽, 위에서 아래 순으로 배치​GridLayout(4,3,5,50);     // 4 X 3 그리드에 좌우간격 5, 상하간격5

		panel = new JPanel[6];
		
		for(int i = 0 ; i < 6 ; i++)
		{
			panel[i] = new JPanel();
			panel[i].setLayout(new FlowLayout());
		}
		
		class job0 extends TimerTask{
			public void run(){
				if(btn[0].getIcon() == breadOnTheGrilImage)
					btn[0].setIcon(vegetableOnBreadImage);
			}
		}
		class tjob0 extends TimerTask{
			public void run(){
				if(btn[0].getIcon() == vegetableOnBreadImage)
					btn[0].setIcon(completeSandwichImage);
			}
		}
		class job1 extends TimerTask{
			public void run(){
				if(btn[1].getIcon() == breadOnTheGrilImage)
					btn[1].setIcon(vegetableOnBreadImage);
			}
		}
		class tjob1 extends TimerTask{
			public void run(){
				if(btn[1].getIcon() == vegetableOnBreadImage)
					btn[1].setIcon(completeSandwichImage);
			}
		}
		class job2 extends TimerTask{
			public void run(){
				if(btn[2].getIcon() == breadOnTheGrilImage)
					btn[2].setIcon(vegetableOnBreadImage);
			}
		}
		class tjob2 extends TimerTask{
			public void run(){
				if(btn[2].getIcon() == vegetableOnBreadImage)
					btn[2].setIcon(completeSandwichImage);
			}
		}
		class job3 extends TimerTask{
			public void run(){
				if(btn[3].getIcon() == breadOnTheGrilImage)
					btn[3].setIcon(vegetableOnBreadImage);
			}
		}
		class tjob3 extends TimerTask{
			public void run(){
				if(btn[3].getIcon() == vegetableOnBreadImage)
					btn[3].setIcon(completeSandwichImage);
			}
		}
		class job4 extends TimerTask{
			public void run(){
				if(btn[4].getIcon() == breadOnTheGrilImage)
					btn[4].setIcon(vegetableOnBreadImage);
			}
		}
		class tjob4 extends TimerTask{
			public void run(){
				if(btn[4].getIcon() == vegetableOnBreadImage)
					btn[4].setIcon(completeSandwichImage);
			}
		}
		class job5 extends TimerTask{
			public void run(){
				if(btn[5].getIcon() == breadOnTheGrilImage)
					btn[5].setIcon(vegetableOnBreadImage);
			}
		}
		class tjob5 extends TimerTask{
			public void run(){
				if(btn[5].getIcon() == vegetableOnBreadImage)
					btn[5].setIcon(completeSandwichImage);
			}
		}
		class job6 extends TimerTask{
			public void run(){
				if(btn[6].getIcon() == breadOnTheGrilImage)
					btn[6].setIcon(vegetableOnBreadImage);
			}
		}
		class tjob6 extends TimerTask{
			public void run(){
				if(btn[6].getIcon() == vegetableOnBreadImage)
					btn[6].setIcon(completeSandwichImage);
			}
		}
		class job7 extends TimerTask{
			public void run(){
				if(btn[7].getIcon() == breadOnTheGrilImage)
					btn[7].setIcon(vegetableOnBreadImage);
			}
		}
		class tjob7 extends TimerTask{
			public void run(){
				if(btn[7].getIcon() == vegetableOnBreadImage)
					btn[7].setIcon(completeSandwichImage);
			}
		}
		class job8 extends TimerTask{
			public void run(){
				if(btn[8].getIcon() == breadOnTheGrilImage)
					btn[8].setIcon(vegetableOnBreadImage);
			}
		}
		class tjob8 extends TimerTask{
			public void run(){
				if(btn[8].getIcon() == vegetableOnBreadImage)
					btn[8].setIcon(completeSandwichImage);
			}
		}
		class job9 extends TimerTask{
			public void run(){
				if(btn[8].getIcon() == breadOnTheGrilImage)
					speed_time -= 100;
			}
		}
		class tjob9 extends TimerTask{
			public void run(){
				if(btn[9].getIcon() == vegetableOnBreadImage)
					btn[9].setIcon(completeSandwichImage);
			}
		}
		
		btn = new JButton[10];
		for ( int i = 0 ; i < 9 ; i++)
			btn[i] = new JButton("",grilImage);
		
		timer[9] = new Timer();
		timer[9].schedule(new job9(), 20000, 20000);
		
		btn[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(btn[0].getIcon() == grilImage)
					{
						timer[0] = new Timer();
						ttimer[0] = new Timer();
						btn[0].setIcon(breadOnTheGrilImage);
						timer[0].schedule(new job0(), speed_time);
						ttimer[0].schedule(new tjob0(), speed_time*2);
					}
					else 
					{
						if(btn[0].getIcon() == breadOnTheGrilImage || btn[0].getIcon() == completeSandwichImage)
							score -= 10;
						else
							score += 10;
						textLabel.setText("Score :" +Integer.toString(score));
						btn[0].setIcon(grilImage);
					}
				}
		});
		btn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btn[1].getIcon() == grilImage)
				{
					timer[1] = new Timer();
					ttimer[1] = new Timer();
					btn[1].setIcon(breadOnTheGrilImage);
					timer[1].schedule(new job1(), speed_time-3000);
					ttimer[1].schedule(new tjob1(), (speed_time-3000)*2);
				}
				else 
				{
					if(btn[1].getIcon() == breadOnTheGrilImage || btn[1].getIcon() == completeSandwichImage)
						score -= 10;
					else
						score += 10;
					textLabel.setText("Score :" +Integer.toString(score));
					btn[1].setIcon(grilImage);
				}
			}
		});
		btn[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btn[2].getIcon() == grilImage)
				{
					timer[2] = new Timer();
					ttimer[2] = new Timer();
					btn[2].setIcon(breadOnTheGrilImage);
					timer[2].schedule(new job2(), speed_time);
					ttimer[2].schedule(new tjob2(), speed_time*2);
				}
				else 
				{
					if(btn[2].getIcon() == breadOnTheGrilImage || btn[2].getIcon() == completeSandwichImage)
						score -= 10;
					else
						score += 10;
					textLabel.setText("Score :" +Integer.toString(score));
					btn[2].setIcon(grilImage);
				}
			}
		});
		btn[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btn[3].getIcon() == grilImage)
				{
					timer[3] = new Timer();
					ttimer[3] = new Timer();
					btn[3].setIcon(breadOnTheGrilImage);
					timer[3].schedule(new job3(), speed_time-3000);
					ttimer[3].schedule(new tjob3(), (speed_time-3000)*2);
				}
				else 
				{
					if(btn[3].getIcon() == breadOnTheGrilImage || btn[3].getIcon() == completeSandwichImage)
						score -= 10;
					else
						score += 10;
					textLabel.setText("Score :" +Integer.toString(score));
					btn[3].setIcon(grilImage);
				}
			}
		});
		btn[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btn[4].getIcon() == grilImage)
				{
					timer[4] = new Timer();
					ttimer[4] = new Timer();
					btn[4].setIcon(breadOnTheGrilImage);
					timer[4].schedule(new job4(), speed_time-5000);
					ttimer[4].schedule(new tjob4(), (speed_time-5000)*2);
				}
				else 
				{
					if(btn[4].getIcon() == breadOnTheGrilImage || btn[4].getIcon() == completeSandwichImage)
						score -= 10;
					else
						score += 10;
					textLabel.setText("Score :" +Integer.toString(score));
					btn[4].setIcon(grilImage);
				}
			}
		});
		btn[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btn[5].getIcon() == grilImage)
				{
					timer[5] = new Timer();
					ttimer[5] = new Timer();
					btn[5].setIcon(breadOnTheGrilImage);
					timer[5].schedule(new job5(), speed_time-3000);
					ttimer[5].schedule(new tjob5(), (speed_time-3000)*2);
				}
				else 
				{
					if(btn[5].getIcon() == breadOnTheGrilImage || btn[5].getIcon() == completeSandwichImage)
						score -= 10;
					else
						score += 10;
					textLabel.setText("Score :" +Integer.toString(score));
					btn[5].setIcon(grilImage);
				}
			}
		});
		btn[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btn[6].getIcon() == grilImage)
				{
					timer[6] = new Timer();
					ttimer[6] = new Timer();
					btn[6].setIcon(breadOnTheGrilImage);
					timer[6].schedule(new job6(), speed_time);
					ttimer[6].schedule(new tjob6(), speed_time*2);
				}
				else 
				{
					if(btn[6].getIcon() == breadOnTheGrilImage || btn[6].getIcon() == completeSandwichImage)
						score -= 10;
					else
						score += 10;
					textLabel.setText("Score :" +Integer.toString(score));
					btn[6].setIcon(grilImage);
				}
			}
		});
		btn[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btn[7].getIcon() == grilImage)
				{
					timer[7] = new Timer();
					ttimer[7] = new Timer();
					btn[7].setIcon(breadOnTheGrilImage);
					timer[7].schedule(new job7(), speed_time-3000);
					ttimer[7].schedule(new tjob7(), (speed_time-3000)*2);
				}
				else 
				{
					if(btn[7].getIcon() == breadOnTheGrilImage || btn[7].getIcon() == completeSandwichImage)
						score -= 10;
					else
						score += 10;
					textLabel.setText("Score :" +Integer.toString(score));
					btn[7].setIcon(grilImage);
				}
			}
		});
		btn[8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btn[8].getIcon() == grilImage)
				{
					timer[8] = new Timer();
					ttimer[8] = new Timer();
					btn[8].setIcon(breadOnTheGrilImage);
					timer[8].schedule(new job8(), speed_time);
					ttimer[8].schedule(new tjob8(), speed_time*2);
				}
				else 
				{
					if(btn[8].getIcon() == breadOnTheGrilImage || btn[8].getIcon() == completeSandwichImage)
						score -= 10;
					else
						score += 10;
					textLabel.setText("Score :" +Integer.toString(score));
					btn[8].setIcon(grilImage);
				}
			}
		});

//		contentPane.add(panel[0]);
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
		System.out.println("error check 1");
		setVisible(true);
		textLabel = new JLabel("Score :" + score);
		panel[4].add(textLabel);
		contentPane.add(panel[4]);
		
	}

}