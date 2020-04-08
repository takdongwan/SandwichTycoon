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

public class Frame_sell extends JFrame implements ActionListener, MouseListener {
	Container contentPane;
	JPanel panel[];
	JPanel textview;
	JPanel imageview;
	JPanel selectview;
	
	JButton btn[];
	ImageIcon one;
	ImageIcon two;
	ImageIcon three;
	ImageIcon four;
	
	int score = 0;
	Timer[] timer;
	Timer[] ttimer;
	
	int speed_time = 10000;
	JLabel textLabel;
	JLabel countLabel;
	
	class job0 extends TimerTask{
		public void run(){
			if(btn[0].getIcon() == two)
				btn[0].setIcon(three);
		}
	}
	class tjob0 extends TimerTask{
		public void run(){
			if(btn[0].getIcon() == three)
				btn[0].setIcon(four);
		}
	}
	class job1 extends TimerTask{
		public void run(){
			if(btn[1].getIcon() == two)
				btn[1].setIcon(three);
		}
	}
	class tjob1 extends TimerTask{
		public void run(){
			if(btn[1].getIcon() == three)
				btn[1].setIcon(four);
		}
	}
	class job2 extends TimerTask{
		public void run(){
			if(btn[2].getIcon() == two)
				btn[2].setIcon(three);
		}
	}
	class tjob2 extends TimerTask{
		public void run(){
			if(btn[2].getIcon() == three)
				btn[2].setIcon(four);
		}
	}
	class job3 extends TimerTask{
		public void run(){
			if(btn[3].getIcon() == two)
				btn[3].setIcon(three);
		}
	}
	class tjob3 extends TimerTask{
		public void run(){
			if(btn[3].getIcon() == three)
				btn[3].setIcon(four);
		}
	}
	class job4 extends TimerTask{
		public void run(){
			if(btn[4].getIcon() == two)
				btn[4].setIcon(three);
		}
	}
	class tjob4 extends TimerTask{
		public void run(){
			if(btn[4].getIcon() == three)
				btn[4].setIcon(four);
		}
	}
	class job5 extends TimerTask{
		public void run(){
			if(btn[5].getIcon() == two)
				btn[5].setIcon(three);
		}
	}
	class tjob5 extends TimerTask{
		public void run(){
			if(btn[5].getIcon() == three)
				btn[5].setIcon(four);
		}
	}
	class job6 extends TimerTask{
		public void run(){
			if(btn[6].getIcon() == two)
				btn[6].setIcon(three);
		}
	}
	class tjob6 extends TimerTask{
		public void run(){
			if(btn[6].getIcon() == three)
				btn[6].setIcon(four);
		}
	}
	class job7 extends TimerTask{
		public void run(){
			if(btn[7].getIcon() == two)
				btn[7].setIcon(three);
		}
	}
	class tjob7 extends TimerTask{
		public void run(){
			if(btn[7].getIcon() == three)
				btn[7].setIcon(four);
		}
	}
	class job8 extends TimerTask{
		public void run(){
			if(btn[8].getIcon() == two)
				btn[8].setIcon(three);
		}
	}
	class tjob8 extends TimerTask{
		public void run(){
			if(btn[8].getIcon() == three)
				btn[8].setIcon(four);
		}
	}
	class job9 extends TimerTask{
		public void run(){
			if(btn[8].getIcon() == two)
				speed_time -= 100;
		}
	}
	class tjob9 extends TimerTask{
		public void run(){
			if(btn[9].getIcon() == three)
				btn[9].setIcon(four);
		}
	}
	
	
	Frame_sell(){
		timer = new Timer[10];
		ttimer = new Timer[9];
		
		setTitle("tycoon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(6,1));
		panel = new JPanel[6];
		
		for(int i = 0 ; i < 6 ; i++)
		{
			panel[i] = new JPanel();
			panel[i].setLayout(new FlowLayout());
		}
		
		one = new ImageIcon("images/빵틀.jpg");
		two = new ImageIcon("images/반죽.jpg");
		three = new ImageIcon("images/완성.jpg");
		four = new ImageIcon("images/탄빵.png");
		
		btn = new JButton[10];
		for ( int i = 0 ; i < 9 ; i++)
			btn[i] = new JButton("",one);
		
		timer[9] = new Timer();
		timer[9].schedule(new job9(), 20000, 20000);
		
		btn[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(btn[0].getIcon() == one)
					{
						timer[0] = new Timer();
						ttimer[0] = new Timer();
						btn[0].setIcon(two);
						timer[0].schedule(new job0(), speed_time);
						ttimer[0].schedule(new tjob0(), speed_time*2);
					}
					else 
					{
						if(btn[0].getIcon() == two || btn[0].getIcon() == four)
							score -= 10;
						else
							score += 10;
						textLabel.setText("Score :" +Integer.toString(score));
						btn[0].setIcon(one);
					}
				}
		});
		btn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btn[1].getIcon() == one)
				{
					timer[1] = new Timer();
					ttimer[1] = new Timer();
					btn[1].setIcon(two);
					timer[1].schedule(new job1(), speed_time-3000);
					ttimer[1].schedule(new tjob1(), (speed_time-3000)*2);
				}
				else 
				{
					if(btn[1].getIcon() == two || btn[1].getIcon() == four)
						score -= 10;
					else
						score += 10;
					textLabel.setText("Score :" +Integer.toString(score));
					btn[1].setIcon(one);
				}
			}
		});
		btn[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btn[2].getIcon() == one)
				{
					timer[2] = new Timer();
					ttimer[2] = new Timer();
					btn[2].setIcon(two);
					timer[2].schedule(new job2(), speed_time);
					ttimer[2].schedule(new tjob2(), speed_time*2);
				}
				else 
				{
					if(btn[2].getIcon() == two || btn[2].getIcon() == four)
						score -= 10;
					else
						score += 10;
					textLabel.setText("Score :" +Integer.toString(score));
					btn[2].setIcon(one);
				}
			}
		});
		btn[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btn[3].getIcon() == one)
				{
					timer[3] = new Timer();
					ttimer[3] = new Timer();
					btn[3].setIcon(two);
					timer[3].schedule(new job3(), speed_time-3000);
					ttimer[3].schedule(new tjob3(), (speed_time-3000)*2);
				}
				else 
				{
					if(btn[3].getIcon() == two || btn[3].getIcon() == four)
						score -= 10;
					else
						score += 10;
					textLabel.setText("Score :" +Integer.toString(score));
					btn[3].setIcon(one);
				}
			}
		});
		btn[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btn[4].getIcon() == one)
				{
					timer[4] = new Timer();
					ttimer[4] = new Timer();
					btn[4].setIcon(two);
					timer[4].schedule(new job4(), speed_time-5000);
					ttimer[4].schedule(new tjob4(), (speed_time-5000)*2);
				}
				else 
				{
					if(btn[4].getIcon() == two || btn[4].getIcon() == four)
						score -= 10;
					else
						score += 10;
					textLabel.setText("Score :" +Integer.toString(score));
					btn[4].setIcon(one);
				}
			}
		});
		btn[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btn[5].getIcon() == one)
				{
					timer[5] = new Timer();
					ttimer[5] = new Timer();
					btn[5].setIcon(two);
					timer[5].schedule(new job5(), speed_time-3000);
					ttimer[5].schedule(new tjob5(), (speed_time-3000)*2);
				}
				else 
				{
					if(btn[5].getIcon() == two || btn[5].getIcon() == four)
						score -= 10;
					else
						score += 10;
					textLabel.setText("Score :" +Integer.toString(score));
					btn[5].setIcon(one);
				}
			}
		});
		btn[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btn[6].getIcon() == one)
				{
					timer[6] = new Timer();
					ttimer[6] = new Timer();
					btn[6].setIcon(two);
					timer[6].schedule(new job6(), speed_time);
					ttimer[6].schedule(new tjob6(), speed_time*2);
				}
				else 
				{
					if(btn[6].getIcon() == two || btn[6].getIcon() == four)
						score -= 10;
					else
						score += 10;
					textLabel.setText("Score :" +Integer.toString(score));
					btn[6].setIcon(one);
				}
			}
		});
		btn[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btn[7].getIcon() == one)
				{
					timer[7] = new Timer();
					ttimer[7] = new Timer();
					btn[7].setIcon(two);
					timer[7].schedule(new job7(), speed_time-3000);
					ttimer[7].schedule(new tjob7(), (speed_time-3000)*2);
				}
				else 
				{
					if(btn[7].getIcon() == two || btn[7].getIcon() == four)
						score -= 10;
					else
						score += 10;
					textLabel.setText("Score :" +Integer.toString(score));
					btn[7].setIcon(one);
				}
			}
		});
		btn[8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btn[8].getIcon() == one)
				{
					timer[8] = new Timer();
					ttimer[8] = new Timer();
					btn[8].setIcon(two);
					timer[8].schedule(new job8(), speed_time);
					ttimer[8].schedule(new tjob8(), speed_time*2);
				}
				else 
				{
					if(btn[8].getIcon() == two || btn[8].getIcon() == four)
						score -= 10;
					else
						score += 10;
					textLabel.setText("Score :" +Integer.toString(score));
					btn[8].setIcon(one);
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
	
		setSize(600, 600);
		setVisible(true);

		textLabel = new JLabel("Score :" + score);
		panel[4].add(textLabel);
		contentPane.add(panel[4]);
		
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}