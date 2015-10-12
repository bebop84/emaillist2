package com.bit2015.emaillist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bit2015.emaillist.vo.EmailListVo;

public class EmailListDao {
	private Connection getConnection() throws SQLException {
		
		Connection connection = null;
		
		try {
			//1.드라이버 로딩
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
		
			//2.커넥션 만들기(ORACLE DB)
			String dbURL  = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection( dbURL, "webdb", "webdb" );
			
		} catch( ClassNotFoundException ex ){
			System.out.println( "드라이버 로딩 실패-" + ex );
		} 
		
		return connection;
	}
	
	public void insert( EmailListVo vo ) {
		try {
			//1. Connection 가져오기
			Connection connection = getConnection();
			
			//2. Statement 준비
			String sql = 
				" insert" +
				" into email_list" + 
				" values( email_list_no_seq.nextval, ?, ?, ? )";
			PreparedStatement pstmt = connection.prepareStatement( sql );
			
			//3. binding
			pstmt.setString( 1, vo.getFirstName() );
			pstmt.setString( 2, vo.getLastName() );
			pstmt.setString( 3, vo.getEmail() );
			
			//4. query 실행
			pstmt.executeUpdate();
			
			//5. 자원정리
			pstmt.close();
			connection.close();
			
		} catch( SQLException ex ) {
			System.out.println( "SQL 오류-" + ex );
		}
	}
	
	public List<EmailListVo> getList() {
		List<EmailListVo> list = new ArrayList<EmailListVo>();
		
		try {
			//1. 커넥션 만들기(ORACLE DB)
			Connection connection = getConnection();
			
			//2. Statement 생성
			Statement stmt = connection.createStatement();
			
			//3. SQL문 실행
			String sql = "select * from email_list order by no desc";
			ResultSet rs = stmt.executeQuery( sql );
			
			//4. row 가져오기
			while( rs.next() ) {
				Long no = rs.getLong( 1 );
				String firstName = rs.getString( 2 );
				String lastName = rs.getString( 3 );
				String email = rs.getString( 4 );
				
				EmailListVo vo = new EmailListVo();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setEmail(email);
				
				list.add( vo );
			}
			
			//6. 자원 정리
			rs.close();
			stmt.close();
			connection.close();
			
		} catch( SQLException ex ) {
			System.out.println( "SQL 오류-" + ex );
		}
		
		return list;
	}
}