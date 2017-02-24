package model;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.ClientHandleConnection;
import server.Point;

public class ClientCore extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int WIDTH = 800;
	static int HEIGHT = 800;
	static int SCALE = 4;
	
	private int port = 1234;
	private  Socket sock;
	private boolean connected = false;
	private String connectAdress = "127.0.0.1";
	private String name="" ;
	private ClientHandleConnection connection;
	
	private JPanel startPanel;
	private JPanel screen;
	private GameBoard gameBoard;
	private JTextField nameField;
	private JButton acceptButton;
	private JScrollPane playerListPane;
	private JList<String> playerList;
	
	public ClientCore() {
		port = 1234;
		connectAdress = "127.0.0.1";
		name = "Default";
		connected = false;
		initComponent();
	}
	
	private void connect(){
		if(connected) return;
		try {
			sock = new Socket(connectAdress, port);
			connection = new ClientHandleConnection(sock, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		connected = true;
		connection.start();
		
		connection.sendName(name);
	}
	
	
	private void initComponent(){
		
		
		startPanel = new JPanel(new FlowLayout() );
		screen = new JPanel(new BorderLayout() );
		gameBoard = new GameBoard(200,200,SCALE);
		
		
		playerList = new JList<>();
		playerListPane = new JScrollPane(playerList);
		
		
		nameField = new JTextField(5);
		acceptButton = new JButton("Connect");
		
		gameBoard.addKeyListener( 
				new KeyListener() {
					
					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						char key = (char) e.getKeyCode();
						if(key == 'Q' || key == 'S' || key == 'D' || key == 'Z' || key == 'M')
						connection.sendDir("" + key);
					}
				}
		);
		
		acceptButton.addActionListener( l->{
			this.name = nameField.getText();
			setContentPane(screen);
			gameBoard.requestFocusInWindow();
			validate();
			connect();
			
			
		});
		startPanel.add(nameField);
		startPanel.add(acceptButton);
		
		
		playerListPane.setPreferredSize(new Dimension(150, 50));
		screen.add(gameBoard,BorderLayout.CENTER);
		screen.add(playerListPane,BorderLayout.EAST);
			
		setResizable(false);
		setPreferredSize(new Dimension(WIDTH+150, HEIGHT));
		setTitle("Snake");
		setContentPane(startPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	
	public void fillUserList(Collection<String> plist){
		playerList.setListData(plist.toArray(new String[0]));
	}

	public void acceptName() {
		connection.askPList();
		
	}

	public void drawBody(Collection<Point> bodyP) {
		gameBoard.drawPoints(bodyP);
		
	}
	
	public void drawHead(Point h){
		gameBoard.drawHead(h);
	}
	
	public void drawTail(Point h){
		gameBoard.drawTail(h);
	}

	public void eraseBody(ArrayList<Point> bodyP) {
		gameBoard.eraseBody(bodyP);
		
	}
	
}