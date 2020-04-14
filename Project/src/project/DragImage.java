package project;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class DragImage implements MouseListener, MouseMotionListener {

	int press_xPosition;
	int press_yPosition;
	
	public DragImage(Component[] component) {
		for (Component image : component) {
			image.addMouseListener(this);
			image.addMouseMotionListener(this);
		}
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		e.getComponent().setLocation(e.getX() + e.getComponent().getX() - press_xPosition, e.getY() + e.getComponent().getY() - press_yPosition);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		press_xPosition = e.getX();
		press_yPosition = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
