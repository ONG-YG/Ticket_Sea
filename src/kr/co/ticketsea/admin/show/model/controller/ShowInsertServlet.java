package kr.co.ticketsea.admin.show.model.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.ticketsea.admin.show.model.service.FileService;
import kr.co.ticketsea.admin.show.model.service.ShowService;
import kr.co.ticketsea.admin.show.model.vo.FileData;
import kr.co.ticketsea.admin.show.model.vo.Show;
import kr.co.ticketsea.member.model.vo.Member;

/**
 * Servlet implementation class ShowInsertServlet
 */
@WebServlet(name = "ShowInsert", urlPatterns = { "/showInsert.do" })
public class ShowInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		try {
			request.setCharacterEncoding("utf-8");
			
			HttpSession session = request.getSession(false);
				
			if(session!=null) {
				Member m = (Member)session.getAttribute("member");
				if(m.getMemberGrade()=='A' && m!=null ) {
					//=====파일 사이즈 업로드==========
					//최대 업로드 파일 사이즈
					int fileSizeLimit = 5 * 1024 * 1024;
					//업로드 될 경로
					
					/*String uploadPath = getServletContext().getRealPath("/")+"img"+"\\"+"poster";*/
					String uploadPath=getServletContext().getRealPath("/")+"img"+"\\"+"poster";
					//인코딩 타입 (파일 인코딩 타입)
					String encType="UTF-8";
					// MultipartRequest 객체를 생성
					MultipartRequest multi = new MultipartRequest(request,
							uploadPath,fileSizeLimit,encType,new DefaultFileRenamePolicy());
					
					
					//getFileNames() : input 태그 중 속성이 파일인 것 
					//파라미터 이름 모두 반환, 벡터에 넣은 순서대로 생성
					
					String fileName = multi.getFilesystemName("show_poster");
					System.out.println("파일 이름 : " + fileName);
					String fullFilePath = uploadPath+"\\"+fileName;
					System.out.println("총 경로 : " + fullFilePath);
					String fileName1 = multi.getFilesystemName("showDtInfo");
					System.out.println("파일 이름 : " + fileName1);
					String fullFilePath1 = uploadPath+"\\"+fileName;
					System.out.println("총 경로 : " + fullFilePath1);
					
					//공연 입력 정보 
					request.setCharacterEncoding("utf-8");
					Show s = new Show();
					System.out.println(multi.getParameter("show_poster"));
					s.setShow_name(multi.getParameter("show_name"));
					s.setTh_no(Integer.parseInt(multi.getParameter("th_no")));
					s.setSc_code(multi.getParameter("sc_code"));
					s.setShow_st_date(multi.getParameter("show_st_date"));
					s.setShow_ed_date(multi.getParameter("show_ed_date"));
					s.setArtists(multi.getParameter("artists"));
					s.setShow_grd(multi.getParameter("show_grd"));
					s.setShow_run(Integer.parseInt(multi.getParameter("show_run")));
					s.setBk_comm(Integer.parseInt(multi.getParameter("comm")));
					s.setShow_poster(multi.getFilesystemName("show_poster"));
					s.setShow_dtInfo(multi.getFilesystemName("showDtInfo"));
					
					int result = new ShowService().insertShow(s);
					
					if(result>0) {
						response.sendRedirect("/views/admin/showInsertSuccess.jsp");
					}else {
						response.sendRedirect("/views/admin/error.jsp");
					}
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
