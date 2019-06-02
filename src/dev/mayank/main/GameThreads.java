package dev.mayank.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import dev.mayank.display.Display;

public class GameThreads implements Runnable, KeyListener  {
	
	
	private Display display;
	private String title;
	private int width, height;
	private Thread thread;
	private BufferStrategy b;
	private Graphics g;
    private int scoreP1; // score for player 1
    private int scoreP2; //score for player 2
	
	private int ballX =250;
    private int ballY = 250;	
    
    private int batX1 = 617;  // right bat 
    private int batY1 = 270;
    
    private int batX2 = 20;  // left bat
    private  int batY2 = 270;
    
    String player1;
    String player2;
    
    String gameOver;
    
    String youWin1;
    String youWin2;
    
    boolean up,down;
    
    boolean up1, down1;
 
    int moveX = 1;
    int moveY = -1;
    
    

    Rectangle Ball = new Rectangle(ballX, ballY, 50,50);    // for ball
    Rectangle Bat2 = new Rectangle(batX2, batY2,7,50); // left bat
    Rectangle Bat = new Rectangle(batX1, batY1, 15,50);  // right bat
   
   
    
   
	
 
	public GameThreads(String title,int width,int height) {
		
	

        this.title = title;
        this.width = width;
        this.height = height;
        scoreP1=0;
        scoreP2 = 0;
        youWin1 = "Player 1 wins";
        youWin2 = "Player 2 wins";
        
        player1 = "Player 1";
        player2 = "Player 2";
        
       
       gameOver = "Game Over";
       
      
       
	}
	

	public void init() {
		display = new Display(title, width, height);
		display.frame.addKeyListener(this);
		
		
	
		
	}
	//create ball method
	public void drawBall(Graphics g) {
		g.setColor(Color.red);
		g.fillRoundRect(Ball.x ,Ball.y, 50, 50, 50, 50);
	}
	// for bat draw create a bat method
    public void drawBat(Graphics g) {  //  right bat
    	g.setColor(Color.WHITE);
    	g.fillRect(Bat.x, Bat.y, 5, 50);
    }
    
    // for second bat
    public void drawBat2(Graphics g) { //left bat
    	g.setColor(Color.WHITE);
    	g.fillRect(Bat2.x, Bat2.y, 5, 50);
    }

    
    //draw score 
    
    public void drawScoreP1(Graphics g) {
    	String p1 = Integer.toString(scoreP1);
    	g.setColor(Color.red);
       	g.drawString(p1, 100, 20);
    }
    
    public void drawScoreP2(Graphics g) {
    	String p2 = Integer.toString(scoreP2);
    	g.setColor(Color.red);
    	g.drawString(p2, 550, 20);
    }
    
    // players
    
    public void drawP1 (Graphics g) {
    	g.setColor(Color.white);
    	g.drawString(player1, 50, 20);
    }
    public void drawP2 (Graphics g) {
    	g.setColor(Color.white);
    	g.drawString(player2, 480, 20);
    }
    // draw game over 
    
