
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
	private Map<String, DataOutputStream> cMap = new HashMap<String, DataOutputStream>();	 //���߿���� Hash, �ش�ҽ��� ����ä�� �����ڷ�(https://opentutorials.org/course/1598/8001)���� ������.
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
    private static final String _id_ ="_ID_REQUEST_"; // ���̵� �ߺ�üũ
    private static final String _SAVE_="_ACCOUNT_SAVE_";//�������� �����û
    private static final String _edit_= "_EDIT_REQUEST_"; // ������������ ��û
    private static final String _info_ ="_USER_INFO_"; //�������� ��û
    private static final String _gStart_ ="_GAME_START_"; //���� ��ŸƮ ��ȣ����
    private static final String _gEND_ ="_GAME_START_"; //���� ���� ��ȣ����
    private static final String _REGI_ = "_ACCOUNT_REGISTER_"; //���� ����
    private static final String _LOGOUT_ = "_ACCOUNT_LOGOUT_"; //���� �α׾ƿ�
    private static final String _FINDER_ = "_ACCOUNT_FINDER_"; //���� ã�� or ����ʱ�ȭ
    
    private static final String info_="_USER_INFO_RESPONSE_"; //�������� ����
    private static final String id_="_ID_RESPONSE_";// ���̵� �ߺ�üũ ����
    private static final String SAVE_="_ACCOUNT_SAVE_RESPONES_"; // ������������ ��û������ ������������
    private static final String edit_= "_EDIT_RESPONSE_"; //�������� �������� 
    private static final String REGI_="ACCOUNT_REGI_RESPONSE"; // ���Կ�û ����
    private static final String FINDER_ = "_ACCOUNT_FINDER_RESP"; //���� ã�� or ����ʱ�ȭ
    private static final String _OK = "_SUCCESS_";
    private static final String _NO = "_FAILD_";
   
    public void gameStartRecevie(String msg){
    	String[] s = msg.split("#");
    	
    	mger.appendMsg(s[1]+"���� ������ �����Ͽ����ϴ�.");
    	
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
    		mger.appendMsg(dc.getId()+"���� ���� Success");
    	} else {
    		_msg=REGI_+"#"+_NO;
    		this.sendMsg(_msg);
    		mger.appendMsg(dc.getId()+"���� ���� Fail");
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
    	
    	sendMsg(s[1]+"���� ������ �����Ͽ����ϴ�");
    	
    }
    

    
    public void resp_Save(String msg){ //����
    	ac=acM.stringToAC(removeFirstString(msg));

    	boolean ck=sql.updateMember(ac);
    	String _msg="";
    	if(ck==true){
    		_msg=SAVE_+"#"+_OK;
    		this.sendMsg(_msg);
    		mger.appendMsg(ac.getId()+"���� ���� DB UPDATE Success");
    	} else {
    		_msg=SAVE_+"#"+_NO;
    		this.sendMsg(_msg);
    		mger.appendMsg(ac.getId()+"���� ���� DB UPDATE Fail");
    	}
    	
    }
 
    public void resp_id_(String msg){ //id �ߺ�üũ
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
    public void resp_finder_(String msg){ //IF & PWD ã��
    	String[] s = msg.split("#");
    	int checkCase=Integer.parseInt(s[2]);
    	System.out.println("��û����");
		String findID="";
    	String _msg="";
			
		if(checkCase ==1){//�Ƶ�ã�����
		findID=sql.idpwdFinder(s[1], checkCase-1);
		} else if(checkCase ==2) {//�Ѵ�ã�����
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
    	//�������� num�� ������� 1.�г��� / 2. ��й�ȣ /3. �̸����ּ�
    	//4. ���ھ�(���� ����ݿ�) 5.������ȣ�� ���س��´�.
    	
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
    	 
    	 System.out.println("���̵��û���亸��");
    }
    
	
	public final void setMger(Managements mger) {
			this.mger = mger;
		}
	
	 public boolean sOn() {
         Collections.synchronizedMap(cMap); // �������Ӷ� �������شٴ� Collections 
        	 try {
        		 
        		
        		 sSocket = new ServerSocket(port);
        		 mger.appendMsg("������ �����մϴ�. ��Ʈ��ȣ: "+port);
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
				 mger.appendMsg("������ �����մϴ�.");
				 
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
	    		msg = "["+id + "]���̵� �ߺ� �α��� �õ�!! ������ �����Ͽ����ϴ�.";
		        mger.appendMsg(msg);
		        this.hashremove(id);
		        return false;
	        }
				msg = ""+id + "���� �α��� �ϼ̽��ϴ�. [���� ������: "+cMap.size() +"��]";
		        mger.appendMsg(msg);
	        return true;
	   					
	   				} else{
	   					String msg = "["+id + "]���̵� �α��� �õ� ��й�ȣ ����.";
		        mger.appendMsg(msg);
		        cMap.put(id, out);
	   				}
	   				return false;
	    	
	    }
	    public void logout(String id) {
	        String msg = "["+id + "]���� �α׾ƿ� �ϼ̽��ϴ�.";
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
	    			 System.out.println(sc.getInetAddress() + "���� ��������.");
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
	                // ������������ ���� -> �α׾ƿ�ó��
	            	int x=1;
	            	logout(id,x);
	            	
	            }
	        }
	    }
	    
	     
}
	    