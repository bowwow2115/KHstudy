package photo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import photo.model.vo.Photo;

public class PhotoDao {

	public int totalCount(Connection conn) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		String query = "select count(*) cnt from photo";
		int totalCount = 0;
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalCount = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return totalCount;
	}

	public int insertPhoto(Connection conn, Photo p) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt =null;
		String query = "insert into photo values(photo_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'))";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, p.getPhotoWriter());
			pstmt.setString(2, p.getPhotoContent());
			pstmt.setString(3, p.getFilepath());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Photo> morePhoto(Connection conn, int start, int end) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from (select rownum as rnum, p.* from (select * from photo order by photo_no desc)p) where rnum between ? and ? ";
		ArrayList<Photo> list = new ArrayList<Photo>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Photo p = new Photo();
				p.setFilepath(rset.getString("filepath"));
				p.setPhotoContent(rset.getString("photo_content"));
				p.setPhotoDate(rset.getString("photo_date"));
				p.setPhotoNo(rset.getInt("photo_no"));
				p.setPhotoWriter(rset.getString("photo_writer"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}

}
