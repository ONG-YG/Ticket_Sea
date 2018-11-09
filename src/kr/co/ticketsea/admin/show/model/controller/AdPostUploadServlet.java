package kr.co.ticketsea.admin.show.model.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.ticketsea.member.model.vo.Member;

/**
 * Servlet implementation class AdPostUploadServlet
 */
@WebServlet(name = "AdPostUpload", urlPatterns = { "/adPostUpload.do" })
public class AdPostUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdPostUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @return 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("utf-8");
			
			HttpSession session = request.getSession(false);
			
			if(session!=null) {
				Member m = (Member)session.getAttribute("member");
					
				if(m!=null && m.getMemberGrade()=='A') {
					//=====파일 사이즈 업로드==========
					
					//최대 업로드 파일 사이즈
					int fileSizeLimit = 5 * 1024 * 1024;
					//업로드 될 경로
					
					String uploadPath = getServletContext().getRealPath("/")+"img"+"\\"+"poster";
					//인코딩 타입 (파일 인코딩 타입)
					String encType="UTF-8";
					// MultipartRequest 객체를 생성
					MultipartRequest multi = new MultipartRequest(request,
							uploadPath,fileSizeLimit,encType,new DefaultFileRenamePolicy());
					
					String fileName = multi.getFilesystemName("show_poster");
					System.out.println(fileName);
				}else {
					throw new Exception();
				}
			}else {
				throw new Exception();
			}
		}catch (Exception e) {
			response.sendRedirect("/views/admin/adminError.jsp");
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
