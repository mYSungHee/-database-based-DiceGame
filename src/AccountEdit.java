import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountEdit extends MyFrame implements ActionListener{
	MyFrame f=new MyFrame();
	JButton eOk = new JButton("닫기");
	JButton eGetInfo = new JButton("불러오기");
	String sex[] = {"남","여"};
	JTextField tName;
	JTextField tId;
	JPasswordField tPassword;
	JTextField tScore;
	JTextField tAddr;
	Account admins;
	JTextField tCode;
	JComboBox genderBox;
	Connection con = null;       
    PreparedStatement ps = null; 
    private JTextField tNick;
    JCheckBox banBox;
    JButton eScore;
    JButton eNick;
	JButton ePwd;
	JButton eAddr;
	int ck=0;
	int cck=0;
	ServerThread svThread;
    public AccountEdit(ServerThread svThread){
    	this();
    	this.svThread=svThread;
    	
    }
	public AccountEdit() {

    try{
    	Class.forName("com.mysql.jdbc.Driver"); 
        con = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=dytc1234");
 
    }catch(Exception e){
    	e.printStackTrace();
        }
    
		
		getContentPane().setEnabled(false);
		setTitle("Edit");
		setSize(409, 402);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = super.getSize();
		int xpos = (int)(screen.getWidth() /2 - frm.getWidth()/2);
		int ypos = (int)(screen.getHeight() /2 - frm.getHeight()/2);
		setLocation(xpos,ypos);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JLabel edit = new JLabel("수정");
		edit.setFont(new Font("나눔고딕코딩", Font.BOLD, 18));
		edit.setBounds(12, 21, 178, 23);
		getContentPane().add(edit);
		
		eGetInfo.setBounds(276, 55, 105, 25);
		getContentPane().add(eGetInfo);
		
		eOk.setBounds(276, 319, 105, 25);
		getContentPane().add(eOk);
		
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
		tPassword.setBounds(100, 204, 127, 20);
		tPassword.setColumns(10);
		getContentPane().add(tPassword);
		
		tScore = new JTextField();
		tScore.setColumns(10);
		tScore.setBounds(100, 239, 127, 21);
		getContentPane().add(tScore);
		
		tAddr = new JTextField();
		tAddr.setColumns(10);
		tAddr.setBounds(100, 273, 160, 21);
		getContentPane().add(tAddr);
	

		
		
		JLabel nick = new JLabel("닉네임 : ");
		nick.setBounds(12, 126, 57, 15);
		getContentPane().add(nick);
		
		tNick = new JTextField();
		tNick.setColumns(10);
		tNick.setBounds(100, 123, 127, 21);
		getContentPane().add(tNick);
		
		JLabel gender = new JLabel("성별 : ");
		gender.setBounds(12, 324, 57, 15);
		getContentPane().add(gender);
		
		genderBox = new JComboBox(sex);
		genderBox.setEnabled(false);
		genderBox.setBounds(53, 321, 47, 21);
		getContentPane().add(genderBox);
		
		banBox = new JCheckBox("차단");
		banBox.setBounds(112, 320, 115, 23);
		getContentPane().add(banBox);
		banBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(banBox.isSelected()==true){
					ck=1;
				}else if(banBox.isSelected()==false){
					ck=0;
				}
				try{
					String sql = "update usertable set ban ='"+ck+
								  "' where code ="+tCode.getText()+";";
			        ps = con.prepareStatement(sql);
			        int ok = ps.executeUpdate();
			        if(ok>0){
			        	JOptionPane.showMessageDialog(f, " 차단 설정을 변경하었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
			        }else{
			        	JOptionPane.showMessageDialog(f, " 차단 설정을 실패하였습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
			        }
					}catch(Exception ex1){
					ex1.printStackTrace();
					}	
			}
		});
		
		eNick = new JButton("닉네임");
		
		eNick.setBounds(276, 122, 105, 23);
		getContentPane().add(eNick);
		
		ePwd = new JButton("비밀번호");
		ePwd.setBounds(276, 203, 105, 23);
		getContentPane().add(ePwd);
		
		eScore = new JButton("포인트");
		eScore.setBounds(276, 238, 105, 23);
		getContentPane().add(eScore);
		
		eAddr = new JButton("이메일");
		eAddr.setBounds(276, 272, 105, 23);
		getContentPane().add(eAddr);
	
		
		eGetInfo.addActionListener(this);
		eNick.addActionListener(this);
		ePwd.addActionListener(this);
		eScore.addActionListener(this);
		eAddr.addActionListener(this);
		eOk.addActionListener(this);
		setVisible(true);
		
	}
	
