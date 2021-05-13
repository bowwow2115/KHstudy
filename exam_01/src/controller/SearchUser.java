package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserDao;

/**
 * Servlet implementation class SearchUser
 */
@WebServlet("/searchUser")
public class SearchUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		UserDao ud = new UserDao();
		User user = ud.selectUser(memberNo);
		
		
		if(user!=null) {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/searchUserResult.jsp");
		//화면구성을 위한 데이터 등록
		request.setAttribute("user", user);
		//페이지 이동
		rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/msg.jsp");
			request.setAttribute("fail", String);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
