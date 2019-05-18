package com.omnicuris.ekart.dao;

import java.util.List;
import com.omnicuris.ekart.pojo.OcItem;
import com.omnicuris.ekart.pojo.OcOrder;

public interface ItemManageDAO 
{
	void addItemToOC(OcItem item) throws Exception;
	
	List<OcItem> getItemFromOC(OcItem item)  throws Exception;
	
	void updateItemToOC(OcItem item) throws Exception;
	
	void deleteItemFromOC(OcItem item) throws Exception;
	
	List<OcItem> getAllItemFromOC()  throws Exception;
	
	void putOrderToOC(OcOrder order)  throws Exception;
	
	List<OcOrder> getAllOrderFromOC()  throws Exception;
}
