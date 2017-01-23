import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.sql.*;

public class Admins extends MyFrame implements ActionListener{
	JButton loginBt;
	JButton regiBt;
	JTextField idField;
	JPasswordField pwField;
	
	Connection con = null;       
    PreparedStatement ps = null; 
    {   
    try{
    	Class.forName("com.mysql.jdbc.Driver"); 
        con = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=dytc1234");
 
    }catch(Exception e){
    	e.printStackTrace();
        }
    }
	public Admins(){
		
	setSize(500,400);
	setTitle("DiceGame");
	
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension frm = super.getSize();
	int xpos = (int)(screen.getWidth() /2 - frm.getWidth()/2);
	int ypos = (int)(screen.getHeight() /2 - frm.getHeight()/2);
	setLocation(xpos,ypos);
	
	loginScreen();
	
	setVisible(true);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setResizable(false);
	}
	public void loginScreen(){
		getContentPane().setLayout(null);
		//---------------------------------------------------CENTER
		
		JPanel pc = new JPanel(); //Center 
		pc.setBounds(0, 39, 584, 402); // x,y ��ǥ�� ���ϴ�ũ��� ����.
		
		pc.setLayout(null);
		
		
		
		ImageLabel mainImgLabel = new ImageLabel();
		mainImgLabel.setImageIcon(new ImageIcon("img/mainImg.png"));
		mainImgLabel.setFont(new Font("��������ڵ�", Font.PLAIN, 15));
		mainImgLabel.setBounds(12, 55, 223, 174);
		
		pc.add(mainImgLabel);//�̹��� �κ��� PC�� �߰�.
		
		JPanel pcText = new JPanel();
		pcText.setBounds(260, 0, 231, 301);
		
		pc.add(pcText);
		pcText.setLayout(null);
		
		idField = new JTextField();
		idField.setBounds(12, 69, 206, 31);
		idField.setPreferredSize(new Dimension(200, 10));
		pcText.add(idField);
		
		pwField = new JPasswordField();
		pwField.setBounds(12, 110, 206, 31);
		pcText.add(pwField);
		pwField.setEchoChar('*');
		pwField.setPreferredSize(new Dimension(200, 10));
		
		loginBt = new JButton("�� �� ��");
		loginBt.setBounds(12, 151, 97, 23);
		pcText.add(loginBt);
		loginBt.addActionListener(this);
		
		regiBt = new JButton("��    ��");
		regiBt.setBounds(121, 151, 97, 23);
		pcText.add(regiBt);
		regiBt.addActionListener(this);
		
		getContentPane().add(pc);
		
		
		//---------------------------------------------------NORTH
		JPanel pn = new JPanel();
		pn.setBounds(0, 0, 494, 39);
		getContentPane().add(pn);
		
		JLabel pnTitle = new JLabel("���� ���α׷�");
		pn.add(pnTitle);
		
		pnTitle.setFont(new Font("Gothic",Font.ITALIC,20));
		


		
	}
	
	public boolean loginCheck(){
		 ResultSet rs=null;
				String idck="";
				String pwck = "";
				try{
					String sql = "select * from admintable where id = '"+idField.getText()+"';";
	    			
	    			ps = con.prepareStatement(sql);
	    			rs = ps.executeQuery();
	    			while(rs.next()){
	    				idck = rs.getString("id");
	    				pwck = rs.getString("password");
	    				
	    			}

	    			
	    			}catch (Exception ex){
					ex.printStackTrace();
				}
				
				if(idck.equals(idField.getText()) && pwck.equals(pwField.getText())){
					
					return true;
					
				} else{
					
				}
				return false;
						
	}
	
	
public void actionPerformed(ActionEvent e){
	
	if(e.getSource()==loginBt){
		
		if(idField.getText().equals("") || pwField.getText().equals("")){
			JOptionPane.showMessageDialog(this,"���̵� Ȥ�� ��й�ȣ�� �Է����ּ���","Message", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(loginCheck()==true){
			
			new Managements();
			dispose();
		} else {
			
			pwField.setText("");
			
			JOptionPane.showMessageDialog(this,"��й�ȣ Ȥ�� ���̵� ����ġ","Message",JOptionPane.ERROR_MESSAGE);
		}
	}else if(e.getSource()==regiBt){
		new AdminAdd();
		dispose();
	}
	
	}
}