import java.awt.Color;
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
	
	Snake snake = new Snake();
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
		
		g.setColor(c);
		snake.eat(egg);
		egg.draw(g);
		snake.draw(g);
				
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

		@Override
		public void run() {
			while(true){
				repaint();
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
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
