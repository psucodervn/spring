package vn.uts.facebookgetfeed.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * @author Hung
 * 
 * This collection was use to store the interval of time
 * that program has already get feed in.
 * The last interval of time is from lastSince to lastUntil, 
 * so in next run the program will get feed in 
 * (0 -> lastSince) and (lastUntil -> NOW)
 */
public class ProfileLog {

	@Id
	private ObjectId id;
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

	public ObjectId getId() {
		return id;
	}
}
