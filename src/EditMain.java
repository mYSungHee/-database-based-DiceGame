import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class EditMain extends JFrame implements ActionListener{
	private JLabel tLabel1 = new JLabel(" ");          
	private JLabel tLabel2 = new JLabel("    * 회원정보 수정 & 조회 ");
	private JLabel tLabel3 = new JLabel("    * 비밀번호는 신중히 변경해주세요."); 
	private JLabel tLabel4 = new JLabel(" "); 
	private JLabel nameLabel= new JLabel("    닉  네  임  :     ");
	private JLabel idLabel = new JLabel("   아  이  디  :     ");
	private JLabel passwordLabel = new JLabel("   비밀번호  :     ");
	private JLabel emailLabel = new JLabel("   이메일 주소 :  ");
	private JLabel emailLabel1 = new JLabel(" @ ");
	private JLabel sexLabel = new JLabel("   성   별 :  ");
	
	private JTextField idField = new JTextField(10);
	private JTextField nameField = new JTextField(5);
	private JPasswordField passwordField = new JPasswordField(10);
	private JTextField emailField = new JTextField(12);
	private JTextField emailField1 = new JTextField(8);
	
	private JButton joinButton = new JButton("수  정");
	private JButton exitButton  = new JButton("이  전");
	private String sex[] ={"남","여"};
	private JPanel panel[] =new JPanel[8];
	private JPanel tPanel = new JPanel(new GridLayout(4,1,5,5) );
		public static void main(String[] args){
			new EditMain();
			
		}
	
		public EditMain(){
			
			init();
			
			//this.setLayout(null);

			setSize(450,500);
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

		public void init() {
			 getContentPane().setBackground(Color.WHITE);
				
			 setTitle("Register");
			 getContentPane().setLayout(new BorderLayout() );
			 tPanel.setBackground(Color.WHITE);
		  
			 tPanel.add(tLabel1);
			 tPanel.add(tLabel2);
			 tPanel.add(tLabel3);

			// cPanel.add("North",tPanel);
			 
			 
			 idField.setEditable(false);
		  
			 for(int i=0;i<panel.length;i++) { 
		  
				panel[i]=new JPanel();
			 }
		 
			 for (int i=1; i <panel.length-1 ; i++ ) {
		   
				 panel[i].setLayout(new FlowLayout(FlowLayout.LEFT) );
				 
			 }
		  
			 panel[0].setLayout(new GridLayout(8,1,18,10) );  
			 panel[0].setBackground(Color.WHITE);

			 panel[1].add(new JLabel("          ")); //정렬용으로 추가해주었음.
			 panel[1].add(idLabel);
			 panel[1].add(idField);
		  
		  

			 panel[2].add(new JLabel("          "));
			 panel[2].add(passwordLabel);
			 panel[2].add(passwordField);
		  
		  

			 panel[3].add(new JLabel("          "));
			 panel[3].add(nameLabel);
			 panel[3].add(nameField);
		  

			 panel[4].add(new JLabel("          "));
		  

			 panel[5].add(new JLabel("          "));
			 panel[5].add(emailLabel);
			 panel[5].add(emailField);

			 panel[5].add(emailLabel1);
			 panel[5].add(emailField1);
			 panel[5].add(new JLabel(" ") );
		  
			 panel[6].add(new JLabel(""));
			 
			 panel[7].add(new JLabel(""));
			 panel[7].add(joinButton);
			 panel[7].add(exitButton);
		  
			 for (int i=1; i<panel.length ;i++ ) {
		   
				 panel[0].add(panel[i] );
				 panel[i].setBackground(Color.WHITE);
		  
			 }
		  
			getContentPane().add("North",tPanel);
			getContentPane().add("Center",panel[0] );
			
			joinButton.addActionListener(this);
			exitButton.addActionListener(this);
		
		 }
		

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comm=e.getActionCommand();
		
		if(comm.equals("수  정")){
			
			//db에 저장
		} else {

			new WaitMain();
			dispose();// 창닫기
		}
	}
	
}
