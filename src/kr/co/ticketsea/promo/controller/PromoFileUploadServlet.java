package kr.co.ticketsea.promo.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.ticketsea.promo.model.service.*;
import kr.co.ticketsea.promo.model.vo.*;
import kr.co.ticketsea.member.model.vo.Member;

/**
 * Servlet implementation class PromoFileUploadServlet
 */
@WebServlet(name = "PromoFileUpload", urlPatterns = { "/promoFileUpload.do" })
public class PromoFileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PromoFileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 파일 업로드를 구현하려면 몇가지 정보가 필요함
		//1. 사용자 계정명 (업로드한 사람의 정보 - session을 이용)
		HttpSession session = request.getSession(false);
		try {
		String userId = ((Member)session.getAttribute("member")).getMemberId();

		//2. 최대 업로드 파일 사이즈 (cos 라이브러리는 10MB가 한계)
		int fileSizeLimit = 5 * 1024 * 1024;
		// 1Byte*1024 == 1024Byte == 1KByte
		// 1KByte*1024 == 1024Kbyte ==  1MByte
				
		//3. 업로드 될 경로
		String uploadPath = getServletContext().getRealPath("/")+"uploadFile"+"\\"+userId;
		
		
		//4. 인코딩 타입 (파일 인코딩 타입)
		String encType="UTF-8";
		
		
		//5. 위 정보들을 바탕으로 파일 업로드에 사용하는 MultipartRequest 객체를 생성
		MultipartRequest multi = new MultipartRequest(
									request,
									uploadPath,
									fileSizeLimit,
									encType,
									new DefaultFileRenamePolicy());
		// 마지막에 만든 DefaultFileRenamePolicy 객체를 만들어서 넣어주게 되면
		// 파일 이름의 중복 처리를 자동으로 처리해줌
		// ex) a.bmp가 중복으로 2번 업로드를 하게 되면 
		// 파일의 이름이 자동으로 a1.bmp, a2.bmp로 변경됨
	
		
		// 업로드된 파일의 정보를 바탕으로 DB에 기록할 수 있도록 생성
		
		//1. 파일 이름
		// getFilesystemName("view의 파라미터 이름);을 사용하면
		// 업로드된 파일의 이름을 가져옴
		String fileName = multi.getFilesystemName("upfile");
		System.out.println("파일 이름 : " + fileName);
			
		//2. 업로드 파일의 실체 총 경로 (filePath)
		// 업로드되는 경로 + 파일 이름	
		String fullFilePath = uploadPath+"\\"+fileName;
		System.out.println("총 경로 : " + fullFilePath);
		
		
		//3. 파일의 크기(length)
		File file = new File(fullFilePath); //import java.io.File
		long fileSize = file.length(); //파일의 사이즈를 가져옴
		
		//usb -> 파일 시스템 : fat32
		//    -> 파일 시스템 : NTFS 혹은 exFat
		
		//4. 업로드 유저
		// 위에서 만들었기 때문에 생략 가능 (userId)
		
		
		//5. 파일이 업로드된 시간 (밀리세컨까지)
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Timestamp uploadTime = null;
		
		uploadTime = Timestamp.valueOf(formatter.format(Calendar.getInstance().getTimeInMillis()));
		//Timestamp import는 java.sql.Timestamp
		
		System.out.println("업로드된 시간 : " + uploadTime);
		
		
		//객체에 값 저장
		FileData fd = new FileData();
		fd.setFileName(fileName);		//파일 이름
		fd.setFilePath(fullFilePath);	//파일 총 경로 (디렉토리+파일명)
		fd.setFileSize(fileSize);		//파일 사이즈
		fd.setFileUser(userId);			//업로드 유저
		fd.setUploadTime(uploadTime);	//업로드된 시간
		
		
		//비즈니스 로직 처리
		int result = new FileService().uploadFile(fd);
		
		
			if(result>0)
			{
				response.sendRedirect("/views/file/fileUploadSuccess.jsp");
			}
			else
			{
				response.sendRedirect("/views/file/fileUploadFail.jsp");
			}
			
		
		} catch (Exception e) {
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
