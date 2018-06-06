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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;

import com.multiplan.stockdaily.rest.config.InsecureHostnameVerifier;
import com.multiplan.stockdaily.rest.config.InsecureTrustManager;
import com.multiplan.stockdaily.rest.resource.StockPrice;


/**
 * API URI : http://{server}:8080/api/stock/daily/{ticker}
 * @author dennis.chacko
 *
 */
@Component
@Path("/stock/daily")
public class StockPriceDailyRestService {
	
	@Context
	private UriInfo uriInfo;
	
	@GET
    @Path("/{ticker}")
    @Produces({ MediaType.APPLICATION_JSON })
	public Response retrieveStockPrice(@PathParam("ticker") String ticker) {
		
		try {
			SSLContext sc = SSLContext.getInstance("TLSv1");
	        System.setProperty("https.protocols", "TLSv1");	
	        String token = System.getProperty("token");
	
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
			//response[0].setTicker(uriInfo.getBaseUri().toURL().getProtocol());
			
			return Response.ok(response[0]).build();
		}
		catch (Throwable ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		}
	}
		
}

