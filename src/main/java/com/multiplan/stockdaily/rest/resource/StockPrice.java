package com.multiplan.stockdaily.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StockPrice {

	private String adjClose;
	private String ticker;
	public String getAdjClose() {
		return adjClose;
	}
	public void setAdjClose(String adjClose) {
		this.adjClose = adjClose;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
}
