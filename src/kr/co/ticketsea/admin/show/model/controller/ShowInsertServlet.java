package kr.co.ticketsea.admin.show.model.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.ticketsea.admin.show.model.service.FileService;
import kr.co.ticketsea.admin.show.model.service.ShowService;
import kr.co.ticketsea.admin.show.model.vo.FileData;
import kr.co.ticketsea.admin.show.model.vo.Show;

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
		  request.setCharacterEncoding("euc-kr");	
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
		
		
		//공연 입력 정보 
		request.getParameter("utf-8");
		Show s = new Show();
		s.setShow_name(multi.getParameter("show_name"));
		s.setTh_no(Integer.parseInt(multi.getParameter("th_no")));
		s.setSc_code(multi.getParameter("sc_code"));
		s.setShow_st_date(multi.getParameter("show_st_date"));
		s.setShow_ed_date(multi.getParameter("show_ed_date"));
		s.setArtists(multi.getParameter("artists"));
		s.setShow_grd(multi.getParameter("show_grd"));
		s.setShow_run(Integer.parseInt(multi.getParameter("show_run")));
		s.setBk_comm(Integer.parseInt(multi.getParameter("comm")));
		s.setShow_poster(multi.getParameter("show_poster"));
		s.setShow_dtInfo(multi.getParameter("showDtInfo"));
		
		int result = new ShowService().insertShow(s);
		
		if(result>0) {
			response.sendRedirect("/views/admin/showInsertSuccess.jsp");
		}else {
			response.sendRedirect("/views/admin/error.jsp");
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
