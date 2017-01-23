
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JFrame{

	
	
	public static void main(String[] args){
		
		new GameFrame();
	}
	
	
	public GameFrame(){
		setTitle("Game");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setResizable(false);
		
		setSize(800,600);
		
		init();
		
		Dimension screen= Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = super.getSize();
		
		int xpos = (int)(screen.getWidth() /2 - frm.getWidth() / 2);
		int ypos = (int)(screen.getHeight()/2 - frm.getHeight() /2);
		
		setLocation(xpos,ypos);
		
		
		setVisible(true);
		
		
		
		
	}
	public void init(){
		
		JMenuBar mbar = new JMenuBar();
		add(mbar);
		
	
		
		
	}
	
	
	
}
