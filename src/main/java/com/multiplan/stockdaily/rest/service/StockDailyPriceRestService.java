package com.multiplan.stockdaily.rest.service;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.multiplan.stockdaily.rest.config.InsecureHostnameVerifier;
import com.multiplan.stockdaily.rest.config.InsecureTrustManager;
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
		
		try {
			SSLContext sc = SSLContext.getInstance("TLSv1");//Java 8
	        System.setProperty("https.protocols", "TLSv1");//Java 8
	
	
	        TrustManager[] trustAllCerts = { new InsecureTrustManager() };
	        sc.init(null, trustAllCerts, new java.security.SecureRandom());
	        HostnameVerifier allHostsValid = new InsecureHostnameVerifier();
	        
			Client client = ClientBuilder.newBuilder().sslContext(sc).hostnameVerifier(allHostsValid).build();
	
			
			
			WebTarget webTarget = client.target("https://api.tiingo.com");
			WebTarget tiingoStockPriceWebTarget = webTarget.path("/tiingo/daily/WMT/prices");
			Invocation.Builder invocationBuilder  = tiingoStockPriceWebTarget.request(MediaType.APPLICATION_JSON)
					                                .header("Authorization", "Token 67437b7b696a5ff08541da926f0a8a7d7027bebd")
					                                .header("Content-Type", "application/json");
			
			StockPrice[] response = new StockPrice[0];
			response = invocationBuilder.get(response.getClass());
			
			
			
			
			
			StockPrice stockPrice = new StockPrice();
			stockPrice.setTicker(ticker);
			stockPrice.setAdjClose("84.62");
			return response[0];
		}
		catch (Throwable ex) {
			ex.printStackTrace();
			StockPrice stockPrice = new StockPrice();
			stockPrice.setTicker(ticker);
			stockPrice.setAdjClose("84.61");
			return stockPrice;
		}
	}
		
}

