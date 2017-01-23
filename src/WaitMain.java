
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
			  // 1. 자바 내장 룩앤필
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
			  // 파일 명이 MyFrame(JFrame 상속한 클래스) 인 경우
			  F= new WaitMain();
	}

	public WaitMain(){
		setSize(600,400);
		init();
		setLocation(screenSetXY()[0],screenSetXY()[1]); //화면 정중앙에 창이뜨도록 위에서 x,y좌표를 받아온후 위치를 넣어줌. 
		

					
					setResizable(false); //사이즈 조절 불가능
					setVisible(true);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public Integer[] screenSetXY(){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize(); //현재의 컴퓨터 스크린의 크기를 받아온다. 
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
					textField_1.setFont(new Font("나눔고딕코딩", Font.PLAIN, 12));
					textField_1.setBounds(0, 9, 116, 21);
					textField_1.setColumns(10);
					textField_1.setBackground(SystemColor.info);
					textField_1.setBorder(new EmptyBorder(5,5,5,5));
					panel_1.add(textField_1);
					
					txtId = new JTextField();
					txtId.setHorizontalAlignment(SwingConstants.CENTER);
					txtId.setForeground(SystemColor.textText);
					txtId.setEditable(false);
					txtId.setFont(new Font("나눔고딕코딩", Font.PLAIN, 13));
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
					txtPoint.setFont(new Font("나눔고딕코딩", Font.PLAIN, 13));
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
					txtGrade.setFont(new Font("나눔고딕코딩", Font.PLAIN, 13));
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
					
					JButton logoutBt = new JButton("로 그 아 웃");
					logoutBt.setBounds(12, 31, 124, 28);
					logoutBt.setFont(new Font("나눔고딕코딩",Font.PLAIN,12));
					rightP.add(logoutBt);
					logoutBt.addActionListener(new ActionListener(){
						
						public void actionPerformed(ActionEvent e){
							new LoginMain();
							dispose();
							
						}
					});
					
					JButton statusBt = new JButton("정 보 수 정");
					statusBt.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							new EditMain();
							dispose();
						}
					});
					statusBt.setBounds(12, 71, 124, 28);
					statusBt.setFont(new Font("나눔고딕코딩",Font.PLAIN,12));
					rightP.add(statusBt);
					
					JButton rankBt = new JButton("순 위 확 인");
					rankBt.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							//card.show(F, "p2");
							new RankPage();
							dispose();
							
						}
					});
					rankBt.setBounds(12, 111, 124, 28);
					rankBt.setFont(new Font("나눔고딕코딩",Font.PLAIN,12));
					rightP.add(rankBt);
					
					JButton stBt = new JButton("게 임 시 작");
					stBt.setFont(new Font("나눔고딕코딩",Font.ROMAN_BASELINE,15));
					stBt.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							//게임 start
						}
					});
					stBt.setBounds(12, 250, 124, 78);
					
					
					rightP.add(stBt);
					
					JButton imgregiBt = new JButton();
					imgregiBt.setBounds(12, 151, 124, 28);
					rightP.add(imgregiBt);
					imgregiBt.setFont(new Font("나눔고딕코딩", Font.PLAIN, 12));
					imgregiBt.setText("사 진 변 경");
					
				
					
					
					imgregiBt.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							
							//이미지등록창 new 해서 띄우기, 해당 이미지를 DB에 저장?
							ImageRegi pro = new ImageRegi();
							
							// 해당이미지를 반환해서 이창에서 적용?
							
						}
						
					});
		
		
	}
	public void imageLoad(){
		//if(이미지가 있을경우)
				//profile_image.setIcon(new ImageIcon(path));
				//else if 이미지가 없을경우
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

if(comm.equals("확    인") ==true){
	 if(emailArea.getText().equals("")){
		 JOptionPane.showMessageDialog(null, "이메일을 입력해 주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
	 }
	 else if(idsel==1 && pwdsel==1){
		 idText=emailArea.getText();
		 JOptionPane.showMessageDialog(null, "아이디는 ["+ idText +" ] 이며, 비밀번호가 초기화 되었습니다.", "Sucess!", JOptionPane.INFORMATION_MESSAGE);
	 } else if(idsel==1){
		 idText=emailArea.getText();
		   JOptionPane.showMessageDialog(null, "아이디는 ["+ idText +" ]입니다.", "Sucess!", JOptionPane.INFORMATION_MESSAGE);
	 } else if(pwdsel==1){
	   JOptionPane.showMessageDialog(null, "비밀번호가 초기화 되었습니다.", "Sucess!", JOptionPane.INFORMATION_MESSAGE);
	 } else {
	   JOptionPane.showMessageDialog(null, "한가지 이상을 선택해 주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
	 }
} else if (comm.equals("재입력")){
	 emailArea.setText("");
	 
} else if(comm.equals("뒤로")){
	 
}


}
*/