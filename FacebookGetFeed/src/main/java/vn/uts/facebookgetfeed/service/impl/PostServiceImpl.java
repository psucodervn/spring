package vn.uts.facebookgetfeed.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.uts.facebookgetfeed.domain.Post;
import vn.uts.facebookgetfeed.repository.PostRepository;
import vn.uts.facebookgetfeed.service.PostService;

@Service("postService")
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	public Post findByPostId(String postId) {
		return postRepository.findByPostId(postId);
	}

	public Post save(Post post) {
		return postRepository.save(post);
	}
}
