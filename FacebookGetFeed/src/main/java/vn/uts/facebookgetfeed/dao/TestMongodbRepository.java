package vn.uts.facebookgetfeed.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import vn.uts.facebookgetfeed.domain.Post;

public interface TestMongodbRepository extends MongoRepository<Post, ObjectId> {

}
