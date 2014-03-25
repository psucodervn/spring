package vn.uts.facebookgetfeed.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import vn.uts.facebookgetfeed.domain.Action;

public interface ActionRepository extends MongoRepository<Action, ObjectId> {

}
