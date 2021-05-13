package photo.controller;

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

import photo.model.service.PhotoService;
import photo.model.vo.Photo;

/**
 * Servlet implementation class PhotoWrite
 */
@WebServlet("/photoWrite")
public class PhotoWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoWrite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "게시판 작성 오류[enctype]");
			request.setAttribute("loc", "/photoList");
			rd.forward(request, response);
			return;
		}
		//파일 업로드 준비
		//1) v파일 업로드 경로 설정
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload/photo";
		//2) 파일 최대크기지정
		int maxSize = 10*1024*1024;
		
		//3) request -> MultipartRequest변환 파일을 업로드
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize,"UTF-8",new DefaultFileRenamePolicy());
		Photo p = new Photo();
		p.setPhotoWriter(mRequest.getParameter("photoWriter"));
		p.setPhotoContent(mRequest.getParameter("photoContent"));
		p.setFilepath(mRequest.getFilesystemName("filename"));
		//3.비지니스 로직
		int result = new PhotoService().insertPhoto(p);
		//4.결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "성공");
		}else {
			request.setAttribute("msg", "오류");
		}
		request.setAttribute("loc", "/photoList");
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
