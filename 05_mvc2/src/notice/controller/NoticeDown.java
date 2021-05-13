package notice.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.http11.filters.BufferedInputFilter;
import org.apache.tomcat.jni.FileInfo;

import com.oreilly.servlet.multipart.BufferedServletInputStream;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDown
 */
@WebServlet("/noticeDown")
public class NoticeDown extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeDown() {
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
		// 2. 값 추출
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		// 3.비지니스 로직
		Notice n = new NoticeService().selectOneNotice(noticeNo);
		// 4.결과처리
		// 파일 위치 지정
		String root = getServletContext().getRealPath("/");
		String file = root + "upload/notice/" + n.getFilepath();
		// 파일과 현재 서블릿을 연결해서 읽어온 후 사용자에게 전달
		// 파일을 서블릿으로 가져오기 위한 객체 생성
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		// 읽어온 파일을 사용자에게 전달하는 객체
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		// 브라우저 종류에 따라 파일명을 변경하는 로직
		String resFilename = "";
		// 브라우저가 IE인지 확인
		boolean bool = request.getHeader("user-agent").indexOf("MSIE") != -1
				|| request.getHeader("user-agent").indexOf("Trident") != -1;
		System.out.println("IE 여부: " + bool);

		if (bool) {
			// IE 인 경우
			resFilename = URLEncoder.encode(n.getFilename(), "UTF-8");
			resFilename = resFilename.replaceAll("\\\\", "%20");
		} else {
			// 그 외 브라우져 인경우
			resFilename = new String(n.getFilename().getBytes("UTF-8"), "ISO-8859-1");
		}
		// 파일 다운로드를 위한 HTTP header 설정
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attatchment;filename=" + resFilename);
		// 파일전송
		while (true) {
			int read = bis.read();
			if (read != -1) {
				bos.write(read);
			}else {
				break;
			}
		}
		bos.close();
		bis.close();

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
