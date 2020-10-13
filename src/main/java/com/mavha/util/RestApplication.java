package com.mavha.util;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.mavha.routes.ListingApi;

@ApplicationPath("/api")
public class RestApplication extends Application {
	
	@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();

        resources.add(org.codehaus.jackson.jaxrs.JacksonJsonProvider.class);
        addRestResourceClasses(resources);
        return resources;
    }
	
    private void addRestResourceClasses(Set<Class<?>> resources) {
    	resources.add(ListingApi.class);
    }
}
