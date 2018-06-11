package com.multiplan.stockdaily.rest.service;

import java.nio.charset.StandardCharsets;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	@Autowired
	private Environment env;
	
	@Context
	private UriInfo uriInfo;
	
	@GET
    @Path("/{ticker}")
    @Produces({ MediaType.APPLICATION_JSON })
	public Response retrieveStockPrice(@PathParam("ticker") String ticker) {
		
		try {
			SSLContext sc = SSLContext.getInstance("TLSv1");
	        System.setProperty("https.protocols", "TLSv1");	
	        String token = env.getProperty("token");
	       
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
	
	@GET
    @Path("/command/{command}")
    @Produces({ MediaType.APPLICATION_JSON })
	public Response updateStockPrices(@PathParam("command") String command) {
		try {			
			ClassPathResource cpr = new ClassPathResource("sp500.json");
			byte[] bdata = FileCopyUtils.copyToByteArray(cpr.getInputStream());
			String sp500  = new String(bdata, StandardCharsets.UTF_8);
		    ObjectMapper mapper = new ObjectMapper();
		    StockPrice[] response = new StockPrice[0];
		    response = mapper.readValue(sp500, response.getClass());
			return Response.ok(response).build();
		}
		catch (Throwable ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		}
	}
		
}

