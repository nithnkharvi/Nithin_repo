package com.omnicuris.ekart.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.omnicuris.ekart.dao.ItemManageDAO;
import com.omnicuris.ekart.dao.ItemManageDAOImpl;
import com.omnicuris.ekart.pojo.OcItem;
import com.omnicuris.ekart.pojo.OcOrder;
import com.omnicuris.ekart.utils.Constants;
import com.omnicuris.ekart.utils.OutOfStockException;
import com.opensymphony.xwork2.Action;

public class CustomerOrderManageAction 
{
	private OcOrder order;
	
	public String putOrderInOmnicurisKart()
	{
		try
		{
			order.setOrderId(getUniqueOrderId());
			order.setOrderStatus(Constants.ORDER_APPROVED);
			List<OcItem> itemList = order.getItemList();

			if(null == itemList || itemList.size() <= 0)
			{
				return Action.ERROR;
			}
			
			ItemManageDAO itemDAO = new ItemManageDAOImpl();
			itemDAO.putOrderToOC(order);
		}
		catch(OutOfStockException e)
		{
			//send action result as none, and display corresponding page
			e.printStackTrace();
			return Action.NONE;
		}
		catch(Exception e)
		{
			//this can be inserted into error log table
			e.printStackTrace();
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
	
	public Long getUniqueOrderId()
	{
		String pattern = "yyyyMMddHHmmss";
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		String date = sf.format(new Date());
		return Long.valueOf(date);
	}

	public OcOrder getOrder() 
	{
		return order;
	}

	public void setOrder(OcOrder order) 
	{
		this.order = order;
	}
}
