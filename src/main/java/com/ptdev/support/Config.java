package com.ptdev.support;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.ptdev.picore.io.IoContext;


public class Config implements ServletContextListener {
	
    public void contextInitialized(ServletContextEvent event) {
        //Start context
    	IoContext.getInstance();
    }
    public void contextDestroyed(ServletContextEvent event) {
        // Do stuff during webapp's shutdown.
    }
}