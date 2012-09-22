package com.setvect.literatureboy.vo.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 권한 맵핑
 * 
 * @version $Id: ConnStat.java 619 2009-06-16 01:28:32Z setvect $
 */
@Entity
@Table(name = "TBAC_AUTH_MAP")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AuthMap {

	/** 복합키 정보 */
	@Id
	private AuthMapKey key = new AuthMapKey();

	/**
	 * @return the authSeq
	 */
	public int getAuthSeq() {
		return key.getAuthSeq();
	}

	/**
	 * @param authSeq
	 *            the authSeq to set
	 */
	public void setAuthSeq(int authSeq) {
		key.setAuthSeq(authSeq);
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return key.getUserId();
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		key.setUserId(userId);
	}

	/**
	 * @return the key
	 */
	public AuthMapKey getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(AuthMapKey key) {
		this.key = key;
	}
}
