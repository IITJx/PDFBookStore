package com.iitjx.pdfbookstore.domain;

import javax.persistence.*;

@Entity
public class BookAccess {
	@Id
	@GeneratedValue
	private int accessId;
	private int bookId;
	private String accessTime;

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

	public String getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(String accessTime) {
		this.accessTime = accessTime;
	}

}
