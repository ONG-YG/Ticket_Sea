package kr.co.tickectsea.member.model.vo;

import java.sql.Date;

public class Member {

	private int memberNo;
	private String memberId;
	private String memberName;
	private String memberPwd;
	private String memberPhone;
	private String memberAddr;
	private String memberEmail;
	private Date memberJoinDate;
	private char memberActive;
	private char memberGender;

	public Member(int memberNo, String memberId, String memberName, String memberPwd, String memberPhone,
			String memberAddr, String memberEmail, Date memberJoinDate, char memberActive, char memberGender) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberPwd = memberPwd;
		this.memberPhone = memberPhone;
		this.memberAddr = memberAddr;
		this.memberEmail = memberEmail;
		this.memberJoinDate = memberJoinDate;
		this.memberActive = memberActive;
		this.memberGender = memberGender;
	}

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberAddr() {
		return memberAddr;
	}

	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public Date getMemberJoinDate() {
		return memberJoinDate;
	}

	public void setMemberJoinDate(Date memberJoinDate) {
		this.memberJoinDate = memberJoinDate;
	}

	public char getMemberActive() {
		return memberActive;
	}

	public void setMemberActive(char memberActive) {
		this.memberActive = memberActive;
	}

	public char getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(char memberGender) {
		this.memberGender = memberGender;
	}

}
