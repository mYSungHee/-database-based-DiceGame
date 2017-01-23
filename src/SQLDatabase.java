import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;


public class SQLDatabase {
	 int r;
	 public Connection getConn(){
	        Connection con = null;
	       
	        try {
	            Class.forName("com.mysql.jdbc.Driver"); //1. 드라이버 로딩
	            con = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=dytc1234"); //2. 드라이버 연결
	           
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	       
	        return con;
	    }
	 
	 /*정보 추가*/
	public boolean insertMember(Account dto){ 
	       
        boolean ok = false;
       
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
       
        try{
           
            con = getConn();
            String sql = "insert into usertable(" +
                        "id,nick,password,name,imgNum,addr, sex, ban,score)"+
                        "values(?,?,?,?,?,?,?,?,?)";
           
            ps = con.prepareStatement(sql);
            ps.setString(1, dto.getId());
            ps.setString(2, dto.getNick());
            ps.setString(3, dto.getPassword());
            ps.setString(4, dto.getName());
            ps.setInt(5, dto.getImgNum());
            ps.setString(6, dto.getAddr());  
            ps.setString(7, dto.getSex());  
            ps.setInt(8, dto.getBan());
            ps.setInt(9, dto.getScore());
            
            r = ps.executeUpdate(); //실행 -> 저장
           if(r>0){
        	   ok=true;
           }
        }catch(Exception e){
            e.printStackTrace();
        }
       
        return ok;
    }//insertMmeber
	public boolean insertMember(AccountAdmin dto){ 
	
        boolean ok = false;
       
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
       
        try{
           
            con = getConn();
            String sql = "insert into admintable(" +
                        "id,password,name,sex)"+
                        "values(?,?,?,?)";
           
            ps = con.prepareStatement(sql);
            ps.setString(1, dto.getId());
            ps.setString(2, dto.getPassword());
            ps.setString(3, dto.getName());
            ps.setString(4, dto.getSex());  
            
           r = ps.executeUpdate(); //실행 -> 저장
           
        }catch(Exception e){
            e.printStackTrace();
        }
        
        if(r>0) ok=true;
        return ok;
    }//insertMmeber
   
   
    /*리스트 출력*/
    public Vector getAdminList(){
        
        Vector adminList = new Vector();  //Jtable에 값을 쉽게 넣는 방법 1. 2차원배열   2. Vector 에 vector추가
       
           
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
        ResultSet rs = null;         //결과
       
        try{
           
            con = getConn();
            String sql = "select * from admintable order by code asc";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
           
            while(rs.next()){
                String code = rs.getString("code");
                String id = rs.getString("id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
               
                Vector row = new Vector();
                row.add(code);
                row.add(id);
                row.add(name);
                row.add(sex);
               
                adminList.add(row);             
            }//while
        }catch(Exception e){
            e.printStackTrace();
        }
        return adminList;
    }//getMemberList()
    
    public Vector getUserList(){
        
        Vector userList = new Vector();  //Jtable에 값을 쉽게 넣는 방법 1. 2차원배열   2. Vector 에 vector추가
       
           
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
        ResultSet rs = null;         //결과
       
        try{
           
            con = getConn();
            String sql = "select * from usertable order by code asc";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
           
            while(rs.next()){
                String code = rs.getString("code");
                String id = rs.getString("id");
                String nick = rs.getString("nick");
          
                String name = rs.getString("name");
                int imgNum = rs.getInt("imgNum");
                String addr = rs.getString("addr");
                String sex = rs.getString("sex");
                int ban = rs.getInt("ban");
                int score = rs.getInt("score");
               
                Vector row = new Vector();
                row.add(code);
                row.add(id);
                row.add(nick);
                row.add(name);
                row.add(imgNum);
                row.add(addr);
                row.add(sex);
                row.add(ban);
                row.add(score);
               
                userList.add(row);             
            }//while
        }catch(Exception e){
            e.printStackTrace();
        }
        return userList;
    }//getMemberList()
    public Account getMemberInfo(String id){
        
    	Account dto = new Account();
       
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
        ResultSet rs = null;         //결과
       
        try {
           
            con = getConn();
            String sql = "select * from usertable where id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
           
            rs = ps.executeQuery();
           
            if(rs.next()){
                dto.setCode(rs.getString("code"));
                dto.setId(rs.getString("id"));
                dto.setPassword(rs.getString("password"));
                dto.setName(rs.getString("name"));
                dto.setBan(rs.getInt("ban"));
                dto.setImgNum(rs.getInt("imgNum"));
                dto.setNick(rs.getString("nick"));
                dto.setScore(rs.getInt("score"));
                dto.setSex(rs.getString("sex"));
                dto.setAddr(rs.getString("addr"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }      
       
        return dto;    
    }
   public Account getMemberInfoCode(int code){
        
    	Account dto = new Account();
       
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
        ResultSet rs = null;         //결과
       
        try {
           
            con = getConn();
            String sql = "select * from usertable where code=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, code);
           
            rs = ps.executeQuery();
           
            if(rs.next()){

                dto.setCode(rs.getString("code"));
                dto.setId(rs.getString("id"));
                dto.setPassword(rs.getString("password"));
                dto.setName(rs.getString("name"));
                dto.setBan(rs.getInt("ban"));
                dto.setImgNum(rs.getInt("imgNum"));
                dto.setNick(rs.getString("nick"));
                dto.setScore(rs.getInt("score"));
                dto.setSex(rs.getString("sex"));
                dto.setAddr(rs.getString("nick"));
  
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }      
       
        return dto;    
    }
    public boolean getMemberSerch(String id){ //중복검사 중복된아이디가있을경우 true 반환
        
       boolean ck=true;
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
        ResultSet rs = null;         //결과
       
        try {
           
            con = getConn();
            String sql = "select * from usertable where id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
           
            rs = ps.executeQuery();
           if(rs.next()){
            	if(rs.getString("id").equals(id))
               ck=false;
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }      
       return ck;
    }
    public String idpwdFinder(String addr, int i){
    	boolean ck=false;
    	Connection con=null;
    	String tossId="";
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	try{
    		con = getConn();
    		String sql = "select id from usertable where addr=?";
    		ps=con.prepareStatement(sql);
    		ps.setString(1, addr);
    		
    		rs=ps.executeQuery();
    		if(rs==null){
    			return "Faild#";
    		}
    		if(rs.next()){
    			tossId=rs.getString("id");
    			if(i==1){
    				_updateMemberPart("2", tossId, "000000");
    				
    			}
    		}
    		
    	} catch (Exception e){
    		return "Faild#";
    	}
    	
    	return tossId;
    }
    
    
    public AccountAdmin getMemberInfo(int code){
        
    	AccountAdmin dto = new AccountAdmin();
       
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
        ResultSet rs = null;         //결과
       
        try {
           
            con = getConn();
            String sql = "select * from usertable where id=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, code);
           
            rs = ps.executeQuery();
           
            if(rs.next()){
                dto.setCode(rs.getInt("code"));
                dto.setId(rs.getString("id"));
                dto.setPassword(rs.getString("password"));
                dto.setName(rs.getString("name"));
                dto.setSex(rs.getString("sex"));
  
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }      
       
        return dto;    
    }
    
    /*정보 수정*/
    public boolean updateMember(Account dto){
        boolean ok = false;
        Connection con = null;
        PreparedStatement ps = null;
        try{
           
            con = getConn();           
            String sql = "update usertable set id=?, nick=?, password=?, name=?, imgNum=?, addr=?, sex=?, ban=?, score=? where id=?";
           
            ps = con.prepareStatement(sql);
           
            ps.setString(1, dto.getId());
            ps.setString(2, dto.getNick());
            ps.setString(3, dto.getPassword());
            ps.setString(4, dto.getName());
            ps.setInt(5, dto.getImgNum());
            ps.setString(6, dto.getAddr());
            ps.setString(7, dto.getSex());
            ps.setInt(8, dto.getBan());
            ps.setInt(9, dto.getScore());
            ps.setString(10, dto.getId());
           
            r = ps.executeUpdate(); //실행 -> 수정
            						    // 1~n: 성공 , 0 : 실패
           
            if(r>0) ok = true; //수정이 성공되면 ok값을 true로 변경
           
        }catch(Exception e){
            e.printStackTrace();
        }
       
        return ok;
    }
    public boolean updateMember(AccountAdmin dto){
        boolean ok = false;
        Connection con = null;
        PreparedStatement ps = null;
        try{
           
            con = getConn();           
            String sql = "update usertable set id=?, password=?, name=?,sex=? where id=?";
           
            
            ps = con.prepareStatement(sql);
           
            ps.setString(1, dto.getId());
            ps.setString(2, dto.getPassword());
            ps.setString(3, dto.getName());
            ps.setString(4, dto.getSex());
            ps.setString(5, dto.getId());
           
            r = ps.executeUpdate(); //실행 -> 수정
            						    // 1~n: 성공 , 0 : 실패
           
            if(r>0) ok = true; //수정이 성공되면 ok값을 true로 변경
           
        }catch(Exception e){
            e.printStackTrace();
        }
       
        return ok;
    }
    public boolean _updateMemberPart(String num,String id,String content){
    	Account ac;
    	ac=getMemberInfo(id);
    	boolean ck = false;
    	if(num.equals("1")){
    	ac.setNick(content);	
    	} else if(num.equals("2")){
    	ac.setPassword(content);
    	} else if(num.equals("3")){
    	ac.setAddr(content);
    	} else if(num.equals("4")){
    	ac.setScore(Integer.parseInt(content));
    	} else if(num.equals("5")){
    		ac.setImgNum(Integer.parseInt(content));
    	}
    	
    	ck=this.updateMember(ac);
    	
    	return ck;
    }
}
