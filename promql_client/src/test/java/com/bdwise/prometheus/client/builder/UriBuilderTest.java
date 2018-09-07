package com.bdwise.prometheus.client.builder;

import java.net.MalformedURLException;
import java.net.URI;


import junit.framework.TestCase;

public class UriBuilderTest  extends TestCase {

	public void testRangeQueryBuilder() throws MalformedURLException {
		RangeQueryBuilder rangeQueryBuilder =  QueryBuilderType.RangeQuery.newInstance("http://52.192.4.59:30900");
		URI targetUri = rangeQueryBuilder.withQuery("irate(received_api_call_total[60s])")
		                 .withStartEpochTime(System.currentTimeMillis() / 1000 - 60*10)
		                 .withEndEpochTime(System.currentTimeMillis() / 1000)
		                 .withStepTime("60s")
		                 .build();
		
		System.out.println(targetUri.toURL().toString());
	}
	
	public void testInstantQueryBuilder() throws MalformedURLException {
		InstantQueryBuilder iqb = QueryBuilderType.InstantQuery.newInstance("http://52.192.4.59:30900");
		URI targetUri = iqb.withQuery("irate(received_api_call_total[60s])").build();
		System.out.println(targetUri.toURL().toString());
	}
}
