package com.bdwise.prometheus.client.converter.query;

import java.util.ArrayList;
import java.util.List;

import com.bdwise.prometheus.client.converter.Data;
import com.bdwise.prometheus.client.converter.Result;


public class DefaultQueryResult<T extends Data> extends Result<T>{

	List<T> result = new ArrayList<T>();
	public void addData(T data) {
		result.add(data);
	}
	
	@Override
	public List<T> getResult() {
		return result;
	}

	@Override
	public String toString() {
		return "DefaultQueryResult [result=" + result + "]";
	}
	
	
	
}
