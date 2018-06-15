package com.multiplan.stockdaily.rest.service;

import java.nio.charset.StandardCharsets;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
import com.multiplan.stockdaily.events.StockEventProducer;
import com.multiplan.stockdaily.rest.resource.StockPrice;
import com.multiplan.stockdaily.rest.util.RestServiceUtils;


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
	
	@Autowired
	private StockEventProducer stockEventProducer;
	
	@GET
    @Path("/{ticker}")
    @Produces({ MediaType.APPLICATION_JSON })
	public Response retrieveStockPrice(@PathParam("ticker") String ticker) {
		
		try {
			String token = env.getProperty("token");
			StockPrice sp = RestServiceUtils.invokeRestService(ticker, token);
			return Response.ok(sp).build(); 
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

		    
		    /*
		    Arrays.stream(response).forEach(sp ->  {
		    	try {
					stockEventProducer.send(mapper.writeValueAsString(sp));
				} catch (Exception e) {
					e.printStackTrace();
				}	
		    });
		    */
		    
		    for (int i=0; i<= 10; i++) {
		    	response[i].setId(i+1);
		    	stockEventProducer.send(mapper.writeValueAsString(response[i]));
		    }
		    
			return Response.ok(response).build();
		}
		catch (Throwable ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		}
	}

	private void doSomething(ObjectMapper mapper, Boolean sp) {
		try {
		stockEventProducer.send(mapper.writeValueAsString(sp));
} catch (Exception e) {
		e.printStackTrace();
}
	}		
		
}

