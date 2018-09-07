package com.bdwise.prometheus.client.converter.status;

import java.util.ArrayList;
import java.util.List;

import com.bdwise.prometheus.client.converter.Result;

public class DefaultConfigResult extends Result<String>{
	List<String> result = new ArrayList<String>();
	public void addData(String data) {
		result.add(data);
	}
	
	@Override
	public List<String> getResult() {
		return result;
	}

	@Override
	public String toString() {
		return "DefaultConfigResult [result=" + result + "]";
	}	

}
