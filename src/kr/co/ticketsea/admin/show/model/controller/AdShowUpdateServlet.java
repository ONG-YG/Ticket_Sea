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

import kr.co.ticketsea.admin.show.model.service.ShowService;
import kr.co.ticketsea.admin.show.model.vo.Show;
import kr.co.ticketsea.member.model.vo.Member;

/**
 * Servlet implementation class AdShowUpdateServlet
 */
@WebServlet(name = "AdShowUpdate", urlPatterns = { "/adShowUpdate.do" })
public class AdShowUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdShowUpdateServlet() {
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
					
					String encType="UTF-8";
					
					MultipartRequest multi = new MultipartRequest(request,
							uploadPath,fileSizeLimit,encType,new DefaultFileRenamePolicy());
					
					String fileName = multi.getFilesystemName("show_poster");
					System.out.println("파일 이름 : " + fileName);
					
					String fullFilePath = uploadPath+"\\"+fileName;
					System.out.println("총 경로 : " + fullFilePath);
					
					
					
					Show s = new Show();
					s.setM_show_no(Integer.parseInt(multi.getParameter("m_show_no")));
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
					
					int result=new ShowService().updateShow(s);
					
					if(result>0) {
						response.sendRedirect("/views/admin/showUpdateSuccess.jsp");
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
