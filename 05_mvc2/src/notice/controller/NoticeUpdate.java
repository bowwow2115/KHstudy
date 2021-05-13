package notice.controller;

import java.io.File;
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
 * Servlet implementation class NoticeUpdate
 */
@WebServlet("/noticeUpdate")
public class NoticeUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeUpdate() {
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
		// 1.인코딩
		request.setCharacterEncoding("utf-8");
		// 2.값추출
		if (!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "공지사항 수정 오류[enctype]");
			request.setAttribute("loc", "/");
			rd.forward(request, response);
			return;
		}
		// 파일 업로드 준비
		// 1) 파일 업로드 경로 지정
		String root = getServletContext().getRealPath("/"); // WebContent 폴더 경로 가져오는 코드
		String saveDirectory = root + "upload/notice"; // 파일저장경로 지정
		System.out.println("파일 저장경로: " + saveDirectory);
		// 2) 업로드 파일의 최대크기 지정 (일반적으로 웹은 10Mb 정도 사용)
		int maxSize = 10 * 1024 * 1024;
		// 3) request -> MultipartRequest 객체로 전환
		// 매개변수 5개 request 객체, 파일저장경로, 최대크기, 인코딩 타입, 파일명 중복 처리 객체

		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());
		int noticeNo = Integer.parseInt(mRequest.getParameter("noticeNo"));
		String noticeTitle = mRequest.getParameter("noticeTitle");
		String filename = mRequest.getOriginalFileName("filename");
		String filepath = mRequest.getFilesystemName("filepath");
		String noticeContent = (mRequest.getParameter("noticeContent"));
		// 기존 파일
		String ofn = (mRequest.getOriginalFileName("oldFilename"));
		String ofp = (mRequest.getFilesystemName("oldFilepath"));
		// 기존 파일 삭제 확인용
		String status = mRequest.getParameter("status");
		
		System.out.println(noticeTitle);
		System.out.println(filename);
		System.out.println(filepath);
		System.out.println(noticeContent);
		
		
		if (status.equals("delete")) {// 기존 첨부파일을 삭제 했을 때
			File delFile = new File(saveDirectory + "/" + ofp);
			delFile.delete();
		} else if (ofn != null) { // 삭제 안했을 때 (그대로 넣어줌)
			filename = ofn;
			filepath = ofp;
		}
		Notice n = new Notice(0, noticeNo, noticeTitle, null, noticeContent, null, filename, filepath);
		// 3.비지니스 로직
		int result = new NoticeService().updateNotice(n);
		// 4. 결과처리
		if (result > 0) {
			request.setAttribute("msg", "공지사항 업데이트 성공");
		} else {
			request.setAttribute("msg", "공지사항 업데이트 실패");
		}
		request.setAttribute("loc", "noticeList?reqPage=1");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		rd.forward(request, response);
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
