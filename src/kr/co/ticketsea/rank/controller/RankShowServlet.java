package kr.co.ticketsea.rank.controller;

import java.io.IOException;
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
 * Servlet implementation class RankShowServlet
 */
@WebServlet(name = "RankShow", urlPatterns = { "/rankShow.do" })
public class RankShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RankShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Rank> list =new RankService().rankShow();
	
		
		
		//PrintWriter out = response.getWriter();
		
		
		JSONArray resultList = new JSONArray(); 
		if(!list.isEmpty())
		{
			for(Rank r : list)
			{
				JSONObject  object=new JSONObject();
				object.put("poster", r.getPoster());
				object.put("no", r.getShowNo());
				object.put("subject", r.getSubject());
				resultList.add(object);
				System.out.println(object);
				
			}
				//System.out.println(resultList);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(resultList);
			
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
