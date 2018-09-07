package com.bdwise.prometheus.client.converter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.bdwise.prometheus.client.converter.am.AlertManagerResultItem;
import com.bdwise.prometheus.client.converter.am.DefaultAlertManagerResult;
import com.bdwise.prometheus.client.converter.label.DefaultLabelResult;
import com.bdwise.prometheus.client.converter.query.DefaultQueryResult;
import com.bdwise.prometheus.client.converter.query.QueryDataType;
import com.bdwise.prometheus.client.converter.series.DefaultSeriesResult;
import com.bdwise.prometheus.client.converter.series.SeriesResultItem;
import com.bdwise.prometheus.client.converter.status.DefaultConfigResult;
import com.bdwise.prometheus.client.converter.target.DefaultTargetResult;
import com.bdwise.prometheus.client.converter.target.TargetResultItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;


public abstract class ConvertUtil {
	
	private static QueryDataType searchDataType(String typeString) {
	    for (QueryDataType each : QueryDataType.class.getEnumConstants()) {
	        if (each.name().compareToIgnoreCase(typeString) == 0) {
	            return each;
	        }
	    }
	    return null;
	}
	
	private static Map<String, String> convertJsonToMap(JsonReader reader) throws IOException {
		Map<String, String> resultMap = new HashMap<String,String>();
		reader.beginObject();
		while(reader.hasNext()) {
			resultMap.put(reader.nextName(), reader.nextString());
		}
		reader.endObject();
		return resultMap;
	}	

