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
	JButton eOk = new JButton("�ݱ�");
	JButton eGetInfo = new JButton("�ҷ�����");
	String sex[] = {"��","��"};
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
		
		JLabel edit = new JLabel("����");
		edit.setFont(new Font("��������ڵ�", Font.BOLD, 18));
		edit.setBounds(12, 21, 178, 23);
		getContentPane().add(edit);
		
		eGetInfo.setBounds(276, 55, 105, 25);
		getContentPane().add(eGetInfo);
		
		eOk.setBounds(276, 319, 105, 25);
		getContentPane().add(eOk);
		
		JLabel name = new JLabel("�̸� : ");
		name.setBounds(12, 91, 57, 15);
		getContentPane().add(name);
		
		JLabel id = new JLabel("���̵� : ");
		id.setBounds(12, 164, 57, 15);
		getContentPane().add(id);
		
		JLabel password = new JLabel("��й�ȣ : ");
		password.setBounds(12, 207, 69, 15);
		getContentPane().add(password);
		
		JLabel score = new JLabel("����Ʈ : ");
		score.setBounds(12, 242, 83, 15);
		getContentPane().add(score);
		
		JLabel addr = new JLabel("�̸��� �ּ� : ");
		addr.setBounds(12, 276, 83, 15);
		getContentPane().add(addr);
		
		JLabel code = new JLabel("������ȣ :");
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
	

		
		
		JLabel nick = new JLabel("�г��� : ");
		nick.setBounds(12, 126, 57, 15);
		getContentPane().add(nick);
		
		tNick = new JTextField();
		tNick.setColumns(10);
		tNick.setBounds(100, 123, 127, 21);
		getContentPane().add(tNick);
		
		JLabel gender = new JLabel("���� : ");
		gender.setBounds(12, 324, 57, 15);
		getContentPane().add(gender);
		
		genderBox = new JComboBox(sex);
		genderBox.setEnabled(false);
		genderBox.setBounds(53, 321, 47, 21);
		getContentPane().add(genderBox);
		
		banBox = new JCheckBox("����");
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
			        	JOptionPane.showMessageDialog(f, " ���� ������ �����Ͼ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
			        }else{
			        	JOptionPane.showMessageDialog(f, " ���� ������ �����Ͽ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
			        }
					}catch(Exception ex1){
					ex1.printStackTrace();
					}	
			}
		});
		
		eNick = new JButton("�г���");
		
		eNick.setBounds(276, 122, 105, 23);
		getContentPane().add(eNick);
		
		ePwd = new JButton("��й�ȣ");
		ePwd.setBounds(276, 203, 105, 23);
		getContentPane().add(ePwd);
		
		eScore = new JButton("����Ʈ");
		eScore.setBounds(276, 238, 105, 23);
		getContentPane().add(eScore);
		
		eAddr = new JButton("�̸���");
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
				if(rs.getString("sex").equals("��")){
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
				JOptionPane.showMessageDialog(this, "�ش� �ڵ尡 �����ϴ�.", "����", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}catch(SQLException sqle){
			if(tCode.getText().equals("")){
				JOptionPane.showMessageDialog(this, "�ڵ���� ���� �Դϴ�.", "����", JOptionPane.INFORMATION_MESSAGE);	
    			}
			else{
				JOptionPane.showMessageDialog(this, "SQL Server ���� �Դϴ�.", "����", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
   		if(e.getSource() == ePwd){
			try{
				if(tPassword.getText().equals("")){
			        JOptionPane.showMessageDialog(this, "��й�ȣ���� ���� �Դϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
			        } //���� �˻�
				else{
					
				String sql = "update usertable set password ='"+tPassword.getText()+
							  "' where code ="+tCode.getText()+";";
		        ps = con.prepareStatement(sql);
		        int ok = ps.executeUpdate();
		        if(ok==1){
		        	JOptionPane.showMessageDialog(this, "��й�ȣ�� ���� �Ǿ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
		        }else
		        	JOptionPane.showMessageDialog(this, "��й�ȣ ������ ���� �Ͽ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
		        
					}
		        }catch(Exception ex1){
				ex1.printStackTrace();
		        }
		}else if(e.getSource() == eScore){
			try{
				if(tScore.getText().equals("")){
			        JOptionPane.showMessageDialog(this, "����Ʈ���� ���� �Դϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
			        } //���� �˻�
				else if(!integerOrNot(tScore.getText())){
					JOptionPane.showMessageDialog(this, "����Ʈ�� ���ڸ� �Է��� �� �����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(Integer.parseInt(tScore.getText()) < 0){
					JOptionPane.showMessageDialog(this, "������ �Է°����մϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					
				String sql = "update usertable set score ='"+tScore.getText()+
							  "' where code ="+tCode.getText()+";";
		        ps = con.prepareStatement(sql);
		        int ok = ps.executeUpdate();
		        if(ok==1){
		        	JOptionPane.showMessageDialog(this, "����Ʈ�� ���� �Ǿ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
		        }else
		        	JOptionPane.showMessageDialog(this, "����Ʈ ������ ���� �Ͽ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
		        
					}
		        }catch(Exception ex1){
				ex1.printStackTrace();
		        }

		}else if(e.getSource() == eAddr){
			try{
				if(tAddr.getText().equals("")){
			        JOptionPane.showMessageDialog(this, "E-mail�ּҶ��� ���� �Դϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
			        } //���� �˻�
				else{
					
				String sql = "update usertable set addr ='"+tAddr.getText()+
							  "' where code ="+tCode.getText()+";";
		        ps = con.prepareStatement(sql);
		        int ok = ps.executeUpdate();
		        if(ok==1){
		        	JOptionPane.showMessageDialog(this, "E-mail�ּҰ� ���� �Ǿ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
		        }else
		        	JOptionPane.showMessageDialog(this, "E-mail�ּ� ������ ���� �Ͽ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
		        
		        
					}
		        }catch(Exception ex1){
				ex1.printStackTrace();
		        }

		}else if(e.getSource() == eNick){
			try{
				if(tNick.getText().equals("")){
			        JOptionPane.showMessageDialog(this, "�г��Ӷ��� ���� �Դϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
			        } //���� �˻�
				else{
					
				String sql = "update usertable set nick ='"+tNick.getText()+
							  "' where code ="+tCode.getText()+";";
		        ps = con.prepareStatement(sql);
		        int ok = ps.executeUpdate();
		        if(ok==1){
		        	JOptionPane.showMessageDialog(this, "�г����� ���� �Ǿ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
		        }else
		        	JOptionPane.showMessageDialog(this, "�г��� ������ ���� �Ͽ����ϴ�.", "�޽���", JOptionPane.INFORMATION_MESSAGE);
		        
		        
					}
		        }catch(Exception ex1){
				ex1.printStackTrace();
		        }

		}else if(e.getSource() == eOk){
			dispose();
			
		}
	
	}
public boolean integerOrNot(String strData){          // �Է°��� �������� �������� �Ǻ� 
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
