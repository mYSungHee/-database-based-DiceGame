
public class Dice_Backup implements BasicDice {
	private int num;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	public Dice_Backup(){
		this.num = (int)(Math.random()*5+1);
	}

	public void reset(){
		this.num=(int)(Math.random()*5+1);
	}
}
