package com.bdwise.prometheus.client.builder;

import java.net.URI;

public class StatusMetaQueryBuilder implements QueryBuilder {

	private static final String TARGET_URI_PATTERN_SUFFIX = "/api/v1/status/config";
	
	private String targetUriPattern;
	
	public StatusMetaQueryBuilder(String serverUrl) {
		this.targetUriPattern = serverUrl + TARGET_URI_PATTERN_SUFFIX;
	}
	public URI build() {
		return URI.create(targetUriPattern);
	}
	
	private boolean validate() {
		return true;
	}	
}
