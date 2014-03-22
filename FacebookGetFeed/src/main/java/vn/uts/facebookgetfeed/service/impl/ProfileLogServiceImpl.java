package vn.uts.facebookgetfeed.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.uts.facebookgetfeed.domain.ProfileLog;
import vn.uts.facebookgetfeed.repository.ProfileLogRepository;
import vn.uts.facebookgetfeed.service.ProfileLogService;

@Service("profileService")
public class ProfileLogServiceImpl implements ProfileLogService {

	@Autowired
	private ProfileLogRepository profileLogRepository;

	@Override
	public ProfileLog findByProfileId(String profileId) {
		return profileLogRepository.findByProfileId(profileId);
	}

	public ProfileLog save(ProfileLog profileLog) {
		return profileLogRepository.save(profileLog);
	}
}
