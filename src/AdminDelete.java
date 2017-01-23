import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDelete extends MyFrame implements ActionListener{
	JButton dOk = new JButton("이전");
	JButton dGetInfo = new JButton("불러오기");
	JButton dDelete = new JButton("삭제");
	String sex[] = {"남","여"};
	JTextField tName;
	JTextField tId;
	JPasswordField tPassword;
	AccountAdmin admins;
	JTextField tCode;
	JComboBox genderBox;
	ServerThread svThread;
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
    public AdminDelete(ServerThread svThread){
    	this();
    	this.svThread=svThread;
    	
    }
	public AdminDelete() {
		getContentPane().setEnabled(false);
		setTitle("삭제");
		setSize(405, 341);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = super.getSize();
		int xpos = (int)(screen.getWidth() /2 - frm.getWidth()/2);
		int ypos = (int)(screen.getHeight() /2 - frm.getHeight()/2);
		setLocation(xpos,ypos);
		setDefaultCloseOperation(MyFrame.EXIT_ON_CLOSE);
		
		JLabel edit = new JLabel("조회 및 삭제<관리자>");
		edit.setFont(new Font("나눔고딕코딩", Font.BOLD, 18));
		edit.setBounds(12, 21, 235, 23);
		getContentPane().add(edit);
		
		dGetInfo.setBounds(259, 55, 105, 25);
		getContentPane().add(dGetInfo);
		
		dOk.setBounds(122, 265, 105, 25);
		getContentPane().add(dOk);
		
		JLabel name = new JLabel("이름 : ");
		name.setBounds(12, 91, 57, 15);
		getContentPane().add(name);
		
		JLabel id = new JLabel("아이디 : ");
		id.setBounds(12, 134, 57, 15);
		getContentPane().add(id);
		
		JLabel password = new JLabel("비밀번호 : ");
		password.setBounds(12, 173, 69, 15);
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
		tId.setBounds(100, 131, 127, 21);
		getContentPane().add(tId);
		
		tPassword = new JPasswordField();
		tPassword.setEditable(false);
		tPassword.setBounds(100, 170, 127, 20);
		tPassword.setColumns(10);
		getContentPane().add(tPassword);
	

		
		dDelete.setBounds(12, 265, 105, 25);
		getContentPane().add(dDelete);
		
		JLabel gender = new JLabel("성별 : ");
		gender.setBounds(12, 214, 57, 15);
		getContentPane().add(gender);
		
		genderBox = new JComboBox(sex);
		genderBox.setBounds(98, 211, 47, 21);
		getContentPane().add(genderBox);
	
		
		dGetInfo.addActionListener(this);
		dDelete.addActionListener(this);
		dOk.addActionListener(this);
		setVisible(true);
		
	}
	
public void actionPerformed(ActionEvent aE) {
	
	String eCode=null;
    String eStrId = null;
    String eStrPassowrd = null;
    String eStrName = null;
    int eGender=0;
    ResultSet rs=null;
    	if(aE.getSource() == dGetInfo){
    		
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
    	if(aE.getSource() == dDelete){
    		int ok=0;
    		
    		if(dDelete.getText().equals("")){
    			JOptionPane.showMessageDialog(this, "계정번호를입력하세요.", "메시지", JOptionPane.INFORMATION_MESSAGE);
    		return;
    		}
    		try{
    			String sql = "delete from admintable where code = "+tCode.getText()+";";
    	
    			ps = con.prepareStatement(sql);
    			ok = ps.executeUpdate();
    			  if(ok==1){
    		        	JOptionPane.showMessageDialog(this, "계정이 삭제 되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
    		        	dispose();
    		        }else
    		        	JOptionPane.showMessageDialog(this, "계정 삭제를 실패 하였습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
    		}catch(SQLException sqle){
    			sqle.printStackTrace();
    		}
    		
    	}
    	if(aE.getSource() == dOk){
			new Managements();
    		dispose();
			
		}
	}   	
}
