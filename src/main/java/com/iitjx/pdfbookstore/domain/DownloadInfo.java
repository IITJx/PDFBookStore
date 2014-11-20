package com.iitjx.pdfbookstore.domain;

import java.util.Date;

import javax.persistence.*;

@Entity
public class DownloadInfo {
	@Id
	@GeneratedValue
	private int downloadId;
	private int bookId;
	private int userId;
	@Temporal(TemporalType.DATE)
	private Date downloadTime;

	public int getDownloadId() {
		return downloadId;
	}

	public void setDownloadId(int downloadId) {
		this.downloadId = downloadId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getDownloadTime() {
		return downloadTime;
	}

	public void setDownloadTime(Date downloadTime) {
		this.downloadTime = downloadTime;
	}

}
