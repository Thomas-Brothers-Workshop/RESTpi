package com.ptdev.support;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Config implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        // Do stuff during webapp's startup.
    }
    public void contextDestroyed(ServletContextEvent event) {
        // Do stuff during webapp's shutdown.
    }
}