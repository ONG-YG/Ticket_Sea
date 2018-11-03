package kr.co.ticketsea.admin.show.model.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
		request.setCharacterEncoding("utf-8");
		//=====파일 사이즈 업로드==========
		
		//최대 업로드 파일 사이즈
		int fileSizeLimit = 5 * 1024 * 1024;
		//업로드 될 경로
		if()
		String uploadPath = getServletContext().getRealPath("/")+"img"+"\\"+"poster";
		//인코딩 타입 (파일 인코딩 타입)
		String encType="UTF-8";
		// MultipartRequest 객체를 생성
		MultipartRequest multi = new MultipartRequest(request,
				uploadPath,fileSizeLimit,encType,new DefaultFileRenamePolicy());
		
		Enumeration fileNames = multi.getFileNames();
		while(fileNames.hasMoreElements()) {
			//파일이 여러개이기때문에
			String parameter = (String) fileNames.nextElement();
			String fileName = multi.getFilesystemName("show_poster");
			String fullFilePath = uploadPath+"\\"+fileName;
		}
		//1. 파일이름
		String fileName = multi.getFilesystemName("show_poster");
		//2. 업로드 파일의 실체 총 경로 (filePath)
		// 업로드되는 경로 + 파일 이름	
		String fullFilePath = uploadPath+"\\"+fileName;
		//3. 파일의 크기(length)
		File file = new File(fullFilePath); //import java.io.File
		long fileSize = file.length(); //파일의 사이즈를 가져옴
		//5. 파일이 업로드된 시간 (밀리세컨까지)
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Timestamp uploadTime = null;
		
		uploadTime = Timestamp.valueOf(formatter.format(Calendar.getInstance().getTimeInMillis()));
		
		//객체에 값 저장
		FileData fd = new FileData();
		fd.setFileName(fileName);		//파일 이름
		fd.setFilePath(fullFilePath);	//파일 총 경로 (디렉토리+파일명)
		fd.setFileSize(fileSize);		//파일 사이즈
		fd.setShowName(request.getParameter("show_name"));
		fd.setUploadTime(uploadTime);	//업로드된 시간
				
		//공연 입력 정보 
		Show s = new Show();
		
		s.setShow_name(request.getParameter("show_name"));
		s.setTh_no(Integer.parseInt(request.getParameter("th_no")));
		s.setSc_code(request.getParameter("sc_code"));
		s.setShow_st_date(request.getParameter("show_st_date"));
		s.setShow_ed_date(request.getParameter("show_ed_date"));
		s.setArtists(request.getParameter("artists"));
		s.setShow_grd(request.getParameter("show_grd"));
		s.setShow_run(Integer.parseInt(request.getParameter("show_run")));
		s.setBk_comm(Integer.parseInt(request.getParameter("comm")));
		s.setShow_poster(request.getParameter("show_poster"));
		s.setShow_dtInfo(request.getParameter("showDtInfo"));
		
		int result = new ShowService().insertShow(s,fileName);
		
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
