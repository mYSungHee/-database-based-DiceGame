import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginMain extends JFrame implements ActionListener {

	

	public static void main(String[] args){ //대기실 pageNum1
	
		new LoginMain();

	}
	public LoginMain(){
	
		setTitle("DiceGamble");
		
		init();
		
		//this.setLayout(null);

		
		setLocation(screenSetXY()[0],screenSetXY()[1]); //화면 정중앙에 창이뜨도록 위에서 x,y좌표를 받아온후 위치를 넣어줌. 

		setResizable(false); //사이즈 조절 불가능
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	public Integer[] screenSetXY(){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize(); //현재의 컴퓨터 스크린의 크기를 받아온다. 
		Dimension frm = super.getSize();
		int xpos = (int) (screen.getWidth() /2 - frm.getWidth() /2);
		int ypos = (int) (screen.getHeight() /2 - frm.getHeight() /2);
		Integer i[] = new Integer[2];
		i[0]=xpos;
		i[1]=ypos;
		return i;
	}
	public void init(){
		setSize(500,380);
		setTitle("DiceGamble");
		getContentPane().setLayout(null);
		//---------------------------------------------------CENTER
		
		JPanel pc = new JPanel(); //Center 
		pc.setBackground(Color.WHITE);
		pc.setBounds(0, 39, 494, 312); // x,y 좌표에 원하는크기로 넣음.
		
		ImageIcon mainImg = new ImageIcon("mainImg.png");
		pc.setLayout(null);
		
		
		JLabel mainImgLabel = new JLabel(mainImg);
		mainImgLabel.setFont(new Font("나눔고딕코딩", Font.PLAIN, 15));
		mainImgLabel.setBounds(12, 55, 223, 174);
		
		pc.add(mainImgLabel);//이미지 부분을 PC에 추가.
		
		JPanel pcText = new JPanel();
		pcText.setBackground(Color.WHITE);
		pcText.setBounds(260, 0, 231, 301);
		
		pc.add(pcText);
		pcText.setLayout(null);
		
		JTextField idField = new JTextField();
		idField.setBounds(12, 69, 206, 31);
		idField.setPreferredSize(new Dimension(200, 10));
		pcText.add(idField);
		JPasswordField pwField = new JPasswordField();
		pwField.setBounds(12, 110, 206, 31);
		pcText.add(pwField);
		pwField.setEchoChar('*');
		pwField.setPreferredSize(new Dimension(200, 10));
		JButton loginBt = new JButton("로 그 인");
		loginBt.setBounds(12, 151, 97, 23);
		pcText.add(loginBt);
		loginBt.addActionListener(this);
		
		JButton regiBt = new JButton("회 원 가 입");
		regiBt.setBounds(121, 151, 97, 23);
		pcText.add(regiBt);
		regiBt.addActionListener(this);
		
		JButton idpwFindBt = new JButton("ID / PW 찾기");
		idpwFindBt.setBounds(12, 184, 206, 23);
		pcText.add(idpwFindBt);
		idpwFindBt.addActionListener(this);
		
		
		getContentPane().add(pc);
		//---------------------------------------------------NORTH
		JPanel pn = new JPanel();
		pn.setBackground(Color.WHITE);
		pn.setBounds(0, 0, 494, 39);
		getContentPane().add(pn);
		
		JLabel pnTitle = new JLabel("D I C E G A M B L E");
		pn.add(pnTitle);
		
		pnTitle.setFont(new Font("Gothic",Font.BOLD,20));
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String comm=e.getActionCommand();
		if(comm.equals("로 그 인")){//대기실
			new WaitMain();
			this.dispose();
		} else if(comm.equals("회 원 가 입")) {//회원가입
			new RegisterMain();
			this.dispose();
		} else if(comm.equals("ID / PW 찾기")){
			new IdFinderMain();
			this.dispose();
	}  else {
		}
	}

	

	
	
}
	
		
	
	

