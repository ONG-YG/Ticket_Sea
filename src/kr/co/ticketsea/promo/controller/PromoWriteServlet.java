package kr.co.ticketsea.promo.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.ticketsea.member.model.vo.Member;
import kr.co.ticketsea.promo.model.service.PromoService;
import kr.co.ticketsea.promo.model.vo.*;

/**
 * Servlet implementation class PromoWriteServlet
 */
@WebServlet(name = "PromoWrite", urlPatterns = { "/promoWrite.do" })
public class PromoWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PromoWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		//3. session에서 글을 작성한 사람의 ID를 추출
		HttpSession session  = request.getSession(false);
		
		
		String title = "";
		String category = "";
		String contents = "";
		String artist = "";
		String location = "";
		String fileName = "";
		int price=0;
		String date = "";
		
		
		try {
			String userId = ((Member)session.getAttribute("member")).getMemberId();
			//userId를 가져오도록 함 (비로그인 사용자 일시 Exception이 발생함)
			
			String uploadPath=getServletContext().getRealPath("/")+"img"+"\\"+"promoPoster";
			
			////////////////////////
				int fileSizeLimit = 5 * 1024 * 1024;
				
				String encType = "UTF-8";
				
				MultipartRequest multi = new MultipartRequest(
						request,
						uploadPath,
						fileSizeLimit,
						encType,
						new DefaultFileRenamePolicy());
				
				title = multi.getParameter("title");
				
				category = multi.getParameter("category");
				contents = multi.getParameter("contents");
				artist = multi.getParameter("artist");
				location = multi.getParameter("location");
				price=Integer.parseInt(multi.getParameter("price"));
				date = multi.getParameter("date");
				
				Enumeration formNames = multi.getFileNames();
				String formName = (String)formNames.nextElement();
				fileName = multi.getFilesystemName(formName);
				System.out.println("파일 이름 : " + fileName);
				
				String fullFilePath = uploadPath+"\\"+fileName;
				System.out.println("업로드패스 : "+uploadPath);
				System.out.println("총 경로 : " + fullFilePath);
				
				File file = new File(fullFilePath); //import java.io.File
				long fileSize = file.length(); //파일의 사이즈를 가져옴
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
				Timestamp uploadTime = null;
				
				uploadTime = Timestamp.valueOf(formatter.format(Calendar.getInstance().getTimeInMillis()));
				
				System.out.println("업로드된 시간 : " + uploadTime);
				
				////////////////////////////////////////////////
				
			if(userId != null) {
			//4. 비즈니스 로직 처리
				int result = new PromoService().insertPromo(title,category,contents,price,date,artist,location,userId,fileName);
				
				if(result>0)
				{
					response.sendRedirect("/views/promo/writeSuccess.jsp");
					
				}else {
					throw new Exception();
				}
					}else {
				response.sendRedirect("/views/notice/writeFail.jsp");
				throw new Exception();
			}
			
		} catch (Exception e) {
			response.sendRedirect("/views/promo/error.jsp");
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
