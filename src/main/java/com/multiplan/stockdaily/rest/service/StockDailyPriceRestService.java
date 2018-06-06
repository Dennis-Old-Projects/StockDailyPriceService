package com.multiplan.stockdaily.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.multiplan.stockdaily.rest.resource.StockPrice;


/**
 * API URI : http://{server}:8080/api//stock/daily/WMT
 * @author dennis.chacko
 *
 */
@Component
@Path("/stock/daily")
public class StockDailyPriceRestService {
	
	@GET
    @Path("/{ticker}")
    @Produces({ MediaType.APPLICATION_JSON })
	public StockPrice retrieveStockPrice(@PathParam("ticker") String ticker) {
		StockPrice stockPrice = new StockPrice();
		stockPrice.setTicker(ticker);
		stockPrice.setAdjClose("84.62");
		return stockPrice;
	}
}

