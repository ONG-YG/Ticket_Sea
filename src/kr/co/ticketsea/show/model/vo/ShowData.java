package kr.co.ticketsea.show.model.vo;

import java.util.ArrayList;

import kr.co.ticketsea.show.model.vo.Comment;
import kr.co.ticketsea.show.model.vo.Show;

public class ShowData {
	private ArrayList<Comment> list;
	private Show show;
	
	public ShowData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShowData(ArrayList<Comment> list, Show show) {
		super();
		this.list = list;
		this.show = show;
	}
	
	public ArrayList<Comment> getList() {
		return list;
	}
	public void setList(ArrayList<Comment> list) {
		this.list = list;
	}
	public Show getShow() {
		return show;
	}
	public void setShow(Show show) {
		this.show = show;
	}
	
	
	
	
	
}


