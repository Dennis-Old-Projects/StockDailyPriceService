package com.multiplan.stockdaily.events;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.multiplan.stockdaily.rest.resource.StockPrice;
import com.multiplan.stockdaily.rest.util.RestServiceUtils;

public class StockEventConsumer {
	static private Executor ex = Executors.newFixedThreadPool(10);
	
    public void receive(String event) {
        
        final ObjectMapper mapper = new ObjectMapper();
        
		try {
			final StockPrice sp = mapper.readValue(event, StockPrice.class);
			ex.execute(() -> readStockPrice(mapper, sp));			
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
            	
    }

    /**
     * 
     * @param mapper
     * @param sp
     */
	private void readStockPrice(final ObjectMapper mapper, final StockPrice sp) {
		try {
			StockPrice stockprice = RestServiceUtils.invokeRestService(sp.getTicker(), sp.getToken());
			System.out.println("Recieved "+ mapper.writeValueAsString(stockprice));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
}
