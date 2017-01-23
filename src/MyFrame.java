import javax.swing.*;
import java.awt.event.*;

public class MyFrame extends JFrame {
	ServerThread svThread;
	public MyFrame() {
		super();
		getContentPane().setLayout(null);
		
	}

	   public MyFrame(ServerThread svThread){
	    	this();
	    	this.svThread=svThread;
	    	
	    }
}
