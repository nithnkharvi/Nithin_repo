package com.omnicuris.ekart.action;

import java.util.List;
import com.omnicuris.ekart.dao.ItemManageDAO;
import com.omnicuris.ekart.dao.ItemManageDAOImpl;
import com.omnicuris.ekart.pojo.OcOrder;
import com.opensymphony.xwork2.Action;

public class AllOcOrderManageAction 
{
	private List<OcOrder> orders;
	
	public String getAllOrderFromOmnicuris()
	{
		try
		{
			ItemManageDAO itemDAO = new ItemManageDAOImpl();
			orders = itemDAO.getAllOrderFromOC();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}

	public List<OcOrder> getOrders()
	{
		return orders;
	}

	public void setOrders(List<OcOrder> orders) 
	{
		this.orders = orders;
	}
}
