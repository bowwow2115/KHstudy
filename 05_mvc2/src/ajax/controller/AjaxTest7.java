package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class AjaxTest7
 */
@WebServlet("/ajaxTest7")
public class AjaxTest7 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxTest7() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		// 2.값 추출
		// 3.비지니스 로직
		ArrayList<Member> list = new MemberService().seletAllMember();
		JSONObject map = new JSONObject();
		for (Member m : list) {
			JSONObject userInfo = new JSONObject();
			userInfo.put("memberNo", m.getMemberNo());
			userInfo.put("memberName", URLEncoder.encode(m.getMemberName(), "UTF-8"));
			userInfo.put("phone", m.getPhone());
			map.put(m.getMemberNo(), userInfo);
		}
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(map);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
