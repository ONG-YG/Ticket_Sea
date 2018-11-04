package kr.co.ticketsea.admin.show.model.vo;

import java.sql.Timestamp;

public class FileData {
		private int fileNo;
		private String fileName;
		private String filePath;
		private long fileSize;
		private String showName;
		private Timestamp uploadTime;
		public FileData() {
			super();
			// TODO Auto-generated constructor stub
		}
		public FileData(int fileNo, String fileName, String filePath, long fileSize, String showName,
				Timestamp uploadTime) {
			super();
			this.fileNo = fileNo;
			this.fileName = fileName;
			this.filePath = filePath;
			this.fileSize = fileSize;
			this.showName = showName;
			this.uploadTime = uploadTime;
		}
		public int getFileNo() {
			return fileNo;
		}
		public void setFileNo(int fileNo) {
			this.fileNo = fileNo;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getFilePath() {
			return filePath;
		}
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
		public long getFileSize() {
			return fileSize;
		}
		public void setFileSize(long fileSize) {
			this.fileSize = fileSize;
		}
		public String getShowName() {
			return showName;
		}
		public void setShowName(String showName) {
			this.showName = showName;
		}
		public Timestamp getUploadTime() {
			return uploadTime;
		}
		public void setUploadTime(Timestamp uploadTime) {
			this.uploadTime = uploadTime;
		}

		

}
