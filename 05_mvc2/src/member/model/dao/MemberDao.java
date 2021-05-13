package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import member.model.vo.Member;

public class MemberDao {

	public Member selectOneMember(Connection conn, String id, String pw) {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_id = ? and member_pw = ?";
		Member member = null;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				member = new Member();
				member.setMemberNo(rset.getInt("member_no"));
				member.setMemberId(rset.getString("member_id"));
				member.setMemberPw(rset.getString("member_pw"));
				member.setMemberName(rset.getString("member_name"));
				member.setPhone(rset.getString("phone"));
				member.setAddress(rset.getString("address"));
				member.setMemberLevel(rset.getInt("member_level"));
				member.setEnrollDate(rset.getString("enroll_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return member;
	}

	public int insertMember(Connection conn, Member m) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;

		String query = "insert into member values(mem_seq.nextval,?,?,?,?,?,2,to_char(sysdate, 'yyyy-mm-dd'))";
		int result = 0;
		conn = JDBCTemplate.getConnection();

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getAddress());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Member selectOneMember(Connection conn, String memberId) {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_id = ?";
		Member member = null;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				member = new Member();
				member.setMemberNo(rset.getInt("member_no"));
				member.setMemberId(rset.getString("member_id"));
				member.setMemberPw(rset.getString("member_pw"));
				member.setMemberName(rset.getString("member_name"));
				member.setPhone(rset.getString("phone"));
				member.setAddress(rset.getString("address"));
				member.setMemberLevel(rset.getInt("member_level"));
				member.setEnrollDate(rset.getString("enroll_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return member;
	}

	public int updateMember(Connection conn, Member m) {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		int result = 0;
		String query = ("update member set member_pw = ?, address = ?, phone = ?, member_name = ? where member_Id = ?");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberPw());
			pstmt.setString(2, m.getAddress());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getMemberName());
			pstmt.setString(5, m.getMemberId());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteMember(Connection conn, int memberNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = ("delete from member where member_no = ?");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Member> selectAllMember(Connection conn) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = ("select * from member ");

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Member m = new Member();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setMemberLevel(rset.getInt("member_level"));
				m.setEnrollDate(rset.getString("enroll_date"));
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;

	}

	public int changeLevel(Connection conn, int memberNo, int memberLevel) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set member_level = ? where member_no = ?";
	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberLevel);
			pstmt.setInt(2, memberNo);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}