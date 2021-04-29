package com.parksh.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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
		//결과를 처리할 페이지 지정
		RequestDispatcher rd = request.getRequestDispatcher("/view/allMember.jsp");
		//화면구성을 위한 데이터 등록
		request.setAttribute("list", list);
		//페이지 이동
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
