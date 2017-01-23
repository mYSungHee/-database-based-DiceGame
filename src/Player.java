
public class Player {

	private int point;
	private String name;
	private double score;
	private int gr;

	String[] wordL1 = {"���𸣰�","��������","����� ����", "�����", "�̼�ģ���� ������","���� 1��������","�˰��� ���� ���� ������","��ģ����", "���� ������","�������","�λ��� ��������","�� A+","���� ���ϴ�"};
	String[] wordL2 = {"��û��","�ٺ���","�����ִ�","����","������ �˰���","������","�˾ȴ���","��������","����","�Ϻ���","����1��","�߻���","������","����","�̻�","�Ƹ��ٿ�","������","����","�ߵ�����","�̷��� ������","������","ģ������","���Դ밢","��","�ױ��Ϻ�����","�̷����Ⱥ��̴�","�����","�Ը���","�ߺθ�ŷ","���ó����ϴ�"};
	String[] wordL3 = {"������","�ֿ���","������","������","������","��ۻ�","�̵���","������","������","������","�赿��","����","�����"};
	int leng1 = wordL1.length;
	int leng2 = wordL2.length;
	int leng3 = wordL3.length;
	int r1=(int)(Math.random()*leng1);
	int r2=(int)(Math.random()*leng2);
	int r3=(int)(Math.random()*leng3);
	public Player(Account ac){
		String grade=null;
		 if(score>10000){
			 grade="<��>";
			 gr=150;
		 }else if (score>=5000){
			 grade="<���>";
			 gr=50;
		 }else if (score>=1000){
			 grade="<�߼�>";
			 gr=20;
		 }else{
			 grade="<�ʺ�>";
			 gr=10;
		 }
	 this.score=ac.getScore();
	 this.name=grade+ac.getName();
	}
	public Player(int rank){
		String grade=null;
		 this.score=Math.random()*20000-rank*3000;
		 if(score>10000){
			 grade="<��>";
			 gr=150;
		 }else if (score>=5000){
			 grade="<���>";
			 gr=50;
		 }else if (score>=1000){
			 grade="<�߼�>";
			 gr=20;
		 }else{
			 grade="<�ʺ�>";
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