	@SuppressWarnings("unchecked")
	public static DefaultAlertManagerResult  convertAlertManagerResultString(String jsonString) {
		Gson gson = new GsonBuilder().registerTypeAdapter(DefaultAlertManagerResult.class, new TypeAdapter<DefaultAlertManagerResult>() {

			@Override
			public void write(JsonWriter out, DefaultAlertManagerResult value) throws IOException {

			}

			@Override
			public DefaultAlertManagerResult read(JsonReader reader) throws IOException {
				DefaultAlertManagerResult amResult = new DefaultAlertManagerResult();
				String status = null;
				reader.beginObject();
				while(reader.hasNext()) {
					String propertyName = reader.nextName();
					if("status".equals(propertyName)) {
						status = reader.nextString(); 
					} else if("data".equals(propertyName)) {
						reader.beginObject();
						while(reader.hasNext()) {
							String _propertyName = reader.nextName();
							if("activeAlertmanagers".equals(_propertyName)) {
								reader.beginArray();
								while(reader.hasNext()) {
									AlertManagerResultItem amResultItem = new AlertManagerResultItem();
									reader.beginObject();
									while(reader.hasNext()) {
										String __propertyName = reader.nextName();
										if("url".equals(__propertyName)) {
											amResultItem.setUrl(reader.nextString());
										} else {
											reader.nextString();
										}
									}
									reader.endObject();
									amResult.addActiveManager(amResultItem);									
								}
								reader.endArray();

							} else if("droppedAlertmanagers".equals(_propertyName)) {
								reader.beginArray();
								while(reader.hasNext()) {
									AlertManagerResultItem amResultItem = new AlertManagerResultItem();
									reader.beginObject();
									while(reader.hasNext()) {
										String __propertyName = reader.nextName();
										if("url".equals(__propertyName)) {
											amResultItem.setUrl(reader.nextString());
										} else {
											reader.nextString();
										}
									}
									reader.endObject();
									amResult.addDroppedManager(amResultItem);
								}
								reader.endArray();
							}
						}
						reader.endObject();
					}
				}
				reader.endObject();
				amResult.setStatus(status);
				return amResult;
			}
			
		}).create();
		return gson.fromJson(jsonString, DefaultAlertManagerResult.class);
	}

	
	@SuppressWarnings("unchecked")
	public static DefaultTargetResult  convertTargetResultString(String jsonString) {
		Gson gson = new GsonBuilder().registerTypeAdapter(DefaultTargetResult.class, new TypeAdapter<DefaultTargetResult>() {

			@Override
			public void write(JsonWriter out, DefaultTargetResult value) throws IOException {

			}

			@Override
			public DefaultTargetResult read(JsonReader reader) throws IOException {
				DefaultTargetResult targetResult = new DefaultTargetResult();
				String status = null;
				reader.beginObject();
				while(reader.hasNext()) {
					String propertyName = reader.nextName();
					if("status".equals(propertyName)) {
						status = reader.nextString(); 
					} else if("data".equals(propertyName)) {
						reader.beginObject();
						while(reader.hasNext()) {
							String _propertyName = reader.nextName();
							if("activeTargets".equals(_propertyName)) {
								reader.beginArray();
								while(reader.hasNext()) {
									TargetResultItem targetResultItem = new TargetResultItem();
									reader.beginObject();
									while(reader.hasNext()) {
										String __propertyName = reader.nextName();
										if("discoveredLabels".equals(__propertyName)) {
											targetResultItem.setDiscoveredLabels(convertJsonToMap(reader));
										} else if("labels".equals(__propertyName)) {
											targetResultItem.setLabel(convertJsonToMap(reader));
										} else if("scrapeUrl".equals(__propertyName)) {
											targetResultItem.setScrapeUrl(reader.nextString());
										} else if("lastError".equals(__propertyName)) {
											targetResultItem.setLastError(reader.nextString());
										} else if("lastScrape".equals(__propertyName)) {
											targetResultItem.setLastScrape(reader.nextString());
										} else if("health".equals(__propertyName)) {
											targetResultItem.setHealth(reader.nextString());
										}
									}
									reader.endObject();
									targetResult.addActiveTarget(targetResultItem);									
								}
								reader.endArray();

							} else if("droppedTargets".equals(_propertyName)) {
								reader.beginArray();
								while(reader.hasNext()) {
									TargetResultItem targetResultItem = new TargetResultItem();
									reader.beginObject();
									while(reader.hasNext()) {
										String __propertyName = reader.nextName();
										if("discoveredLabels".equals(__propertyName)) {
											targetResultItem.setDiscoveredLabels(convertJsonToMap(reader));
										} 
									}
									reader.endObject();
									targetResult.addDroppedTarget(targetResultItem);
								}
								reader.endArray();
							}
						}
						reader.endObject();
					}
				}
				reader.endObject();
				targetResult.setStatus(status);
				return targetResult;
			}
			
		}).create();
		return gson.fromJson(jsonString, DefaultTargetResult.class);
	}

	
	@SuppressWarnings("unchecked")
	public static DefaultConfigResult convertConfigResultString(String jsonString) {
		Gson gson = new GsonBuilder().registerTypeAdapter(DefaultConfigResult.class, new TypeAdapter<DefaultConfigResult>() {

			@Override
			public void write(JsonWriter out, DefaultConfigResult value) throws IOException {

			}

			@Override
			public DefaultConfigResult read(JsonReader reader) throws IOException {
				DefaultConfigResult configResult = new DefaultConfigResult();
				String status = null;
				reader.beginObject();
				while(reader.hasNext()) {
					String propertyName = reader.nextName();
					if("status".equals(propertyName)) {
						status = reader.nextString(); 
					} else if("data".equals(propertyName)) {
						reader.beginObject();
						while(reader.hasNext()) {
							String _propertyName = reader.nextName();
							if("yaml".equals(_propertyName)) {
								configResult.addData(reader.nextString());
							} else {
								reader.nextString();
							}
						}
						reader.endObject();
					}
				}
				reader.endObject();
				configResult.setStatus(status);
				return configResult;
			}
			
		}).create();
		return gson.fromJson(jsonString, DefaultConfigResult.class);
		
	}
	
