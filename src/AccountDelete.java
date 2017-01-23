import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDelete extends MyFrame implements ActionListener{

	JButton dOk = new JButton("이전");
	JButton dGetInfo = new JButton("불러오기");
	JButton dDelete = new JButton("삭제");
	String sex[] = {"남","여"};
	JTextField tName;
	JTextField tId;
	JPasswordField tPassword;
	JTextField tScore;
	JTextField tAddr;
	Account ac;
	JTextField tCode;
	JComboBox genderBox;
	Connection con = null;       
    PreparedStatement ps = null; 
    private JTextField tNick;
    JCheckBox banBox;
    ServerThread svThread;
    {   
    try{
    	Class.forName("com.mysql.jdbc.Driver"); 
        con = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=dytc1234");
 
    }catch(Exception e){
    	e.printStackTrace();
        }
    }
    public AccountDelete(ServerThread svThread){
    	this();
    	this.svThread=svThread;
    	
    }
    
	public AccountDelete() {
		
		getContentPane().setEnabled(false);
		setTitle("삭제");
		setSize(409, 402);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = super.getSize();
		int xpos = (int)(screen.getWidth() /2 - frm.getWidth()/2);
		int ypos = (int)(screen.getHeight() /2 - frm.getHeight()/2);
		setLocation(xpos,ypos);
		setDefaultCloseOperation(MyFrame.EXIT_ON_CLOSE);
		
		JLabel edit = new JLabel("조회 및 삭제");
		edit.setFont(new Font("나눔고딕코딩", Font.BOLD, 18));
		edit.setBounds(12, 21, 178, 23);
		getContentPane().add(edit);
		
		dGetInfo.setBounds(276, 55, 105, 25);
		getContentPane().add(dGetInfo);
		
		dOk.setBounds(276, 319, 105, 25);
		getContentPane().add(dOk);
		
		JLabel name = new JLabel("이름 : ");
		name.setBounds(12, 91, 57, 15);
		getContentPane().add(name);
		
		JLabel id = new JLabel("아이디 : ");
		id.setBounds(12, 164, 57, 15);
		getContentPane().add(id);
		
		JLabel password = new JLabel("비밀번호 : ");
		password.setBounds(12, 207, 69, 15);
		getContentPane().add(password);
		
		JLabel score = new JLabel("포인트 : ");
		score.setBounds(12, 242, 83, 15);
		getContentPane().add(score);
		
		JLabel addr = new JLabel("이메일 주소 : ");
		addr.setBounds(12, 276, 83, 15);
		getContentPane().add(addr);
		
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
		tId.setBounds(100, 161, 127, 21);
		getContentPane().add(tId);
		
		tPassword = new JPasswordField();
		tPassword.setEditable(false);
		tPassword.setBounds(100, 204, 127, 20);
		tPassword.setColumns(10);
		getContentPane().add(tPassword);
		
		tScore = new JTextField();
		tScore.setEditable(false);
		tScore.setColumns(10);
		tScore.setBounds(100, 239, 127, 21);
		getContentPane().add(tScore);
		
		tAddr = new JTextField();
		tAddr.setEditable(false);
		tAddr.setColumns(10);
		tAddr.setBounds(100, 273, 208, 21);
		getContentPane().add(tAddr);
	

		
		dDelete.setBounds(159, 319, 105, 25);
		getContentPane().add(dDelete);
		
		JLabel nick = new JLabel("닉네임 : ");
		nick.setBounds(12, 126, 57, 15);
		getContentPane().add(nick);
		
		tNick = new JTextField();
		tNick.setEditable(false);
		tNick.setColumns(10);
		tNick.setBounds(100, 123, 127, 21);
		getContentPane().add(tNick);
		
		JLabel gender = new JLabel("성별 : ");
		gender.setBounds(264, 242, 57, 15);
		getContentPane().add(gender);
		
		genderBox = new JComboBox(sex);
		genderBox.setBounds(318, 239, 47, 21);
		getContentPane().add(genderBox);
		
		banBox = new JCheckBox("차단");
		banBox.setBounds(315, 272, 115, 23);
		getContentPane().add(banBox);
	
		
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
    int eScore = 0;
    String eStrAddr = null;
    String eStrNick = null;
    int eGender=0;
    int eBan = 0;
    ResultSet rs=null;
    	if(aE.getSource() == dGetInfo){
    		
    		try{
    			
    			String sql = "select * from usertable where code = "+tCode.getText()+";";
    			
    			ps = con.prepareStatement(sql);
    			rs = ps.executeQuery();
    			while(rs.next()){
    				eCode = rs.getString("code");
    				eStrId = rs.getString("id");
    				eStrPassowrd = rs.getString("password");
    				eStrName = rs.getString("name");
    				eScore = rs.getInt("score");
    				eStrAddr = rs.getString("addr");
    				eStrNick = rs.getString("nick");
    				if(rs.getString("sex").equals("남")){
    					eGender=0;
    				} else { eGender=1;}
    				eBan = rs.getInt("ban");
    				
    			}
    			tName.setText(eStrName);
    			tId.setText(eStrId);
    			tNick.setText(eStrNick);
    			tPassword.setText(eStrPassowrd);
    			tScore.setText(Integer.toString(eScore));
    			tAddr.setText(eStrAddr);
    			genderBox.setSelectedIndex(eGender);
    			if(eBan==1){
    			banBox.setSelected(true);
    			} else { banBox.setSelected(false);}
    			
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
    		try{
    			String sql = "delete from usertable where code = "+tCode.getText()+";";
    	
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
