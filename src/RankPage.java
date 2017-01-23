import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import javax.swing.border.LineBorder;
public class RankPage extends JFrame{
	
	JPanel pmain=new JPanel(new GridLayout(1,1));
	JTable ranktable;
	private JPanel ptop;
	JLabel rankLabel = new JLabel("순위");
	private JButton okBt;

	public static void main(String[] args){
		
		new RankPage();
	}
	
	public RankPage(){
		

		init();


		setResizable(false);
		

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));


				//		pmain.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	
						 
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = super.getSize();
		int xpos = (int) (screen.getWidth()	 /2 - frm.getWidth() /2);
		int ypos = (int) (screen.getHeight() /2 - frm.getHeight() /2);
		this.setLocation(xpos, ypos);
								



	}
	public void init(){
		okBt = new JButton("이 전");
		okBt.setFont(new Font("나눔고딕코딩",Font.PLAIN,12));
		okBt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new WaitMain();
				dispose();
				
			}
			
		});
		
		ptop = new JPanel();
		ptop.setBackground(Color.DARK_GRAY);
		ptop.setBorder(new LineBorder(Color.BLACK, 4, true));
		
		
		tableInit();
		ptop.add(rankLabel);
		getContentPane().add(ptop,"North");
		getContentPane().add(pmain);
		getContentPane().add(okBt,"South");
		

	}
	
	public void tableInit(){

		//---------------------------------------------------------
		Object row[][] = { {"1","300","플래티넘","남","은수"},
                {"2","200","골드","여","오햄"},
                {"2","200","골드","여","오햄"},
                {"2","200","골드","여","오햄"},
                {"2","200","골드","여","오햄"},
                {"2","200","골드","여","오햄"},
                {"2","200","골드","여","오햄"},
                {"2","200","골드","여","오햄"},
                {"2","200","골드","여","오햄"},
                {"2","200","골드","여","오햄"},
                {"2","200","골드","여","오햄"},
                {"2","200","골드","여","오햄"},
                {"2","200","골드","여","오햄"},
                {"2","200","골드","여","오햄"},
                {"2","200","골드","여","오햄"},
                {"2","200","골드","여","오햄"},
                {"2","200","골드","여","오햄"},
                {"2","200","골드","여","오햄"},
                {"2","200","골드","여","오햄"}
		};
		Object column[] = {"순위","포인트","등급","성별","이름"};
		//------------------------------------------------------------ 순위부분. 데이터를받아와서 넣어야함.
		ranktable = new JTable(row,column);
		
		ranktable.setFont(new Font("나눔고딕코딩", Font.PLAIN, 11));
		//				pmain.add(ranktable);
		JScrollPane scrollPane = new JScrollPane(ranktable);
		pmain.add(scrollPane);
		scrollPane.setSize(400, 400);
		setSize(400,400);
		
		rankLabel.setEnabled(false);
		rankLabel.setBackground(Color.DARK_GRAY);
		rankLabel.setForeground(Color.YELLOW);
		rankLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rankLabel.setFont(new Font("나눔고딕코딩", Font.BOLD, 17));
		
		// DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
		DefaultTableCellRenderer tRender = new DefaultTableCellRenderer();
		
		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		tRender.setHorizontalAlignment(SwingConstants.CENTER);
		 
		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel tcm = ranktable.getColumnModel();
		 
		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (int i = 0; i < tcm.getColumnCount(); i++) {
		tcm.getColumn(i).setCellRenderer(tRender);
		}
	}
}
