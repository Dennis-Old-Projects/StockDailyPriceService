package com.multiplan.stockdaily.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StockPrice {

	private String adjClose;
	private String adjHigh;
	private String adjLow;
	private String adjOpen;
	private String adjVolume;
	private String close;
	private String date;
	private String divCash;
	private String high;
	private String low;
	private String open;
	private String splitFactor;
	private String volume;	
	private String ticker;
	private String name;
	private String sector;
	
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

	public String getAdjHigh() {
		return adjHigh;
	}

	public void setAdjHigh(String adjHigh) {
		this.adjHigh = adjHigh;
	}

	public String getAdjLow() {
		return adjLow;
	}

	public void setAdjLow(String adjLow) {
		this.adjLow = adjLow;
	}

	public String getAdjOpen() {
		return adjOpen;
	}

	public void setAdjOpen(String adjOpen) {
		this.adjOpen = adjOpen;
	}

	public String getAdjVolume() {
		return adjVolume;
	}

	public void setAdjVolume(String adjVolume) {
		this.adjVolume = adjVolume;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDivCash() {
		return divCash;
	}

	public void setDivCash(String divCash) {
		this.divCash = divCash;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getSplitFactor() {
		return splitFactor;
	}

	public void setSplitFactor(String splitFactor) {
		this.splitFactor = splitFactor;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}
	
	
}
