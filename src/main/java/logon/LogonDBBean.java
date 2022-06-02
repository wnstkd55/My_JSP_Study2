package logon;

import java.sql.SQLException;

import common.DBConnPool;
import work.crypt.SHA256;
import work.crypt.BCrypt;

public class LogonDBBean extends DBConnPool{
	
	//DAO : 실제 DB를 Select , Insert, delete, update 
	
	
	//LogonDBBean 전역 객체 생성 <-- 한개의 객체만 생성 해서 공유 (싱글톤 패턴) 
		//외부의 다른 클래스에서 new 키를 사용하면 여러개의 객체를 생성할 수 있다. 
		//특정 클래스는 여러개의 객체를 생성하지 못하도록 하고 단 하나의 객체만 생성해서 사용해야 될 경우 
			//(회사)
	
	
		//싱글톤 패턴 : 외부에서 여러개의 객체를 생성하지 못하고 하나의 객체만 공유 해서 사용하도록 함. 
			//0. 객체 생성자는 private 셋팅
			//1. private static 으로 객체 생성, 2. 생성된 객체를 메소로 객체를 전달  
	private static LogonDBBean instance = new LogonDBBean();
    
    //LogonDBBean 객체를 리턴하는 메소드
    	//메소드를 사용해서만 객체를 생성할 수 있다.
    public static LogonDBBean getInstance() {
        return instance;
    }
    
  //기본 생성자 : private   : 외부에서 객체 생성이 불가능 함. 
  		// 부모 클래스의 기본 생성자 호출 
  	private LogonDBBean () { super(); };
  	
    
 
    //회원가입 처리 (registerPro.jsp)에서 넘어오는 값을 DB에 저장(insert)
    public void insertMember(LogonDataBean member){
        
//        SHA256 sha = SHA256.getInsatnce();
        try {
        	//Form에서 넘겨받은 Passwd를 DB에 저장할떄 암호화
        	
            	//orgPass : Form에서 넘겨받은 password
            String orgPass = member.getPasswd();
//            String shaPass = sha.getSha256(orgPass.getBytes());		//hash 패스워드
//        	String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());
        		//bcPass : 암호화된 암호
        	
        	System.out.println("암호화 되지않은 패스워드: "+orgPass);
//        	System.out.println("암호화된 패스워드 : "+bcPass);
        	
        	String sql = "insert into member01 values(?,?,?,?,?,?)";
        	
            psmt = con.prepareStatement(sql);
            psmt.setString(1, member.getId());
            //psmt.setString(2, bcPass);						//암호회된 password저장
            psmt.setString(2, member.getPasswd());				//암호화 되지 않는 패스워드 저장
            psmt.setString(3, member.getName());
            psmt.setTimestamp(4, member.getReg_date());
            psmt.setString(5, member.getAddress());
            psmt.setString(6, member.getTel());		
            psmt.executeUpdate();
            
            System.out.println(member.getId());
			System.out.println(member.getPasswd());
			System.out.println(member.getName());
			System.out.println(member.getReg_date());
			System.out.println(member.getAddress());
			System.out.println(member.getTel());
			System.out.println(sql);
            
            
            System.out.println("회원정보 DB 입력 성공");
            
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("회원정보 DB입력시 예외 발생");
        } finally {
//           instance.close();
        	close();
        }
    }
 
	//로그인 처리(loginPro.jsp) : 폼에서 넘겨받은 ID와 Passwd를 DB에서 확인
    //사용자 인증 처리, DB에 정보를 수정할때 , DB의 정보를 삭제할때. 
    // 사용자 인증(MemerCheck.jsp)에서 사용하는 메소드
    public int userCheck(String id, String passwd){
		int x = -1;		// x = -1	: ID가 존재하지 않는다.
						// x = 0 : ID는 존재하지만 PW가 일치하지 않을때
						// x = 1	: 인증성공, 
        
		//복호화 : 암호화된 passwd를 암호를 해독된 password로 변환
//		SHA256 sha = SHA256.getInsatnce();
		try {
            
            String orgPass = passwd;
//            String shaPass = sha.getSha256(orgPass.getBytes());
        	
            String sql = ("select passwd from member01 where id = ?");
            
            psmt = con.prepareStatement(sql);
            psmt.setString(1, id);		
            rs= psmt.executeQuery();	

			if(rs.next()){		// 아이디가 존재하면
				String dbpasswd= rs.getString("passwd"); 	//DB에서 가져온 패스워드
				if(orgPass.equals(dbpasswd)) {
//				if(BCrypt.checkpw(shaPass,dbpasswd)) {
//					x= 1; //폼에서 넘겨온 패스워드와 DB에서 가져온 패스워드가 일치할때 => 1
//				}
					x = 1;
				}else {
					x= 0;	//패스워드가 일치하지 않을때
				}
				
			}else {
					x=-1;
			}
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("아이디와 패스워드 인증 실패했습니다.");
        } finally {
			//instance.close();		//객체 사용 종료. rs, stmt, psmt 사용 종료
        }
		return x;
	}
    
