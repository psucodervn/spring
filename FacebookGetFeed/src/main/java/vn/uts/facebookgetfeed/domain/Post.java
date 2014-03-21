package vn.uts.facebookgetfeed.domain;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * @author Hung
 * 
 */
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
	 * Id of profile (user) whose accessToken is use to get feeds
	 */
	private String profileId;

	/**
	 * Id of profile (or page,...) that created this post
	 */
	private String fromId;

	/**
	 * Name of profile (or page,...) that created this post
	 */
	private String fromName;
	private String message;
	private Date createdTime;
	private Date updatedTime;
	private String link;
	
	/**
	 * Type of the post, i.e STATUS, PHOTO, LINK,... 
	 */
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

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
