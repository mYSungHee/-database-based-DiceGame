
public class Player {

	private int point;
	private String name;
	private double score;
	private int gr;

	String[] wordL1 = {"남모르게","세계최초","역사상 제일", "사실은", "이성친구는 없지만","빚은 1억이지만","알고보면 응가 많이 싸지만","미친듯이", "배운건 없지만","개념없이","인생은 망했지만","올 A+","모든걸 잘하는"};
	String[] wordL2 = {"멍청한","바보인","센스있는","동안","김은수 똥같은","더러운","똥안닦은","게임폐인","롤충","일베충","전교1등","잘생긴","못생긴","돼지","이쁜","아름다운","현명한","현자","야동쟁이","미래가 유망한","똥쟁이","친구없는","제입대각","ㅄ","죽기일보직전","미래가안보이는","답없는","입만산","야부리킹","오늘내일하는"};
	String[] wordL3 = {"김은수","최원찬","오혜민","정연준","윤성희","김글샘","이동엽","조윤재","오근재","정성래","김동욱","장명식","정경원"};
	int leng1 = wordL1.length;
	int leng2 = wordL2.length;
	int leng3 = wordL3.length;
	int r1=(int)(Math.random()*leng1);
	int r2=(int)(Math.random()*leng2);
	int r3=(int)(Math.random()*leng3);
	public Player(Account ac){
		String grade=null;
		 if(score>10000){
			 grade="<신>";
			 gr=150;
		 }else if (score>=5000){
			 grade="<고수>";
			 gr=50;
		 }else if (score>=1000){
			 grade="<중수>";
			 gr=20;
		 }else{
			 grade="<초보>";
			 gr=10;
		 }
	 this.score=ac.getScore();
	 this.name=grade+ac.getName();
	}
	public Player(int rank){
		String grade=null;
		 this.score=Math.random()*20000-rank*3000;
		 if(score>10000){
			 grade="<신>";
			 gr=150;
		 }else if (score>=5000){
			 grade="<고수>";
			 gr=50;
		 }else if (score>=1000){
			 grade="<중수>";
			 gr=20;
		 }else{
			 grade="<초보>";
			 gr=10;
		 }
		String phrase =grade+wordL1[r1]+ " " +wordL2[r2]+ " " + wordL3[r3];

		 this.name=phrase ;
		}
	public Player(Player p){
		 if(score>10000){
			 gr=150;
		 }else if (score>=5000){
			 gr=50;
		 }else if (score>=1000){
			 gr=20;
		 }else{
			 gr=10;
		 }
		 this.name=p.getName() ;
		 this.point=p.getPoint();
		 this.score=p.getScore();
		 
		}
	public int getGr() {
		return gr;
	}
	public void setGr(int gr) {
		this.gr = gr;
	}
	public int getPoint() {
		return point;
	}


	public void setPoint(int point) {
		this.point = point;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getScore() {
		return score;
	}


	public void setScore(double score) {
		this.score = score;
	}
}
