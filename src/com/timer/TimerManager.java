package com.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.service.ExportCustomerService;
import com.service.ExportMaterialService;
import com.service.ImportOutletService;

@Component
public class TimerManager {
	private static final long PERIOD = 24 * 60 * 60 * 1000;
	private Date exportDate;
	private Date importDate;
	
	@Autowired
	private ExportMaterialService exportMaterialService;
	@Autowired
	private ExportCustomerService exportCustomerService;
	@Autowired
	private ImportOutletService importOutletService;
	
	public TimerManager() {
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 40);
		calendar.set(Calendar.SECOND, 0);
		
		exportDate = calendar.getTime();
		
		calendar.set(Calendar.HOUR_OF_DAY, 01);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		importDate = calendar.getTime();
		
		if (exportDate.before(new Date()))
			exportDate = this.addDay(exportDate, 1);
		if (importDate.before(new Date()))
			importDate = this.addDay(importDate, 1);
	}
	
	public Date addDay(Date date, int day) {
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(date);
		startDate.add(Calendar.DAY_OF_MONTH, day);
		return startDate.getTime();
	}
	
	public void runTask() {
		System.out.println("=====定时任务开始=====");
		System.out.println("数据导出预计执行时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(exportDate));
		System.out.println("数据导入预计执行时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(importDate));
		
		Timer timer = new Timer();
		timer.schedule(exportMaterialService, 1000);
		timer.schedule(exportCustomerService, 1000);
		timer.schedule(importOutletService, 1000);
		//timer.schedule(exportMaterialService, exportDate, PERIOD);
		//timer.schedule(exportCustomerService, exportDate, PERIOD);
		//timer.schedule(importOutletService, importDate, PERIOD);
	}
}
