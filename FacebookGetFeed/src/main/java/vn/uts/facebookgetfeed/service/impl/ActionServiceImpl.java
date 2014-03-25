package vn.uts.facebookgetfeed.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.uts.facebookgetfeed.domain.Action;
import vn.uts.facebookgetfeed.repository.ActionRepository;
import vn.uts.facebookgetfeed.service.ActionService;

@Service("actionService")
public class ActionServiceImpl implements ActionService {

	@Autowired
	private ActionRepository actionRepository;

	@Override
	public Action save(Action object) {
		actionRepository.save(object);
		return object;
	}

}
