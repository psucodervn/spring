package vn.uts.facebookgetfeed.object;

import java.util.LinkedHashMap;

import org.springframework.social.facebook.api.FqlResult;
import org.springframework.social.facebook.api.FqlResultMapper;

public class ActionCount {

	private int likeCount;
	private int commentCount;
	private int shareCount;

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getShareCount() {
		return shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	public static class ActionCountMapper implements
			FqlResultMapper<ActionCount> {
		@SuppressWarnings("rawtypes")
		@Override
		public ActionCount mapObject(FqlResult objectValues) {
			ActionCount actionCount = new ActionCount();
			LinkedHashMap info = (LinkedHashMap) objectValues
					.getObject("comment_info");
			actionCount.setCommentCount(Integer.parseInt(info.get(
					"comment_count").toString()));
			info = (LinkedHashMap) objectValues.getObject("like_info");
			actionCount.setLikeCount(Integer.parseInt(info.get("like_count")
					.toString()));
			actionCount.setShareCount(objectValues.getInteger("share_count"));
			return actionCount;
		}
	}
}
