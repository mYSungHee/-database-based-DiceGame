import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.io.*;

public class ImageRegi extends JFrame {
	String imgpath[] = new String[16];
	FileDialog fd;
	String imagefile;
	String directory;
	JPanel bottomP;
	JButton okBt;
	JButton exitBt;
	JPanel mp;
	int chkNum=0;
	ImageButton im[] = new ImageButton[16];
	JButton nextBt= new JButton("▶");
	JButton previousBt=new JButton("◀");
	
	private CardLayout cardly;
	
	public static void main(String[] args){
		
		new ImageRegi();
		
	}
	
	public ImageRegi(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,400);
		setResizable(false);
		
		cardly = new CardLayout();
		mp = new JPanel(cardly);
		JPanel panel_1= new JPanel(new GridLayout(2,4,1,1));
		JPanel panel_2= new JPanel(new GridLayout(2,4,1,1));
		
		mp.add(panel_1, "p1");
		mp.add(panel_2, "p2");
		

		nextBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cardly.next(mp);
			}
		});
		getContentPane().add(nextBt,"East");
		
		previousBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardly.previous(mp);
			}
		});
		getContentPane().add(previousBt,"West");
		
		for(int i=1; i<17; i++){
		imgpath[i-1]="img/"+i+".jpg"; 
		}

	
		cardly.show(mp, "p1");
		
		for(int i=0; i<16; i++){
		
		im[i] = new ImageButton();
		im[i].setImageIcon(new ImageIcon(imgpath[i]));
		im[i].setName(String.valueOf(i+1));
		im[i].doClick();
		im[i].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JButton bt = (ImageButton)e.getSource();
				
				for(int i=0; i<im.length; i++){
				im[i].setBorder(new LineBorder(Color.gray,1));
				
				}
				chkNum=Integer.parseInt(bt.getName());
				bt.setBorder(new TitledBorder(new LineBorder(Color.blue,5),"선택"));
				
			
			}
		});
		}
		for(int i=0; i<16; i++){
		if(i<8) panel_1.add(im[i]);
		else panel_2.add(im[i]);
		}
			
		JLabel manual = new JLabel(" * 사용하실 사진선택해 주세요.");
		getContentPane().add(manual,"North");
		manual.setFont(new Font("나눔고딕코딩", Font.PLAIN, 15));
		add(mp);
		okBt = new JButton("확인");
		exitBt = new JButton("취소");
		
		okBt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				//서버에 정보 보냄?
				
				System.out.println(chkNum);
				dispose();
			}
			
		});
		
		exitBt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				dispose();
				
			}
			
		});
		bottomP = new JPanel(new FlowLayout());
		getContentPane().add(bottomP, BorderLayout.SOUTH);
		bottomP.add(okBt);
		bottomP.add(exitBt);
		
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = super.getSize();
		int xpos = (int)(screen.getWidth() /2 - frm.getWidth() / 2);
		int ypos = (int)(screen.getHeight() /2 - frm.getHeight() /2 );
		setLocation(xpos,ypos);
		
		init();
		
		setVisible(true);
	}
	
	public void init(){
	

		
		
	}
}
