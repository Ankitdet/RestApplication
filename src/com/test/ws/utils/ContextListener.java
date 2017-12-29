package com.test.ws.utils;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

import com.test.ws.logger.Log4jLogger;


public class ContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		System.out.println("Initializing Logger.....");
		String contextPath = sce.getServletContext().getRealPath("");

		org.apache.log4j.Logger logger = null;
		ServletContext servletContext = sce.getServletContext();
		String log4jFile = servletContext.getInitParameter("log4jFileName");
		System.out.println("log4j configuration file:'" + log4jFile + "'");

		DOMConfigurator.configure(contextPath + File.separator + log4jFile);
		logger = LogManager.getLogger("MyApplication");

		if (logger == null) {
			try {
				throw new Exception("Failed to get Logger");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Log4jLogger.setLogger(logger);
	}
}
