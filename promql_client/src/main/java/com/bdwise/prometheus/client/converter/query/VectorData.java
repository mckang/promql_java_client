package com.bdwise.prometheus.client.converter.query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bdwise.prometheus.client.converter.Data;


public class VectorData implements Data {

	private Map<String,String> metric = new HashMap<String,String>();
	
	private QueryResultItemValue dataValue;
	

	public Map<String, String> getMetric() {
		return metric;
	}

	public void setMetric(Map<String, String> metric) {
		this.metric = metric;
	}

	public QueryResultItemValue getDataValue() {
		return dataValue;
	}

	public void setDataValue(QueryResultItemValue value) {
		this.dataValue = value;
	}
	

	public double getValue() {
		return dataValue.getValue();
	}
	
	public double getTimestamps() {
		return dataValue.getTimestamp();
	}
	
	public String getFormattedTimestamps(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(new Date(Math.round(dataValue.getTimestamp()*1000)));
	}

	@Override
	public String toString() {
		return "VectorData [metric=" + metric + ", dataValue=" + dataValue + "]";
	}

	
}
