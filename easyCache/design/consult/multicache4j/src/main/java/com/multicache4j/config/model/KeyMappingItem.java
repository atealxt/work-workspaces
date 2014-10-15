package com.multicache4j.config.model;

import java.io.Serializable;

public class KeyMappingItem implements Serializable {

	private static final long serialVersionUID = 369483380571049497L;
	private String pattern;
	private String remotecache;
	private String localcache;

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getRemotecache() {
		return remotecache;
	}

	public void setRemotecache(String remotecache) {
		this.remotecache = remotecache;
	}

	public String getLocalcache() {
		return localcache;
	}

	public void setLocalcache(String localcache) {
		this.localcache = localcache;
	}

}
