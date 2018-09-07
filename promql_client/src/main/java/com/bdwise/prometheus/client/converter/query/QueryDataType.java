package com.bdwise.prometheus.client.converter.query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.bdwise.prometheus.client.converter.Data;
import com.google.gson.stream.JsonReader;

public enum QueryDataType {
	Matrix{

		@SuppressWarnings("unchecked")
		@Override
		public MatrixData convert(JsonReader reader) throws IOException {
			MatrixData resultDataItem = new MatrixData();
			reader.beginObject();
			while(reader.hasNext()) {
				String name = reader.nextName();
				if("metric".equalsIgnoreCase(name)) {
					Map<String,String> metric = new HashMap<String,String>();
					reader.beginObject();
					while(reader.hasNext()) {
						metric.put(reader.nextName(), reader.nextString());
					}
					reader.endObject();
					resultDataItem.setMetric(metric);
				} else if("values".equalsIgnoreCase(name)) {
					ArrayList<QueryResultItemValue> resultDataItemValue = new ArrayList<QueryResultItemValue>();
					reader.beginArray();
					while(reader.hasNext()) {
						reader.beginArray();
						resultDataItemValue.add(new QueryResultItemValue(reader.nextDouble(), reader.nextDouble()));
						reader.endArray();
					}
					reader.endArray();
					resultDataItem.setDataValues(resultDataItemValue.toArray(new QueryResultItemValue[] {}));
				} 
			}
			reader.endObject();
			return resultDataItem;
		}
		
	},
	Vector{

		@SuppressWarnings("unchecked")
		@Override
		public VectorData convert(JsonReader reader) throws IOException {
			VectorData resultDataItem = new VectorData();
			reader.beginObject();
			while(reader.hasNext()) {
				String name = reader.nextName();
				if("metric".equalsIgnoreCase(name)) {
					Map<String,String> metric = new HashMap<String,String>();
					reader.beginObject();
					while(reader.hasNext()) {
						metric.put(reader.nextName(), reader.nextString());
					}
					reader.endObject();
					resultDataItem.setMetric(metric);
				} else if("value".equalsIgnoreCase(name)) {
					reader.beginArray();
					resultDataItem.setDataValue(new QueryResultItemValue(reader.nextDouble(), reader.nextDouble()));
					reader.endArray();
				} 
			}
			reader.endObject();
			return resultDataItem;
		}
		
	},
	Scalar{

		@SuppressWarnings("unchecked")
		@Override
		public ScalarData convert(JsonReader reader) throws IOException {
			ScalarData resultDataItem = null;
			resultDataItem = new ScalarData(reader.nextDouble(), reader.nextDouble());
			return resultDataItem;
		}
		
	};
	public abstract <T extends Data> T convert(JsonReader reader) throws IOException ;
}
