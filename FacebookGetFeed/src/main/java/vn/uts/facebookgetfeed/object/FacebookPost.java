package vn.uts.facebookgetfeed.object;

import java.util.Date;

public class FacebookPost {

	private String id;
	private String message;
	private String caption;
	private String link;
	private Date created_time;
//	private PostType type;
	private String type;
	private FacebookFrom from;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

	public FacebookFrom getFrom() {
		return from;
	}

	public void setFrom(FacebookFrom from) {
		this.from = from;
	}

	public static final String FIELDS = "id,message,link,created_time,type,caption,from.fields(id)";

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
