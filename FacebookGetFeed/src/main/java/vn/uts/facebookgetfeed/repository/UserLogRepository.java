package vn.uts.facebookgetfeed.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import vn.uts.facebookgetfeed.domain.UserLog;
import vn.uts.facebookgetfeed.repository.extend.UserLogRepositoryExtend;

@Repository
public interface UserLogRepository extends
		MongoRepository<UserLog, ObjectId>, UserLogRepositoryExtend {

	public UserLog findByUserId(String userId);
}
