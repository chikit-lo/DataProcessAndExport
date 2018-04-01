package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mapper.ReportMapper;
import com.pojo.SaleItem;
import com.service.ExportReportsService;
import com.service.ImportOutletService;
import com.service.ProcessSaleData;
import com.service.UpdateDataService;
import com.utils.CSVUtils;
import com.utils.DateUtils;
import com.utils.EmailUtils;
import com.utils.ExcelUtils;


public class TestMybatis {
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//TimerManager tm = (TimerManager) context.getBean("timerManager");
		//tm.runTask();
		//ImportOutletService ios = (ImportOutletService) context.getBean("importOutletService");
		//ios.processData();
		
		//ExportReportsService exportReportsService = (ExportReportsService) context.getBean("exportReportsService");
		//exportReportsService.exportReports();
		
		//UpdateDataService uds = (UpdateDataService) context.getBean("updateDataService");
		//uds.updateData();
		
		ProcessSaleData psd = (ProcessSaleData) context.getBean("processSaleData");
		psd.processData();
		
	}
}