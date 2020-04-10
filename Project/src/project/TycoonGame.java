package project;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;




public class TycoonGame extends JFrame implements ItemListener {

	private Image screenImage;
	private Component scr;
	private Graphics screenGraphic;
	
	int select;
	int tickteNum;
	
	
	private ImageIcon exitButtonEnterdImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon startButtonEnteredImage =new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage =new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage =new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage =new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	
	private ImageIcon leftButtonBasicImage =new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon rightButtonBasicImage =new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage =new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon rightButtonEnteredImage =new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon buyButtonEnteredImage =new ImageIcon(Main.class.getResource("../images/buyButtonEntered.png"));
	private ImageIcon sellButtonEnteredImage =new ImageIcon(Main.class.getResource("../images/sellButtonEntered.png"));
	private ImageIcon sellButtonBasicImage =new ImageIcon(Main.class.getResource("../images/sellButtonBasic.png"));
	private ImageIcon buyButtonBasicImage =new ImageIcon(Main.class.getResource("../images/buyButtonBasic.png"));
	private ImageIcon backButtonEnteredImage =new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage =new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	
	
	private Image background = new ImageIcon(Main.class.getResource("../images/introbackGround.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	//private JLabel gameExplain = new JLabel(new ImageIcon(Main.class.getResource("../images/gameExplain.PNG")));
	
	//private Image selectedImage = new ImageIcon(Main.class.getResource("../images/.png")).getImage();
	//private Image titleImage = new ImageIcon(Main.class.getResource("../images/.png")).getImage();
	
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton= new JButton(quitButtonBasicImage);
//	private JButton leftButton= new JButton(leftButtonBasicImage);
	//private JButton rightButton= new JButton(rightButtonBasicImage);
	private JButton buyButton= new JButton(buyButtonBasicImage);
	private JButton sellButton= new JButton(sellButtonBasicImage);
	private JButton backButton= new JButton(backButtonBasicImage);
	private int mouseX, mouseY;
	
	private boolean isMainScreen = false;
	private boolean isGameScreen =false;
	private int nowSelected = 0;
	
	Frame_store storeFrame;
	Frame_mission missionFrame;
	JLabel gameExplain,name,menuLabel,beverageLabel;
	Choice sandwichName,selectTime; 
	 	
	ArrayList<SandwichMenu> sandwichList = new ArrayList<SandwichMenu>() ;
	private Image sandwichCase;
	
	Random random = new Random();
	long missionTime = 0;
	int missionNumber;
	
	public TycoonGame() {
		setUndecorated(true);// ����� �⺻ ���� �޴��ٰ������
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		///////////////////////////////////////////////////////////////////////////////////
		
		sandwichName=new Choice();
		sandwichName.setVisible(true);
		sandwichName.add("종류");     
		sandwichName.add("�ㅈㅇ");
		sandwichName.add("ㅈ");
		sandwichName.add("ㅈ");
		sandwichName.addItemListener(this);
		sandwichName.setBounds(90,163,150,30);
		add(sandwichName);
		sandwichName.setVisible(true);
		
		////////////////////////////////////////////////////////////////////////////////////////
		gameExplain = new JLabel("게임설명 쓸고 /수정해야됨 ");
		gameExplain.setBounds(20,20,500,500);
		gameExplain.setVisible(true);
		gameExplain.setFont(new Font(gameExplain.getFont().getName(), Font.PLAIN, 30));
		gameExplain.setForeground(Color.red);
		add(gameExplain);
		//SCREEN_WIDTH  SCREEN_HEIGHT
		menuLabel = new JLabel("샌드위치 메뉴 ");
		menuLabel.setBounds(1000,-200,1280,720);
		menuLabel.setVisible(false);
		menuLabel.setFont(new Font(menuLabel.getFont().getName(), Font.PLAIN, 30));
		menuLabel.setForeground(Color.black);
		add(menuLabel);
		
		beverageLabel= new JLabel("음료수 메뉴  ");
		beverageLabel.setBounds(100,-200,1280,720);
		beverageLabel.setVisible(false);
		beverageLabel.setFont(new Font(beverageLabel.getFont().getName(), Font.PLAIN, 30));
		beverageLabel.setForeground(Color.black);
		add(beverageLabel);
		//gameExplain.setBounds(500, 150, 329, 360);
		//add(gameExplain);
		
		
		//sandwichList.add(new SandwichMenu("", title, title));
		
		exitButton.setBounds(1245, 0,30, 30);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnterdImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
				System.exit(0);
			}
		});
		add(exitButton);
		
