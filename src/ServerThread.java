
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ServerThread extends Thread{
	private int port=9191;
	private ServerSocket sSocket;
	private Socket sc;
	private String msg;
	private Managements mger;
	private Thread myThread;
	private DataInputStream in;
    private DataOutputStream out;
    private Receiver receiver;
    private int list=0;
    private String _temp;
	private Map<String, DataOutputStream> cMap = new HashMap<String, DataOutputStream>();	 //다중연결용 Hash, 해당소스는 다중채팅 강의자료(https://opentutorials.org/course/1598/8001)에서 참고함.
	AcManager acM= new AcManager();
	Connection con = null;       
	SQLDatabase sql = new SQLDatabase();
    PreparedStatement ps = null; 
    {   
    try{
    	Class.forName("com.mysql.jdbc.Driver"); 
        con = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=dytc1234");
 
    }catch(Exception e){
    	e.printStackTrace();
        }
    }
	Account ac;
    private static final String _id_ ="_ID_REQUEST_"; // 아이디 중복체크
    private static final String _SAVE_="_ACCOUNT_SAVE_";//계정정보 저장요청
    private static final String _edit_= "_EDIT_REQUEST_"; // 계정정보수정 요청
    private static final String _info_ ="_USER_INFO_"; //유저정보 요청
    private static final String _gStart_ ="_GAME_START_"; //게임 스타트 신호보냄
    private static final String _gEND_ ="_GAME_START_"; //게임 종료 신호보냄
    private static final String _REGI_ = "_ACCOUNT_REGISTER_"; //계정 가입
    private static final String _LOGOUT_ = "_ACCOUNT_LOGOUT_"; //계정 로그아웃
    private static final String _FINDER_ = "_ACCOUNT_FINDER_"; //계정 찾기 or 비번초기화
    
    private static final String info_="_USER_INFO_RESPONSE_"; //유저정보 응답
    private static final String id_="_ID_RESPONSE_";// 아이디 중복체크 응답
    private static final String SAVE_="_ACCOUNT_SAVE_RESPONES_"; // 계정정보수정 요청에대한 성공여부응답
    private static final String edit_= "_EDIT_RESPONSE_"; //계정정보 수정응답 
    private static final String REGI_="ACCOUNT_REGI_RESPONSE"; // 가입요청 응답
    private static final String FINDER_ = "_ACCOUNT_FINDER_RESP"; //계정 찾기 or 비번초기화
    private static final String _OK = "_SUCCESS_";
    private static final String _NO = "_FAILD_";
   
    public void gameStartRecevie(String msg){
    	String[] s = msg.split("#");
    	
    	mger.appendMsg(s[1]+"님이 게임을 시작하였습니다.");
    	
    }
    
    public void resp_Register(String msg){
    	
    	String acinfo= removeFirstString(msg);
    	System.out.println(acinfo);
    	Account dc =acM.stringToAC(acinfo);
    	
    	
    	boolean ck=sql.insertMember(dc);;
    	String _msg="";
    	if(ck==true){
    		_msg=REGI_+"#"+_OK;
    		this.sendMsg(_msg);
    		mger.appendMsg(dc.getId()+"계정 생성 Success");
    	} else {
    		_msg=REGI_+"#"+_NO;
    		this.sendMsg(_msg);
    		mger.appendMsg(dc.getId()+"계정 생성 Fail");
    	}
    	
    }
    
    
    public String removeFirstString(String s){
	String[] temp =s.split("#");
		
		for(int i=1; i<temp.length;i++){
			if(i==1) _temp=temp[i]+"#";
			else if(i==temp.length) _temp+=temp[i];
			else _temp+=temp[i]+"#";
		}
		return _temp;
    }
    public void gameEndReceiver(String msg){
    	String[] s = msg.split("#");
    	//String msg = _gEND_+"#"+"id";
    	
    	sendMsg(s[1]+"님이 게임을 종료하였습니다");
    	
    }
    

    
    public void resp_Save(String msg){ //저장
    	ac=acM.stringToAC(removeFirstString(msg));

    	boolean ck=sql.updateMember(ac);
    	String _msg="";
    	if(ck==true){
    		_msg=SAVE_+"#"+_OK;
    		this.sendMsg(_msg);
    		mger.appendMsg(ac.getId()+"계정 정보 DB UPDATE Success");
    	} else {
    		_msg=SAVE_+"#"+_NO;
    		this.sendMsg(_msg);
    		mger.appendMsg(ac.getId()+"계정 정보 DB UPDATE Fail");
    	}
    	
    }
 
    public void resp_id_(String msg){ //id 중복체크
    	String[] s = msg.split("#");
		boolean ck=sql.getMemberSerch(s[1]);
		String _msg="";
    	if(ck==true){
    		_msg=id_+"#"+_OK;
    		this.sendMsg(_msg);
    	} else {
    		_msg=id_+"#"+_NO;
    		this.sendMsg(_msg);
    	}
		
    	System.out.println(_msg);
    }
    public void resp_finder_(String msg){ //IF & PWD 찾기
    	String[] s = msg.split("#");
    	int checkCase=Integer.parseInt(s[2]);
    	System.out.println("요청들어옴");
		String findID="";
    	String _msg="";
			
		if(checkCase ==1){//아디만찾을경우
		findID=sql.idpwdFinder(s[1], checkCase-1);
		} else if(checkCase ==2) {//둘다찾을경우
		findID=sql.idpwdFinder(s[1], checkCase-1);
		}
		
    	if(findID.equals("faild#")){
    		_msg=FINDER_+"#"+_NO;
    		this.sendMsg(_msg);
    	} else {
    		_msg=FINDER_+"#"+_OK+"#"+findID;
    		this.sendMsg(_msg);
    	}
		
    }
    public void resp_edit_(String msg){ 
    	//정보수정 num는 순서대로 1.닉네임 / 2. 비밀번호 /3. 이메일주소
    	//4. 스코어(게임 결과반영) 5.사진번호로 정해놓는다.
    	
    	String[] s=msg.split("#");
    	String st="";
    	System.out.println(s[1]);
    	System.out.println(s[3]);
    	System.out.println(s[2]);
    	boolean ck=sql._updateMemberPart(s[1], s[3], s[2]);
    	
    	if(ck==true){
    		st=edit_+"#"+_OK;
    		this.sendMsg(st);
    	} else {
    		st=edit_+"#"+_NO;
    		this.sendMsg(st);
    		
    	}
    	sendMsg(msg);
    	
    	
    }
   
    public void resp_info(String msg){
    	String[] s=msg.split("#");
    	System.out.println(msg);
    ac=sql.getMemberInfo(s[1]);
    
    String st =info_+"#"+acM.acToString(ac);
    this.sendMsg(st);
    	 
    	 System.out.println("아이디요청응답보냄");
    }
    
	
	public final void setMger(Managements mger) {
			this.mger = mger;
		}
	
	 public boolean sOn() {
         Collections.synchronizedMap(cMap); // 다중접속때 정리해준다는 Collections 
        	 try {
        		 
        		
        		 sSocket = new ServerSocket(port);
        		 mger.appendMsg("서버를 시작합니다. 포트번호: "+port);
        		 Thread startS = new ServerOn();
        		 startS.start();
        		 port=9191;
        		 return true;
        	 } catch (IOException e) {
        		 e.printStackTrace();

 				port++;
        		 
			} finally{
			}
        	 return false;
	 }
	
	 public void release(){
	      if(myThread != null){
	         myThread = null;
	      }
	      try{
	         if(out != null){
	            out.close();
	         }
	      }catch(IOException e){
	      }finally{
	         out = null;
	      }
	      try{
	         if(in != null){
	            in.close();
	         }
	      }catch(IOException e){
	      }finally{
	         in = null;
	      }
	      try{
	         if(sc != null){
	            sc.close();
	         }
	      }catch(IOException e){
	      }finally{
	         sc = null;
	      }	      
	      try {
			sSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	   }
	 
	 public void svOFF(ServerThread svThread) throws IOException {
		 if(svThread!=null)
		 try{
			 
			 svThread=null;
			 release();
		 } finally {
			 
			 if(svThread==null){
				 mger.appendMsg("서버를 종료합니다.");
				 
			 }
			 
		 }
	 }
	    public boolean login(String id,String pwd, DataOutputStream out) throws IOException {
	         ResultSet rs=null;
	   				String idck="";
	   				String pwck = "";
	   				try{
	   					String sql = "select * from usertable where id = '"+id+"';";
	   	    			
	   	    			ps = con.prepareStatement(sql);
	   	    			rs = ps.executeQuery();
	   	    			while(rs.next()){
	   	    				idck = rs.getString("id");
	   	    				pwck = rs.getString("password");
	   	    				
	   	    			}

	   	    			
	   	    			}catch (Exception ex){
	   					ex.printStackTrace();
	   				}
	   				if(idck.equals(id) && pwck.equals(pwd)){
	   					
	    	
	        boolean check=this.hashInputCheck(id, out);
	        if(check==false){
	    		msg = "["+id + "]아이디 중복 로그인 시도!! 접속을 차단하였습니다.";
		        mger.appendMsg(msg);
		        this.hashremove(id);
		        return false;
	        }
				msg = ""+id + "님이 로그인 하셨습니다. [현재 접속자: "+cMap.size() +"명]";
		        mger.appendMsg(msg);
	        return true;
	   					
	   				} else{
	   					String msg = "["+id + "]아이디 로그인 시도 비밀번호 오류.";
		        mger.appendMsg(msg);
		        cMap.put(id, out);
	   				}
	   				return false;
	    	
	    }
	    public void logout(String id) {
	        String msg = "["+id + "]님이 로그아웃 하셨습니다.";
	        mger.appendMsg(msg);
	        cMap.remove(id);
            list--;
	    }
	    public void logout(String id, int x) {
	        mger.appendMsg(msg);
	        cMap.remove(id);
	        list--;
	    }
	    public void hashremove(String id) {
	        cMap.remove(id);
	    }
	    public void sendMessage(String msg) {
	        Iterator<String> it = cMap.keySet().iterator();
	        String key = "";
	        while (it.hasNext()) {
	            key = it.next();
	            try {
	                cMap.get(key).writeUTF(msg);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    public boolean hashInputCheck(String id, DataOutputStream out) {
	        Iterator<String> it = cMap.keySet().iterator();
	        String key = "";
	        while (it.hasNext()) {
	            key = it.next();
	            	if(key.equals(id)){
	            		return false;
	            } 
	            	
	        }
	        cMap.put(id, out);
	        return true;
	    }
	    public void sendMsg(String msg2) {
	        try {
	            out.writeUTF(msg2);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    class ServerOn extends Thread {
	    	
	    	
	    	public void run(){ 
	    	try {
	    		while(true){
	    			 sc = sSocket.accept();
	    			 System.out.println(sc.getInetAddress() + "에서 소켓접속.");
	    			 receiver= new Receiver(sc);
	    			 receiver.start();
	    		}
	    	} catch (IOException e) {
	    	} // 
            
	    	
	    	}
	    	}
	    
	    class Receiver extends Thread {
	       
	        private String idpwd;
	        private String id_pwd[];
	        private String id;
	        public Receiver(Socket sc) throws IOException {
	            out = new DataOutputStream(sc.getOutputStream());
	            in = new DataInputStream(sc.getInputStream());
	            idpwd = in.readUTF();
	            
	            id_pwd=idpwd.split("#");
	            if(id_pwd[0].equals("_check_")==false){
	            boolean ck = login(id_pwd[0],id_pwd[1], out);
	            if(ck==true){
	            	
	    	        out.writeUTF("#ok");
	    	        
	            } else if ( ck== false){
	            	 out.writeUTF("#fail");
	            	 hashremove(id_pwd[0]);

	            }
	            
	            id=id_pwd[0];
	            } else if(idpwd.equals(_id_)) {
	            	
	            	
	            } else if(idpwd.equals(_FINDER_)) {
	            	
	            	
	            }
	        }
	  
	        public void run() {
	            try {
	            	
	                while (in != null) {
	                    msg = in.readUTF();
	                    String[] s=msg.split("#");
	                    if(s[0].equals(_id_)){
	                    	
	                    	resp_id_(msg);
	                    } else if(s[0].equals(_SAVE_)){
	                    	resp_Save(msg);

	                    } else if(s[0].equals(_FINDER_)){
	                    	resp_finder_(msg);

	                    } else if(s[0].equals(_edit_)){
	                    	resp_edit_(msg);

	                    } else if(s[0].equals(_gStart_)){
	                    	gameStartRecevie(msg);

	                    } else if(s[0].equals(_info_)){
	                    	 resp_info(msg);
	                    } else if(s[0].equals(_gEND_)){
	                    	gameEndReceiver(msg);
	                    } else if(s[0].equals(_REGI_)){
	                    	resp_Register(msg);
	                    } else if(s[0].equals(_LOGOUT_)){
	                    	
	                    	logout(id);
	                    }
	                }
	                
	            } catch (IOException e) {
	                // 사용접속종료시 에러 -> 로그아웃처리
	            	int x=1;
	            	logout(id,x);
	            	
	            }
	        }
	    }
	    
	     
}
	    