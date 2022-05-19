package model2.mvcboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import utils.BoardPage;   //����¡ ó���ϴ� ��ü 

public class ListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get ������� ��û�� ������ �������� ó�� 
		
		//1. DAO ��ü ���� (Model : ����Ͻ� ���� ó�� ) 
		MVCBoardDAO dao = new MVCBoardDAO(); 
		
		//2.�信 ������ �Ű����� ����� �� ���� (Key,Value) 
	
        Map<String, Object> map = new HashMap<String, Object>();

        String searchField = req.getParameter("searchField");
        String searchWord = req.getParameter("searchWord");
        if (searchWord != null) {
            // ������Ʈ������ ���޹��� �Ű����� �� �˻�� �ִٸ� map�� ����
            map.put("searchField", searchField);
            map.put("searchWord", searchWord);
        }
        int totalCount = dao.selectCount(map);  // �Խù� ����
		
	/* ����¡ ó�� �κ� start */
		
			//web.xml �� ���õ� ������ �ҷ����� 
		ServletContext application = getServletContext(); 
		int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE")); 
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
		
		//System.out.println(pageSize);
		//System.out.println(blockPage);
		
		//���� ������ Ȯ�� 
		int pageNum = 1; 
		String pageTemp = req.getParameter("pageNum");  
		if (pageTemp != null && !pageTemp.equals("")) {
			pageNum = Integer.parseInt(pageTemp); 	//  ���� ��� ���� ������ �ѿ��� ������ ������ ������ ��ȯ�ؼ� ���� 
		}
		
		//��Ͽ� ����� �Խù� ���� ��� 
		int start = (pageNum -1) * pageSize + 1 ;  //ù �Խù� ��ȣ 
		int end = pageNum * pageSize;     //������ �Խù� ��ȣ 
		
		//�� �������� ���� ������     
		map.put("start", start); 
		map.put("end", end);
		
		System.out.println(start);
		System.out.println(end);
		
		
		
	/* ����¡ ó�� �κ� end */ 
		
	// �Խù� ����� �޾ƿ��� (DAO ��ü�� �۾��� ���� )
		//boardLists�� DB�� ���ڵ带 ���� DTO��ü(5��) �� ��� �ִ�. 
	
        List<MVCBoardDTO> boardLists = dao.selectListPage(map);  // �Խù� ��� �ޱ�
        dao.close(); // DB ���� �ݱ�
	
	//���������� ���� �� �Ű��������� �߰� 
        //
    String pagingImg = BoardPage.pagingStr(totalCount, pageSize,
            blockPage, pageNum, "../mvcboard/list.do");  // �ٷΰ��� ���� HTML ���ڿ�
    map.put("pagingImg", pagingImg);
    map.put("totalCount", totalCount);
    map.put("pageSize", pageSize);
    map.put("pageNum", pageNum); 
	
	//���������� ������ ����, request ������ ������ �����͸� ������ List.jsp (��������) �� ������ 
    req.setAttribute("boardLists", boardLists);
    req.setAttribute("map", map);
    req.getRequestDispatcher("/mvcboard/List.jsp").forward(req, resp);
	
	
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Post  ������� ��û�� ������ �������� ó�� 
	}
	
}
