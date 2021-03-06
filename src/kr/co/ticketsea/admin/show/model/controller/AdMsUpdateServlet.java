package kr.co.ticketsea.admin.show.model.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javafx.scene.input.DataFormat;
import kr.co.ticketsea.admin.show.model.service.MiniShowService;
import kr.co.ticketsea.admin.show.model.vo.MiniShow;
import kr.co.ticketsea.member.model.vo.Member;

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
			request.setCharacterEncoding("utf-8");
			
			HttpSession session = request.getSession(false);
			
			if(session!=null) {
				Member m = (Member)session.getAttribute("member");
					
				if(m!=null && m.getMemberGrade()=='A') {
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
					ms.setBoardp_no(Integer.parseInt(multi.getParameter("msNo")));
					ms.setBoardp_artist(multi.getParameter("artists"));
					ms.setBoardp_location(multi.getParameter("place"));
					ms.setBoardp_price(Integer.parseInt(multi.getParameter("price")));
					if(fileName==null) {
						ms.setBoardp_filename(multi.getParameter("exist_poster"));
					}else {
						ms.setBoardp_filename(multi.getFilesystemName("show_poster"));
					}
					ms.setBoardp_contents(multi.getParameter("intd"));
					
					int result = new MiniShowService().updateApMiniShow(ms);
					if(result>0) {
						response.sendRedirect("/views/admin/msUpdateSuccess.jsp");
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
