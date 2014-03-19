package vn.uts.facebookgetfeed.dao.extend;

import vn.uts.facebookgetfeed.dao.GenericDao;
import vn.uts.facebookgetfeed.domain.ProfileLog;

public interface ProfileLogDao extends GenericDao<ProfileLog> {

	ProfileLog findByProfileId(String profileId);
}
