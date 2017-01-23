import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminEdit extends MyFrame implements ActionListener{
	MyFrame f=new MyFrame();
	JButton eOk = new JButton("닫기");
	JButton eGetInfo = new JButton("불러오기");
	String sex[] = {"남","여"};
	JTextField tName;
	JTextField tId;
	JPasswordField tPassword;
	Account admins;
	JTextField tCode;
	JComboBox genderBox;
	Connection con = null;       
    PreparedStatement ps = null; 
	JButton ePwd;
	int ck=0;
	ServerThread svThread;
    public AdminEdit(ServerThread svThread){
    	this();
    	this.svThread=svThread;
    	
    }
	public AdminEdit() {
    try{
    	Class.forName("com.mysql.jdbc.Driver"); 
        con = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=dytc1234");
 
    }catch(Exception e){
    	e.printStackTrace();
        }
    
		
		getContentPane().setEnabled(false);
		setTitle("Edit");
		setSize(409, 283);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = super.getSize();
		int xpos = (int)(screen.getWidth() /2 - frm.getWidth()/2);
		int ypos = (int)(screen.getHeight() /2 - frm.getHeight()/2);
		setLocation(xpos,ypos);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JLabel edit = new JLabel("수정<관리자>");
		edit.setFont(new Font("나눔고딕코딩", Font.BOLD, 18));
		edit.setBounds(12, 10, 178, 23);
		getContentPane().add(edit);
		
		eGetInfo.setBounds(276, 55, 105, 25);
		getContentPane().add(eGetInfo);
		
		eOk.setBounds(276, 202, 105, 25);
		getContentPane().add(eOk);
		
		JLabel name = new JLabel("이름 : ");
		name.setBounds(12, 91, 57, 15);
		getContentPane().add(name);
		
		JLabel id = new JLabel("아이디 : ");
		id.setBounds(12, 131, 57, 15);
		getContentPane().add(id);
		
		JLabel password = new JLabel("비밀번호 : ");
		password.setBounds(12, 171, 69, 15);
		getContentPane().add(password);
		
		JLabel code = new JLabel("계정번호 :");
		code.setBounds(12, 60, 69, 14);
		getContentPane().add(code);
		
		tCode = new JTextField();
		tCode.setBounds(100, 57, 127, 20);
		getContentPane().add(tCode);
		tCode.setColumns(10);
		
		
		tName = new JTextField();
		tName.setEditable(false);
		tName.setBounds(100, 88, 127, 21);
		//tName.s
		getContentPane().add(tName);
		tName.setColumns(10);
		
		tId = new JTextField();
		tId.setEditable(false);
		tId.setColumns(10);
		tId.setBounds(100, 128, 127, 21);
		getContentPane().add(tId);
		
		tPassword = new JPasswordField();
		tPassword.setBounds(100, 168, 127, 20);
		tPassword.setColumns(10);
		getContentPane().add(tPassword);
		
		JLabel gender = new JLabel("성별 : ");
		gender.setBounds(12, 207, 57, 15);
		getContentPane().add(gender);
		
		genderBox = new JComboBox(sex);
		genderBox.setEnabled(false);
		genderBox.setBounds(61, 204, 47, 21);
		getContentPane().add(genderBox);
		
		ePwd = new JButton("비밀번호");
		ePwd.setBounds(276, 167, 105, 23);
		getContentPane().add(ePwd);
	
		
		eGetInfo.addActionListener(this);
		ePwd.addActionListener(this);
		eOk.addActionListener(this);
		setVisible(true);
		
	}
	
public void actionPerformed(ActionEvent e) {
	
	String eCode=null;
    String eStrId = null;
    String eStrPassowrd = null;
    String eStrName = null;
    int eGender=0;
    ResultSet rs=null;
   	if(e.getSource() == eGetInfo){
		
		try{
			
			String sql = "select * from admintable where code = "+tCode.getText()+";";
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				eCode = rs.getString("code");
				eStrId = rs.getString("id");
				eStrPassowrd = rs.getString("password");
				eStrName = rs.getString("name");
				if(rs.getString("sex").equals("남")){
					eGender=0;
				} else { eGender=1;}
				
			}
			tName.setText(eStrName);
			tId.setText(eStrId);
			tPassword.setText(eStrPassowrd);
			genderBox.setSelectedIndex(eGender);
			
			if(eCode==null){
				JOptionPane.showMessageDialog(this, "해당 코드가 없습니다.", "에러", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}catch(SQLException sqle){
			if(tCode.getText().equals("")){
				JOptionPane.showMessageDialog(this, "코드란이 공백 입니다.", "에러", JOptionPane.INFORMATION_MESSAGE);	
    			}
			else{
				JOptionPane.showMessageDialog(this, "SQL Server 문제 입니다.", "에러", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
   	else if(e.getSource() == ePwd){
			try{
				if(tPassword.getText().equals("")){
			        JOptionPane.showMessageDialog(this, "비밀번호란이 공백 입니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
			        } //공백 검사
				else{
					
				String sql = "update admintable set password ='"+tPassword.getText()+
							  "' where code ="+tCode.getText()+";";
		        ps = con.prepareStatement(sql);
		        int ok = ps.executeUpdate();
		        if(ok==1){
		        	JOptionPane.showMessageDialog(this, "비밀번호가 변경 되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
		        }else
		        	JOptionPane.showMessageDialog(this, "비밀번호 변경을 실패 하였습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
		        
					}
		        }catch(Exception ex1){
				ex1.printStackTrace();
		        }
		} else if(e.getSource()==eOk){
			dispose();
			
		}
	
	}
public boolean integerOrNot(String strData){          // 입력값이 숫자인지 문자인지 판별 
	char[] charData = strData.toCharArray();
	boolean check=true;
	while(check){
		for(int i=0; i<charData.length; i++){		
			if(!Character.isDigit(charData[i])){
					check = !check;
					break;
			}
		}
		break;	
	}return check;
}
}
