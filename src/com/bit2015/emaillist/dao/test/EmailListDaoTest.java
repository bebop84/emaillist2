package com.bit2015.emaillist.dao.test;

import java.util.List;

import com.bit2015.emaillist.dao.EmailListDao;
import com.bit2015.emaillist.vo.EmailListVo;

public class EmailListDaoTest {

	public static void main(String[] args) {
		// insert test
		insertTest();
		
		// getList test
		getListTest();
	}

	public static void getListTest() {
		EmailListDao dao = new EmailListDao();
		List<EmailListVo> list = dao.getList();
		for( EmailListVo vo : list ) {
			System.out.println( vo );
		}
	}
	
	public static void insertTest() {
		EmailListDao dao = new EmailListDao();
		
		EmailListVo vo = new EmailListVo();
		vo.setFirstName( "고" );
		vo.setLastName( "길동2" );
		vo.setEmail( "gidong2@gmail.com" );
		
		dao.insert(vo);
	}
}
