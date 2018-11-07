package kr.co.ticketsea.search.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.ticketsea.common.JDBCTemplate;

import kr.co.ticketsea.search.model.dao.SearchDao;
import kr.co.ticketsea.search.model.vo.PageData;
import kr.co.ticketsea.search.model.vo.Search;

public class SearchService {

	public PageData searchList(String keyword,int currentPage) {


		Connection conn = JDBCTemplate.getConnection();
		
		// 2개값을 저장하는 변수 생성 (게시물의 개수, navi의 개수)	
		int recordCountPerPage = 10; //게시물의 개수
		int naviCountPerPage = 5; //navi의 개수
		
		
		// Service에서 DAO를 호출 (2번의 DAO를 호출)
		// 1. 현재 페이지의 게시물 리스트 요청
		// 2. 현재 페이지를 중심으로 만들어지는 navi 리스트 요청
		
		
		ArrayList<Search> list = new SearchDao().getMusicalSearchCurrentPage(conn,currentPage,recordCountPerPage,keyword);
		String pageNavi = new SearchDao().getSearchPageNavi(conn,currentPage,recordCountPerPage,naviCountPerPage,keyword);
		PageData pd = null;
		
		
		if(!list.isEmpty() && !pageNavi.isEmpty())
		{
			pd = new PageData();
			pd.setList(list);
			pd.setPageNavi(pageNavi);
		}
		
		JDBCTemplate.close(conn);
		
		return pd;
		
	}

}
