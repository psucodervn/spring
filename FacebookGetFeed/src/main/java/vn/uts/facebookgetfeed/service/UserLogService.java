package vn.uts.facebookgetfeed.service;

import vn.uts.facebookgetfeed.domain.UserLog;

public interface UserLogService extends GenericService<UserLog> {
	public UserLog findByProfileId(String profileId);
}
