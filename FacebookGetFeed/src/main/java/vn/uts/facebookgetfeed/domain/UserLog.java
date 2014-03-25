package vn.uts.facebookgetfeed.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * @author Hung
 * 
 *         This collection was use to store the interval of time that program
 *         has already get feed in. The last interval of time is from lastSince
 *         to lastUntil, so in next run the program will get feed in (0 ->
 *         lastSince) and (lastUntil -> NOW)
 */
public class UserLog {

	@Id
	private ObjectId id;
	private String userId;
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

	public ObjectId getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
