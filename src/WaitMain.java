
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class WaitMain extends JFrame implements WindowListener{
	String path;
	ImageLabel profile_image=new ImageLabel();
	private JTextField textField_1;
	private JTextField txtId;
	private JTextField txtPoint;
	private JTextField txtGrade;
	private JTextField textField;
	private JTextField textField_5;
	private JTextField textField_6;
	private ImageLabel imageLabel;
	static WaitMain F;
	private CardLayout card;
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
			  F= new WaitMain();
	}

	public WaitMain(){
		setSize(600,400);
		init();
		setLocation(screenSetXY()[0],screenSetXY()[1]); //ȭ�� ���߾ӿ� â�̶ߵ��� ������ x,y��ǥ�� �޾ƿ��� ��ġ�� �־���. 
		

					
					setResizable(false); //������ ���� �Ұ���
					setVisible(true);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public Integer[] screenSetXY(){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize(); //������ ��ǻ�� ��ũ���� ũ�⸦ �޾ƿ´�. 
		Dimension frm = super.getSize();
		int xpos = (int) (screen.getWidth()	 /2 - frm.getWidth() /2);
		int ypos = (int) (screen.getHeight() /2 - frm.getHeight() /2);
		Integer i[] = new Integer[2];
		i[0]=xpos;
		i[1]=ypos;
		return i;
	}
	public void init(){
	
		card = new CardLayout();
		getContentPane().setLayout(card);
		imageLoad();
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, "p1main");
				panel.setLayout(null);
		
				
				JPanel leftP = new JPanel();
				leftP.setBounds(0, 0, 249, 371);
				leftP.setBackground(SystemColor.info);
				panel.add(leftP);
				leftP.setLayout(null);
				
				imageLabel = new ImageLabel();
				imageLabel.setImageIcon(new ImageIcon("mainImg.png"));
				imageLabel.setBounds(0, 0, 249, 373);
				imageLabel.setPreferredSize(new Dimension(100, 400));
				leftP.add(imageLabel);
				JPanel centerP = new JPanel();
				centerP.setBounds(249, 0, 198, 371);
				centerP.setBackground(SystemColor.info);
				panel.add(centerP);
				centerP.setLayout(null);
				profile_image.setBounds(12, 30, 186, 148);
				
				profile_image.setPreferredSize(new Dimension(100,400));
				centerP.add(profile_image);
				
					imageLoad();
					JLabel stLb = new JLabel();
					stLb.setBounds(149, 421, 0, 0);
					
					centerP.add(stLb);
					
					JPanel panel_1 = new JPanel();
					panel_1.setBackground(SystemColor.info);
					panel_1.setBounds(12, 201, 186, 126);
					centerP.add(panel_1);
					panel_1.setLayout(null);
					
					textField_1 = new JTextField();
					textField_1.setForeground(SystemColor.windowText);
					textField_1.setEditable(false);
					textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
					textField_1.setFont(new Font("��������ڵ�", Font.PLAIN, 12));
					textField_1.setBounds(0, 9, 116, 21);
					textField_1.setColumns(10);
					textField_1.setBackground(SystemColor.info);
					textField_1.setBorder(new EmptyBorder(5,5,5,5));
					panel_1.add(textField_1);
					
					txtId = new JTextField();
					txtId.setHorizontalAlignment(SwingConstants.CENTER);
					txtId.setForeground(SystemColor.textText);
					txtId.setEditable(false);
					txtId.setFont(new Font("��������ڵ�", Font.PLAIN, 13));
					txtId.setText("ID");
					txtId.setBounds(0, 40, 62, 21);
					txtId.setColumns(10);
					txtId.setBackground(SystemColor.info);
					txtId.setBorder(new EmptyBorder(5,5,5,5));
					panel_1.add(txtId);
					
					txtPoint = new JTextField();
					txtPoint.setHorizontalAlignment(SwingConstants.CENTER);
					txtPoint.setForeground(SystemColor.textText);
					txtPoint.setEditable(false);
					txtPoint.setFont(new Font("��������ڵ�", Font.PLAIN, 13));
					txtPoint.setText("Point");
					txtPoint.setBounds(0, 71, 62, 21);
					txtPoint.setColumns(10);
					txtPoint.setBackground(SystemColor.info);
					txtPoint.setBorder(new EmptyBorder(5,5,5,5));
					panel_1.add(txtPoint);
					
					txtGrade = new JTextField();
					txtGrade.setHorizontalAlignment(SwingConstants.CENTER);
					txtGrade.setForeground(SystemColor.menuText);
					txtGrade.setEditable(false);
					txtGrade.setFont(new Font("��������ڵ�", Font.PLAIN, 13));
					txtGrade.setText("Grade");
					txtGrade.setBounds(0, 102, 62, 21);
					txtGrade.setColumns(10);
					txtGrade.setBackground(SystemColor.info);
					txtGrade.setBorder(new EmptyBorder(5,5,5,5));
					panel_1.add(txtGrade);
					
					textField = new JTextField();
					textField.setEditable(false);
					textField.setColumns(10);
					textField.setBackground(SystemColor.info);
					textField.setBounds(74, 40, 116, 21);
					textField.setBorder(new EmptyBorder(5,5,5,5));
					panel_1.add(textField);
					
					textField_5 = new JTextField();
					textField_5.setEditable(false);
					textField_5.setColumns(10);
					textField_5.setBackground(SystemColor.info);
					textField_5.setBounds(74, 71, 116, 21);
					textField_5.setBorder(new EmptyBorder(5,5,5,5));
					panel_1.add(textField_5);
					
					textField_6 = new JTextField();
					textField_6.setEditable(false);
					textField_6.setColumns(10);
					textField_6.setBackground(SystemColor.info);
					textField_6.setBounds(74, 102, 116, 21);
					textField_6.setBorder(new EmptyBorder(5,5,5,5));
					panel_1.add(textField_6);
					
					
					
					
					JPanel rightP = new JPanel();
					rightP.setBounds(446, 0, 148, 371);
					rightP.setBackground(SystemColor.info);
					panel.add(rightP);
					rightP.setLayout(null);
					
					JButton logoutBt = new JButton("�� �� �� ��");
					logoutBt.setBounds(12, 31, 124, 28);
					logoutBt.setFont(new Font("��������ڵ�",Font.PLAIN,12));
					rightP.add(logoutBt);
					logoutBt.addActionListener(new ActionListener(){
						
						public void actionPerformed(ActionEvent e){
							new LoginMain();
							dispose();
							
						}
					});
					
					JButton statusBt = new JButton("�� �� �� ��");
					statusBt.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							new EditMain();
							dispose();
						}
					});
					statusBt.setBounds(12, 71, 124, 28);
					statusBt.setFont(new Font("��������ڵ�",Font.PLAIN,12));
					rightP.add(statusBt);
					
					JButton rankBt = new JButton("�� �� Ȯ ��");
					rankBt.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							//card.show(F, "p2");
							new RankPage();
							dispose();
							
						}
					});
					rankBt.setBounds(12, 111, 124, 28);
					rankBt.setFont(new Font("��������ڵ�",Font.PLAIN,12));
					rightP.add(rankBt);
					
					JButton stBt = new JButton("�� �� �� ��");
					stBt.setFont(new Font("��������ڵ�",Font.ROMAN_BASELINE,15));
					stBt.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							//���� start
						}
					});
					stBt.setBounds(12, 250, 124, 78);
					
					
					rightP.add(stBt);
					
					JButton imgregiBt = new JButton();
					imgregiBt.setBounds(12, 151, 124, 28);
					rightP.add(imgregiBt);
					imgregiBt.setFont(new Font("��������ڵ�", Font.PLAIN, 12));
					imgregiBt.setText("�� �� �� ��");
					
				
					
					
					imgregiBt.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							
							//�̹������â new �ؼ� ����, �ش� �̹����� DB�� ����?
							ImageRegi pro = new ImageRegi();
							
							// �ش��̹����� ��ȯ�ؼ� ��â���� ����?
							
						}
						
					});
		
		
	}
	public void imageLoad(){
		//if(�̹����� �������)
				//profile_image.setIcon(new ImageIcon(path));
				//else if �̹����� �������
		profile_image.setImageIcon(new ImageIcon("defaultpf.png"));
		
		profile_image.setImageIcon(new ImageIcon("img/15.jpg"));
		profile_image.repaint();
		
	}
	
	
	public void windowActivated(WindowEvent e){
		
		imageLoad();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}

