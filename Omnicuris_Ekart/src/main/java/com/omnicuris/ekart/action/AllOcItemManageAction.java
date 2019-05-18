package com.omnicuris.ekart.action;

import java.util.List;

import com.omnicuris.ekart.dao.ItemManageDAO;
import com.omnicuris.ekart.dao.ItemManageDAOImpl;
import com.omnicuris.ekart.pojo.BusinessContext;
import com.omnicuris.ekart.pojo.OcItem;
import com.opensymphony.xwork2.Action;

public class AllOcItemManageAction 
{
	private BusinessContext context;
	
	public String getAllItemFromOmnicuris()
	{
		List<OcItem> items = null;
		try
		{
			ItemManageDAO itemDAO = new ItemManageDAOImpl();
			items = itemDAO.getAllItemFromOC();
		}
		catch(Exception e)
		{
			context.setItemList(null);
			//this can be inserted into error log table
			e.printStackTrace();
			return Action.ERROR;
		}
		
		context.setItemList(items);
		return Action.SUCCESS;
	}
	
	public BusinessContext getContext() 
	{
		return context;
	}

	public void setContext(BusinessContext context) 
	{
		this.context = context;
	}
}