		startButton.setBounds(200, 600,400, 100);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
			
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
			//// �Ʒ��� ���ӽ����̺�Ʈ ������ /////
				enterMain();
			}
		});
		add(startButton);

		quitButton.setBounds(750, 600, 400, 100);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
				System.exit(0);
			}
		});
		add(quitButton);
		/*
		leftButton.setVisible(false);//ó������ �Ⱥ��̰� ������ ���̰� �ϱ����ؼ� false �� ���� 
		leftButton.setBounds(140, 600, 60, 60);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//���� or �Ǹ�
			}
		});
		add(leftButton);
		
		rightButton.setVisible(false);//��ó���� ������ �ʴٰ� .�����ϱ��ư ������ ����ȭ�� �Ѿ������ ���ν�ũ���� ���ͼ� ���̱������.
		rightButton.setBounds(1080, 600, 60, 60);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}
		});
		add(rightButton);*/
		
		buyButton.setVisible(false);//��ó���� ������ �ʴٰ� .�����ϱ��ư ������ ����ȭ�� �Ѿ������ ���ν�ũ���� ���ͼ� ���̱������.
		buyButton.setBounds(375, 580, 250, 67);
		buyButton.setContentAreaFilled(false);
		buyButton.setFocusPainted(false);
		buyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buyButton.setIcon(buyButtonEnteredImage);
				buyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				buyButton.setIcon(buyButtonBasicImage);
				buyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				gameStart(nowSelected,"buy");
				storeFrame = new Frame_store();
				storeFrame.setVisible(true);
				
				System.out.println("재료상점 입장");
			}
		});
		add(buyButton);
		
		sellButton.setVisible(false);
		sellButton.setBounds(655, 580, 250, 67);
		sellButton.setContentAreaFilled(false);
		sellButton.setFocusPainted(false);
		sellButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sellButton.setIcon(sellButtonEnteredImage);
				sellButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sellButton.setIcon(sellButtonBasicImage);
				sellButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				gameStart(nowSelected,"sell");
				generateMission();
				
			}
		});
		add(sellButton);

		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60, 60);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				backMain();
				
			}
		});
		add(backButton);
		
		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getXOnScreen();
				setLocation(x-mouseX, y-mouseY);
			}
		});
		add(menuBar);
		
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);

	}

	private void screenDraw(Graphics g) {

		g.drawImage(background, 0, 0, null);// 0,0 ��ǥ , add �ȰԾƴ϶� ȭ�餷 ��������ִ� ���� g �� ���  
		if(isMainScreen) {
		//	g.drawImage( ������ġ �̹��� �־����,340, 100, null); 
		//	g.drawImage(,100,70, null);
		}
		paintComponents(g);// �޴��ٰ��� �� �׻� �����ϱ� ������ �ѹ�ư�̳� ������ �޴��ٿ� �����., add�� �߰��� �ֵ��� �����ִ� �κ��̰� 
		this.repaint();// ��üȭ�� �̹����� �ż������� �׷���.
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		
	}
	
	public void gameStart(int nowSelected , String isSell) {
		
		isMainScreen = false;
		//leftButton.setVisible(false);
		//rightButton.setVisible(false);.
		beverageLabel.setVisible(false);
		menuLabel.setVisible(false);
		sellButton.setVisible(false);
		buyButton.setVisible(false);
		background =  new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		backButton.setVisible(true);
		
		isGameScreen = true;
	}
	
	public void selectSell() {
	//판매버튼누를시
	}
	
	
	
	public void selectBuy() {
		//물품구매 버튼 누를시
		
	}
	
	
	public void backMain() {
		isMainScreen =true;
		//leftButton.setVisible(true);
		//rightButton.setVisible(true);
		beverageLabel.setVisible(true);
		menuLabel.setVisible(true);
		sellButton.setVisible(true);
		buyButton.setVisible(true);
		background =  new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		backButton.setVisible(false);
		isMainScreen = false;
		gameExplain.setVisible(false);
		
	}
	public void enterMain() {		
		startButton.setVisible(false);
		quitButton.setVisible(false);
		beverageLabel.setVisible(true);
		menuLabel.setVisible(true);
		background =  new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		isMainScreen = true;
		//leftButton.setVisible(true);//����ȭ�鿡�� �¿� ��ư�� �������ϱ⶧���� true �� �ؾ���
		//rightButton.setVisible(true);
		sellButton.setVisible(true);//
		buyButton.setVisible(true);
		isMainScreen = true;
		gameExplain.setVisible(false);
		
	}
	
	public void generateMission() {
		if(missionTime < System.currentTimeMillis()) {
			missionTime = System.currentTimeMillis() + random.nextInt(10000) + 5000;
			missionNumber = random.nextInt(3);
			missionFrame = new Frame_mission(missionNumber);
			missionFrame.setVisible(true);
			
		}
	}

}
