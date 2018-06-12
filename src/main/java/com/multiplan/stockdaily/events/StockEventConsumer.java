package com.multiplan.stockdaily.events;

public class StockEventConsumer {
	
    public void receive(String event) {
        System.out.println(" [x] Received '" + event + "'");
    }
}
