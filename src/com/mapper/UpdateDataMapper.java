package com.mapper;

import java.util.Map;

public interface UpdateDataMapper {
	public int updateFiorgvid(Map<String, String> params);
	public int updateLiabilityorg(Map<String, String> params);
	public int updateFinanceorg(Map<String, String> params);
	public int updateLiabilitydept(String yyyyMM);
	public int updateLibdeptvid(String yyyyMM);
	public int updateFipdeptvid(String yyyyMM);
	public int updateFinancedept(String yyyyMM);
	public int updateP2value();
	public int updateP3value();
}