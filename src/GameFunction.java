import java.util.Scanner;
import java.util.Vector;

public class GameFunction {
	Vector<Dice> dset = null;
	
	public GameFunction(){
		dset=new Vector<Dice>();
	}
	public Dice[] randomDice(){
		Dice[] d=new Dice[5];
		for(int i=0; i<d.length; i++) d[i]=new Dice();
		d=orderDice(d);
		
		return d;
	}
	public Dice[] randomDice(Dice[] d,int x){
		d[x]=new Dice();
		d=orderDice(d);
		
		return d;
	}
	public Dice[] orderDice(Dice[] d){ 
		//주사위로 포커패를 계산할 방법을 많이 생각하다가.. 정렬이 필요할거같아서 산업기사 준비하면서 공부한 버블정렬을 이용하였습니다.
		int temp;
		for(int i=0; i < d.length-1; i++){ 
			for(int j=0;j < d.length-i-1;j++){
				if( d[j].getNum() > d[j+1].getNum()){// 인접한 두값비교(j번째, j+1번째)
					temp = d[j].getNum();
					d[j].setNum(d[j+1].getNum());
					d[j+1].setNum(temp);
				}
			}
		}
		return d;
	}
	
	public int diceResult(Dice[] d){
		/* 패 체크용 범위
		 * onepair 11~19
		 * twopair 41~99
		 * triple 111~199
		 * straight 201~1006
		 * flush 1000~1299
		 * fourth 20000~29999
		 * full 30000~
		 */
		int twopair=30;
		int triple=100;
		int straight=200;
		int flush=1000; //주사위는 문양이 없으므로 홀수 혹은 짝수로만 구성된경우 플러쉬로 
		int fourth=20000;
		int fullhouse=30000;
		int count=0;
		int color1=0; // 플러쉬 체크(짝수)  
		int color2=0; // 플러쉬 체크(홀수)
		int stck=0; // 스트레이트 체크
		int result=0;
		Vector<Integer> def= new Vector<Integer>();
		Vector<Integer> sam= new Vector<Integer>();
		for(int i=0; i<d.length-1; i++){
			if(d[i].getNum()==d[i+1].getNum()){
				sam.add(i+1); //같을경우 카운트
			}
			else{
				def.add(i+1); // 앞의 주사위 경우의 수와 뒤의 주사위 경우의수가 다를경우 그 위치가 기록됨.
			}
			if(d[i].getNum()+1 == d[i+1].getNum()) stck++; // 다음수와 연속된수일경우 스트레이트 체크를 1증가 
			
			if(d[i].getNum()==1 || d[i].getNum()==3 || d[i].getNum()==5){ color2++;}//홀수일경우 color2를 1 증가
			else if(d[i].getNum()==2 || d[i].getNum()==4 || d[i].getNum()==6) color1++; //짝수일경우 color1을  1증가
			
			
			if(stck==4) result+=straight; // stck가 5일경우 스트레이트
		}
		if(d[4].getNum()==1 || d[4].getNum()==3 || d[4].getNum()==5) color2++;
		else if(d[4].getNum()==2 || d[4].getNum() ==4 || d[4].getNum()==6) color1++;
		
		if(color1==5 || color2==5) result+=flush; // 5일경우 플러쉬
		
		if(sam.size()>=3 && def.size()<2){//같은경우가 3번 이상에 다른경우가 1번 이하인경우
			if(def.get(0)==2 || def.get(0)==3){ // 그중  2번째와 3번재에서 다른경우는 풀하우스임.
				if(def.get(0)==3){ //3번째에서 다른경우는 앞에있는것이 십의자리로( 333 44 이므로 34풀하우스)
					result += fullhouse+d[sam.get(0)].getNum()*10+d[sam.get(2)].getNum();
				}else{ //2번�에서 다른경우는 뒤에있는것을 십의자리로
					result += fullhouse+d[sam.get(2)].getNum()*10+d[sam.get(0)].getNum();
				}
			}else{//그외 경우는 전부  동일한숫자가나오거나, 하나만 다른경우 이므로 포카드임.
					result += fourth+d[2].getNum();
			}}
		else if(sam.size() ==2&& def.size()==2 ){ // 2개가 같은경우는 트리플 또는 투페어이다.
			for(int y=0; y<4;y++){
				if(sam.get(0)==y && sam.get(1)==y+1){
					result += triple+d[sam.get(0)].getNum();
					break;} // 두개가 연속으로 같은경우는 트리플임.
				}//위에서 안걸러진것은 투페어이다. def.get(서로다른수의 자리) ==1,3 / 2,3 / 2,4인경우 
			if(result <=100)
			result += twopair+d[sam.get(1)].getNum()*10+d[sam.get(0)].getNum();
			
		
		}else if(sam.size()==0){ result +=d[4].getNum();}else{ // 같은게 하나도없는 노페어
			result += d[sam.get(0)].getNum()+10; // 하나만같을경우
		}
		
		
		return result;
	}
	
	public String stringDice(int result){
		String str=null;
		
		if(result >=31000){
			str ="★★★★★ "+ Integer.toString(result-31000) +" Full House";
		}else if(result >=30000 && result < 31000){
			str ="★★★★★ "+ Integer.toString(result-30000) +" Full House";
		}else if(result >= 21000 && result < 30000){
			str ="★★★★ "+ Integer.toString(result-21000) +" Four of a kind";
		}else if(result >= 20000 && result < 21000){
			str ="★★★★ "+ Integer.toString(result-20000) +" Four of a kind";
		}else if(result >= 1000 && result < 1299 ){
			str ="★★★☆ Flush";
		}else if(result >= 200 && result <300 ){
			str ="★★☆☆ Stragiht ";
		}else if(result >= 101 && result<107){
			str ="★★☆ "+Integer.toString(result-100)+" Three of a kind";
		}else if(result >= 40 && result<100){
			str ="★☆ "+Integer.toString(result-30)+" two pairs";
		}else if(result >= 10 && result <20){
			str ="☆☆ "+Integer.toString(result-10)+" pair";
		}else if(result >0 && result <10){
			str ="☆" +Integer.toString(result)+" top";
		}
		return str;
	}
	
