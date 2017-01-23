import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyFrame extends JFrame{
	public MyFrame() {
		
		
	}
	public void receiveMsg(String msg){
		
		JOptionPane.showMessageDialog(this, msg, "¾Ë¸²", JOptionPane.INFORMATION_MESSAGE);
	}
	public boolean ck(boolean ck){
		
		return ck;
		
	}
}
