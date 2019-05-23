package com.bdwise.prometheus.client.builder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class InstantQueryBuilder implements QueryBuilder {
	private static final String TARGET_URI_PATTERN_SUFFIX = "/api/v1/query?query=#{query}&time=#{time}&timeout=#{timeout}";

	private static final String TIME_EPOCH_TIME = "time";
	private static final String TIMEOUT = "timeout";
	private static final String QUERY_STRING = "query";
	
	
	private String targetUriPattern;
	private Map<String, Object> params = new HashMap<String, Object>();
	
	public InstantQueryBuilder(String serverUrl) {
		targetUriPattern = serverUrl+ TARGET_URI_PATTERN_SUFFIX;
		params.put(TIMEOUT, "");
		params.put(TIME_EPOCH_TIME, "");
	}
	
	public InstantQueryBuilder withQuery(String query) {
		try {
			params.put(QUERY_STRING, URLEncoder.encode(query, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public InstantQueryBuilder withEpochTime(long time) {
		params.put(TIME_EPOCH_TIME, time);
		return this;
	}
	
	
	public InstantQueryBuilder withTimeout(String timeout) {
		params.put(TIMEOUT, timeout);
		return this;
	}
	
	public URI build() {
		return URI.create(Utils.namedFormat(targetUriPattern, params));
	}
	
	private boolean validate() {
		return true;
	}
	
	
	
}
