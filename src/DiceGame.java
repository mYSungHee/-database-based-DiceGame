import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.event.*;
import java.awt.Color;
import java.awt.FlowLayout;
public class DiceGame extends JFrame {
	private Vector<Integer[]> dSet= new Vector<Integer[]>();
	private ImageLabel imageLabel;
	private ImageButton imageButton1;
	private ImageButton imageButton2;
	private ImageButton imageButton3;
	private ImageButton imageButton4;
	private ImageButton rivalImage;
	JPanel rivalsPn;
	JPanel rival;

	
	DicePanel dicePanel;
	int ck;
	public DiceGame() {
		
		this.setTitle("실험");
		this.setSize(850,546);
		
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();  //게임정보 스크롤팬
		scrollPane.setViewportBorder(UIManager.getBorder("Tree.editorBorder"));
		scrollPane.setBounds(10, 11, 195, 351);
		getContentPane().add(scrollPane);
		
		JTextArea gameRecode = new JTextArea();
		gameRecode.setToolTipText("정보 기록창");
		scrollPane.setViewportView(gameRecode);
		
		JPanel myInfoPn = new JPanel();  //내정보 패널
		myInfoPn.setBackground(Color.BLACK);
		myInfoPn.setBorder(UIManager.getBorder("Tree.editorBorder"));
		myInfoPn.setBounds(12, 373, 193, 122);
		getContentPane().add(myInfoPn);
		myInfoPn.setLayout(null);
		
		JPanel imagePn = new JPanel(); //내 사진 패널
		imagePn.setBackground(Color.BLACK);
		imagePn.setBorder(UIManager.getBorder("Tree.editorBorder"));
		imagePn.setBounds(1, 1, 120, 122);
		myInfoPn.add(imagePn);
		imagePn.setLayout(null);

		imageLabel = new ImageLabel();  //
		imageLabel.setBackground(Color.BLACK);
		imageLabel.setImageIcon(new ImageIcon("defaultpf.png"));
		imageLabel.setBounds(0, 0, 120, 121);
		imageLabel.setPreferredSize(new Dimension(155, 95));
		imagePn.add(imageLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(122, 1, 69, 120);
		myInfoPn.add(panel);
		
		rivalsPn = new JPanel();
		rivalsPn.setBorder(UIManager.getBorder("Tree.editorBorder"));
		rivalsPn.setBounds(644, 9, 180, 486);
		getContentPane().add(rivalsPn);
		rivalsPn.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel buttonPn = new JPanel();
		buttonPn.setBorder(UIManager.getBorder("Tree.editorBorder"));
		buttonPn.setBounds(207, 373, 427, 124);
		getContentPane().add(buttonPn);
		buttonPn.setLayout(new GridLayout(2, 2, 1, 1));
		
		imageButton1 = new ImageButton();
		buttonPn.add(imageButton1);
		imageButton1.setImageIcon(new ImageIcon("dicebutton.jpg"));
				
		imageButton2 = new ImageButton();
		buttonPn.add(imageButton2);
		imageButton2.setImageIcon(new ImageIcon("dicebutton.jpg"));
		
		imageButton3 = new ImageButton();
		buttonPn.add(imageButton3);
		imageButton3.setImageIcon(new ImageIcon("dicebutton.jpg"));
		
		imageButton4 = new ImageButton();
		buttonPn.add(imageButton4);
		imageButton4.setImageIcon(new ImageIcon("dicebutton.jpg"));
		
		dicePanel = new DicePanel(dSet);
		dicePanel.setBounds(207, 1, 427, 361);
		getContentPane().add(dicePanel);
		dicePanel.setLayout(null);
		
		JButton btnNewButton = new JButton("@@@@@@");
		btnNewButton.setBackground(Color.MAGENTA);
		btnNewButton.setBounds(241, 251, 100, 99);
		dicePanel.add(btnNewButton);
		
		rivalsCount();
		
		JButton selection = new JButton("결정");
		selection.setBounds(238, 11, 55, 23);
		buttonPn.add(selection);
		selection.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent Se){

					//dicePanel.throwButton.setEnabled(false);
				for(int i=0; i<dSet.size(); i++){
					if(i==(dSet.size()-1))
					for(int j=0; j<5; j++)
					gameRecode.append(Integer.toString(dSet.get(i)[j]));
				}	gameRecode.append("\n");
				
			}
			
			
		});
		
		JButton throwButton = new JButton("던지기");
		buttonPn.add(throwButton);
		throwButton.setBounds(0,220, 200, 40);
		throwButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent Te){
				if(ck==0){
					dSet.removeAllElements();
				}
				repaint();

				ck++;
				
			}
		});
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args){
		new DiceGame();
		
	}
	
	public void rivalsCount(){
		
		for(int imageCount =1; imageCount<5; imageCount++){
			rival = new JPanel();
			rival.setBorder(UIManager.getBorder("Tree.editorBorder"));
			rivalsPn.add(rival);
			rival.setLayout(null);
			rivalImage = new ImageButton();
			int imageNum =(int) (Math.random()*16+1);
			rivalImage.setImageIcon(new ImageIcon("img\\"+imageNum+".jpg"));
			rival.add(rivalImage);
			rivalImage.setBounds(0, 0, 120, 122);
		}
		
		return ;
	}
}
