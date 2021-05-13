package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.NoticeComment;

/**
 * Servlet implementation class InsertComment
 */
@WebServlet("/insertComment")
public class InsertComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		//2.값추출
		NoticeComment nc = new NoticeComment();
		nc.setNcContent(request.getParameter("ncContent"));
		nc.setNcLevel(Integer.parseInt(request.getParameter("ncLevel")));
		nc.setNcWriter(request.getParameter("ncWriter"));
		nc.setNoticeRef(Integer.parseInt(request.getParameter("noticeRef")));
		nc.setNcRef(Integer.parseInt(request.getParameter("ncRef")));
		System.out.println(nc.getNcContent());
		System.out.println(nc.getNcDate());
		System.out.println(nc.getNcLevel());
		System.out.println(nc.getNcNo());
		//3.비지니스 로직
		
		int result = new NoticeService().insertComment(nc);
		RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		//4.결과처리
		if(result>0) {
			request.setAttribute("msg", "댓글 등록 성공");
		}else {
			request.setAttribute("msg", "댓륵 등록 실패");
		}
		System.out.println("getnoticeref: " + nc.getNoticeRef());
		request.setAttribute("loc", "noticeView?noticeNo=" + nc.getNoticeRef());
		rd.forward(request, response);
			
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
