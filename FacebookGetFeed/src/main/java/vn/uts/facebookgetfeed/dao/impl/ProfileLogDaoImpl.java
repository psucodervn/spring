package vn.uts.facebookgetfeed.dao.impl;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import vn.uts.facebookgetfeed.DatabaseManager;
import vn.uts.facebookgetfeed.dao.extend.ProfileLogDao;
import vn.uts.facebookgetfeed.domain.ProfileLog;

public class ProfileLogDaoImpl implements ProfileLogDao {

	private MongoOperations mongo;

	public ProfileLogDaoImpl() {
		mongo = DatabaseManager.getMongoOperation();
	}

	@Override
	public ProfileLog findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(ProfileLog object) {
		// TODO Auto-generated method stub

	}

	@Override
	public ProfileLog save(ProfileLog object) {
		mongo.save(object);
		return object;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<ProfileLog> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exist(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ProfileLog findByProfileId(String profileId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("profileId").is(profileId));
		return mongo.findOne(query, ProfileLog.class);
	}

}
