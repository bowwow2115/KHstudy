package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;

/**
 * Servlet implementation class DeleteMember
 */
@WebServlet("/deleteMember")
public class DeleteMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값 추출
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		//3.비지니스 로직
		int result = new MemberService().deleteMember(memberNo);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");

		if (result == 0) {
			// 회원가입 실패
			request.setAttribute("msg", "회원탈퇴 실패!!!.");
			// alert으로 안내 후 이동할 페이지 지정
			// 페이지 이동
		} else {
			// 성공
			request.setAttribute("msg", "성공!!");
			HttpSession session = request.getSession(false);
			session.invalidate();
		}
		request.setAttribute("loc", "/");
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
