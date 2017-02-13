package model;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ClientHandleConnection;

public class ClientCore extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int WIDTH = 800;
	static int HEIGHT = 800;
	
	private int port = 1234;
	private  Socket sock;
	private boolean connected = false;
	private String connectAdress = "127.0.0.1";
	private String name ;
	private ClientHandleConnection connection;
	
	private JPanel startPanel;
	private JPanel gameBoard;
	private JTextField nameField;
	private JButton acceptButton;
	
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
		
	}
	
	
	private void initComponent(){
		
		startPanel = new JPanel(new FlowLayout() );
		gameBoard = new  JPanel(new BorderLayout() );
		
		nameField = new JTextField(5);
		acceptButton = new JButton("Connect");
		
		
		acceptButton.addActionListener( l->{
			name = nameField.getText();
			setContentPane(gameBoard);
			validate();
			connect();
			
			
		});
		startPanel.add(nameField);
		startPanel.add(acceptButton);
		
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setTitle("Snake");
		setContentPane(startPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	

}
