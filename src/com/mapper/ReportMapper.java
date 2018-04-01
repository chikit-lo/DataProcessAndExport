package com.mapper;

import java.util.List;
import java.util.Map;

public interface ReportMapper {
	public List<Map<String, Object>> getKHZKDXSMX_1(Map<String, String> params);
	public List<Map<String, Object>> getKHZKDXSMX_2(Map<String, String> params);
	public List<Map<String, Object>> getXSFPMX(Map<String, String> params);
	public List<Map<String, Object>> getXSMXB(Map<String, String> params);
	public List<Map<String, Object>> getZCXPMX(Map<String, String> params);
	public List<Map<String, Object>> getKHXXB();
}