public void actionPerformed(ActionEvent e) {
	
	String eCode=null;
    String eStrId = null;
    String eStrPassowrd = null;
    String eStrName = null;
    int escore = 0;
    String eStrAddr = null;
    String eStrNick = null;
    int eGender=0;
    int eBan = 0;
    ResultSet rs=null;
   	if(e.getSource() == eGetInfo){
		
		try{
			
			String sql = "select * from usertable where code = "+tCode.getText()+";";
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				eCode = rs.getString("code");
				eStrId = rs.getString("id");
				eStrPassowrd = rs.getString("password");
				eStrName = rs.getString("name");
				escore = rs.getInt("score");
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
			tScore.setText(Integer.toString(escore));
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
   		if(e.getSource() == ePwd){
			try{
				if(tPassword.getText().equals("")){
			        JOptionPane.showMessageDialog(this, "비밀번호란이 공백 입니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
			        } //공백 검사
				else{
					
				String sql = "update usertable set password ='"+tPassword.getText()+
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
		}else if(e.getSource() == eScore){
			try{
				if(tScore.getText().equals("")){
			        JOptionPane.showMessageDialog(this, "포인트란이 공백 입니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
			        } //공백 검사
				else if(!integerOrNot(tScore.getText())){
					JOptionPane.showMessageDialog(this, "포인트는 문자를 입력할 수 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(Integer.parseInt(tScore.getText()) < 0){
					JOptionPane.showMessageDialog(this, "정수만 입력가능합니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					
				String sql = "update usertable set score ='"+tScore.getText()+
							  "' where code ="+tCode.getText()+";";
		        ps = con.prepareStatement(sql);
		        int ok = ps.executeUpdate();
		        if(ok==1){
		        	JOptionPane.showMessageDialog(this, "포인트가 변경 되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
		        }else
		        	JOptionPane.showMessageDialog(this, "포인트 변경을 실패 하였습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
		        
					}
		        }catch(Exception ex1){
				ex1.printStackTrace();
		        }

		}else if(e.getSource() == eAddr){
			try{
				if(tAddr.getText().equals("")){
			        JOptionPane.showMessageDialog(this, "E-mail주소란이 공백 입니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
			        } //공백 검사
				else{
					
				String sql = "update usertable set addr ='"+tAddr.getText()+
							  "' where code ="+tCode.getText()+";";
		        ps = con.prepareStatement(sql);
		        int ok = ps.executeUpdate();
		        if(ok==1){
		        	JOptionPane.showMessageDialog(this, "E-mail주소가 변경 되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
		        }else
		        	JOptionPane.showMessageDialog(this, "E-mail주소 변경을 실패 하였습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
		        
		        
					}
		        }catch(Exception ex1){
				ex1.printStackTrace();
		        }

		}else if(e.getSource() == eNick){
			try{
				if(tNick.getText().equals("")){
			        JOptionPane.showMessageDialog(this, "닉네임란이 공백 입니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
			        } //공백 검사
				else{
					
				String sql = "update usertable set nick ='"+tNick.getText()+
							  "' where code ="+tCode.getText()+";";
		        ps = con.prepareStatement(sql);
		        int ok = ps.executeUpdate();
		        if(ok==1){
		        	JOptionPane.showMessageDialog(this, "닉네임이 변경 되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
		        }else
		        	JOptionPane.showMessageDialog(this, "닉네임 변경을 실패 하였습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
		        
		        
					}
		        }catch(Exception ex1){
				ex1.printStackTrace();
		        }

		}else if(e.getSource() == eOk){
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
