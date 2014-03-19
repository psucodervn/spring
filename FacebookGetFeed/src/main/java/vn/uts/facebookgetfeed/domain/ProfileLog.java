package vn.uts.facebookgetfeed.domain;

import org.springframework.data.annotation.Id;

/**
 * @author Hung
 *
 */
public class ProfileLog {

	@Id
	private String id;
	private String profileId;
	private long lastSince;
	private long lastUntil;

	public long getLastSince() {
		return lastSince;
	}

	public void setLastSince(long lastSince) {
		this.lastSince = lastSince;
	}

	public long getLastUntil() {
		return lastUntil;
	}

	public void setLastUntil(long lastUntil) {
		this.lastUntil = lastUntil;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
}
