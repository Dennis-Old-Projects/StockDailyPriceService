package com.multiplan.stockdaily.events;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.multiplan.stockdaily.dao.entity.Security;
import com.multiplan.stockdaily.dao.entity.SecurityPrice;
import com.multiplan.stockdaily.dao.entity.SecurityType;
import com.multiplan.stockdaily.rest.resource.StockPrice;
import com.multiplan.stockdaily.rest.util.RestServiceUtils;
import com.multiplan.stockdaily.service.SecurityService;

@Component
public class StockEventConsumer {
	@Autowired
	private Environment env;
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private SecurityService securityService;
	
	static private Executor executor = Executors.newFixedThreadPool(10);
	//@Resource
	//private ManagedExecutorService executor;
	//@Autowired
	//private DefaultManagedTaskExecutor executor;
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
    public void receive(String event) {
        
        final ObjectMapper mapper = new ObjectMapper();
        
		try {
			final StockPrice sp = mapper.readValue(event, StockPrice.class);
			executor.execute(() -> readStockPrice(mapper, sp));			
			
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
			LOGGER.info("Recieved "+ mapper.writeValueAsString(sp));
			
			StockPrice stockprice = RestServiceUtils.invokeRestService(sp.getTicker(), env.getProperty("token"));
			Security security = new Security();
			security.setId(sp.getId());
			security.setName(sp.getName());
			security.setTicker(stockprice.getTicker());
			
			SecurityType securityType = new SecurityType();
			securityType.setId(Integer.valueOf(1));			
			security.setSecurityType(securityType);
			
			SecurityPrice price = new SecurityPrice();
			price.setAdjClose(Double.valueOf(stockprice.getAdjClose()));
			price.setVolume(Integer.valueOf(stockprice.getVolume()));
			price.setPricedTime(new Date());
			price.setSecurity(security);
			security.getSecurityPrices().add(price);
			
			LOGGER.debug("securityType id "+ securityType.getId());
			securityService.saveSecurity(security);
			
		} catch (Throwable e) {
			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
}
