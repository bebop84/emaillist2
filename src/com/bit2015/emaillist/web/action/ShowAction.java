package com.bit2015.emaillist.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.emaillist.dao.EmailListDao;
import com.bit2015.emaillist.vo.EmailListVo;
import com.bit2015.web.WebUtil;
import com.bit2015.web.action.Action;

public class ShowAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// default action
		EmailListDao dao = new EmailListDao();
		List<EmailListVo> list = dao.getList();
		
		request.setAttribute( "list", list );
		WebUtil.forwarding( request, response, "/views/show_emaillist.jsp" );
	}

}
