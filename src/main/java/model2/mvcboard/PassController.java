package model2.mvcboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.JSFunction;


@WebServlet("/mvcboard/pass.do")
public class PassController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get ��û�� ó��
		/*
		System.out.println("PassController �����۵� ");
		String mode = req.getParameter("mode"); 
		System.out.println( "mode ������ �� : " + mode);
		*/
		
		//mode : edit <== �ۼ���,   mode : delete <== �ۻ��� 
		req.setAttribute("mode", req.getParameter("mode"));
		req.getRequestDispatcher("/mvcboard/Pass.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Post ��û�� ó�� 
		
		//pass.jsp (��) ���� ������ ���� 3�� 
		String idx = req.getParameter("idx"); 
		String mode = req.getParameter("mode"); 
		String pass = req.getParameter("pass");
		
		//��й�ȣ Ȯ�� (DAO�� �۾��� ��Ŵ) 
		MVCBoardDAO dao = new MVCBoardDAO(); 
		boolean confirmed = dao.confirmPassword(pass, idx);
		dao.close();
		
		if (confirmed) {   //��� ��ȣ�� ��ġ�Ҷ� (mode������ Ȯ���ؼ�, edit : ����, delete:���� 
			if (mode.equals("edit")) {  //���� ������
				HttpSession session = req.getSession(); 
				session.setAttribute("pass", pass); 		//pass�� Session ������ �Ҵ�.
				resp.sendRedirect("../mvcboard/edit.do?idx=" + idx );
				
			}else if (mode.equals("delete")) { //���� ������
				dao = new MVCBoardDAO(); 
				MVCBoardDTO dto = dao.selectView(idx); 
				int result = dao.deletePost(idx);    //�Խù� ���� 
				dao.close(); 
				
				//�Խù� ������ ÷�� ���ϵ� ���� ����   <<���߿� �߰��� �κ� >> 
				
				//���� ���� ������ �̵� (JavaScript )  : JSFunction.java
				
				JSFunction.alertLocation(resp, "�����Ǿ����ϴ�", "../mvcboard/list.do");
			}
			
			
		}else  {	//��� ��ȣ�� ��ġ���� ������  (Java Script���༭ ������������ ���ư����� 
				//���� �������� �̵� (JavaScript) 
			JSFunction.alertBack(resp, "��й�ȣ ������ �����߽��ϴ�"); 
		}
		
		
		
			
	}
	
	

}
