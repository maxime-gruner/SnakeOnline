package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Collection;

import javax.swing.JComponent;

import server.Point;

public class GameBoard extends JComponent {
		BufferedImage img;

	private int scale;
	
	
	public GameBoard(int width,int height,int scale) {
		this.scale = scale;
		img = new BufferedImage(width*scale, height*scale, BufferedImage.TYPE_INT_RGB);
		setPreferredSize(new Dimension(width*scale, height*scale));
		
	}
	
	
	public void drawPoints(Collection<Point> lPoint){
		Graphics g = img.getGraphics();
		g.setColor(Color.WHITE);
		for (Point p: lPoint) {
			g.fillRect(p.getAbs()*scale, p.getOrd()*scale, scale, scale);
		}
		repaint();
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}
}
