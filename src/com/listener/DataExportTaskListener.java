package com.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.timer.TimerManager;

// ����TimerTask��ʱ����ļ�����
public class DataExportTaskListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
		TimerManager timerManager = (TimerManager) applicationContext.getBean("timerManager");
		timerManager.runTask();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
}