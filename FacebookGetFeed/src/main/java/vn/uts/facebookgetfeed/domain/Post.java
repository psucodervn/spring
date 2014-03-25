package vn.uts.facebookgetfeed.domain;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * @author Hung
 * 
 */
public class Post {

	/**
	 * Primary key
	 */
	@Id
	private ObjectId id;

	/**
	 * Id of this post
	 */
	private String postId;

	/**
	 * Id of profile (or page,...) that created this post
	 */
	private String fromId;

	private String message;
	private String caption;
	private Date createdTime;
	private String link;

	/**
	 * Type of the post, i.e STATUS, PHOTO, LINK,...
	 */
	// private PostType type;
	private String type;

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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public static enum PostType {
		POST, CHECKIN, LINK, NOTE, PHOTO, STATUS, VIDEO, SWF, MUSIC
	}

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
