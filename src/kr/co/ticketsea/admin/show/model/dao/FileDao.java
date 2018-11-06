package kr.co.ticketsea.admin.show.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.co.ticketsea.admin.show.model.vo.FileData;
import kr.co.ticketsea.common.JDBCTemplate;

public class FileDao {

	public int uploadFile(Connection conn, FileData fd) {
		
		PreparedStatement pstmt= null;
		int result = 0;
		
		String query = "insert into fileTbl values(file_NO.nextVal,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, fd.getFileName());
			pstmt.setString(2, fd.getFilePath());
			pstmt.setLong(3, fd.getFileSize());
			pstmt.setString(4, fd.getShowName());
			pstmt.setTimestamp(5, fd.getUploadTime());
			
			
			result = pstmt.executeUpdate();
			System.out.println(result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
