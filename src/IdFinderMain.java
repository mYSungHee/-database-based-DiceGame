import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class IdFinderMain extends JFrame {


	private JCheckBox idck = new JCheckBox();
	private JCheckBox pwdck = new JCheckBox();
	private JLabel idlb = new JLabel("             아  이  디 :                        ");
	private JLabel pwdlb= new JLabel("             비밀번호 :                       ");
	private JLabel topText= new JLabel("     * 분실한 항목을 선택해주세요.");
	private JTextField emailArea;
	private JLabel bottomText = new JLabel("     * 비밀번호는 000000으로 초기화됩니다.");
	private JLabel emaillb;
	private int idsel=2;
	private int pwdsel=2;
	private JPanel mp[] = new JPanel[8];
	String idText;
	JButton okBt = new JButton("확    인");
	JButton cnBt = new JButton("재입력");
	JButton exBt = new JButton("이    전");
	JPanel mainP = new JPanel();
	
	public static void main(String[] args){
		new IdFinderMain();
		
	}
	public IdFinderMain(){
		setSize(380,350);
		init();
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
	public void init(){
	
		
		emaillb = new JLabel("             이  메  일 :  ");
		emailArea = new JTextField(13);
//		getContentPane().setLayout(null);
		getContentPane().setBackground(SystemColor.info);
		for(int i=0; i<7; i++){
		mp[i] = new JPanel();
		mp[i].setBackground(UIManager.getColor("info"));
		if (i==0) mp[0].setLayout(new GridLayout(6,1,12,5));
		else if(i>0){
		mp[i].setLayout(new FlowLayout(FlowLayout.LEFT) );
		mp[0].add(mp[i]);	
		if(i==6) mp[i].setLayout(new FlowLayout(FlowLayout.CENTER));
		
		}
		}
		mp[1].add(topText);
		mp[2].add(bottomText);
		mp[3].add(idlb);
		idck.setBackground(UIManager.getColor("info"));
		mp[3].add(idck);
		mp[4].add(pwdlb);
		mp[4].add(pwdck);
		pwdck.setBackground(UIManager.getColor("info"));
		mp[4].add(new JLabel("                   "));

		
		mp[5].add(emaillb);
		mp[5].add(emailArea);
		mp[5].add(cnBt);
		mp[6].add(okBt);
		mp[6].add(exBt);
		cnBt.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
			
					 emailArea.setText("");
				
				
			 }	
		});
		exBt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

					new LoginMain();
					dispose();
				
			}
			
		});
		
		okBt.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 if(emailArea.getText().equals("")){
					 JOptionPane.showMessageDialog(null, "이메일을 입력해 주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
				 }
				 else if(idsel==1 && pwdsel==1){
					 idText=emailArea.getText();
					 JOptionPane.showMessageDialog(null, "아이디는 ["+ idText +" ] 이며, 비밀번호가 초기화 되었습니다.", "Sucess!", JOptionPane.INFORMATION_MESSAGE);
				 } else if(idsel==1){
					 idText=emailArea.getText();
					   JOptionPane.showMessageDialog(null, "아이디는 ["+ idText +" ]입니다.", "Sucess!", JOptionPane.INFORMATION_MESSAGE);
				 } else if(pwdsel==1){
				   JOptionPane.showMessageDialog(null, "비밀번호가 초기화 되었습니다.", "Sucess!", JOptionPane.INFORMATION_MESSAGE);
				 } else {
				   JOptionPane.showMessageDialog(null, "한가지 이상을 선택해 주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
				 }		
			
		 }	
	});
		idck.addItemListener(new ItemListener (){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				idsel=e.getStateChange();
			}
			
		});
		pwdck.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				
				pwdsel=e.getStateChange();
			}
		});
		getContentPane().add(mp[0]);
//		fp.setBounds(x, y, width, height);
		
	}

	

}
/*
public void actionPerformed(ActionEvent e){
String comm = e.getActionCommand();

if(comm.equals("확    인") ==true){
	 if(emailArea.getText().equals("")){
		 JOptionPane.showMessageDialog(null, "이메일을 입력해 주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
	 }
	 else if(idsel==1 && pwdsel==1){
		 idText=emailArea.getText();
		 JOptionPane.showMessageDialog(null, "아이디는 ["+ idText +" ] 이며, 비밀번호가 초기화 되었습니다.", "Sucess!", JOptionPane.INFORMATION_MESSAGE);
	 } else if(idsel==1){
		 idText=emailArea.getText();
		   JOptionPane.showMessageDialog(null, "아이디는 ["+ idText +" ]입니다.", "Sucess!", JOptionPane.INFORMATION_MESSAGE);
	 } else if(pwdsel==1){
	   JOptionPane.showMessageDialog(null, "비밀번호가 초기화 되었습니다.", "Sucess!", JOptionPane.INFORMATION_MESSAGE);
	 } else {
	   JOptionPane.showMessageDialog(null, "한가지 이상을 선택해 주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
	 }
} else if (comm.equals("재입력")){
	 emailArea.setText("");
	 
} else if(comm.equals("뒤로")){
	 
}


}
*/