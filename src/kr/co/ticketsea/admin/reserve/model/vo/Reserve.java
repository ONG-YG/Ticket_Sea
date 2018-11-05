package kr.co.ticketsea.admin.reserve.model.vo;

import java.sql.Date;

public class Reserve {
	private int bk_no; //예매번호
	private int member_no; //예매한 회원 번호
	private String bk_stat_cd; //예매 상태코드
	private Date bk_date; //예매일
	private int tot_price; //총 결제 금액
	private String bk_pay_type; //결제방법
	
	

}
