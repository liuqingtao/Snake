import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Yard extends Frame {
	
	
	public static final int ROWS = 50;
	public static final int CLOS = 50;
	public static final int BLOCK_SIZE =10;
	
	Snake snake = new Snake();
	public void alunch(){
		this.setLocation(200,200);
		this.setSize(ROWS*BLOCK_SIZE,CLOS*BLOCK_SIZE);
		this.setBackground(Color.GRAY);
		this.addWindowListener(new WindowAdapter(){

			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
			
		});
		this.setVisible(true);
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
		snake.draw(g);
				
	}
	
	public static void main(String[] args) {
		
		new Yard().alunch();
	}
	

}
