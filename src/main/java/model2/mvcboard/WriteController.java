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

import com.oreilly.servlet.MultipartRequest;

import fileupload.FileUtil;
import utils.JSFunction;

/**
 * Servlet implementation class WriteController
 */
@WebServlet("/mvcboard/write.do")
public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get ��� ��û ó��
		//List.jsp(view ������)���� �۾��⸦ Ŭ�� ������ �۾��� ��������(Write.jsp)
		
		//�� �������� �̵�
		request.getRequestDispatcher("/mvcboard/Write.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Post ��� ��û ó��
		//Write.jsp ���������� ������ Ŭ�������� form���� �Ѿ���� ������ ���� ó��
		
		//form���� ������ �����ϹǷ� cos.jar���̺귯���� ��ü ������ ������ ���� �޾ƾ��Ѵ�.
		
		//1. ���� ���ε� ó��=================================================================
			//saveDirectory ������ ���ε��� ������ ������ ������ �������� ��θ� ����
		
		String saveDirectory = request.getServletContext().getRealPath("/Uploads");
		
			//maxPostSize : ���ε��� �ִ� �뷮(web.xml <== 1MB)
		
		ServletContext application = getServletContext();
		int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
		
		
		//���� ���ε� ��ü ���� (
		MultipartRequest mr = FileUtil.uploadFile(request, saveDirectory, maxPostSize);
		
		//��ü ���� ���н� ó���� ����
		if(mr == null) {	//��ü ���� ����(1MB �̻��� �뷮�� ���� ���۽�)
			JSFunction.alertLocation(response, "÷�� �뷮�� �ʰ� �Ǿ����ϴ�.", "../mvcboard/write.do");
			
			return;
		}
		
		
		// ���� ���ε� 
		
		
		
		//2. ���� ���ε� �� ó��(Form�� ���� �� ó��)
			//������ �Ѱܹ��� ���� �޾Ƽ� DTO(vo)�� Setter �����ϰ� DAO�� Insert �޼ҵ忡 ������.
		MVCBoardDTO dto = new MVCBoardDTO();
		dto.setName(mr.getParameter("name"));
		dto.setTitle(mr.getParameter("title"));
		dto.setContent(mr.getParameter("content"));
		dto.setPass(mr.getParameter("pass"));
		
		//���� ���� �̸��� �������� �̸� ����.
		String fileName = mr.getFilesystemName("ofile");		//client�� ÷�������� ������ �ּ�
		System.out.println("ofilename"+fileName); 	//�ּ�ó��
		
		if(fileName != null) {		//÷�� ������ ������� �ʴٶ��
			
			//���ο� �����̸����� �����ؼ� ������ ������. (������ �ش������� ������ ��찡 �����Ƿ�)
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			System.out.println("now: "+now); 		//�ּ�ó��
			
			//Ȯ���ڸ� �߶� ����
			String ext = fileName.substring(fileName.lastIndexOf("."));
			System.out.println("ext "+ext);
			
			//������ ������ �����̸� ����
			String newFileName = now + ext;
			System.out.println(newFileName);
			
			//���ϸ� ����
			File oldFile = new File(saveDirectory + File.separator + fileName);
			File newFile = new File(saveDirectory + File.separator + newFileName);
			System.out.println("oldFile : " + oldFile);		//�ּ� ó��
			System.out.println("newFile : " + newFile);		//�ּ� ó��
			
			oldFile.renameTo(newFile);
			
			
			//DTO�� Setter ���� (���� : ������ ���ε��� ��쿡��)
			dto.setOfile(fileName);		//���� ���� �̸�
			dto.setSfile(newFileName);		//������ ����� �����̸�
			
		}
		
		//DTO�� ��ü�� DAO�� insert �޼ҵ带 ȣ���ؼ� DB�� ����
		MVCBoardDAO dao = new MVCBoardDAO();
		int result = dao.insertWrite(dto);
		
		//��ü ���� �޼ҵ� ȣ��(rs, stmt, psmt, con��� ����)
		dao.close();
		
		//�۾��� �����϶� �̵��� ������
		if(result == 1) {	//�۾��� �����϶� list �������� �̵�
			response.sendRedirect("../mvcboard/list.do");
		}
		//�۾��� �����϶� �̵��� ������
		if(result == 0) {
			response.sendRedirect("../mvcboard/write.do");
		}
	}

}
