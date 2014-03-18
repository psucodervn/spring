package vn.uts.facebookgetfeed.domain;

import java.util.Date;

public class Post {
	
	private String postId;
	private String message;
	private Date createdTime;
	private Date updatedTime;
	private String link;

	public Post(String postId, String message, Date createdTime,
			Date updatedTime, String link) {
		super();
		this.postId = postId;
		this.message = message;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.link = link;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