    public void gameO(Graphics g) {
    	Font font1 = new Font("SansSerif", Font.BOLD, 50);
    	g.setFont(font1);
    	g.setColor(Color.white);
    	g.drawString(gameOver,200,250);
    }
    // draw you win 
    public void youWinP1(Graphics g) {
    	Font font1 = new Font("SansSerif", Font.BOLD, 20);
    	g.setFont(font1);
    	g.setColor(Color.white);
    	g.drawString(youWin1,250,300);
    }
    public void youWinP2(Graphics g) {
    	Font font1 = new Font("SansSerif", Font.BOLD, 20);
    	g.setFont(font1);
    	g.setColor(Color.white);
    	g.drawString(youWin2,250,300);
    }
    
    

public void music()  {      //*********Background Music & Other Sound Effects************** 
	
	try {
		
	
	AudioInputStream audioIn = AudioSystem.getAudioInputStream(Main.class.getResource("/BackgroundMusic.wav"));

    Clip clip = AudioSystem.getClip();
    clip.open(audioIn);
    clip.start();
    clip.loop(Clip.LOOP_CONTINUOUSLY);
    FloatControl gainControl = 
    	    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    	gainControl.setValue(-15.0f); 
    }catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
		
		e.printStackTrace();
	}
}
public void soundForBat() {
	try {
		
		
	AudioInputStream audioIn = AudioSystem.getAudioInputStream(Main.class.getResource("/bat+hit+ball.wav"));

    Clip clip = AudioSystem.getClip();
    clip.open(audioIn);
    clip.start();
    }catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
		
		e.printStackTrace();
	}
}
public void soundForPoints() {
	try {
		
		
	AudioInputStream audioIn = AudioSystem.getAudioInputStream(Main.class.getResource("/SoundforPoints.wav"));

    Clip clip = AudioSystem.getClip();
    clip.open(audioIn);
    clip.start();
    }catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
		
		e.printStackTrace();
	}
}

    
    
    
	public void tick() {
	
		if( Ball.y >=405 || Ball.y <=27) {
			moveY = -moveY;
		}
		
		
        if(up) {
        	if(Bat.y >=30) {
        	Bat.y -= 1;
        
        	}
		}
		if(down) {
			if(Bat.y <=395) {
			Bat.y += 1;
			}
		}
		if(up1) {
			if(Bat2.y >=30) {
			Bat2.y -= 1;
			}
		}
		if(down1) {
			if(Bat2.y <=395) {
			Bat2.y += 1;
			}
		} 
		
		
		
		//touch for bat and ball
		
		
		if(Bat2.intersects(Ball)) {
		moveX =-moveX; 
		soundForBat();
		
		}
		
		
		if(Bat.intersects(Ball)) {
		moveX =-moveX;
		soundForBat();
		}
		
		
	
		if( Ball.x >=580 ) {
			scoreP1 += 1;
			moveX = -moveX;
			soundForPoints();
		}
		if( Ball.x <=15  ) {
			scoreP2 += 1;
			moveX = -moveX;
			soundForPoints();
		}
		
       
		Ball.x  += moveX;
		
		Ball.y   -= moveY;
	}
	
//*********************Start of Rendering Method ************************
	public void render() {
		 
		b = display.canvas.getBufferStrategy();
		if(b == null) {
			display.canvas.createBufferStrategy(3);
			return;
		}
		
		g = b.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		//Draw Here 
	    g.setColor(Color.BLACK);
	    g.fillRect(0, 0, 640, 480);
	    
	
	    
	    //draw ball
	    drawBall(g);
	    
	    //draw Bat
	    drawBat(g);
	    
	    //draw second bat
	    
	    drawBat2(g);
	
	   
	    //draw score
		
		drawScoreP1(g);
		drawScoreP2(g);
		
		drawP1(g);
		drawP2(g);
		
	
		//  draw you win
		if(scoreP1 == 5) {
			gameO(g);
			youWinP1(g);
			
		}
		if(scoreP2 == 5) {
			gameO(g);
			youWinP2(g);
		}
		
	  
		  
		
	  
		//End Here
		b.show();
		g.dispose();
	}
	
	
	
	public synchronized void start() {
	
		
		thread = new Thread(this);
		thread.start();
		music();
	}
		 
	

public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
	
			e.printStackTrace();
		}
	}  //**************End of Rendering Method*******************




@SuppressWarnings("deprecation") //control thread
public void run() {
	init();
	while(true) {
		try {
			Thread.sleep(5); //used to pause the execution of current thread
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(scoreP1 == 5 || scoreP2 == 5) {
		thread.stop(); // stops thread
		}

	
		
		tick(); // update
		
		render(); // draw 
		
	}
	
}



//***********Keyboard Controls***************
@Override
public void keyPressed(KeyEvent e) {


	int source = e.getKeyCode();
	
	if(source == KeyEvent.VK_UP) {  // for right bat
		up = true;
	}
	if(source == KeyEvent.VK_DOWN) { // for left bat
		down = true;
	}

	if(source == KeyEvent.VK_W) { // for left bat
		up1 = true;
	}
	if(source == KeyEvent.VK_S) { // for left bat
		down1 = true;
	
}
	
}
@Override

public void keyReleased(KeyEvent e) {

	int source = e.getKeyCode();
	
	
	if(source == KeyEvent.VK_W) {  // for left bat
		up1 = false;
	}
	if(source == KeyEvent.VK_S) {  // for left bat
		down1 = false;
	}
	if(source == KeyEvent.VK_UP) { // for right bat
		up = false;
	}
	if(source == KeyEvent.VK_DOWN) { // for right bat
		down = false;
		
	}

}

@Override
public void keyTyped(KeyEvent arg0) {

	}
}








