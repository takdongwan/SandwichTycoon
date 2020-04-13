package project;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class DragImage implements MouseListener, MouseMotionListener {

	int x;
	int y;
	
	public DragImage(Component... pns) {
		for (Component image : pns) {
			image.addMouseListener(this);
			image.addMouseMotionListener(this);
		}
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		e.getComponent().setLocation(e.getX() + e.getComponent().getX() - x, e.getY() + e.getComponent().getY() - y);
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
		x = e.getX();
		y = e.getY();
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
