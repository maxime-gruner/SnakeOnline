package model;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.ClientHandleConnection;
import server.Player;
import server.Point;
import server.Snake;
import server.SnakeModel;

public class ClientCore extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static int SCALE = 3; //a changer pour la taille de la FENETRE

	private int port = 1234;
	private  Socket sock;
	private boolean connected = false;
	private String connectAdress;
	private String name="" ;
	private ClientHandleConnection connection;

	private JPanel startPanel;
	private JPanel screen;
	private GameBoard gameBoard;
	private JTextField nameField;
	private JLabel labelName ;
	private JButton acceptButton;
	private JScrollPane playerListPane;
	private JList<String> playerList;
	
	private JTextField addressField;
	private JLabel labelAddress;
	
	
	

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


		startPanel = new JPanel(new GridBagLayout() );
		screen = new JPanel(new BorderLayout() );
		gameBoard = new GameBoard(SnakeModel.WIDTH,SnakeModel.HEIGHT,SCALE);

		screen.setPreferredSize(new Dimension(SnakeModel.WIDTH*SCALE,SnakeModel.HEIGHT*SCALE));
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
		
		addressField = new JTextField("127.0.0.1");
		addressField.setPreferredSize(new Dimension(90, 20));

		acceptButton.addActionListener( l->{
			this.name = nameField.getText();
			setContentPane(screen);
			gameBoard.requestFocusInWindow();
			validate();
			
			this.connectAdress = addressField.getText();
			connect();


		});
		
		labelAddress = new JLabel("Enter the Adress: ");
		labelName = new JLabel("Enter the name: ");
		
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0; c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 0);
		startPanel.add(labelName,c);
		c.gridx = 1; c.gridy = 0; 
		startPanel.add(nameField,c);
		
		
		c.gridx = 2; c.gridy = 0;
		startPanel.add(labelAddress,c);
		c.gridx =3;
		startPanel.add(addressField,c);
		
		
		c.gridy = 3; c.gridx = 0; c.gridwidth = 4;
		startPanel.add(acceptButton,c);


		playerListPane.setPreferredSize(new Dimension(150, 50));
		playerListPane.setFocusable(false);
		playerList.setFocusable(false);
		screen.add(gameBoard,BorderLayout.CENTER);
		screen.add(playerListPane,BorderLayout.EAST);

		
		nameField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					acceptButton.doClick();
				}
			}
		});
		setResizable(false);
		setPreferredSize(new Dimension(SnakeModel.WIDTH*SCALE+150, SnakeModel.HEIGHT*SCALE));
		setTitle("Snake");
		setContentPane(startPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null); //centre la fenetre
		setVisible(true);
	}

	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}

	public void fillUserList( ArrayList<Player> playerScore){
		Collection<String> to_print = new ArrayList<>();
		for(Player entry : playerScore){
			to_print.add(entry.getName() +" "+ entry.getScore());
		}
		
		playerList.setListData(to_print.toArray(new String[0]));
		
	}
	
	public void acceptName() {
		connection.askPList();

	}

	public void drawBody(Collection<Point> bodyP,String name) {
		if(name.equals(this.name)){
			gameBoard.drawMyPoints(bodyP);
		}else{
			gameBoard.drawPoints(bodyP);
		}
	}

	public void drawHead(Point h,String name){
		if(name.equals(this.name)){
			gameBoard.drawMyHead(h);
		}else{
			gameBoard.drawHead(h);
		}
	}

	public void drawTail(Point h){
		gameBoard.drawTail(h);
	}

	public void eraseBody(ArrayList<Point> bodyP) {
		gameBoard.eraseBody(bodyP);

	}
	
	public void drawApple(Point a){
		gameBoard.drawApple(a);
	}
	
	
	public void restart(){
		
		connected = false;
		initComponent();
		setContentPane(startPanel);
	}
}