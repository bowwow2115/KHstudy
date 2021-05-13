package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxTest3
 */
@WebServlet("/ajaxTest3")
public class AjaxTest3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxTest3() {
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
		//2.값추출
		// /test?id=user01
		int firstNum = Integer.parseInt(request.getParameter("firstNum"));
		int secondNum = Integer.parseInt(request.getParameter("secondNum"));
		System.out.println(firstNum + secondNum);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(firstNum + secondNum);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