	@SuppressWarnings("unchecked")
	public static DefaultLabelResult convertLabelResultString(String jsonString) {
		Gson gson = new GsonBuilder().registerTypeAdapter(DefaultLabelResult.class, new TypeAdapter<DefaultLabelResult>() {

			@Override
			public void write(JsonWriter out, DefaultLabelResult value) throws IOException {

			}

			@Override
			public DefaultLabelResult read(JsonReader reader) throws IOException {
				DefaultLabelResult seriesResult = new DefaultLabelResult();
				String status = null;
				reader.beginObject();
				while(reader.hasNext()) {
					String propertyName = reader.nextName();
					if("status".equals(propertyName)) {
						status = reader.nextString(); 
					} else if("data".equals(propertyName)) {
						reader.beginArray();
						while(reader.hasNext()) {
							seriesResult.addData(reader.nextString());
						}
						reader.endArray();
					}
				}
				reader.endObject();
				seriesResult.setStatus(status);
				return seriesResult;
			}
			
		}).create();
		return gson.fromJson(jsonString, DefaultLabelResult.class);
		
	}
	
	
	@SuppressWarnings("unchecked")
	public static DefaultSeriesResult convertSeriesResultString(String jsonString) {
		Gson gson = new GsonBuilder().registerTypeAdapter(DefaultSeriesResult.class, new TypeAdapter<DefaultSeriesResult>() {

			@Override
			public void write(JsonWriter out, DefaultSeriesResult value) throws IOException {

			}

			@Override
			public DefaultSeriesResult read(JsonReader reader) throws IOException {
				DefaultSeriesResult seriesResult = new DefaultSeriesResult();
				String status = null;
				reader.beginObject();
				while(reader.hasNext()) {
					String propertyName = reader.nextName();
					if("status".equals(propertyName)) {
						status = reader.nextString(); 
					} else if("data".equals(propertyName)) {
						reader.beginArray();
						while(reader.hasNext()) {
							SeriesResultItem seriesData = new SeriesResultItem();
							reader.beginObject();
							while(reader.hasNext()) {
								seriesData.put(reader.nextName(), reader.nextString());
							}
							reader.endObject();
							seriesResult.addData(seriesData);
//							System.out.println(seriesData);
						}
						reader.endArray();
					}
				}
				reader.endObject();
				seriesResult.setStatus(status);
				return seriesResult;
			}
			
		}).create();
		return gson.fromJson(jsonString, DefaultSeriesResult.class);
		
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Data> DefaultQueryResult<T> convertQueryResultString(String jsonString) {
		Gson gson = new GsonBuilder().registerTypeAdapter(DefaultQueryResult.class, new TypeAdapter<DefaultQueryResult<T>>() {
			@Override
			public DefaultQueryResult<T> read(JsonReader reader) throws IOException {
				DefaultQueryResult<T> queryResult = new DefaultQueryResult<T>();
				String status = null;
				String resultType = null;
				reader.beginObject();
				while(reader.hasNext()) {
					String propertyName = reader.nextName();
					if("status".equals(propertyName)) {
						status = reader.nextString(); 
					} else if("data".equals(propertyName)) {
						reader.beginObject();
						while(reader.hasNext()) {
							propertyName = reader.nextName();		
							if("resultType".equals(propertyName)) {
								resultType = reader.nextString(); 
							} else if("result".equals(propertyName)) {
								System.out.println("resultType:"+ resultType);
								reader.beginArray();
								while(reader.hasNext()) {
									queryResult.addData((T) searchDataType(resultType).convert(reader));
								}
								reader.endArray();
							}
						}
						reader.endObject();	
					}
				}
				reader.endObject();
				queryResult.setStatus(status);
				queryResult.setResultType(resultType);
				return queryResult;
			}

			@Override
			public void write(JsonWriter arg0, DefaultQueryResult<T> arg1) throws IOException {
			}		
		}).create();
		return gson.fromJson(jsonString, DefaultQueryResult.class);
	}
	
}
