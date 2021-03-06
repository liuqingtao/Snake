import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Yard extends Frame {
	
	
	

	public static final int ROWS = 50;
	public static final int CLOS = 50;
	public static final int BLOCK_SIZE =10;
	private int score =0;
	private boolean gameOver =false;
	private Font gameFont = new Font("����",Font.BOLD,60);
	PaintThread paintThread = new PaintThread();
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	
	
	Snake snake = new Snake(this);
	Egg egg = new Egg();
	Image offScreenImage =null;
	
	public void alunch(){
		this.setLocation(200,200);
		this.setSize(ROWS*BLOCK_SIZE,CLOS*BLOCK_SIZE);
		this.setBackground(Color.GRAY);
		this.addWindowListener(new WindowAdapter(){

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		this.addKeyListener(new KeyMonitor());
		this.setVisible(true);
		new Thread(new PaintThread()).start();
	}
	
	public void paint(Graphics g) {
		
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, CLOS*BLOCK_SIZE, ROWS*BLOCK_SIZE);
		g.setColor(Color.DARK_GRAY);
		for(int i=1;i<CLOS;i++){
			g.drawLine(BLOCK_SIZE*i, 0, BLOCK_SIZE*i, ROWS*BLOCK_SIZE);
		}
		for(int i=1;i<ROWS;i++){
			g.drawLine(0, BLOCK_SIZE*i, CLOS*BLOCK_SIZE, BLOCK_SIZE*i);
		}
		
		g.setColor(Color.YELLOW);
		g.drawString("Score: "+score, 10, 60);
		if(gameOver == true){
			g.setFont(gameFont);
			g.drawString("Game Over", 100, 300);
			paintThread.stop();
			
		}
		g.setColor(c);
		snake.eat(egg);
		egg.draw(g);
		snake.draw(g);
		
		
				
	}
	
	public void stop(){
		gameOver =true;
	}
	
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null){
			offScreenImage = this.createImage(CLOS*BLOCK_SIZE, ROWS*BLOCK_SIZE);
		}
		Graphics goff = offScreenImage.getGraphics();
		paint(goff);
		g.drawImage(offScreenImage,0,0,null);
		
	}
	
	private class PaintThread implements Runnable{
		boolean flag =true;
		@Override
		public void run() {
			while(flag){
				repaint();
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		public void stop(){
			flag =false;
		}
		
	}
	
	private class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			snake.keyPressed(e);
		}
		
	}
	
	public static void main(String[] args) {
		
		new Yard().alunch();
	}
	

}
