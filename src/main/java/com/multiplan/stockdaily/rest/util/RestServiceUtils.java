package com.multiplan.stockdaily.rest.util;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.multiplan.stockdaily.rest.config.InsecureHostnameVerifier;
import com.multiplan.stockdaily.rest.config.InsecureTrustManager;
import com.multiplan.stockdaily.rest.resource.StockPrice;

public class RestServiceUtils {

	public static StockPrice invokeRestService(String ticker,String token) throws Throwable {

		SSLContext sc = SSLContext.getInstance("TLSv1");
        System.setProperty("https.protocols", "TLSv1");	        
       
        TrustManager[] trustAllCerts = { new InsecureTrustManager() };
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HostnameVerifier allHostsValid = new InsecureHostnameVerifier();
        
		Client client = ClientBuilder.newBuilder().sslContext(sc).hostnameVerifier(allHostsValid).build();					
		WebTarget webTarget = client.target("https://api.tiingo.com");
		WebTarget tiingoStockPriceWebTarget = webTarget.path("/tiingo/daily/"+ticker+"/prices");
		Invocation.Builder invocationBuilder  = tiingoStockPriceWebTarget.request(MediaType.APPLICATION_JSON)
				                                .header("Authorization", "Token "+token)
				                                .header("Content-Type", "application/json");
		
		StockPrice[] response = new StockPrice[0];
		response = invocationBuilder.get(response.getClass());			
		response[0].setTicker(ticker);
		return response[0];
		//response[0].setTicker(uriInfo.getBaseUri().toURL().getProtocol());
		 

		
       
	
	}
}
