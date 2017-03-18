package client.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import server.model.Point;

public class GameBoard extends JComponent {
		
	
	private static final long serialVersionUID = 1L;

	BufferedImage img;

	private int scale;
	
	BufferedImage background; 
	
	private int factor = 7; //grossisement du pixel
	int width;
	int height;
	
	int centerX = 0;
	int centerY = 0;
	
	int rangeX ; //taille de la fentre
	int rangeY ;
	
	int exScale;
	
	
	public GameBoard(int w,int h,int scale) {
		
		
		this.rangeX = w*scale;
		this.rangeY = h*scale;
		
		this.width = w*factor;
		this.height = h*factor;
		
		
		img = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
		setPreferredSize(new Dimension(w, h));
		
		
		
		
		try {
			background = ImageIO.read(new File("src/fond.jpg"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.exScale = scale;
		
		this.scale = factor;
		
	}
	
	
	
	public void drawPoints(Collection<Point> lPoint){
		Graphics g = img.getGraphics();
		g.setColor(Color.RED);
		for (Point p: lPoint) {
			g.fillRect(p.getAbs()*scale, p.getOrd()*scale, scale, scale);
			
		}
		repaint();
		
	}
	public void drawMyPoints(Collection<Point> lPoint){
		Graphics g = img.getGraphics();
		g.setColor(Color.WHITE);
		for (Point p: lPoint) {
			g.fillRect(p.getAbs()*scale, p.getOrd()*scale, scale, scale);
			centerX = p.getAbs()*scale;
			centerY = p.getOrd()*scale;
		}
		repaint();
		
	}
	
	public void drawHead(Point p){
		Graphics g = img.getGraphics();
		g.setColor(Color.RED);
		g.fillRect(p.getAbs()*scale, p.getOrd()*scale, scale, scale);
		
		repaint();
	}
	public void drawMyHead(Point p){
		Graphics g = img.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(p.getAbs()*scale, p.getOrd()*scale, scale, scale);
		
		centerX = p.getAbs()*scale;
		centerY = p.getOrd()*scale;
		repaint();
	}
	
	public void drawTail(Point t){
		Graphics g = img.getGraphics();
		
		int erase = background.getRGB(t.getAbs()*scale,t.getOrd()*scale);
		
		g.setColor(new Color(erase));
		
		g.fillRect(t.getAbs()*scale, t.getOrd()*scale, scale, scale);
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int offsetX = -centerX+rangeX/2;
		int offsetY = -centerY+rangeY/2;
		
		//super.paintComponent(g);
		if(centerX < rangeX/2){ //limite
			offsetX = 0;
		}else if(centerX > width-rangeX/2 ){
			offsetX =  -width+rangeX;

		}
		if(centerY < rangeY/2){
			offsetY = 0;
		}else if(centerY > height-rangeY/2){
			offsetY = -height+rangeY;					
		}
		
		
		
		g.drawImage(background, offsetX  , offsetY,null);
		g.drawImage(img, offsetX , offsetY, null);
	}


	public void eraseBody(ArrayList<Point> bodyP) {
		Graphics g = img.getGraphics();

		for (Point p: bodyP) {
			int erase = background.getRGB(p.getAbs()*scale,p.getOrd()*scale);
			
			g.setColor(new Color(erase));
			g.fillRect(p.getAbs()*scale, p.getOrd()*scale, scale, scale);
		}
		repaint();
	}
	
	public void drawApple(Point p){
		Graphics g = img.getGraphics();
		g.setColor(Color.GREEN);
		g.fillRect(p.getAbs()*scale, p.getOrd()*scale, scale, scale);
		repaint();
	}
}
