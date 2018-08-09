package com.gavs.iot.application;

import java.util.HashSet;

import java.util.Set;
import javax.ws.rs.core.Application;
import com.gavs.iot.api.employee.CardPrintingRepository;
import com.gavs.iot.api.employee.Test;


public class APIService extends Application
{
    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> empty = new HashSet<Class<?>>();

    public APIService() {
        // ADD YOUR RESTFUL RESOURCES HERE
    	this.singletons.add(new CardPrintingRepository());
    	this.singletons.add(new Test());
    }

    public Set<Class<?>> getClasses()
    {
        return this.empty;
    }

    public Set<Object> getSingletons()
    {
        return this.singletons;
    }
}

