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
 * Servlet implementation class NoticeCommentUpdate
 */
@WebServlet("/noticeCommentUpdate")
public class NoticeCommentUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeCommentUpdate() {
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
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		int ncNo = Integer.parseInt(request.getParameter("ncNo"));
		String ncContent = request.getParameter("ncContent");
		//3.비지니스 로직
		
		int result = new NoticeService().updateNoticeCommet(ncNo,ncContent);
		//4. 결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "댓글 등록 성공");
		}else {
			request.setAttribute("msg", "댓륵 등록 실패");
		}
		request.setAttribute("loc", "noticeView?noticeNo=" + noticeNo);
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
