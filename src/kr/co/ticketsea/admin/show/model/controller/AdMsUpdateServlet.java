package kr.co.ticketsea.admin.show.model.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javafx.scene.input.DataFormat;
import kr.co.ticketsea.admin.show.model.service.MiniShowService;
import kr.co.ticketsea.admin.show.model.vo.MiniShow;

/**
 * Servlet implementation class AdMsUpdateServlet
 */
@WebServlet(name = "AdMsUpdate", urlPatterns = { "/adMsUpdate.do" })
public class AdMsUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdMsUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		
		int fileSizeLimit = 5 * 1024 * 1024;
		String uploadPath = getServletContext().getRealPath("/")+"img"+"\\"+"poster";
		
		//파일 인코딩 타입
		String encType="UTF-8";
		
		MultipartRequest multi = new MultipartRequest(request,
				uploadPath,fileSizeLimit,encType,new DefaultFileRenamePolicy());
		
		String fileName = multi.getFilesystemName("show_poster");
		System.out.println("파일 이름 : " + fileName);
		
		String fullFilePath = uploadPath+"\\"+fileName;
		System.out.println("총 경로 : " + fullFilePath);
		
		request.setCharacterEncoding("utf-8");
		
		MiniShow ms = new MiniShow();
		ms.setMs_no(Integer.parseInt(multi.getParameter("msNo")));
		ms.setMs_artists(multi.getParameter("artists"));
		ms.setMs_place(multi.getParameter("place"));
		ms.setMs_st_date(multi.getParameter("st_date"));
		ms.setMs_ed_date(multi.getParameter("ed_date"));
		ms.setMs_poster(multi.getFilesystemName("show_poster"));
		ms.setMs_intd(multi.getParameter("intd"));
		
		int result = new MiniShowService().updateApMiniShow(ms);
		if(result>0) {
			response.sendRedirect("/views/admin/msUpdateSuccess.jsp");
		}else {
			response.sendRedirect("/views/admin/error.jsp");
		}
		}catch (Exception e) {
			response.sendRedirect("/views/file/error.jsp");
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
