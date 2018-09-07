package com.bdwise.prometheus.client.converter.query;

public class QueryResultItemValue {
	private double timestamp;
	private double value;
	public QueryResultItemValue(double timestamp, double value) {
		super();
		this.timestamp = timestamp;
		this.value = value;
	}
	public double getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(double timestamp) {
		this.timestamp = timestamp;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "QueryResultItemValue [timestamp=" + timestamp + ", value=" + value + "]";
	}

}