/*
public void actionPerformed(ActionEvent e){
String comm = e.getActionCommand();

if(comm.equals("Ȯ    ��") ==true){
	 if(emailArea.getText().equals("")){
		 JOptionPane.showMessageDialog(null, "�̸����� �Է��� �ּ���.", "Warning", JOptionPane.WARNING_MESSAGE);
	 }
	 else if(idsel==1 && pwdsel==1){
		 idText=emailArea.getText();
		 JOptionPane.showMessageDialog(null, "���̵�� ["+ idText +" ] �̸�, ��й�ȣ�� �ʱ�ȭ �Ǿ����ϴ�.", "Sucess!", JOptionPane.INFORMATION_MESSAGE);
	 } else if(idsel==1){
		 idText=emailArea.getText();
		   JOptionPane.showMessageDialog(null, "���̵�� ["+ idText +" ]�Դϴ�.", "Sucess!", JOptionPane.INFORMATION_MESSAGE);
	 } else if(pwdsel==1){
	   JOptionPane.showMessageDialog(null, "��й�ȣ�� �ʱ�ȭ �Ǿ����ϴ�.", "Sucess!", JOptionPane.INFORMATION_MESSAGE);
	 } else {
	   JOptionPane.showMessageDialog(null, "�Ѱ��� �̻��� ������ �ּ���.", "Warning", JOptionPane.WARNING_MESSAGE);
	 }
} else if (comm.equals("���Է�")){
	 emailArea.setText("");
	 
} else if(comm.equals("�ڷ�")){
	 
}


}
*/