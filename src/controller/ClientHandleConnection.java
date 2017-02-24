package controller;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

import model.ClientCore;
import server.Point;

public class ClientHandleConnection extends Thread implements Runnable, ClientProtocol  {
	
	
	private Socket sock = null;
	private ClientOutput cOut;
	private ClientInput cIn;
	private ClientCore c;
	private boolean stop = false;
	
	public ClientHandleConnection(Socket s,ClientCore c) {
		this.sock= s;
		this.c = c;
		try {
			cOut = new ClientOutput(sock.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void run() {
		try(Socket s1 = sock){
			cIn = new ClientInput(s1.getInputStream(), this);
			cIn.doRun();
		} catch (IOException e) {
			e.printStackTrace();
			finish();
		}
		
	}
	
	private void finish(){
		if(!stop){
			stop = true;
			try {
				sock.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void sendName(String name) {
		cOut.sendName(name);
	}

	@Override
	public void nameOK() {
		c.acceptName();
	}

	@Override
	public void nameBad() {
		finish();
		
	}

	@Override
	public void askPList() {
		cOut.askPList();
		
	}

	@Override
	public void sendPlist(Collection<String> pList) {
		c.fillUserList(pList);
	}

	@Override
	public void initSnake(Collection<Point> bodyP) {
		c.drawBody(bodyP);
		
	}

	@Override
	public void sendDir(String dir) {
		cOut.sendDir(dir);
	}

	@Override
	public void drawMove(Point head, Point tail) {
		System.out.println( "head : " + head.toString());
		System.out.println( "tail : " + tail.toString());
		c.drawHead(head);
		c.drawTail(tail);
	}
	
	@Override
	public void eraseSnake(ArrayList<Point> bodyP) {
		c.eraseBody(bodyP);
	}
	
	@Override
	public void die() {
		finish(); // a changer
	}

	
	
	

}
