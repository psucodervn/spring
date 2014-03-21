package vn.uts.facebookgetfeed.service;

import vn.uts.facebookgetfeed.domain.ProfileLog;

public interface ProfileLogService {
	public ProfileLog save(ProfileLog profileLog);
	public ProfileLog findByProfileId(String profileId);
}
