package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeWrite
 */
@WebServlet("/noticeWrite")
public class NoticeWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeWrite() {
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
		// 파일 업로드를 하는경우 enctype ="multipart/form-data"로 처리해야ㅁ므로 enctype이 정상인지 확인
		if (!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "공지사항 작성오류[enctyle]");
			request.setAttribute("loc", "/");
			rd.forward(request, response);
			return;
		}

		// 파일 업로드 준비
		// 1) 파일 업로드 경로 지정
		String root = getServletContext().getRealPath("/");  //WebContent 폴더 경로 가져오는 코드
		String saveDirectory = root + "upload/notice";		//파일저장경로 지정
		System.out.println("파일 저장경로: " + saveDirectory);
		// 2) 업로드 파일의 최대크기 지정 (일반적으로 웹은 10Mb 정도 사용)
		int maxSize = 10 * 1024 * 1024;
		// 3) request -> MultipartRequest 객체로 전환
		// 매개변수 5개 request 객체, 파일저장경로, 최대크기, 인코딩 타입, 파일명 중복 처리 객체

		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());
		Notice n = new Notice();

		n.setNoticeTitle(mRequest.getParameter("noticeTitle"));
		n.setNoticeWriter(mRequest.getParameter("noticeWriter"));
		n.setNoticeContent(mRequest.getParameter("noticeContent"));
		n.setFilename(mRequest.getOriginalFileName("filename"));//사용자가 업로드한 파일명
		n.setFilepath(mRequest.getFilesystemName("filename"));//실제 업로드 된 파일 이름
		//getOriginalFileNamez, getFilesystemName 매개변수는<input type = "file" name="" >name 속성값
		System.out.println("파일이름: " + n.getFilename());
		System.out.println("파일경로: " + n.getFilepath());
		System.out.println("내용: " + n.getNoticeContent());
		// 3. 비지니스 로직
		int result = new NoticeService().insertNotice(n);
		
		if(result>0) {
			request.setAttribute("msg", "공지사항 등록 성공");
		}else {
			request.setAttribute("msg", "공지사항 등록 실패");
		}
		request.setAttribute("loc", "noticeList?reqPage=1");
		RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		rd.forward(request, response);
		// 4.결과 처리

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
