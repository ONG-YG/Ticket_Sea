package kr.co.ticketsea.search.model.vo;

import java.sql.Date;

public class Search {
	
	private int mShowNo;
	private int thNo;
	private String scCode;
	private String mShowName;
	private Date mShowStDate;
	private Date mShoEdDate;
	private String mArtists;
	private String mShowGrd;
	private int mShowRun;
	private int bkComm;
	private String mShowPoster;
	private String mShowDtInfo;
	
	
	public Search(int mShowNo, int thNo, String scCode, String mShowName, Date mShowStDate, Date mShoEdDate,
			String mArtists, String mShowGrd, int mShowRun, int bkComm, String mShowPoster, String mShowDtInfo) {
		super();
		this.mShowNo = mShowNo;
		this.thNo = thNo;
		this.scCode = scCode;
		this.mShowName = mShowName;
		this.mShowStDate = mShowStDate;
		this.mShoEdDate = mShoEdDate;
		this.mArtists = mArtists;
		this.mShowGrd = mShowGrd;
		this.mShowRun = mShowRun;
		this.bkComm = bkComm;
		this.mShowPoster = mShowPoster;
		this.mShowDtInfo = mShowDtInfo;
	}
	public Search() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getmShowNo() {
		return mShowNo;
	}
	public void setmShowNo(int mShowNo) {
		this.mShowNo = mShowNo;
	}
	public int getThNo() {
		return thNo;
	}
	public void setThNo(int thNo) {
		this.thNo = thNo;
	}
	public String getScCode() {
		return scCode;
	}
	public void setScCode(String scCode) {
		this.scCode = scCode;
	}
	public String getmShowName() {
		return mShowName;
	}
	public void setmShowName(String mShowName) {
		this.mShowName = mShowName;
	}
	public Date getmShowStDate() {
		return mShowStDate;
	}
	public void setmShowStDate(Date mShowStDate) {
		this.mShowStDate = mShowStDate;
	}
	public Date getmShoEdDate() {
		return mShoEdDate;
	}
	public void setmShoEdDate(Date mShoEdDate) {
		this.mShoEdDate = mShoEdDate;
	}
	public String getmArtists() {
		return mArtists;
	}
	public void setmArtists(String mArtists) {
		this.mArtists = mArtists;
	}
	public String getmShowGrd() {
		return mShowGrd;
	}
	public void setmShowGrd(String mShowGrd) {
		this.mShowGrd = mShowGrd;
	}
	public int getmShowRun() {
		return mShowRun;
	}
	public void setmShowRun(int mShowRun) {
		this.mShowRun = mShowRun;
	}
	public int getBkComm() {
		return bkComm;
	}
	public void setBkComm(int bkComm) {
		this.bkComm = bkComm;
	}
	public String getmShowPoster() {
		return mShowPoster;
	}
	public void setmShowPoster(String mShowPoster) {
		this.mShowPoster = mShowPoster;
	}
	public String getmShowDtInfo() {
		return mShowDtInfo;
	}
	public void setmShowDtInfo(String mShowDtInfo) {
		this.mShowDtInfo = mShowDtInfo;
	}

	
	
	
	
}
