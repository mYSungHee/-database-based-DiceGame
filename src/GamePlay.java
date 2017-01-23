import java.util.Scanner;
import java.util.Vector;

public class GamePlay {
	Scanner mSca = new Scanner(System.in);
	GameFunction gf= null;
	Vector<Player> pp;
	public GamePlay(){
	gf= new GameFunction();

	}
	public Player Play(Player p,Vector<Player> pp,int myTurn) {
		// ��ǻ�Ͱ� �ڽź��� ���� ������ �����ο��� ������� �ڽ��� �п� ������� ��޿����� �ٽ� ����.
		int sel=0;
		int rr=0;
		boolean low=false;
		while(sel==0){
		Dice[] d= new Dice[5];
		d=gf.randomDice();

		System.out.println("======="+ p.getName() + "�� ����=======");
		gf.diceResultPrint(d);
		System.out.println(gf.stringDice(rr=gf.diceResult(d)));
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setPoint(rr);
		low=lowCheck(pp,myTurn);
		if(rr<100 || low==true ){
			System.out.println("=!="+ p.getName() + "(��)�� �ѹ� �� �ֻ����� ������.");
			d=gf.randomDice();
			gf.diceResultPrint(d);
			System.out.println(gf.stringDice(rr=gf.diceResult(d)));
			p.setPoint(rr);
			if(myTurn>1) low=lowCheck(pp,myTurn);
		}
		if((rr<=1000 || low==true) && p.getScore() >=5000 ){
			System.out.println("=!!="+ p.getName() + "(��)�� �ѹ� �� �ֻ����� ������.");
			d=gf.randomDice();
			gf.diceResultPrint(d);
			System.out.println(gf.stringDice(rr=gf.diceResult(d)));
			p.setPoint(rr);
			low=lowCheck(pp,myTurn);
		}
		if((rr<=500 || low==true)&& p.getScore() >=10000 ){ 
			System.out.println("=!!!="+ p.getName() + "(��)�� �ѹ� �� �ֻ����� ������.");
			d=gf.randomDice();
			gf.diceResultPrint(d);
			System.out.println(gf.stringDice(rr=gf.diceResult(d)));
		}
		p.setPoint(rr);
		sel++;
		}
		return p;
	}
	public boolean lowCheck(Vector<Player> pp,int myTurn){
		boolean low=false;
		for(int i=0; i<myTurn;i++){
			if(i==myTurn){
				
			}else{
				if(pp.get(myTurn).getPoint() < pp.get(i).getPoint()) low=true; 
			}
		}
		return low;
	}
	public Player Play(Player p, Account myID){
		int sel=0;
		int rr=0;
		while(sel==0){
		Dice[] d= new Dice[5];
		d=gf.randomDice();
		System.out.println("=======���� ����=======");
		gf.diceResultPrint(d);
		System.out.println(gf.stringDice(rr=gf.diceResult(d)));
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int cho=0;
		while(cho==0){
		System.out.println("�Ϻ� �ֻ��� �ٽñ����� = 1 || ��� �ֻ��� �ٽ�  = 2 ");
		System.out.println("  Ȯ�� = 3  ||   ����  = 4 || �ٸ� �ο� �� Ȯ�� = 0 ");
		System.out.println("���� ��:" +gf.stringDice(rr=gf.diceResult(d)));
		cho = mSca.nextInt();
		if(cho==1){
			d=gf.changeDice(d);
			gf.diceResultPrint(d);
			System.out.println(gf.stringDice(rr=gf.diceResult(d)));
			p.setPoint(rr);
		}else if(cho==2){
			d=gf.randomDice();
			gf.diceResultPrint(d);
			System.out.println(gf.stringDice(rr=gf.diceResult(d)));
			p.setPoint(rr);
		}else if(cho==0){
			System.out.println("------------------------------");
			gf.stringDice(pp,d);
			System.out.println("------------------------------");
		}else if(cho==3){
			p.setPoint(rr);
		}else if(cho==4){
			p.setPoint(0);
			return p;
		}
		else{
			System.out.println("���� ����� �Է����ּ���.");
		}
		}
		sel++;
		p.setPoint(rr);
		}
		return p;
	}
	public void GameStart(Account myID,AccountManager acmger){
		int cc=0;
		Player p=null;
		Player myP=null;
		int sel=0;
		int turn=0;
		int rank=0;
		pp= new Vector<Player>();
		while(sel==0){
		while(cc==0){
			pp.removeAllElements();
		System.out.println("�÷����� �÷��̾��� ����� �Է��ϼ���.(2~6)");
		System.out.println("(�ο��� �������� ȹ���ϴ� ����Ʈ���� �þ�ϴ�.)");
		int playerNum=mSca.nextInt();
		if(playerNum>=2 && playerNum <7){
			System.out.println("������ ���ϴ���..");
			turn = (int)(Math.random()*playerNum);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if(myID.getScore()>10000){
				 rank=1;
			 }else if (myID.getScore()>=5000){
				 rank=2;
			 }else if (myID.getScore()>=1000){
				 rank=3;
			 }else{
				 rank=4;
			 }
			for(int i=0; i<playerNum;i++){
				if(i==turn){
					p = new Player(myID);
					pp.add(p);
				}else{
					p = new Player(rank);
					pp.add(p);
						
				}
				
			}
			System.out.println("���� ========= ");
			for(int i=0; i<pp.size();i++){
				System.out.println("["+(i+1)+"]" + pp.get(i).getName()+" ");
			}
		
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0; i<pp.size();i++){
				if(turn==i){ //���� �����̶��.
					myP=Play(pp.get(i),myID);
					pp.set(i,myP);
				}else{
					p=Play(pp.get(i),pp,i); // PC�� ��
					pp.set(i,p);
				}
			}
			cc++;
		}else {
			System.out.println("����� �� ���� �ƴմϴ�.");
		}}
		
		while(cc==1){
			System.out.println("*============================================*");
			boolean win=gf.result(pp,myP.getName());
			if(win==true){
				System.out.println("�¸�!!");
				cc++;
			}else{
				System.out.println("�й�Ф�");
				cc++;
			}
			gf.scoreCalc(pp, myID, myP, win, turn, acmger);
			cc++;
		}
		System.out.println("�ѹ���? (y/n)");
		String ss=mSca.next();
		if(ss.equals("y") || ss.equals("Y")){
			System.out.println("3���� ������ �ٽ� �����մϴ�.");
			
			cc=0;
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("3���� ������ �����մϴ�.");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sel=1;
		}
		
		}
	}
	
}
