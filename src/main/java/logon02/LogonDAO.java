package logon02;


import java.text.SimpleDateFormat;

import common.DBConnPool;



public class LogonDAO extends DBConnPool{
	
	private static LogonDAO instance = new LogonDAO();
    
    
    public static LogonDAO getInstance() {
        return instance;
    }
	
	private LogonDAO() {
		super();
	}
	
	public void InsertMember(LogonDTO dto) {
		try {
			//String orgPass = dto.getU_pass();
			
			//String basePass = Base64.encodeBase64String(orgPass.getBytes());

			String sql = "insert into member02 values (?,?,?,?,?,?,to_date(?,'yyyy-MM-DD'))";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getU_id());
			psmt.setString(2, dto.getU_pass());
			psmt.setString(3, dto.getU_name());
			psmt.setTimestamp(4, dto.getR_date());
			psmt.setString(5, dto.getU_address());
			psmt.setString(6, dto.getU_tel());
			psmt.setString(7, dto.getU_birthdate());
			
			System.out.println(sql);
			
			psmt.executeUpdate();
			
			System.out.println("회원정보 입력 성공");
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("회원가입도중 오류 발생");
		}finally {
			//close();
		}
	}
	
	public int userCheck(String u_id, String u_pass) {
		int x = -1;
		
		try {
			String orgPass = u_pass;
			//String basePass = Base64.encodeBase64String(orgPass.getBytes());
			
			String sql = "SELECT u_pass FROM member02 WHERE u_id = ?";
			psmt =con.prepareStatement(sql);
			psmt.setString(1, u_id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				String dbPass = rs.getString("u_pass");
				if(orgPass.equals(dbPass)) {
					x = 1;
				}else {
					x = 0;
				}
			}else {
				x = -1;
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("회원체크중 오류발생");
		}finally {
			//close();
		}
		return x;
	}
	
	public int confirmId(String u_id) {
		int x = -1;
		
		try {
			String sql = "SELECT u_id FROM member02 WHERE u_id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, u_id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				x = 1;
				System.out.println(u_id+" 는 DB에 존재하는 id 입니다.");
			}else {
				x = -1;
				System.out.println(u_id+"는 DB에 존재하지않는 id 입니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("아이디확인과정에서 예외발생");
		}finally {
			//close();
		}
		return x;
	}
}
