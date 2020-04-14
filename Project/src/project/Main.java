package project;

import javax.swing.SwingUtilities;

public class Main {

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT= 720;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new TycoonGame();				
			}
			

		});
	//dongwan workspace 2
		//dongwan workspace 2

	}

}

 