    //ID 중복체크 (confirmId.jsp) : ID 중복을 확인하는 메소드
	public int confirmId(String id) {
		int x=-1;		// x = -1 일때 같은 아이디가 DB에 존재하지 않는다.
						// x = 1 일때 같은 아이디가 DB에 존재한다.(중복)
        
		try {
            String sql = "select id from member01 where id = ?";
			
            psmt = con.prepareStatement(sql);
            
            System.out.println(sql);
            psmt.setString(1, id);
            rs= psmt.executeQuery();

			if(rs.next()) {	//ID가 DB에 존재하는 경우.
				System.out.println(id+" 은 DB에 존재하는 ID입니다.");
				x= 1; 
			}else {	//ID가 DB에 존재하지 않는 경우
				x= -1;
				System.out.println(id+"는 DB에 존재하지않는 ID 입니다.");
			}
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("ID 중복체크중 예외발생");
        } finally {
			//instance.close();
        }
		return x;
	}

	//회원 수정폼(modifyPro.jsp)DB에서 회원정보의 값을 가져오는 메소드
    public LogonDataBean getMember(String id, String passwd){
        LogonDataBean member = null;
        
        
//        SHA256 sha = SHA256.getInsatnce();
        try {
            
            String orgPass = passwd;
//            String shaPass = sha.getSha256(orgPass.getBytes());
            String sql = "select * from member01 where id = ?";
            
            psmt = con.prepareStatement(sql);
            psmt.setString(1, id);
            rs = psmt.executeQuery();
     
            if (rs.next()) {	//해당 ID가 존재한다.
            	String dbpasswd= rs.getString("passwd");
            		//db의 password를 dbpasswd에 담는다.
//				if(BCrypt.checkpw(shaPass,dbpasswd)){		//폼에서 넘어온 passwd 와 db에서 넘어온 passwd가 같을때
						//DB에서 select 레코드르 DTO(LogonDataBean)에 Setter 주입해서 값을 반환
				if(orgPass.equals(dbpasswd)) {	
				  //member객체 생성후 DB의 rs에 저장된 값을 setter 주입 후 리턴
                  member = new LogonDataBean();		
                  member.setId(rs.getString("id"));
				  member.setName(rs.getString("name"));
                  member.setReg_date(rs.getTimestamp("reg_date"));
                  member.setAddress(rs.getString("address"));
                  member.setTel(rs.getString("tel"));
				}else {		//폼에서 넘어온 passwd 와 db에서 넘어온 passwd가 다를때
					
				}
			}
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("회원정보 읽어오는중 예외 발생");
        } finally {
            //instance.close();
        }
		return member; //member(LogonDataBean) : DTO에 Setter 주입 후
    }
    
    // 수정 페이지에서 수정한 내용을 DB에 저장하는 메소드()
    //회원 정보 수정 처리(modifyPro.jsp)에서 회원정보를 수정을 처리하는 메소드
    public int updateMember(LogonDataBean member){
        int x=-1;	//x = -1 : update 실패시
        			// x = +1 : update 성공
        
        //ID와 Passwd를 확인후 업데이트 진행.
        
//        SHA256 sha = SHA256.getInsatnce(); //객체 활성화
        
        try {
            
            String orgPass = member.getPasswd();
//            String shaPass = sha.getSha256(orgPass.getBytes());
            String sql = "select passwd from member01 where id = ?";
            
            psmt = con.prepareStatement(sql);
            psmt.setString(1, member.getId());
            rs = psmt.executeQuery();
                
            if(rs.next()){	//해당 id가 DB에 존재할때
            	//폼에서 넘긴 패스워드와 DB에서 가져온 패스워드가 일치하는지 확인 후 처리
				String dbpasswd= rs.getString("passwd"); 
//				if(BCrypt.checkpw(shaPass,dbpasswd)){	//두 패스워드가 일치할때
					//DTO(member)에서 들어온 값을 DB에 Insert
				if(orgPass.equals(dbpasswd)) {	
					String sql1 = "update member01 set name=?,address=?,tel=? where id=?";
                    psmt = con.prepareStatement(sql1);
                    psmt.setString(1, member.getName());
                    psmt.setString(2, member.getAddress());
                    psmt.setString(3, member.getTel());
                    psmt.setString(4, member.getId());
                    psmt.executeUpdate();
                    x= 1;	//update 성공시 x 변수에 1을 할당.
				}else
					x= 0;	//update 실패시 x 변수에 0을 할당.
			}else {
				x= -1;
			}
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("회원정보 수정시 예외 발생");
        } finally {
        	//instance.close();
        }
        return x;
    }
    
    //회원 탈퇴 처리(deletePro.jsp)에서 회원정보 삭제 메소드
    public int deleteMember(String id, String passwd){
        int x=-1;	// x = -1 : 회원 탈퇴 실패
        			// x = 1 : 회원 탈퇴 성공
        
        SHA256 sha = SHA256.getInsatnce();
        try {
			
			String orgPass = passwd;
//            String shaPass = sha.getSha256(orgPass.getBytes());
            String sql = "select passwd from member01 where id = ?";

            psmt = con.prepareStatement(sql);
            psmt.setString(1, id);
            rs = psmt.executeQuery();
            
			if(rs.next()){		//id가 DB에 존재
				String dbpasswd= rs.getString("passwd"); 
//				if(BCrypt.checkpw(shaPass,dbpasswd)){		//password가 일치하는 경우 : delete
				
				if(orgPass.equals(dbpasswd)) {
					String sql1 = "delete from member01 where id = ?";
					psmt = con.prepareStatement(sql1);
                    psmt.setString(1, id);
                    psmt.executeUpdate();
					x= 1;	//
				}else
					x= 0;	//
			}else {
				x = -1;
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
           //instance.close();
        }
		return x;
    }
}