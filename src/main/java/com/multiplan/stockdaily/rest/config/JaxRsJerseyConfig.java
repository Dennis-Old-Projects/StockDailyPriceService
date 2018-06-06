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
/*
public class JaxRsJerseyConfig extends Application {
	@Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(CatalogRestService.class);
        return classes;
    }
	
	@Override
    public Map<String, Object> getProperties() {
        final Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("jersey.config.server.provider.packages",
                       "com.multiplan.catalog.rest.service");
        return properties;
    }
}
*/