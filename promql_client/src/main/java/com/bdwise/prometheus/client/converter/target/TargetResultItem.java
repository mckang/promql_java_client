package com.bdwise.prometheus.client.converter.target;

import java.util.HashMap;
import java.util.Map;

public class TargetResultItem {
	private Map<String, String> discoveredLabels = new HashMap<String,String>();
	private Map<String, String> label = new HashMap<String,String>();
	private String scrapeUrl;
	private String lastError="";
	private String lastScrape;
	private String health="UNKNOWN";
	
	public Map<String, String> getDiscoveredLabels() {
		return discoveredLabels;
	}
	public void setDiscoveredLabels(Map<String, String> discoveredLabels) {
		this.discoveredLabels = discoveredLabels;
	}
	public Map<String, String> getLabel() {
		return label;
	}
	public void setLabel(Map<String, String> label) {
		this.label = label;
	}
	public String getScrapeUrl() {
		return scrapeUrl;
	}
	public void setScrapeUrl(String scrapeUrl) {
		this.scrapeUrl = scrapeUrl;
	}
	public String getLastError() {
		return lastError;
	}
	public void setLastError(String lastError) {
		this.lastError = lastError;
	}
	public String getLastScrape() {
		return lastScrape;
	}
	public void setLastScrape(String lastScrape) {
		this.lastScrape = lastScrape;
	}
	public String getHealth() {
		return health;
	}
	public void setHealth(String health) {
		this.health = health;
	}
	@Override
	public String toString() {
		return "TargetResultItem [discoveredLabels=" + discoveredLabels + ", label=" + label + ", scrapeUrl="
				+ scrapeUrl + ", lastError=" + lastError + ", lastScrape=" + lastScrape + ", health=" + health + "]";
	}
		
	
}
