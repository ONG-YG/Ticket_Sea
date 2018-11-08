package kr.co.ticketsea.rank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.ticketsea.rank.model.service.RankService;
import kr.co.ticketsea.rank.model.vo.Rank;

/**
 * Servlet implementation class NewestShowServlet
 */
@WebServlet(name = "NewestShow", urlPatterns = { "/newestShow.do" })
public class NewestShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewestShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		request.setCharacterEncoding("utf-8");
		
		
		ArrayList<Rank> list =new RankService().newestShow();
		response.setContentType("text/html; charset=utf-8");
		response.setContentType("application/json");
		
		
		PrintWriter out = response.getWriter();
		
		JSONObject object = null;
		JSONArray resultList = new JSONArray(); 
		if(list.isEmpty())
		{
			for(Rank r : list)
			{
				new JSONObject();
				object.put("poster", r.getPoster());
				object.put("no", r.getShowNo());
				object.put("subject", r.getSubject());
				resultList.add(object);
				
				out.println(resultList);
			}
			
			
		}else {
			
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
