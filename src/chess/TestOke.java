package chess;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class TestOke {

	public static void main(String args[]) {
////		int x = 2;
//		int width = 608;
//		int offset = 0;
//		int boardSize = width-offset*2-17;
//		System.out.println(boardSize/8.0);
		System.out.println((int)(Math.random()));
		JFrame frame = new JFrame();
		frame.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
//				System.out.println("click");

				System.out.println((int)(Math.random()+.5));
			}

			@Override
			public void mousePressed(MouseEvent e) {
//				System.out.println("press");
			}

			@Override
			public void mouseReleased(MouseEvent e) {
//				System.out.println("release");
			}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
		frame.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
//				System.out.println("drag");
			}

			@Override
			public void mouseMoved(MouseEvent e) {}
			
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}
