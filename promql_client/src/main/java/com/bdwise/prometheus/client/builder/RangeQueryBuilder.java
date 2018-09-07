package com.bdwise.prometheus.client.builder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;


public class RangeQueryBuilder implements QueryBuilder {
	private static final String TARGET_URI_PATTERN_SUFFIX = "/api/v1/query_range?query=#{query}&start=#{start}&end=#{end}&step=#{step}";

	private static final String START_TIME_EPOCH_TIME = "start";
	private static final String END_TIME_EPOCH_TIME = "end";
	private static final String STEP_TIME = "step";
	private static final String QUERY_STRING = "query";
	

	private String targetUriPattern;
	private Map<String, Object> params = new HashMap<String, Object>();
	
	public RangeQueryBuilder(String serverUrl) {
		targetUriPattern = serverUrl+ TARGET_URI_PATTERN_SUFFIX;
	}
	
	public RangeQueryBuilder withQuery(String query) {
		params.put(QUERY_STRING, query);
		return this;
	}
	
	public RangeQueryBuilder withStartEpochTime(long startTime) {
		params.put(START_TIME_EPOCH_TIME, startTime);
		return this;
	}
	
	public RangeQueryBuilder withEndEpochTime(long endTime) {
		params.put(END_TIME_EPOCH_TIME, endTime);
		return this;
	}
	
	public RangeQueryBuilder withStepTime(String step) {
		params.put(STEP_TIME, step);
		return this;
	}
	
	public URI build() {
		return URI.create(Utils.namedFormat(targetUriPattern, params));
	}
	
	private boolean validate() {
		return true;
	}
	
	
	
}
