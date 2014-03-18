package vn.uts.facebookgetfeed;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.social.facebook.api.StatusPost;

import vn.uts.facebookgetfeed.domain.Post;

public class DatabaseManager {

	private static MongoOperations mongo;

	public static MongoOperations getMongoOperation() {
		return mongo;
	}

	public static void setMongoOperation(MongoOperations mongoOperation) {
		mongo = mongoOperation;
	}
	
	public static long foo() {
		long res = mongo.count(new Query(), StatusPost.class);
		return res;
	}
	
	public static boolean savePost(Post post) {
		mongo.save(post);
		return true;
	}
}