	public Dice[] changeDice(Dice[] d) {
		Scanner scc= new Scanner(System.in);
		int key=0; //while문 두개를 거치기위해 키워드용
		int ck=0; //중복체크용
		int[] lo=null; // 바꿀주사위를 입력받을 배열을 추가.
		while(key==0){
			int or=0;
			while(or==0){
		System.out.println("현재 주사위 [" + d[0].getNum() +", " + d[1].getNum() +", " + d[2].getNum() +", " + d[3].getNum() +", " + d[4].getNum() +"]");
		System.out.println("다시 던질 주사위 갯수를 입력하세요.");
		System.out.print("입력 :");
		int dnum= scc.nextInt();//주사위갯수를 입력받고
		if(dnum >=1 && dnum <6){
			lo=new int[dnum];// 갯수만큼 배열에 공간을 할당한다.
			for(int ii=0; ii<dnum; ii++){
			System.out.print(dnum +" 중 " +(ii+1)+"/ 변경할 주사위의 위치 입력[1~5] :");
			lo[ii]=scc.nextInt();
			}
			
			if(dnum>1)
			for(int i=0; i<dnum-1; i++){
				for(int j=i; j<dnum-1; j++){
					if(lo[j]==lo[j+1]) ck=1;
				}
			}
			or++;
		}else{
			System.out.println("다시 입력해주세요.");
		}
			}
		if(ck==1){
			System.out.println("중복이 존재합니다. 다시 진행합니다");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(ck==0){

			System.out.print("변경하는 주사위가 ");
			for(int s:lo){
				System.out.print("["+s+"]번째 ");
			}
			System.out.print("가 맞습니까?(y/n) :");
			String s= scc.next();
			if(s.equals("y") || s.equals("Y"))
			for(int i=0; i<lo.length; i++){
				d[lo[i]-1].reset();
				key++;
			}
			else System.out.println("다시 진행합니다.");
		}
		}
		return d;
	}
	public void diceResultPrint(Dice[] d)  {
		orderDice(d);
		for(int i=0; i<d.length; i++){
			System.out.println("["+(i+1)+"]번째 주사위 : "+d[i].getNum());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void stringDice(Vector<Player> pp, Dice[] d) {
		String str=null;
		int cc=0;
		for(int i=0; i<pp.size(); i++){
			if(pp.get(i).getPoint()==0 && cc==0){
				str="<<본인>>";
				cc++;
			}else if(pp.get(i).getPoint()==0 && cc>0){
				str="<순번대기>";
			}else{
				str =stringDice(pp.get(i).getPoint());
			}
				System.out.println("["+(i+1)+"]번 "+pp.get(i).getName()+" : " + str);
		
		}
	}	
	
	public void scoreCalc(Vector<Player> pp,Account myID,Player me,boolean winc,int myTurn,AccountManager acmger){
		// 등급은 4가지로, 초보,중수,고수,신 승리시 해당 계급 한명당 10점,20점,50점,150점을 부여
		// 패배시 자신의 포인트의 1%+자신의계급(0.2%,0.5%,1%,2%)만큼을 감점한다.
		// 0점 이하로는 내려가지 않도록 설정.
		double score=0;
		int gr=0;
		score=me.getScore();
		int scc=0;
		if(winc==true){ 
			//승리
			for(int i=0; i<pp.size(); i++){
				if(i != myTurn){
					score+=pp.get(i).getGr();
					scc +=(int)pp.get(i).getGr();
				}
			}
			System.out.println("<총 " +scc +"포인트를 획득>");
			me.setScore(score);
			
		}else{
			//패배
			int mygr = 0;
			
			score = score * (1-(0.1+mygr));
			scc +=score * (0.1+mygr);
			if(score <0) score=0; //0점 아래로 내려갈경우
			me.setScore(score);
			System.out.println("<총 " +scc +"포인트를 손실>");

			
		}
		System.out.println("<잔여 포인트: "+score+" >");
		myID.setScore(me.getScore());
		acmger.saveAcFile();
		
	}
	public boolean result(Vector<Player> pp, String name) { //게임의 승패여부를 판단하는 메소드
		Player[] pArray = new Player[pp.size()];
		Player temp;
		boolean winc=false;
		for(int i =0; i<pArray.length; i++){
		pArray[i]= new Player(pp.get(i));	
		}
		for(int i=0; i < pArray.length-1; i++){ 
			for(int j=0;j < pArray.length-i-1;j++){
				if( pArray[j].getPoint() < pArray[j+1].getPoint()){// 인접한 두값비교(j번째, j+1번째)
					temp = new Player(pArray[j]);
					pArray[j]=new Player(pArray[j+1]);
					pArray[j+1]=new Player(temp);
				}
			}
		}
		for(int i=0; i<pArray.length; i++){
			if(pArray[i].getName().equals(name)){
				if(i==0){
					System.out.println("[1]등 " +pArray[i].getName() +"  [[본인]]");

					winc=true;
				}else if(i>1){
					System.out.println("["+(i+1)+"]등 "+pArray[i].getName() +"  [[본인]]");	
					winc=false;
				}
			}else{
				System.out.println("["+(i+1)+"]등 "+pArray[i].getName());
			}
		}
		return winc;
					
	}
}
