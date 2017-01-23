import javax.swing.JFrame;
import javax.swing.UIManager;

public class Main extends JFrame {

	static JFrame F;
	public static void main(String[] args){
		
		  try {
		  // 1. 자바 내장 룩앤필
		  // UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		 
		  // 2. quaqua.jar : Quaqua Look and Feel
//		   UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
//		   JFrame.setDefaultLookAndFeelDecorated(true);

		  // 3. liquidlnf.jar : Liquid Look and Feel
//		   UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");

		  // 4. idw-gpl.jar : InfoNode Look and Feel
//		   UIManager.setLookAndFeel("net.infonode.gui.laf.InfoNodeLookAndFeel");

		  // 5. JTattoo.jar : JTattoo Look and Feel
		   UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		   UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		  }  catch (Exception e) { }
		  // 파일 명이 MyFrame(JFrame 상속한 클래스) 인 경우
		  
		  F = new LoginMain();
		
		
	}
}
