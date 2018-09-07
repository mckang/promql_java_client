package com.bdwise.prometheus.client.converter;

import java.io.IOException;

import com.bdwise.prometheus.client.converter.query.DefaultQueryResult;
import com.bdwise.prometheus.client.converter.query.QueryDataType;
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
