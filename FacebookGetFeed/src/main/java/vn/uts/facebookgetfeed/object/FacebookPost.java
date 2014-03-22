package vn.uts.facebookgetfeed.object;

import java.util.Date;
import java.util.LinkedHashMap;

public class FacebookPost {

	private String id;
	private String message;
	private String link;
	private Date created_time;
	private PostType type;
	private LinkedHashMap<String,Object> from;
	private String fromId;

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

	public PostType getType() {
		return type;
	}

	public void setType(PostType type) {
		this.type = type;
	}
	
	public static final String FIELDS = "id,message,link,created_time,from.fields(id)"; 
	public static enum PostType { POST, CHECKIN, LINK, NOTE, PHOTO, STATUS, VIDEO, SWF, MUSIC }
	
	public Object getFrom() {
		return from;
	}

	public void setFrom(LinkedHashMap<String, Object> from) {
		this.from = from;
		fromId = from.get("id").toString();
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
		from.put("id", fromId);
	}
}
