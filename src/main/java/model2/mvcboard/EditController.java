package model2.mvcboard;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import fileupload.FileUtil;
import utils.JSFunction;

@WebServlet("/mvcboard/edit.do")
public class EditController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��� ��ȣ ������ ������ �Ϸ� �Ǹ� idx �� �ش��ϴ� ���ڵ带 dto�� �� Edit.jsp 
		//Get������� ��û�� �޾Ƽ� Ŭ���̾�Ʈ�� �ѱ�� ������ �Ҵ� �޾Ƽ� �� �������� �ѱ�. 
		String idx = req.getParameter("idx");
		MVCBoardDAO dao = new MVCBoardDAO (); 
		MVCBoardDTO dto = dao.selectView(idx);    // idx�� �ش��ϴ� ���ڵ带 �Ѱ� �޾� DTO������ 
		
		System.out.println("idx : " + idx);
		
		
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/mvcboard/Edit.jsp").forward(req, resp); 	
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Edit.jsp ���� ������ ������ �޾ DB�� ���� 
		
		// 1. ���� ���ε� ó�� 
			//���ε� ���丮�� �������� ��θ� Ȯ�� �ؾ� �Ѵ�.  (������ ������ ���ε��� �������� ������ )
		String saveDirectory = req.getServletContext().getRealPath("/Uploads"); 
		
			//���ε� �� ������ �ִ� �뷮 Ȯ�� (web.xml �� ������ ������: 1MB) 
		ServletContext application = this.getServletContext();
		int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
		
		//���� ���ε�  <<���߿� ó�� >> 
		MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize); 
		
		if (mr == null) {
			//���� ���ε� ���� 
			JSFunction.alertBack(resp, "÷�� ���� �뷮�� �ʰ� �Ǿ����ϴ�. "); 
			return; 
		}
		
		
		
		//2. ���� ���ε� �� ó�� ========================================
			//Request ��ü�� �ƴ϶� MutipartRequest ��ü���� Form�� ���� ���� �޴´�. 
			//���ε� ���̺귯�� ���� form�� ���� ���� �޴� �޼ҵ� �̸��� �ٸ� �� �ִ�. 
		
		String idx = mr.getParameter("idx"); 
		String prevOfile = mr.getParameter("prevOfile"); 
		String prevSfile = mr.getParameter("prevSfile"); 
		
		String name = mr.getParameter("name"); 
		String title = mr.getParameter("title"); 
		String content = mr.getParameter("content"); 
	
		//��� ��ȣ : �� Session ������ ������ �����´�. 
		HttpSession session = req.getSession(); 
		String pass = (String) session.getAttribute("pass"); 
		
		// DTO �� �Ѱܹ��� ���� ���� ����  ( Client Form ===> DTO ===> DAO �� ���� ) 
		MVCBoardDTO dto = new MVCBoardDTO(); 
		dto.setIdx(idx); 
		dto.setName(name); 
		dto.setTitle(title); 
		dto.setContent(content); 
		dto.setPass(pass); 
		
		/*
		System.out.println("=======DTO ������ ����� �� �ҷ����� ==========");
		System.out.println(dto.getIdx());
		System.out.println(dto.getName());
		System.out.println(dto.getTitle());
		System.out.println(dto.getContent());
		System.out.println(dto.getPass());
		
		*/ 
		
		//dto ��ü�� Ofile, Sfile �� ���ε� ��ο� �ش� ���ϸ��� �����ϴ� ��� ó�� 
		
		//���� ���ϸ�� ����� ���� �̸� ���� 
		String fileName = mr.getFilesystemName("ofile"); 
		if (fileName != null) {   //÷�� ������ Uploads ������ �����ϴ� ��� ���� �̸��� �����ؼ� ����
			//���ο� ���ϸ� ���� 
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date()); //��¥
			String ext = fileName.substring(fileName.lastIndexOf(".")); 
			String newFileName = now + ext; 
			
			System.out.println("now : " + now);
			System.out.println("ext : " + ext);
			System.out.println("newFileName" + newFileName);
			
			//���ϸ� ���� 
			File oldFile = new File (saveDirectory + File.separator + fileName); 
			File newFile = new File (saveDirectory + File.separator + newFileName); 
			
			oldFile.renameTo(newFile); 
			
			//����� ������ DTO �� ���� 
			
			dto.setOfile(fileName); 		//���� ���� �̸�
			dto.setSfile(newFileName); 		//���ο� ���� �̸� (������ ����� ���� �̸�) 
			
			//������ ���� ���� 
			FileUtil.deleteFile(req, "/Uploads", prevSfile); 			
			
		}else {   //÷�� ������ �������� ������ ������ �̸� ���� 
			dto.setOfile(prevOfile); 
			dto.setSfile(prevSfile); 		
		}
		
		
		
		
		//DB�� ���� ������ �ݿ�.  (DTO �� ����� ���� DAO�� �޼ҵ��� �Ű������� ���� ) 
		MVCBoardDAO dao = new MVCBoardDAO(); 
		int result = dao.updatePost(dto);       
			// result == 1 : update ����, result == 0 : update ���� 
		dao.close(); 
		
		//���� ���� vs ���� ���� 
		if (result==1) { 	//����
			session.removeAttribute("pass"); 
			resp.sendRedirect("../mvcboard/view.do?idx=" + idx ); 
			
		} else {			//����
			JSFunction.alertLocation(resp, "��й�ȣ ������ �ٽ� ������ �ּ���",
					"../mvcboard/view.do?idx=" + idx); 			
			
		}
		
		
		
		
		
		
		
		
		
		
	}
	

}
