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
	
	

	private boolean isMainScreen = false;//ó������ ����ȭ���� �ƴ� ����ȭ�� �̱� ������ false ,  
	
	 JLabel gameExplain,name;
	Choice sandwichName,selectTime; 
	 	
	ArrayList<SandwichMenu> sandwichList = new ArrayList<SandwichMenu>() ;
	private Image sandwichCase;
	
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
		sandwichName.add("�ȳ�");     
		sandwichName.add("������");
		sandwichName.add("������ġ");
		sandwichName.add("����");
		sandwichName.addItemListener(this);
		sandwichName.setBounds(90,163,150,30);
		add(sandwichName);
		sandwichName.setVisible(true);
		
		////////////////////////////////////////////////////////////////////////////////////////
		gameExplain = new JLabel("���Ӽ���  ���� //���� ");
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
			//// �Ʒ��� ���ӽ����̺�Ʈ ������ /////
				startButton.setVisible(false);
				quitButton.setVisible(false);
				leftButton.setVisible(true);//����ȭ�鿡�� �¿� ��ư�� �������ϱ⶧���� true �� �ؾ���
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
				
				//������ ��ư �̺�Ʈ ,����or �Ǹ�?
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

}
