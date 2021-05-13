package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class CheckId
 */
@WebServlet(name = "CheckIdServ", urlPatterns = { "/checkId" })
public class CheckId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckId() {
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
		String memberId = request.getParameter("checkId");
		//3.비즈니스 로직
		Member m = new MemberService().selectOneMember(memberId);
		//4.결과처리
		RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/views/member/checkId.jsp");
		//겨롸처리에 사용할 데이터 등록
		request.setAttribute("memberId", memberId);
		if(m!=null) {
			//아이디가 중복인경우
			request.setAttribute("result", false);
		}else {
			request.setAttribute("result", true);
		}
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
