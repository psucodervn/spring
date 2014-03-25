package vn.uts.facebookgetfeed.domain;

import java.util.List;

import org.bson.types.ObjectId;

public class Action {

	private Integer likeCount;
	private List<String> friendLikeIdList;
	private Integer commentCount;
	private List<String> friendCommentIdList;
	private Integer shareCount;
	private List<String> friendShareIdList;

	private String postId;
	private String userId;

	private ObjectId postObjectId;

	public List<String> getFriendLikeIdList() {
		return friendLikeIdList;
	}

	public void setFriendLikeIdList(List<String> friendLikeIdList) {
		this.friendLikeIdList = friendLikeIdList;
	}

	public List<String> getFriendCommentIdList() {
		return friendCommentIdList;
	}

	public void setFriendCommentIdList(List<String> friendCommentIdList) {
		this.friendCommentIdList = friendCommentIdList;
	}

	public List<String> getFriendShareIdList() {
		return friendShareIdList;
	}

	public void setFriendShareIdList(List<String> friendShareIdList) {
		this.friendShareIdList = friendShareIdList;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ObjectId getPostObjectId() {
		return postObjectId;
	}

	public void setPostObjectId(ObjectId postObjectId) {
		this.postObjectId = postObjectId;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getShareCount() {
		return shareCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}
}
