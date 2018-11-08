package kr.co.ticketsea.promo.model.vo;

import java.sql.Connection;
import java.util.ArrayList;


public class PromoData {
	private ArrayList<Comment> list;
	private Promo promo;
	
	public PromoData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PromoData(ArrayList<Comment> list, Promo promo) {
		super();
		this.list = list;
		this.promo = promo;
	}
	
	public ArrayList<Comment> getList() {
		return list;
	}
	public void setList(ArrayList<Comment> list) {
		this.list = list;
	}
	public Promo getPromo() {
		return promo;
	}
	public void setPromo(Promo promo) {
		this.promo = promo;
	}
	
	
	
	
	
}











