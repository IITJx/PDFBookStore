package com.iitjx.pdfbookstore.domain;

import java.util.Date;

import javax.persistence.*;

@Entity
public class File {
	@Id
	@GeneratedValue
	private int id;
	@Lob
	@Column(length = 20971520, nullable = false)
	private byte[] data;
	@Column(length = 64, nullable = false)
	private String name;

	@Column(length = 20, nullable = false)
	private String contentType;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date timestamp;

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}
