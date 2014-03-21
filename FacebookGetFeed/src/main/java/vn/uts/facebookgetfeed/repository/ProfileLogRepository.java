package vn.uts.facebookgetfeed.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import vn.uts.facebookgetfeed.domain.ProfileLog;
import vn.uts.facebookgetfeed.repository.extend.ProfileLogRepositoryExtend;

@Repository
public interface ProfileLogRepository extends
		MongoRepository<ProfileLog, ObjectId>, ProfileLogRepositoryExtend {

	public ProfileLog findByProfileId(String profileId);
}
