package vn.uts.facebookgetfeed.dao.impl;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import vn.uts.facebookgetfeed.DatabaseManager;
import vn.uts.facebookgetfeed.dao.extend.PostDao;
import vn.uts.facebookgetfeed.domain.Post;

public class PostDaoImpl implements PostDao {

	private MongoOperations mongo;

	public PostDaoImpl() {
		mongo = DatabaseManager.getMongoOperation();
	}

	@Override
	public Post findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Post object) {
		// TODO Auto-generated method stub

	}

	@Override
	public Post save(Post object) {
		mongo.save(object);
		return object;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Post> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exist(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Post findByPostId(String postId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("postId").is(postId));
		return mongo.findOne(query, Post.class);
	}

}
