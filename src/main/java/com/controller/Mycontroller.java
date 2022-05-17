package com.controller;

import java.io.IOException;

import org.apache.catalina.connector.Response;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.model.LoginBean;

public class Mycontroller extends HttpServlet{	// controller ����

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Client���� get������� ��û�� ��� ó���ϴ� ���
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Client ���� post������� ��û�� ��� ó���ϴ� ���
			// client���������� id�� password�� �Ѿ���� ó��
		
		response.setContentType("text.html; charset = UTF-8");
			// response : client�� view �������� ������ contentType�� ���� 
		String id = request.getParameter("id"); //�Ѱܿ� id�� ����
		String password = request.getParameter("passwd"); //�Ѱܿ� password�� ����
		
		LoginBean bean = new LoginBean();	// ��ü ����
		bean.setId(id);	// setter�� id�� ����
		bean.setPassword(password); // setter�� password�� ����
		
		request.setAttribute("bean", bean);  //"����",bean(��ü) => "bean"�� bean��ü ��ü�� ������
		
		boolean status = bean.validate();	// password�� "admin"�� ���� status
		
		if (status) {
			RequestDispatcher rd = request.getRequestDispatcher("mvc_success.jsp");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("mvc_error.jsp");
			rd.forward(request, response);
			
			
		}
		
		
		
	}	
	
}
