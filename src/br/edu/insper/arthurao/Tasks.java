package br.edu.insper.arthurao;

public class Tasks {
	private String userEmail;
	private String description;
	private Integer tag;
	private Integer id;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getTag() {
		return tag;
	}
	
	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
