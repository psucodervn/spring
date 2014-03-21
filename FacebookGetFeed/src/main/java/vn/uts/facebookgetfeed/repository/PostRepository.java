package vn.uts.facebookgetfeed.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import vn.uts.facebookgetfeed.domain.Post;
import vn.uts.facebookgetfeed.repository.extend.PostRepositoryExtend;

@Repository
public interface PostRepository extends MongoRepository<Post, ObjectId>,
		PostRepositoryExtend {
	public Post findByPostId(String postId);
}
