package kr.co.ticketsea.mypage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.ticketsea.mypage.service.MypageService;
import kr.co.ticketsea.reserve.model.vo.ShowInfo;

/**
 * Servlet implementation class MainImgServlet
 */
@WebServlet(name = "MainImg", urlPatterns = { "/mainImg.do" })
public class MainImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainImgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩
		request.setCharacterEncoding("utf-8");
		
		// musical_l 테이블의 m_show_no / m_show_poster / m_show_name 을 받아오는 비즈니스 로직
		ArrayList<ShowInfo> list = new MypageService().mainImg();
		
		JSONArray arr = new JSONArray();

		for (ShowInfo si : list) {

			JSONObject result = new JSONObject();
			
			result.put("no",si.getM_show_no());
			result.put("name",si.getM_show_name());
			result.put("poster",si.getM_show_poster());
			
			arr.add(result);
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(arr);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
