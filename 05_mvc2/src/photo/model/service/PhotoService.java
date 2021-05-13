package photo.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import photo.model.dao.PhotoDao;
import photo.model.vo.Photo;

public class PhotoService {

	public int totalCount() {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int totalCount = new PhotoDao().totalCount(conn);
		JDBCTemplate.close(conn);
		
		return totalCount;
	}

	public int insertPhoto(Photo p) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = new PhotoDao().insertPhoto(conn,p);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Photo> morePhoto(int start) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int length = 5;
		int end = start+length-1;
		ArrayList<Photo> list = new PhotoDao().morePhoto(conn,start,end);
		JDBCTemplate.close(conn);
		
		return list;
	}


}
