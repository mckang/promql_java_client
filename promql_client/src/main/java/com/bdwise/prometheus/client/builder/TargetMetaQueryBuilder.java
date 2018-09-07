package com.bdwise.prometheus.client.builder;

import java.net.URI;

public class TargetMetaQueryBuilder implements QueryBuilder {

	private static final String TARGET_URI_PATTERN_SUFFIX = "/api/v1/targets";
	
	private String targetUriPattern;
	
	public TargetMetaQueryBuilder(String serverUrl) {
		this.targetUriPattern = serverUrl + TARGET_URI_PATTERN_SUFFIX;
	}

	public URI build() {
		return URI.create(targetUriPattern);
	}
	
	private boolean validate() {
		return true;
	}	
}
