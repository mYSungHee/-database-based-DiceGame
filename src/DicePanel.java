

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
class Dice {
int locx;
int locy;
int colx;
int coly;
 
int colz;
int num;
 
public Dice(int x, int y, int cx, int cy, int cz, int n) {
locx = x;
locy = y;
colx = cx;
coly = cy;
colz = cz;
num = n;
}
 
public int getNum() {
	return num;
}

public void setNum(int num) {
	this.num = num;
}

public void paintComponent(Graphics g) {
Color c = new Color(this.colx, this.coly, this.colz);
g.setColor(c);
g.fillRect(locx, locy, 100, 100);
g.setColor(Color.BLACK);
g.drawRect(locx, locy, 100, 100);
if (this.num == 1)
g.fillOval(locx + 45, locy + 45, 10, 10);
else if (this.num == 2) {
g.fillOval(locx + 20, locy + 20, 10, 10);
g.fillOval(locx + 70, locy + 70, 10, 10);
} else if (this.num == 3) {
g.fillOval(locx + 20, locy + 20, 10, 10);
g.fillOval(locx + 45, locy + 45, 10, 10);
g.fillOval(locx + 70, locy + 70, 10, 10);
} else if (this.num == 4) {
g.fillOval(locx + 20, locy + 20, 10, 10);
g.fillOval(locx + 20, locy + 70, 10, 10);
g.fillOval(locx + 70, locy + 20, 10, 10);
g.fillOval(locx + 70, locy + 70, 10, 10);
} else if (this.num == 5) {
g.fillOval(locx + 20, locy + 20, 10, 10);
g.fillOval(locx + 20, locy + 70, 10, 10);
g.fillOval(locx + 70, locy + 20, 10, 10);
g.fillOval(locx + 70, locy + 70, 10, 10);
g.fillOval(locx + 45, locy + 45, 10, 10);
} else {
g.fillOval(locx + 20, locy + 20, 10, 10);
g.fillOval(locx + 20, locy + 70, 10, 10);
g.fillOval(locx + 70, locy + 20, 10, 10);
g.fillOval(locx + 70, locy + 70, 10, 10);
g.fillOval(locx + 20, locy + 45, 10, 10);
g.fillOval(locx + 70, locy + 45, 10, 10);
}
}
}
 
public class DicePanel extends JPanel {
	Vector<Integer[]> dSet;
	Integer dNum[];
	int ck=0;
	JButton throwButton;
	public DicePanel(Vector<Integer[]> dSet){
		this.dSet = dSet;
	}
public void paint(Graphics g) {
	dNum= new Integer[5];
	Graphics graph = (Graphics) g;
paintComponent(graph);
Dice d[] = new Dice[5];

if(ck>0){
d[0] = new Dice(110, 5, 255, 255, 255, (int) (Math.random() * 6) + 1);
d[1] = new Dice(5, 108, 255, 255, 255, (int) (Math.random() * 6) + 1);
d[2] = new Dice(317, 108, 255, 255, 255, (int) (Math.random() * 6) + 1);
d[3] = new Dice(50, 220, 255, 255, 255, (int) (Math.random() * 6) + 1);
d[4] = new Dice(241, 251, 255, 255, 255, (int) (Math.random() * 6) + 1);
} else if(ck==0){
	d[0] = new Dice(110, 5, 255, 255, 255, (int) 1);
	d[1] = new Dice(5, 108, 255, 255, 255, (int) 1);
	d[2] = new Dice(220, 108, 255, 255, 255,(int) 1);
	d[3] = new Dice(50, 220, 255, 255, 255, (int) 1);
	d[4] = new Dice(170, 220, 255, 255, 255, (int) 1);
	ck++;
}

for(int i=0; i<5;i++){
	d[i].paintComponent(graph);
	dNum[i]=(Integer)d[i].getNum();
}
dSet.add(dNum);	
}
//public void actionPerformed(ActionEvent e) {
//	if(ck==0){
//		dSet.removeAllElements();
//	}
//	repaint();
//
//
//	
//}
}
// 
//public class DiceThrow extends JFrame {
//public DiceThrow() {
//this.add(new MyPanel());
//setSize(500, 500);
//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//setTitle("ÁÖ»çÀ§");
//setVisible(true);
//}
// 
//}