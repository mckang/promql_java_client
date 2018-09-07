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
	
	public void testSeriesMetaQueryBuilder() throws MalformedURLException {
		SeriesMetaQueryBuilder smqb = QueryBuilderType.SeriesMetadaQuery.newInstance("http://52.192.4.59:30900");
		URI targetUri = smqb.withSelector("match[]=up&match[]=process_start_time_seconds{job=\"prometheus\"}").build();
		System.out.println(targetUri.toURL().toString());
	}
	
	public void testLabelMetaQueryBuilder() throws MalformedURLException {
		LabelMetaQueryBuilder lmqb = QueryBuilderType.LabelMetadaQuery.newInstance("http://52.192.4.59:30900");
		URI targetUri = lmqb.withLabel("pod").build();
		System.out.println(targetUri.toURL().toString());
	}
	
	public void testStatusMetaQueryBuilder() throws MalformedURLException {
		StatusMetaQueryBuilder smqb = QueryBuilderType.StatusMetadaQuery.newInstance("http://52.192.4.59:30900");
		URI targetUri = smqb.build();
		System.out.println(targetUri.toURL().toString());
	}
	
	public void testTargetsMetaQueryBuilder() throws MalformedURLException {
		TargetMetaQueryBuilder tmqb = QueryBuilderType.TargetMetadaQuery.newInstance("http://52.192.4.59:30900");
		URI targetUri = tmqb.build();
		System.out.println(targetUri.toURL().toString());
	}
	
	public void testAlertManagerMetaQueryBuilder() throws MalformedURLException {
		AlertManagerMetaQueryBuilder ammqb = QueryBuilderType.AlertManagerMetadaQuery.newInstance("http://52.192.4.59:30900");
		URI targetUri = ammqb.build();
		System.out.println(targetUri.toURL().toString());
	}
}
