package vn.uts.facebookgetfeed;

import org.springframework.data.mongodb.core.MongoOperations;

import vn.uts.facebookgetfeed.dao.impl.PostDaoImpl;
import vn.uts.facebookgetfeed.dao.impl.ProfileLogDaoImpl;
import vn.uts.facebookgetfeed.domain.Post;
import vn.uts.facebookgetfeed.domain.ProfileLog;

public class DatabaseManager {

	private static MongoOperations mongo;
	private PostDaoImpl postDaoImpl = new PostDaoImpl();
	private ProfileLogDaoImpl profileLogDaoImpl = new ProfileLogDaoImpl();

	public static MongoOperations getMongoOperation() {
		return mongo;
	}

	public static void setMongoOperation(MongoOperations mongoOperation) {
		mongo = mongoOperation;
	}

	public Post savePost(Post post) {
		return postDaoImpl.save(post);
	}

	public ProfileLog saveProfileLog(ProfileLog log) {
		return profileLogDaoImpl.save(log);
	}

	public Post findPostByPostId(String postId) {
		return postDaoImpl.findByPostId(postId);
	}

	public ProfileLog findProfileLogByProfileId(String profileId) {
		return profileLogDaoImpl.findByProfileId(profileId);
	}

	public boolean existPostId(String postId) {
		return findPostByPostId(postId) != null;
	}
}
