package com.mapper;

import java.util.List;
import java.util.Map;

import com.pojo.OutletItem;

public interface OutletItemMapper {
	public int insertOutletTable(OutletItem outletItem);
	public int insertDefdoc(Map<String, String> params);
	public int insertRegion(Map<String, String> params);
	public int insertOutletTableBatch(List<OutletItem> outletItemList);
	public int insertDefdocBatch(List<Map<String, String>> defdocItem);
	public int insertRegionBatch(List<Map<String, String>> regionItem);
	
	public String selectSupplyCity(String supplyCity);
	
	public void truncateOutletTable();
	public void updateOutletStatus();
	public void updateOutletAheadSupplyDays();
	public void updateOutletEndSupplyDays();
	public void updateOutletStoreCity();
	public void updateOutletSupplyCity();
	
	public List<Map<String, String>> selectDistinctSystem();
	public List<Map<String, String>> selectDistinctRegion();
	public List<Map<String, String>> selectDistinctRegionSystem();
	
	public int selectCountRegion();
	
	public List<OutletItem> selectOutletData();
}