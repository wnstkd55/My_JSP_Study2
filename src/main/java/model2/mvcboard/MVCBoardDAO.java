package model2.mvcboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool {
	
	//�⺻ ������ ȣ��� �θ� Ŭ������ ȣ�� 
	public MVCBoardDAO () {
		super(); 			//DBConnPool��ü�� �⺻ ������ ȣ�� , DBCP���� con ��ü Ȱ��ȭ
	}
	
	//�˻� ���ǿ� �´� �Խù� ������ ��ȯ�մϴ�. 
	public int selectCount( Map<String, Object> map ) {
		int totalCount = 0; 
		String query = "SELECT COUNT(*) FROM mvcboard";    //���ڵ��� �Ѱ��� ��ȯ ,  
			if (map.get("searchWord") != null) {			// �˻������ ��������� where 
				query += " Where " + map.get("searchField") + " " + "like '%" + map.get("searchWord") + "%'"; 
			}
		try {
			stmt = con.createStatement(); 
			rs = stmt.executeQuery(query);
			rs.next(); 
			totalCount = rs.getInt(1); 
			
		} catch (Exception e) {
			System.out.println("�Խù� ī��Ʈ�� ���� �߻�");
			e.printStackTrace();
		}
					
		return totalCount; 
	}
	
	
	
	
	//�˻� ���ǿ� �´� �Խù� ����� ��ȯ�մϴ�.
		//DataBase���� Select �� ��� ����  DTO�� ��Ƽ� ���� ������.  
    public List<MVCBoardDTO> selectListPage(Map<String,Object> map) {
        List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();
        String query = " "
                     + "SELECT * FROM ( "
                     + "    SELECT Tb.*, ROWNUM rNum FROM ( "
                     + "        SELECT * FROM mvcboard ";

        if (map.get("searchWord") != null)
        {
            query += " WHERE " + map.get("searchField")
                   + " LIKE '%" + map.get("searchWord") + "%' ";
        }

        query += "        ORDER BY idx DESC "
               + "    ) Tb "
               + " ) "
               + " WHERE rNum BETWEEN ? AND ?";
        
        System.out.println(query);  //�ֿܼ� ��ü ���� ��� 

        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, map.get("start").toString());
            psmt.setString(2, map.get("end").toString());
            rs = psmt.executeQuery();

            while (rs.next()) {
                MVCBoardDTO dto = new MVCBoardDTO();

                dto.setIdx(rs.getString(1));
                dto.setName(rs.getString(2));
                dto.setTitle(rs.getString(3));
                dto.setContent(rs.getString(4));
                dto.setPostdate(rs.getDate(5));
                dto.setOfile(rs.getString(6));
                dto.setSfile(rs.getString(7));
                dto.setDowncount(rs.getInt(8));
                dto.setPass(rs.getString(9));
                dto.setVisitcount(rs.getInt(10));

                board.add(dto);
            }
        }
        catch (Exception e) {
            System.out.println("�Խù� ��ȸ �� ���� �߻�");
            e.printStackTrace();
        }
        return board;
    }
	
	
	
	
	//��� �� �˻� (Select ) : �־��� �Ϸ� ��ȣ�� �ش� �ϴ� ���� DTO �� �� �� ��ȯ�մϴ�. (�������� read)
    //ViewController ��û�� ó�� 
    
    public MVCBoardDTO selectView(String idx) {
    	MVCBoardDTO dto = new MVCBoardDTO();
    	String query = "SELECT * FROM mvcboard WHERE idx = ?"; 
    	
    	try {
    		psmt = con.prepareStatement(query); 
    		psmt.setString(1,idx); 
    		rs = psmt.executeQuery(); 
    		
    		if (rs.next()) {
    			dto.setIdx(rs.getString(1));
    			dto.setName(rs.getString(2));
    			dto.setTitle(rs.getString(3));
    			dto.setContent(rs.getString(4));
    			dto.setPostdate(rs.getDate(5));
    			dto.setOfile(rs.getString(6));
    			dto.setSfile(rs.getString(7));
    			dto.setDowncount(rs.getInt(8));
    			dto.setPass(rs.getString(9));
    			dto.setVisitcount(rs.getInt(10));
    			
    		}
    		
    	}catch (Exception e) {
    		System.out.println("�Խù� ������ ����� ���� �߻�");
    		e.printStackTrace();
    	}
    	
    	return dto; 
    }
    
    //�־��� �Ϸ� ��ȣ�� �ش��ϴ� �Խù��� ��ȸ���� 1 ���� ��Ŵ. 
    public void updateVisitCount(String idx) {
    	String query = "UPDATE mvcboard SET "
    			+ " visitcount = visitcount + 1 "
    			+ " WHERE idx = ?"; 
    	
    	try {
    		psmt = con.prepareStatement(query); 
    		psmt.setString (1, idx); 
    		psmt.executeUpdate(); 
    		
    	}catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("�Խù� ��ȸ�� ������ ���� �߻�");
    	}    	
    }
    
    
    
    
    
	
	//������ ���� (Insert)
	public int insertWrite (MVCBoardDTO dto) {
		int result = 0 ; 
		try {
			String query = "INSERT INTO mvcboard ( "
					+ " idx,name, title, content, ofile,sfile,pass) "
					+ " VALUES ("
					+ " seq_board_num.nextval, ?, ?, ?, ?, ?, ?)"; 
			
		psmt = con.prepareStatement(query);  //PareparedStatement ��ü ���� 
		
		psmt.setString(1, dto.getName());
		psmt.setString(2, dto.getTitle());
		psmt.setString(3, dto.getContent());
		psmt.setString(4, dto.getOfile());
		psmt.setString(5, dto.getSfile());
		psmt.setString(6, dto.getPass());
		
		result = psmt.executeUpdate();  //insert�� �����϶� result > 0   //DB �� ���� ���� 
			//result : 0�϶� <== Insert ����, result : 1 �϶� insert ����
		
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return result; 		// result : Insert ������ > 0 , ���н� : 0 
	}
	
	
	//������ ���� (Update)
	public int updatePost (MVCBoardDTO dto) {
		int result = 0 ; 
		
		try {
			String query =    "UPDATE mvcboard "
							+ " SET title = ? , name = ?, content = ?, ofile = ?, sfile = ? "
							+ " WHERE idx = ? and pass = ?"; 
			//������ �غ� 
			psmt = con.prepareStatement(query); 
			
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getIdx());
			psmt.setString(7, dto.getPass()); 
			
			result = psmt.executeUpdate();   //update������ result ������ ���� > 0 	
			
			//System.out.println("result : " + result);
					
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Update�� ���ܹ߻�");
		}
				
		return result ;   //result > 0 : ���� ����, result = 0 : ���� ���� 		
	}

	
	//������ ���� (delete)
	public int deletePost(String idx) {
		int result = 0 ; 
		
		try {
			String query = "DELETE mvcboard WHERE idx = ?"; 
			psmt = con.prepareStatement(query); 
			psmt.setString(1, idx);
			
			result = psmt.executeUpdate();  //result > 0 ���� ����, result = 0 : ���� ���� 
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("delete �� ���� �߻� ");
		}
				
		return result; 				
	}
	
	
	
	//�ٿ� �ε� Ƚ���� ������Ű�� �޼ҵ� 
	public void downCountPlus (String idx) {
		String query = "UPDATE mvcboard SET downcount = downcount + 1 "
					   + " WHERE idx = ?"; 
		
		try {
			psmt = con.prepareStatement(query); 
			psmt.setString(1, idx);
			psmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("�ٿ�ε� Ƚ�� ������ ���� �߻�");
		}
	}
	
	// ��й�ȣ�� Ȯ�� �ϴ� �޼ҵ� (�Է��� ��� ��ȣ�� DataBase �� ���� ��ġ�ϴ��� Ȯ�� 
	public boolean confirmPassword(String pass, String idx) {
		boolean isCorr = true; 
		try {
				//COUNT(*) = 1 : ���ڵ尡 �����ϸ� : client���� �ѱ� pass , idx DB�� ���� 
				//COUNT(*) = 0 : ���ڵ尡 �������� ������ client���� �ѱ� ���� DB�� �������� �ʴ´�. 
			String query = "SELECT COUNT(*) FROM mvcboard WHERE pass = ? and idx = ?";
			psmt = con.prepareStatement(query); 
			psmt.setString(1, pass);
			psmt.setString(2, idx);
			rs = psmt.executeQuery(); 
			
			rs.next(); 	
			if (rs.getInt(1) == 0) {
				isCorr = false; 
			}
					
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("��й�ȣ Ȯ�ν� ���� �߻�");
		}		
		return isCorr; 
	}
	
	
	
	
	
	
	

}
