import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class Managements extends MyFrame implements ActionListener{
	JButton edit = new JButton("����");
	JButton delete = new JButton("����");
	JButton cancel = new JButton("����");
	JButton cancel2;
	JButton clearBt= new JButton("�α������");
	JTable usertable;
	JTable admintable;
	JPanel pbtn;
	JButton renewBt=new JButton("���ΰ�ħ");
	JButton renewBt_=new JButton("���ΰ�ħ");
	DefaultTableModel modelUser;
	DefaultTableModel modelAdmin;
	JScrollPane scrollPaneUser;
	JScrollPane scrollPaneAdmin;
	Vector getDataUser;
	Vector getDataAdmin;
	Vector columnAdmin;
	Vector columnUser;
	AccountEdit acEdit;
	Managements myFrame;
	Container c;
	SQLDatabase sql = new SQLDatabase();
	JTabbedPane tp = new JTabbedPane(JTabbedPane.TOP);
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JButton delete2 = new JButton("����");
	private final JButton edit2 = new JButton("����");
	private final JPanel panel_2 = new JPanel();
	private final JTextField jtf = new JTextField();
	private final JTextArea jta = new JTextArea();
	private final JScrollPane spServer = new JScrollPane(jta);
	private ServerThread svThread = new ServerThread(); 
	private final JPanel panel_3 = new JPanel();
	private final JButton onBt = new JButton("���� ON");
	private final JButton offBt = new JButton("���� OFF");
	public Managements(ServerThread s,String t,boolean b1,boolean b2, int tap){
		this();
		svThread=s;
		jta.setText(t);
		
		onBt.setEnabled(b1);
		offBt.setEnabled(b2);
		tp.setSelectedIndex(tap);
	}
	
	public Managements(){
		c=getContentPane();
		jtf.setColumns(10);
		setTitle("��������");
		setSize(642, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = super.getSize();
		int xpos = (int)(screen.getWidth() /2 - frm.getWidth()/2);
		int ypos = (int)(screen.getHeight() /2 - frm.getHeight()/2);
		setLocation(xpos,ypos);
	
		
		getDataAdmin = sql.getAdminList();
		getDataUser = sql.getUserList();
		columnUser = getColumnUser();
		columnAdmin = getColumnAdmin();
		modelUser = new DefaultTableModel(getDataUser, columnUser);
		modelAdmin = new DefaultTableModel(getDataAdmin, columnAdmin);
		

		
		
		tp.setBounds(0, 0, 614, 261);
		getContentPane().add(tp);
		
		tp.addTab("Admin", null, panel, null);
		panel.setLayout(null);
		admintable = new JTable(modelAdmin);
		scrollPaneAdmin = new JScrollPane(admintable);
		scrollPaneAdmin.setLocation(0, 0);
		scrollPaneAdmin.setSize(609,197);
		panel.add(scrollPaneAdmin);
		delete.setBounds(0, 209, 68, 23);
		panel.add(delete);
		edit.setBounds(68, 209, 68, 23);
		panel.add(edit);
		cancel.setBounds(136, 209, 68, 23);
		panel.add(cancel);
		panel.add(renewBt_);
		renewBt_.setBounds(204, 209, 81, 23);
		
		renewBt_.addActionListener(this);
		cancel.addActionListener(this);
		edit.addActionListener(this);
		delete.addActionListener(this);
		
		tp.addTab("User", null, panel_1, null);
		panel_1.setLayout(null);
		usertable = new JTable(modelUser);
		scrollPaneUser = new JScrollPane(usertable);
		scrollPaneUser.setBounds(0, 0, 609, 197);
		panel_1.add(scrollPaneUser);
		edit2.setBounds(68, 209, 68, 23);
		edit2.addActionListener(this);
		
		panel_1.add(edit2);
		delete2.setBounds(0, 209, 68, 23);
		delete2.addActionListener(this);
		panel_1.add(delete2);
		renewBt.setBounds(204, 209, 81, 23);
		panel_1.add(renewBt);
		
		cancel2= new JButton("����");
		cancel2.setBounds(136, 209, 68, 23);
		panel_1.add(cancel2);
		
		tp.addTab("Sever", null, panel_2, null);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		panel_2.add(jtf,"South");
		jtf.addActionListener(this);
		
		panel_2.add(spServer, BorderLayout.CENTER);
		
		panel_2.add(panel_3, BorderLayout.NORTH);
		
		panel_3.add(onBt);
		panel_3.add(offBt);
		
		cancel2.addActionListener(this);
		onBt.addActionListener(this);
		offBt.addActionListener(this);

		offBt.setEnabled(false);
		renewBt.addActionListener(this);
		
		panel_3.add(clearBt);
		clearBt.addActionListener(this);
		// ���̺� �������
		// DefaultTableCellHeaderRenderer ���� (��� ������ ����)
		DefaultTableCellRenderer tRender = new DefaultTableCellRenderer();
		
		// DefaultTableCellHeaderRenderer�� ������ ��� ���ķ� ����
		tRender.setHorizontalAlignment(SwingConstants.CENTER);
		 
		// ������ ���̺��� ColumnModel�� ������
		TableColumnModel tcm = usertable.getColumnModel();
		 
		// �ݺ����� �̿��Ͽ� ���̺��� ��� ���ķ� ����
		for (int i = 0; i < tcm.getColumnCount(); i++) {
		tcm.getColumn(i).setCellRenderer(tRender);
		}
		
		tcm = admintable.getColumnModel();
		 
		// �ݺ����� �̿��Ͽ� ���̺��� ��� ���ķ� ����
		for (int i = 0; i < tcm.getColumnCount(); i++) {
		tcm.getColumn(i).setCellRenderer(tRender);
		}
	        
		
		setVisible(true);
		setDefaultCloseOperation(MyFrame.EXIT_ON_CLOSE);
	}
	
	 public Vector getColumnUser(){
	        Vector col = new Vector();
	        col.add("������ȣ");
	        col.add("ID");
	        col.add("�г���");
	        col.add("�̸�");
	        col.add("ImgNum");
	        col.add("E-Mail");
	        col.add("����");
	        col.add("block");
	        col.add("����");

	        return col;
	    }//getColumn
	 public Vector getColumnAdmin(){
	        Vector col = new Vector();
	        col.add("������ȣ");
	        col.add("ID");
	        col.add("�̸�");
	        col.add("����");

	        return col;
	    }//getColumn
	
	    public void appendMsg(String msg) {
	        jta.append(msg+"\n");
	        jta.requestFocus();
	    }
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == edit2){
			this.acEdit=new AccountEdit();
			
		}
		else if(e.getSource() == delete2){
			new AccountDelete();
			
		}
		else if(e.getSource() == cancel || e.getSource() == cancel2){
			if(onBt.isEnabled()==false){
			try {
				svThread.svOFF(svThread);
				onBt.setEnabled(true);
				offBt.setEnabled(false);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				appendMsg("���� ���ῡ �����Ͽ����ϴ�.");
			}
			}
			dispose();
			new Admins();
			
		} else if(e.getSource() == delete){
			new AdminDelete();
		} else if(e.getSource() == edit){
			new AdminEdit();
			
		} else if(e.getSource() == jtf){
			
			jta.append("[����] "+jtf.getText()+"\n");
			jtf.setText("");
			
		} else if(e.getSource() == onBt){
			
			svThread.setMger(this);
			while(true){
				boolean su_ck=svThread.sOn();
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(su_ck==true){
					appendMsg("Success ����� ���Ӵ����...");
					onBt.setEnabled(false);
					offBt.setEnabled(true);	
					return;
				}else {
					appendMsg("Fail! �ٽ� ��Ʈ��ȣ ������ ��õ��մϴ�.");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		}else if(e.getSource() == offBt ){
			
			try {
				svThread.svOFF(svThread);
				onBt.setEnabled(true);
				offBt.setEnabled(false);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				appendMsg("���� ���ῡ �����Ͽ����ϴ�.");
			}
			
		}else if(e.getSource() == clearBt){
			jta.setText("");
			
		} else if(e.getSource()== renewBt || e.getSource()==renewBt_){
			if(onBt.isEnabled()==false){

				String t=jta.getText();
				boolean b1=onBt.isEnabled();
				boolean b2=offBt.isEnabled();
				int tap =tp.getSelectedIndex();
			new Managements(svThread,t,b1,b2,tap);
			dispose();
		} else {
			String t=jta.getText();
			boolean b1=onBt.isEnabled();
			boolean b2=offBt.isEnabled();
			int tap =tp.getSelectedIndex();
			new Managements(svThread,t,b1,b2,tap);
			dispose();
			
		}
		
	}
}
}
