
public class AcManager {

	
	
	Account acc=new Account();
	
	
	public String acToString(Account ac){
//		0.코드
	//  1.아이디 / 
//    	2.비밀번호 / 
//    	3.이름 / 
//		4.닉네임 /
//    	5.성별 / 
//    	6.이메일 / 
//    	7.사진번호
//    	().벤여부
//		8.점수
		
		String info=ac.getCode()+"#";
		info+=ac.getId()+"#";
		info+=ac.getPassword()+"#";
		info+=ac.getName()+"#";
		info+=ac.getNick()+"#";
		info+=ac.getSex()+"#";
		info+=ac.getAddr()+"#";
		info+=ac.getImgNum()+"#";
		info+=ac.getBan()+"#";
		info+=ac.getScore();
		
		return info;
		
	}
	
	public Account stringToAC(String acInfo){
//		0.코드
	//  1.아이디 / 
//    	2.비밀번호 / 
//    	3.이름 / 
//		4.닉네임 /
//    	5.성별 / 
//    	6.이메일 / 
//    	7.사진번호
//    	().벤여부
//		8.점수
		String[] info=acInfo.split("#");
		acc.setCode(info[0]);
		acc.setId(info[1]);
		acc.setPassword(info[2]);
		acc.setName(info[3]);
		acc.setNick(info[4]);
		acc.setSex(info[5]);
		acc.setAddr(info[6]);
		acc.setImgNum(Integer.parseInt(info[7]));
		acc.setBan(Integer.parseInt(info[8]));
		acc.setScore(Integer.parseInt(info[9]));
		
		return acc;
	}
	
	public Account acCopy(Account ac){
		
		this.acc=ac;
		
		return ac;
	}
	
	public Account acinit(String code , String id,String name, String pwd , int score, String nick, int picNum, String email,int ban,String sex){
		acc.setAddr(email);
		acc.setName(name);
		acc.setImgNum(picNum);
		acc.setBan(ban);
		acc.setCode(code);
		acc.setNick(nick);
		acc.setScore(score);
		acc.setPassword(pwd);
		acc.setSex(sex);
	return acc;	
	}
}  

