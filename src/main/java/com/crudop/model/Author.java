package com.crudop.model;

public class Author {
	
	private int authorId;
	private String username;
	private String email;
	private String phone;
	private String qualification;
	private String profile;
	private String createdAt;
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public Author() {
		super();
	}
	
	public Author(String username, String email, String phone, String qualification, String profile, String createdAt) {
		super();
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.qualification = qualification;
		this.profile = profile;
		this.createdAt = createdAt;
	}
	
	public Author(int authorId, String username, String email, String phone, String qualification, String profile,
			String createdAt) {
		this(username, email, phone, qualification, profile, createdAt);
		this.authorId = authorId;
	}
	
	@Override
	public String toString() {
		return "Author [getAuthorId()=" + getAuthorId() + ", getUsername()=" + getUsername() + ", getEmail()="
				+ getEmail() + ", getPhone()=" + getPhone() + ", getQualification()=" + getQualification()
				+ ", getProfile()=" + getProfile() + ", getCreatedAt()=" + getCreatedAt() + "]";
	}
	
	
	
}
