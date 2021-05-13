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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class AjaxTest6
 */
@WebServlet("/ajaxTest6")
public class AjaxTest6 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxTest6() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		ArrayList<Member> list = new MemberService().seletAllMember();
		JSONArray memberArray = new JSONArray(); //jsonarray로 변환
		if(!list.isEmpty()) {
			for(Member m : list) {
				JSONObject obj = new JSONObject();
				obj.put("memberNo", m.getMemberNo());
				obj.put("memberName", URLEncoder.encode(m.getMemberName(),"UTF-8"));
				obj.put("phone", m.getPhone());
				memberArray.add(obj);
			}
		}
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(memberArray);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
