import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class IdFinderMain extends JFrame {


	private JCheckBox idck = new JCheckBox();
	private JCheckBox pwdck = new JCheckBox();
	private JLabel idlb = new JLabel("             ��  ��  �� :                        ");
	private JLabel pwdlb= new JLabel("             ��й�ȣ :                       ");
	private JLabel topText= new JLabel("     * �н��� �׸��� �������ּ���.");
	private JTextField emailArea;
	private JLabel bottomText = new JLabel("     * ��й�ȣ�� 000000���� �ʱ�ȭ�˴ϴ�.");
	private JLabel emaillb;
	private int idsel=2;
	private int pwdsel=2;
	private JPanel mp[] = new JPanel[8];
	String idText;
	JButton okBt = new JButton("Ȯ    ��");
	JButton cnBt = new JButton("���Է�");
	JButton exBt = new JButton("��    ��");
	JPanel mainP = new JPanel();
	
	public static void main(String[] args){
		new IdFinderMain();
		
	}
	public IdFinderMain(){
		setSize(380,350);
		init();
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
	public void init(){
	
		
		emaillb = new JLabel("             ��  ��  �� :  ");
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
					 JOptionPane.showMessageDialog(null, "�̸����� �Է��� �ּ���.", "Warning", JOptionPane.WARNING_MESSAGE);
				 }
				 else if(idsel==1 && pwdsel==1){
					 idText=emailArea.getText();
					 JOptionPane.showMessageDialog(null, "���̵�� ["+ idText +" ] �̸�, ��й�ȣ�� �ʱ�ȭ �Ǿ����ϴ�.", "Sucess!", JOptionPane.INFORMATION_MESSAGE);
				 } else if(idsel==1){
					 idText=emailArea.getText();
					   JOptionPane.showMessageDialog(null, "���̵�� ["+ idText +" ]�Դϴ�.", "Sucess!", JOptionPane.INFORMATION_MESSAGE);
				 } else if(pwdsel==1){
				   JOptionPane.showMessageDialog(null, "��й�ȣ�� �ʱ�ȭ �Ǿ����ϴ�.", "Sucess!", JOptionPane.INFORMATION_MESSAGE);
				 } else {
				   JOptionPane.showMessageDialog(null, "�Ѱ��� �̻��� ������ �ּ���.", "Warning", JOptionPane.WARNING_MESSAGE);
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

if(comm.equals("Ȯ    ��") ==true){
	 if(emailArea.getText().equals("")){
		 JOptionPane.showMessageDialog(null, "�̸����� �Է��� �ּ���.", "Warning", JOptionPane.WARNING_MESSAGE);
	 }
	 else if(idsel==1 && pwdsel==1){
		 idText=emailArea.getText();
		 JOptionPane.showMessageDialog(null, "���̵�� ["+ idText +" ] �̸�, ��й�ȣ�� �ʱ�ȭ �Ǿ����ϴ�.", "Sucess!", JOptionPane.INFORMATION_MESSAGE);
	 } else if(idsel==1){
		 idText=emailArea.getText();
		   JOptionPane.showMessageDialog(null, "���̵�� ["+ idText +" ]�Դϴ�.", "Sucess!", JOptionPane.INFORMATION_MESSAGE);
	 } else if(pwdsel==1){
	   JOptionPane.showMessageDialog(null, "��й�ȣ�� �ʱ�ȭ �Ǿ����ϴ�.", "Sucess!", JOptionPane.INFORMATION_MESSAGE);
	 } else {
	   JOptionPane.showMessageDialog(null, "�Ѱ��� �̻��� ������ �ּ���.", "Warning", JOptionPane.WARNING_MESSAGE);
	 }
} else if (comm.equals("���Է�")){
	 emailArea.setText("");
	 
} else if(comm.equals("�ڷ�")){
	 
}


}
*/