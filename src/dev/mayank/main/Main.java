package dev.mayank.main;

import java.awt.event.*;
import java.io.IOException;

import javax.swing.*; 


public class Main {
	
	
	public static void main(String[] args) {
		 GameThreads th = new GameThreads("Ball Game", 640, 480);
		
		 class Play extends JFrame implements ActionListener //adds a button play GameButton
		 { 
		     
			 private static final long serialVersionUID = 1L;
			// Declaration of object of JButton class. 
		     JButton b1; 
		     JButton b2;
		     JButton b3;
		       
		     // Constructor of Play class 
		     Play() 
		     { 
		         // Setting layout as null of JFrame. 
		         this.setLayout(null); 
		           
		         // Initialization of object of "JButton" class. 
		         b1 = new JButton("Play Game!"); 
		         b2 = new JButton("Exit");
		         b3 = new JButton("Blog");
		         
		           
		         // Setting Bounds of a JButton. 
		         b1.setBounds(150, 50, 100, 50); 
		         b2.setBounds(150, 100, 100, 50); 
		         b3.setBounds(150,150,100,50);
		         
		           
		         //"this" keyword in java refers to current object. 
		         // Adding JButton on JFrame. 
		         this.add(b1); 
		         this.add(b2);
		         this.add(b3);
		           
		         // Adding Listener toJButton. 
		         b1.addActionListener(this); 
		         b2.addActionListener(this);
		         b3.addActionListener(this);
		     } 
		   
		     // Override Method 
		     public void actionPerformed(ActionEvent evt) { 
		         if (evt.getSource() == b1)  
		         { 
		           th.start();
	              }
		         
		         if (evt.getSource() == b2)  
		         { 
		          System.exit(0);
		         } 
		         if (evt.getSource() == b3)  
		         { 
		        	 try {
						java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://mayankchoudhary.home.blog/"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		         } 
		     } 
		 } 
		        
		         Play f = new Play(); 
		        
		         f.setBounds(200, 200, 400, 300); 
		           
		        
		         f.setResizable(false);  
		        
		         f.setVisible(true); 
		         
		         f.setLocationRelativeTo(null);
 } 
		  

}


