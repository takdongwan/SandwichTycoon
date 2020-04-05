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

	
	private Image background = new ImageIcon(Main.class.getResource("../images/introbackGround.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	//private JLabel gameExplain = new JLabel(new ImageIcon(Main.class.getResource("../images/gameExplain.PNG")));
	
	//private Image selectedImage = new ImageIcon(Main.class.getResource("../images/.png")).getImage();
	//private Image titleImage = new ImageIcon(Main.class.getResource("../images/.png")).getImage();
	
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton= new JButton(quitButtonBasicImage);
	private JButton leftButton= new JButton(leftButtonBasicImage);
	private JButton rightButton= new JButton(rightButtonBasicImage);
	private int mouseX, mouseY;
	
	
	private boolean isMainScreen = false;//처음에는 메인화면이 아닌 시작화면 이기 때문에 false ,  
	
	 JLabel gameExplain,name;
	Choice sandwichName,selectTime; 
	 
	
	ArrayList<SandwichMenu> sandwichList = new ArrayList<SandwichMenu>() ;
	private Image sandwichCase;
	
	public TycoonGame() {
		
		setUndecorated(true);// 실행시 기본 존재 메뉴바가사라짐
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		///////////////////////////////////////////////////////////////////////////////////
		
		sandwichName=new Choice();
		sandwichName.setVisible(true);
		sandwichName.add("냠냠");     
		sandwichName.add("가나다");
		sandwichName.add("샌드위치");
		sandwichName.add("동완");
		sandwichName.addItemListener(this);
		sandwichName.setBounds(90,163,150,30);
		add(sandwichName);
		sandwichName.setVisible(true);
		
		////////////////////////////////////////////////////////////////////////////////////////
		gameExplain = new JLabel("게임설명  쓸곳 //수정 ");
		gameExplain.setBounds(20,20,500,500);
		gameExplain.setVisible(true);
		gameExplain.setFont(new Font(gameExplain.getFont().getName(), Font.PLAIN, 30));
		gameExplain.setForeground(Color.red);
		add(gameExplain);
		
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
			//// 아래는 게임시작이벤트 넣을곳 /////
				startButton.setVisible(false);
				quitButton.setVisible(false);
				leftButton.setVisible(true);//메인화면에서 좌우 버튼이 보여야하기때문에 true 로 해야함
				rightButton.setVisible(true);
				background =  new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
				gameExplain.setVisible(false);
			
				isMainScreen = true;
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
		
		leftButton.setVisible(false);//처음에는 안보이고 눌리면 보이게 하기위해서 false 로 설정 
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
				//상점 or 판매
			}
		});
		add(leftButton);
		
		rightButton.setVisible(false);//맨처음엔 보이지 않다가 .시작하기버튼 누르고 메인화면 넘어왔을때 메인스크린에 들어와서 보이기시작함.
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
				
				//오른쪽 버튼 이벤트 ,상점or 판매?
			}
		});
		add(rightButton);
		
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

		g.drawImage(background, 0, 0, null);// 0,0 좌표 , add 된게아니라 화면ㅇ ㅔ출력해주는 것을 g 를 사용  
		if(isMainScreen) {
		//	g.drawImage( 샌드위치 이미지 넣어야함,340, 100, null); 
		//	g.drawImage(,100,70, null);
		}
		paintComponents(g);// 메뉴바같은 건 항상 존재하기 때문에 한버튼이나 고정된 메뉴바에 사용함., add등 추가된 애들을 보여주는 부분이고 
		this.repaint();// 전체화면 이미지를 매순간마다 그려줌.
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		
	}

}
