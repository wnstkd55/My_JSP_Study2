package model2.mvcboard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PassController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Get ��û�� ó��

		System.out.println("PassController �����۵�");
		String mode = req.getParameter("mode");
		System.out.println("mode ������ �� : " + mode);
		
		//mode : edit <== �ۼ���, mode : delete <== �� ����
		req.setAttribute("mode", req.getParameter("mode"));
		req.getRequestDispatcher("/mvcboard/Pass.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Post ��û�� ó��
	}
	
}
