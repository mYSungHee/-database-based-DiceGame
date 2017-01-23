import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class AdminAdd extends MyFrame implements ActionListener {
	JTextField name;
	JTextField id;
	JPasswordField password;
	String sex[] = {"남","여"};
	JComboBox comboBox;
	JButton bt1 = new JButton("등록");
	JButton bt2 = new JButton("이전");

	AccountAdmin m = new AccountAdmin();
	String tempId;
	
	ServerThread svThread;
	
	static SQLDatabase sql = new SQLDatabase();
	static Connection con = sql.getConn();      //연결
    PreparedStatement ps = null; //명령
    ResultSet rs = null;         //결과
    
    public AdminAdd(ServerThread svThread){
    	this();
    	this.svThread=svThread;
    	
    }
	public AdminAdd(){
		this.svThread=svThread;
		setTitle("계정등록");
		setSize(500, 330);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = super.getSize();
		int xpos = (int)(screen.getWidth() /2 - frm.getWidth()/2);
		int ypos = (int)(screen.getHeight() /2 - frm.getHeight()/2);
		setLocation(xpos,ypos);
		setDefaultCloseOperation(MyFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);  //항상위
		con = sql.getConn();
		
		JLabel mName = new JLabel("이      름 :");
		mName.setBounds(120, 139, 62, 30);
		getContentPane().add(mName);
		
		JLabel mId = new JLabel("아  이  디 :");
		mId.setBounds(120, 30, 62, 30);
		getContentPane().add(mId);
		
		JLabel mAddr = new JLabel("성      별 :");
		mAddr.setBounds(120, 192, 90, 30);
		getContentPane().add(mAddr);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(217, 144, 82, 21); 
		getContentPane().add(name);
		
		password = new JPasswordField();  
		password.setColumns(10);
		password.setBounds(217, 88, 82, 21);
		getContentPane().add(password);
		
		id = new JTextField();
		id.setColumns(10);
		id.setBounds(217, 35, 82, 21); 
		getContentPane().add(id);

		bt1.setBounds(361, 187, 97, 40);
		getContentPane().add(bt1);
		
		bt2.setBounds(361, 241, 97, 40);
		getContentPane().add(bt2);
		
		
		JLabel mPassword = new JLabel("비 밀 번 호 :");
		mPassword.setBounds(119, 83, 73, 30);
		getContentPane().add(mPassword);
		
		
		comboBox = new JComboBox(sex);
		comboBox.setBounds(217, 197, 38, 21);
		getContentPane().add(comboBox);
		
		setVisible(true);
		
		bt1.addActionListener(this);
		bt2.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bt1){
			String memName = name.getText();
			String memId = id.getText();
			String memPassword = password.getText();
			int tempSex = comboBox.getSelectedIndex();
			String memSex="남";
			if(tempSex==0){
				memSex="남";
			} else if(tempSex==1){
				memSex="여";
				
			}
			
			if(memName.equals("")){
				setAlwaysOnTop(false); 
				JOptionPane.showMessageDialog(this, "이름을 입력해 주세요", "이름공란", JOptionPane.INFORMATION_MESSAGE);
			}else if(memId.equals("")){
				setAlwaysOnTop(false); 
				JOptionPane.showMessageDialog(this, "계정명 입력해 주세요", "ID공란", JOptionPane.INFORMATION_MESSAGE);
			}else if(memPassword.equals("")){
				setAlwaysOnTop(false); 
				JOptionPane.showMessageDialog(this, "비밀번호를 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}else{
				
				
					int check = JOptionPane.showConfirmDialog(this, "입력한 내용이 맞습니까?\n" + 
							 "\n계정 이름 : "+memName + "\n계정 ID : " + memId + 
							"\n계정 Passowrd  : " + memPassword + "\n성별 : " + memSex +
							"메시지" );
					if(check == 0){
						m.setId(memId);
						m.setName(memName);
						m.setPassword(memPassword);
						m.setSex(memSex);
						boolean ck=sql.insertMember(m);  //SQLDatabase에 있는 추가 메소드
						if(ck==true){
						JOptionPane.showMessageDialog(this, "계정가 등록되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
						} else{
							JOptionPane.showMessageDialog(this, "계정 등록에 실패하였습니다..", "메시지", JOptionPane.ERROR_MESSAGE);
							return;
						}
						int check2 = JOptionPane.showConfirmDialog(this, "계속 입력하시겠습니까?");
						if(check2 == 0){
							name.setText(null);
							id.setText(null);
							password.setText(null);
							comboBox.setSelectedIndex(0);
						}else if(check2 == 1){
							new Managements();
							dispose();
							
						}
					
				}
			}
		}else if(e.getSource() == bt2){
			new Admins();
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

