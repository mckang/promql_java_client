package com.bdwise.prometheus.client;

import java.net.MalformedURLException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.bdwise.prometheus.client.builder.AlertManagerMetaQueryBuilder;
import com.bdwise.prometheus.client.builder.InstantQueryBuilder;
import com.bdwise.prometheus.client.builder.LabelMetaQueryBuilder;
import com.bdwise.prometheus.client.builder.QueryBuilderType;
import com.bdwise.prometheus.client.builder.RangeQueryBuilder;
import com.bdwise.prometheus.client.builder.StatusMetaQueryBuilder;
import com.bdwise.prometheus.client.builder.TargetMetaQueryBuilder;
import com.bdwise.prometheus.client.converter.ConvertUtil;
import com.bdwise.prometheus.client.converter.am.DefaultAlertManagerResult;
import com.bdwise.prometheus.client.converter.label.DefaultLabelResult;
import com.bdwise.prometheus.client.converter.query.DefaultQueryResult;
import com.bdwise.prometheus.client.converter.query.MatrixData;
import com.bdwise.prometheus.client.converter.query.QueryResultItemValue;
import com.bdwise.prometheus.client.converter.query.VectorData;
import com.bdwise.prometheus.client.converter.status.DefaultConfigResult;
import com.bdwise.prometheus.client.converter.target.DefaultTargetResult;

import junit.framework.TestCase;

public class PromqlTest extends TestCase {
	private final static String TARGET_SERVER = "http://192.168.66.60:9090";
	
	private RestTemplate template = null;
	
	@Override
	protected void setUp() throws Exception {
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectTimeout(1000);
		httpRequestFactory.setReadTimeout(2000);
		HttpClient httpClient = HttpClientBuilder.create()
		 .setMaxConnTotal(100)
		 .setMaxConnPerRoute(10)
		 .build();
		httpRequestFactory.setHttpClient(httpClient);
		
		template = new RestTemplate(httpRequestFactory);
	}
	
	private static String ConvertEpocToFormattedDate(String format, double epocTime) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(new Date(Math.round(epocTime*1000)));
	}
	
	public void testSimpleRangeQuery() throws MalformedURLException {
		RangeQueryBuilder rangeQueryBuilder =  QueryBuilderType.RangeQuery.newInstance(TARGET_SERVER);
		URI targetUri = rangeQueryBuilder.withQuery("100 - avg(rate(node_cpu{application=\"node_exporter\", mode=\"idle\"}[1m])) by (instance)*100")
		                 .withStartEpochTime(System.currentTimeMillis() / 1000 - 60*10)
		                 .withEndEpochTime(System.currentTimeMillis() / 1000)
		                 .withStepTime("60s")
		                 .build();
		
		System.out.println(targetUri.toURL().toString());
		
		String rtVal = template.getForObject(targetUri, String.class);

		
		
		
		DefaultQueryResult<MatrixData> result = ConvertUtil.convertQueryResultString(rtVal);

		for(MatrixData matrixData : result.getResult()) {
			System.out.println(String.format("%s", matrixData.getMetric().get("instance")));
			for(QueryResultItemValue itemValue : matrixData.getDataValues()) {
				System.out.println(String.format("%s %10.2f ",
						ConvertEpocToFormattedDate("yyyy-MM-dd hh:mm:ss", itemValue.getTimestamp()),
						itemValue.getValue()
						));
			}
		}
		
	}
	
	public void testSimpleVectorQuery() throws MalformedURLException {
		InstantQueryBuilder iqb = QueryBuilderType.InstantQuery.newInstance(TARGET_SERVER);
		URI targetUri = iqb.withQuery("node_cpu{application=\"node_exporter\", mode=\"idle\"}[1m]").build();
		
		
		
		String rtVal = template.getForObject(targetUri, String.class);


		DefaultQueryResult<MatrixData> result = ConvertUtil.convertQueryResultString(rtVal);

		
		for(MatrixData matrixData : result.getResult()) {
			System.out.println(String.format("%s", matrixData.getMetric().get("instance")));
			for(QueryResultItemValue itemValue : matrixData.getDataValues()) {
				System.out.println(String.format("%s %10.2f ",
						ConvertEpocToFormattedDate("yyyy-MM-dd hh:mm:ss", itemValue.getTimestamp()),
						itemValue.getValue()
						));
			}
		}
		
		System.out.println(targetUri.toURL().toString());
//		System.out.println(result);		
	}
	
	public void testSimpleInstantQuery() throws MalformedURLException {
		InstantQueryBuilder iqb = QueryBuilderType.InstantQuery.newInstance(TARGET_SERVER);
		URI targetUri = iqb.withQuery("100 - avg(rate(node_cpu{application=\"node_exporter\", mode=\"idle\"}[1m])) by (instance)*100").build();
		System.out.println(targetUri.toURL().toString());
		
		
		String rtVal = template.getForObject(targetUri, String.class);


		DefaultQueryResult<VectorData> result = ConvertUtil.convertQueryResultString(rtVal);

		
		for(VectorData vectorData : result.getResult()) {
			System.out.println(String.format("%s %s %10.2f", 
					vectorData.getMetric().get("instance"), 
					vectorData.getFormattedTimestamps("yyyy-MM-dd hh:mm:ss"), 
					vectorData.getValue() ));	
		}
		
		System.out.println(result);		
	}	
	
	public void testSimpleLabel() throws MalformedURLException {
		LabelMetaQueryBuilder lmqb = QueryBuilderType.LabelMetadaQuery.newInstance(TARGET_SERVER);
		URI targetUri = lmqb.withLabel("pod").build();
		System.out.println(targetUri.toURL().toString());
		
		
		String rtVal = template.getForObject(targetUri, String.class);


		DefaultLabelResult result = ConvertUtil.convertLabelResultString(rtVal);

		
		System.out.println(result);		
	}
	
	public void testSimpleConfig() throws MalformedURLException {
		StatusMetaQueryBuilder smqb = QueryBuilderType.StatusMetadaQuery.newInstance(TARGET_SERVER);
		URI targetUri = smqb.build();
		System.out.println(targetUri.toURL().toString());
		
		
		String rtVal = template.getForObject(targetUri, String.class);


		DefaultConfigResult result = ConvertUtil.convertConfigResultString(rtVal);

		
		System.out.println(result);		
	}
	
	public void testSimpleTargets() throws MalformedURLException {
		TargetMetaQueryBuilder tmqb = QueryBuilderType.TargetMetadaQuery.newInstance(TARGET_SERVER);
		URI targetUri = tmqb.build();
		System.out.println(targetUri.toURL().toString());
		
		
		String rtVal = template.getForObject(targetUri, String.class);


		DefaultTargetResult result = ConvertUtil.convertTargetResultString(rtVal);

		
		System.out.println(result);		
	}
	
	public void testSimpleAlertManager() throws MalformedURLException {
		AlertManagerMetaQueryBuilder ammqb = QueryBuilderType.AlertManagerMetadaQuery.newInstance(TARGET_SERVER);
		URI targetUri = ammqb.build();
		System.out.println(targetUri.toURL().toString());
		
		
		String rtVal = template.getForObject(targetUri, String.class);


		DefaultAlertManagerResult result = ConvertUtil.convertAlertManagerResultString(rtVal);

		
		System.out.println(result);		
	}
}
