package kr.co.ticketsea.member.model.vo;

public class PwdMember {

	
	private int result;
	private String ranPwd;
	private String userId;
	public PwdMember(int result, String ranPwd, String userId) {
		super();
		this.result = result;
		this.ranPwd = ranPwd;
		this.userId = userId;
	}
	public PwdMember() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getRanPwd() {
		return ranPwd;
	}
	public void setRanPwd(String ranPwd) {
		this.ranPwd = ranPwd;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
