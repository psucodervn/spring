package vn.uts.facebookgetfeed.service;

import vn.uts.facebookgetfeed.domain.Post;

public interface PostService extends GenericService<Post> {
	public Post findByPostId(String postId);
}
