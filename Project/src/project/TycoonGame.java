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
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
	int delayTime;

	static ImageIcon minigameButtonImage = new ImageIcon(new ImageIcon(Main.class.getResource("../images/nyanCatButton.png")).getImage()
			.getScaledInstance(119, 71, Image.SCALE_SMOOTH));
	private ImageIcon exitButtonEnterdImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));

	private static ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
//	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	//private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	
	private ImageIcon buyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/buyButtonEntered.png"));
	private ImageIcon sellButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/sellButtonEntered.png"));

	private static ImageIcon sellButtonBasicImage = new ImageIcon(Main.class.getResource("../images/sellButtonBasic.png"));
	private static ImageIcon buyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/buyButtonBasic.png"));


	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));

	private static ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));


	private static Image background = new ImageIcon(Main.class.getResource("../images/introbackGround.png")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	// private JLabel gameExplain = new JLabel(new
	// ImageIcon(Main.class.getResource("../images/gameExplain.PNG")));

	// private Image selectedImage = new
	// ImageIcon(Main.class.getResource("../images/.png")).getImage();
	// private Image titleImage = new
	// ImageIcon(Main.class.getResource("../images/.png")).getImage();

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private static JButton minigameButton = new JButton(minigameButtonImage);
	private static JButton leftButton= new JButton(leftButtonBasicImage);
	// private JButton rightButton= new JButton(rightButtonBasicImage);
	private static JButton buyButton = new JButton(buyButtonBasicImage);
	private static JButton sellButton = new JButton(sellButtonBasicImage);
	private static JButton backButton = new JButton(backButtonBasicImage);
	private int mouseX, mouseY;

	private boolean isTimerRun = false;
	private static boolean isMainScreen = false;
	private boolean isGameScreen =false;
	private boolean isSellMain = false;
	private int nowSelected = 0;

	Frame_order orderFrame;
	Frame_store storeFrame;
	Frame_mission missionFrame;
	Frame_jumpGame jumpGameFrame;

	Frame_sell  sellFrame;
	static JLabel gameExplain;
	JLabel name;
	static JLabel menuLabel;
	static JLabel beverageLabel;
	Choice sandwichName,selectTime; 

	// ArrayList<SandwichMenu> sandwichList = new ArrayList<SandwichMenu>() ;

	private Image sandwichCase;

	Random random = new Random();
	long missionTime = 0;
	int missionNumber;

	static TimerTask timerTask;
	Timer timer;

	public TycoonGame() {
		setUndecorated(true);// ����� �⺻ ���� �޴��ٰ������
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		///////////////////////////////////////////////////////////////////////////////////

		/*
		 * sandwichName=new Choice(); sandwichName.setVisible(true);
		 * sandwichName.add("종류"); sandwichName.add("�ㅈㅇ"); sandwichName.add("ㅈ");
		 * sandwichName.add("ㅈ"); sandwichName.addItemListener(this);
		 * sandwichName.setBounds(90,163,150,30); add(sandwichName);
		 * sandwichName.setVisible(true);
		 */

		////////////////////////////////////////////////////////////////////////////////////////
		gameExplain = new JLabel("물건 구매 후 6000원을  벌기");
		gameExplain.setBounds(40, 150, 500, 500);
		gameExplain.setVisible(true);
		gameExplain.setFont(new Font(gameExplain.getFont().getName(), Font.PLAIN, 30));
		gameExplain.setForeground(Color.black);
		add(gameExplain);
		// SCREEN_WIDTH SCREEN_HEIGHT
		menuLabel = new JLabel("샌드위치 메뉴 ");
		menuLabel.setBounds(1000, -200, 1280, 720);
		menuLabel.setVisible(false);
		menuLabel.setFont(new Font(menuLabel.getFont().getName(), Font.PLAIN, 30));
		menuLabel.setForeground(Color.black);
		add(menuLabel);

		beverageLabel = new JLabel("음료수 메뉴  ");
		beverageLabel.setBounds(100, -200, 1280, 720);
		beverageLabel.setVisible(false);
		beverageLabel.setFont(new Font(beverageLabel.getFont().getName(), Font.PLAIN, 30));
		beverageLabel.setForeground(Color.black);
		add(beverageLabel);

		exitButton.setBounds(1245, 0,30, 30);//종료버튼  위치설정 
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

		startButton.setBounds(200, 600, 400, 100);
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
		
		 leftButton.setVisible(false);//ó�����
		 leftButton.setBounds(110, 580, 250, 67);
		 leftButton.setContentAreaFilled(false); leftButton.setFocusPainted(false);
		 leftButton.addMouseListener(new MouseAdapter() {
		  
		  @Override public void mouseEntered(MouseEvent e) {
		  leftButton.setIcon(leftButtonEnteredImage); leftButton.setCursor(new
		  Cursor(Cursor.HAND_CURSOR)); }
		  
		  @Override public void mouseExited(MouseEvent e) {
		  leftButton.setIcon(leftButtonBasicImage); leftButton.setCursor(new
		  Cursor(Cursor.DEFAULT_CURSOR)); }
		  
		  @Override public void mousePressed(MouseEvent e) { 
			  
				orderFrame = new Frame_order();
				orderFrame.Frame_order();
		  } 
		  });
		  add(leftButton);
		 
	/*	  rightButton.setVisible(false);//��ó���� ������ �ʴٰ� .�����ϱ��ư ������ ����ȭ�� �Ѿ������ ���ν�ũ���� ���ͼ� ���̱������. 
		  rightButton.setBounds(1080, 600, 60,60); rightButton.setContentAreaFilled(false);
		  rightButton.setFocusPainted(false); rightButton.addMouseListener(new
		  MouseAdapter() {
		  
		  @Override public void mouseEntered(MouseEvent e) {
		  rightButton.setIcon(rightButtonEnteredImage); rightButton.setCursor(new
		  Cursor(Cursor.HAND_CURSOR)); }
		  
		  @Override public void mouseExited(MouseEvent e) {
		  rightButton.setIcon(rightButtonBasicImage); rightButton.setCursor(new
		  Cursor(Cursor.DEFAULT_CURSOR)); }
		  
		  @Override public void mousePressed(MouseEvent e) {
		 
		 
		  } }); add(rightButton);*/
		 

		buyButton.setVisible(false);
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
				gameStart(nowSelected, "sell");
				isSellMain = true;
				sellFrame = new Frame_sell();

				isTimerRun = true;
				delayTime = 40000;
				timer = new Timer();
				timer.schedule(timerTaskMaker(), delayTime);

				System.out.println("판매상점  입장");
			}
		});
		add(sellButton);

		minigameButton.setVisible(false);
		minigameButton.setBounds(940, 580, minigameButtonImage.getIconWidth(), minigameButtonImage.getIconHeight());
		minigameButton.setContentAreaFilled(false);
		minigameButton.setFocusPainted(false);
		minigameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				minigameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				minigameButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				jumpGameFrame = new Frame_jumpGame();
			}
		});
		add(minigameButton);
		
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

				// 타이머가 실행되고 있을 경우
				if (isTimerRun == true) {
					timer.cancel();
				} else {

				}

				if (isSellMain == true) {
					sellFrame.dispose();
					;
				} else {
					System.out.println("background 화면전환");
				}
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
				setLocation(x - mouseX, y - mouseY);
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

		g.drawImage(background, 0, 0, null);
		if (isMainScreen) {
			// g.drawImage( ������ġ �̹��� �־����,340, 100, null);
			// g.drawImage(,100,70, null);
		}
		paintComponents(g);//
		this.repaint();
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {

	}

	public void gameStart(int nowSelected, String isSell) {

		isMainScreen = false;
		// leftButton.setVisible(false);
		// rightButton.setVisible(false);.
		beverageLabel.setVisible(false);
		menuLabel.setVisible(false);
		sellButton.setVisible(false);
		leftButton.setVisible(false);
		buyButton.setVisible(false);
		minigameButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
		backButton.setVisible(true);

		isGameScreen = true;
	}

	public void selectSell() {
		// 판매버튼누를시
	}

	public void selectBuy() {
		// 물품구매 버튼 누를시

	}

	public static void backMain() {
		isMainScreen = true;

		beverageLabel.setVisible(true);
		menuLabel.setVisible(true);
		sellButton.setVisible(true);
		buyButton.setVisible(true);
		leftButton.setVisible(true);
		minigameButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
		backButton.setVisible(false);
		isMainScreen = false;
		gameExplain.setVisible(false);

	}

	public void enterMain() {
		 leftButton.setVisible(true);
		startButton.setVisible(false);
		quitButton.setVisible(false);
		beverageLabel.setVisible(true);
		menuLabel.setVisible(true);
		minigameButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
		isMainScreen = true;

		sellButton.setVisible(true);//
		buyButton.setVisible(true);
		isMainScreen = true;
		gameExplain.setVisible(false);

	}

	public TimerTask timerTaskMaker() {

		TimerTask timerTask = new TimerTask() {

			@Override
			public void run() {
				System.out.println("TimerTask 생성");
				delayTime = (random.nextInt(2) * 10000) + 20000; // 20초 ~ 30초에 한번 미션 창 생성
				timer.schedule(timerTaskMaker(), delayTime);

				missionNumber = random.nextInt(3);
				missionFrame = new Frame_mission(missionNumber);
				System.out.println("미션" + missionNumber + " 생성");
			}

		};
		return timerTask;

	}
}
