package vn.uts.facebookgetfeed.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.uts.facebookgetfeed.domain.UserLog;
import vn.uts.facebookgetfeed.repository.UserLogRepository;
import vn.uts.facebookgetfeed.service.UserLogService;

@Service("userLogService")
public class UserLogServiceImpl implements UserLogService {

	@Autowired
	private UserLogRepository userLogRepository;

	@Override
	public UserLog findByProfileId(String userId) {
		return userLogRepository.findByUserId(userId);
	}

	public UserLog save(UserLog userLog) {
		return userLogRepository.save(userLog);
	}
}
