package kr.co.ticketsea.admin.show.model.vo;

import java.sql.Timestamp;

public class FileData {
		private String fileName;
		private String filePath;
		private long fileSize;
		private String fileUser;
		private Timestamp uploadTime;

		public FileData() {
			super();
			// TODO Auto-generated constructor stub
		}
		public FileData(String fileName, String filePath, long fileSize, String fileUser, Timestamp uploadTime) {
			super();
			this.fileName = fileName;
			this.filePath = filePath;
			this.fileSize = fileSize;
			this.fileUser = fileUser;
			this.uploadTime = uploadTime;
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
		public String getFileUser() {
			return fileUser;
		}
		public void setFileUser(String fileUser) {
			this.fileUser = fileUser;
		}
		public Timestamp getUploadTime() {
			return uploadTime;
		}
		public void setUploadTime(Timestamp uploadTime) {
			this.uploadTime = uploadTime;
		}


}
