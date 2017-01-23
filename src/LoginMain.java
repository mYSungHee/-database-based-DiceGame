import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginMain extends JFrame implements ActionListener {

	

	public static void main(String[] args){ //���� pageNum1
	
		new LoginMain();

	}
	public LoginMain(){
	
		setTitle("DiceGamble");
		
		init();
		
		//this.setLayout(null);

		
		setLocation(screenSetXY()[0],screenSetXY()[1]); //ȭ�� ���߾ӿ� â�̶ߵ��� ������ x,y��ǥ�� �޾ƿ��� ��ġ�� �־���. 

		setResizable(false); //������ ���� �Ұ���
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	public Integer[] screenSetXY(){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize(); //������ ��ǻ�� ��ũ���� ũ�⸦ �޾ƿ´�. 
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
		pc.setBounds(0, 39, 494, 312); // x,y ��ǥ�� ���ϴ�ũ��� ����.
		
		ImageIcon mainImg = new ImageIcon("mainImg.png");
		pc.setLayout(null);
		
		
		JLabel mainImgLabel = new JLabel(mainImg);
		mainImgLabel.setFont(new Font("��������ڵ�", Font.PLAIN, 15));
		mainImgLabel.setBounds(12, 55, 223, 174);
		
		pc.add(mainImgLabel);//�̹��� �κ��� PC�� �߰�.
		
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
		JButton loginBt = new JButton("�� �� ��");
		loginBt.setBounds(12, 151, 97, 23);
		pcText.add(loginBt);
		loginBt.addActionListener(this);
		
		JButton regiBt = new JButton("ȸ �� �� ��");
		regiBt.setBounds(121, 151, 97, 23);
		pcText.add(regiBt);
		regiBt.addActionListener(this);
		
		JButton idpwFindBt = new JButton("ID / PW ã��");
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
		if(comm.equals("�� �� ��")){//����
			new WaitMain();
			this.dispose();
		} else if(comm.equals("ȸ �� �� ��")) {//ȸ������
			new RegisterMain();
			this.dispose();
		} else if(comm.equals("ID / PW ã��")){
			new IdFinderMain();
			this.dispose();
	}  else {
		}
	}

	

	
	
}
	
		
	
	

