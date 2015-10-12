package com.bit2015.emaillist.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.emaillist.dao.EmailListDao;
import com.bit2015.emaillist.vo.EmailListVo;
import com.bit2015.web.WebUtil;
import com.bit2015.web.action.Action;

public class AddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter( "fn" );
		String lastName = request.getParameter( "ln" );
		String email = request.getParameter( "email" );
		
		EmailListVo vo = new EmailListVo();
		vo.setFirstName(firstName);
		vo.setLastName(lastName);
		vo.setEmail(email);
		
		EmailListDao dao = new EmailListDao();
		dao.insert(vo);
		
		WebUtil.redirect( response, "/emaillist2/el?a=show" );
	}

}
