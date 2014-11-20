package com.iitjx.pdfbookstore.domain;

import java.util.Date;

import javax.persistence.*;

@Entity
public class BookAccess {
	@Id
	@GeneratedValue
	private int accessId;
	private int bookId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date accessTime;

	public int getAccessId() {
		return accessId;
	}

	public void setAccessId(int accessId) {
		this.accessId = accessId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public Date getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

}
