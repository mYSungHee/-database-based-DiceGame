import javax.swing.UIManager;

public class First {
	 static MyFrame f;
		
		public static void main(String[] args){
			
			  try {
			  // 1. �ڹ� ���� �����
			  // UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			 
			  // 2. quaqua.jar : Quaqua Look and Feel
//			   UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
//			   JFrame.setDefaultLookAndFeelDecorated(true);

			  // 3. liquidlnf.jar : Liquid Look and Feel
//			   UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");

			  // 4. idw-gpl.jar : InfoNode Look and Feel
//			   UIManager.setLookAndFeel("net.infonode.gui.laf.InfoNodeLookAndFeel");

			  // 5. JTattoo.jar : JTattoo Look and Feel
			   UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
			   UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			  }  catch (Exception e) { }
			  // ���� ���� MyFrame(JFrame ����� Ŭ����) �� ���
			  f=new Admins();
			
			
		}
}
