package vn.uts.facebookgetfeed.dao.extend;

import vn.uts.facebookgetfeed.dao.GenericDao;
import vn.uts.facebookgetfeed.domain.Post;

public interface PostDao extends GenericDao<Post> {
	
	Post findByPostId(String postId);
}
