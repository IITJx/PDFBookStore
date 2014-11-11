package com.iitjx.pdfbookstore.domain;

import javax.persistence.*;

@Entity
@NamedQuery(name = "User.byName", query = "from User where userName = ?")
public class User {
	@Id
	@GeneratedValue
	private int userId;
	private String userName;
	private String password;
	private String type;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
