package com.multiplan.stockdaily.rest.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.multiplan.stockdaily.rest.service.StockPriceDailyRestService;

@Component
@ApplicationPath("/api")


public class JaxRsJerseyConfig extends ResourceConfig{

  public JaxRsJerseyConfig() {
        registerEndpoints();
   }
    
   private void registerEndpoints() {
       register(StockPriceDailyRestService.class);
   }
     
}