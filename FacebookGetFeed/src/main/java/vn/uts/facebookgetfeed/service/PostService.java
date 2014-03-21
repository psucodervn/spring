package vn.uts.facebookgetfeed.service;

import vn.uts.facebookgetfeed.domain.Post;

public interface PostService {
	public Post save(Post post);
	public Post findByPostId(String postId);
}
