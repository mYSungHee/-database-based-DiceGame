import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class RegisterMain extends JFrame implements ActionListener{
	private JLabel tLabel1 = new JLabel(" ");          
	private JLabel tLabel2 = new JLabel("    * ȸ������ �Է� ");
	private JLabel tLabel3 = new JLabel("    * ���Կ� �ݵ�� �ʿ��� ������ �Է����ּ���."); 
	private JLabel tLabel4 = new JLabel(" "); 
	private JLabel nameLabel= new JLabel("    ��  ��  ��  :     ");
	private JLabel idLabel = new JLabel("   ��  ��  ��  :     ");
	private JLabel passwordLabel = new JLabel("   ��й�ȣ  :     ");
	private JLabel emailLabel = new JLabel("   �̸��� �ּ� :  ");
	private JLabel emailLabel1 = new JLabel(" @ ");
	private JLabel sexLabel = new JLabel("   ��   �� :  ");
	
	private JTextField idField = new JTextField(10);
	private JTextField nameField = new JTextField(5);
	private JPasswordField passwordField = new JPasswordField(10);
	private JTextField emailField = new JTextField(12);
	private JTextField emailField1 = new JTextField(8);
	
	private JButton checkButton = new JButton("�ߺ�üũ");
	private JButton joinButton = new JButton("��  ��");
	private JButton clearButton = new JButton("���Է�");
	private JButton exitButton  = new JButton("��  ��");
	private String sex[] ={"��","��"};
	private JComboBox sexComboBox=new JComboBox(sex);
	private JPanel panel[] =new JPanel[8];
	private JPanel tPanel = new JPanel(new GridLayout(4,1,5,5) );
		public static void main(String[] args){
			new RegisterMain();
			
		}
	
		public RegisterMain(){
			
			init();
			
			//this.setLayout(null);

			setSize(450,500);
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

		public void init() {
			 getContentPane().setBackground(Color.WHITE);
				
			 setTitle("Register");
			 getContentPane().setLayout(new BorderLayout() );
			 tPanel.setBackground(Color.WHITE);
		  
			 tPanel.add(tLabel1);
			 tPanel.add(tLabel2);
			 tPanel.add(tLabel3);

			// cPanel.add("North",tPanel);
		  
			 for(int i=0;i<panel.length;i++) { 
		  
				panel[i]=new JPanel();
			 }
		 
			 for (int i=1; i <panel.length-1 ; i++ ) {
		   
				 panel[i].setLayout(new FlowLayout(FlowLayout.LEFT) );
				 
			 }
		  
			 panel[0].setLayout(new GridLayout(8,1,18,10) );  
			 panel[0].setBackground(Color.WHITE);

			 panel[1].add(new JLabel("          ")); //���Ŀ����� �߰����־���.
			 panel[1].add(idLabel);
			 panel[1].add(idField);
			 panel[1].add(checkButton);
		  
		  

			 panel[2].add(new JLabel("          "));
			 panel[2].add(passwordLabel);
			 panel[2].add(passwordField);
		  
		  

			 panel[3].add(new JLabel("          "));
			 panel[3].add(nameLabel);
			 panel[3].add(nameField);
		  

			 panel[4].add(new JLabel("          "));
			 panel[4].add(sexLabel);
			 panel[4].add(sexComboBox);
		  

			 panel[5].add(new JLabel("          "));
			 panel[5].add(emailLabel);
			 panel[5].add(emailField);

			 panel[5].add(emailLabel1);
			 panel[5].add(emailField1);
			 panel[5].add(new JLabel(" ") );
		  
			 panel[6].add(new JLabel(""));
			 
			 panel[7].add(new JLabel(""));
			 panel[7].add(joinButton);
			 panel[7].add(clearButton);
			 panel[7].add(exitButton);
		  
			 for (int i=1; i<panel.length ;i++ ) {
		   
				 panel[0].add(panel[i] );
				 panel[i].setBackground(Color.WHITE);
		  
			 }
		  
			getContentPane().add("North",tPanel);
			getContentPane().add("Center",panel[0] );
			
			checkButton.addActionListener(this);
			joinButton.addActionListener(this);
			clearButton.addActionListener(this);
			exitButton.addActionListener(this);
		
		 }
		

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comm=e.getActionCommand();
		
		if(comm.equals("��  ��")){
			
			//ȸ������ 
		} else if(comm.equals("���Է�")){
			idField.setText("");
			nameField.setText("");
			passwordField.setText("");
			emailField.setText("");
			emailField1.setText("");
			sexComboBox.setSelectedIndex(0);
		} else if(comm.equals("�ߺ�üũ")){
			
			// �ߺ�üũ�Ұ�
		} else {
			
			this.dispose();// â�ݱ�
			new LoginMain();
		}
	}
	
}
