package com.bit2015.emaillist.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.emaillist.dao.EmailListDao;
import com.bit2015.emaillist.vo.EmailListVo;

@WebServlet("/el")
public class EmailListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding( "UTF-8" );
		
		String a = request.getParameter( "a" );
		
		if( "form".equals( a ) ) {
			
			forwarding( request, response, "/views/form_emaillist.jsp");
			
		} else if( "add".equals( a ) ) {
			String firstName = request.getParameter( "fn" );
			String lastName = request.getParameter( "ln" );
			String email = request.getParameter( "email" );
			
			EmailListVo vo = new EmailListVo();
			vo.setFirstName(firstName);
			vo.setLastName(lastName);
			vo.setEmail(email);
			
			EmailListDao dao = new EmailListDao();
			dao.insert(vo);
			
			response.sendRedirect( "/emaillist2/el?a=show" );
		} else {
			// default action
			EmailListDao dao = new EmailListDao();
			List<EmailListVo> list = dao.getList();
			
			request.setAttribute( "list", list );
			forwarding( request, response, "/views/show_emaillist.jsp" );
		}
	}
	
	private void forwarding(
		HttpServletRequest request,
		HttpServletResponse response,
		String path ) throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher =
				request.getRequestDispatcher( path );
		requestDispatcher.forward( request, response );
	}
}
