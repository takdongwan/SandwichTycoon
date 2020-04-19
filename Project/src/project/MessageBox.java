package project;


import java.awt.*;
import java.awt.event.*;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MessageBox extends Dialog implements ActionListener 	//다이어로그로 상속받은 메시지 박스에 엑션리스너를 추가하는 클래스
{
	boolean id = false;				
	Button ok,can;					
	JFrame dialogJFrame;
	MessageBox(JFrame jframe, String productName,String productNum,String msg,String orderNumber, boolean okcan,JFrame dialogJFrame)
	{
		super(jframe, "Message", true);		
		this.dialogJFrame = dialogJFrame;
		
		Container panel = jframe.getContentPane();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		if(productName!=null) panel.add(new Label(productName));
		if(productNum!=null) panel.add(new Label(productNum));

		if(orderNumber!=null) panel.add(new Label(orderNumber));
		panel.add(new Label(msg));
		add(panel);
		addOKCancelPanel(okcan);
		createFrame();	//프레임 생성					
		pack();
		setVisible(true);
	}

	void addOKCancelPanel( boolean okcan ) 
	{
		Panel p = new Panel();				//패널 생성
		p.setLayout(new FlowLayout());
		createOKButton( p );				
		if (okcan == true)
			createCancelButton( p );
		add("South",p);
	}

	void createOKButton(Panel p) //초기화후 리스너 설정
	{
		p.add(ok = new Button("OK"));		
		ok.addActionListener(this); 
	}

	void createCancelButton(Panel p) 
	{
		p.add(can = new Button("Cancel"));
		can.addActionListener(this);
	}

	void createFrame() 
	{
		Dimension d = getToolkit().getScreenSize();		//프레임 생성후 설정
		setLocation(d.width/3,d.height/3);
	}

	public void actionPerformed(ActionEvent ae)//버튼 클릭에 따라 액션 설정
	{
		if(ae.getSource() == ok) 				
		{
			id = true;
			if(dialogJFrame!=null) dialogJFrame.dispose();
			new Frame_order().show();
			setVisible(false);
		}
		else if(ae.getSource() == can) 
		{
			setVisible(false);
		}
	}
}






