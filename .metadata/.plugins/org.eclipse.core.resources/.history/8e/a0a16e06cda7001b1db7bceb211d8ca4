package com.parksh.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parksh.member.dao.MemberDao;
import com.parksh.member.vo.Member;

/**
 * Servlet implementation class AllMember
 */
@WebServlet("/allMember")
public class AllMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//3. 처리 로직
		MemberDao dao = new MemberDao();
		ArrayList<Member> list = dao.selectAllMember();
		//4.결과 처리
		response.setContentType("text/html;charset=utf8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html><head><title>조회 결과</title></head><body>");
		
		out.println("<table><tr><th>번호</th><th>아이디</th><th>비밀번호</th><th>이름</th><th>전화번호</th><th>주소</th><th>가입일</th>");
		for(Member m : list) {
			out.println("<tr>");
			out.println("<td>" + m.getMemberNo() + "</td>");
			out.println("<td>" + m.getMemberId() + "</td>");
			out.println("<td>" + m.getMemberPw() + "</td>");
			out.println("<td>" + m.getMemberName() + "</td>");
			out.println("<td>" + m.getPhone() + "</td>");
			out.println("<td>" + m.getAddress() + "</td>");
			out.println("<td>" + m.getEnrollDate() + "</td>");
			out.println("</tr>");
			
		}
		out.println("</talbe>");
		
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
