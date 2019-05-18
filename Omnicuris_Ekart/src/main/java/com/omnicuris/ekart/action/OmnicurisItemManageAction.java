package com.omnicuris.ekart.action;

import java.util.List;
import com.omnicuris.ekart.dao.ItemManageDAO;
import com.omnicuris.ekart.dao.ItemManageDAOImpl;
import com.omnicuris.ekart.pojo.BusinessContext;
import com.omnicuris.ekart.pojo.OcItem;
import com.opensymphony.xwork2.Action;

//Class to manage the CRUD operation on item
//Business context holds the item, order, user details which can be used to process the request.
public class OmnicurisItemManageAction 
{
	private BusinessContext context;
	
	//This method will save the item list given by the Admin user to the database.
	public String addItemToOmnicuris()
	{
		OcItem item = null;
		try
		{
			List<OcItem> itemList = context.getItemList();
			//here it is assumed that user will add one item at a time
			if(null != itemList && itemList.size() > 0)
			{
				item = itemList.get(0);
			}
			
			ItemManageDAO itemDAO = new ItemManageDAOImpl();
			itemDAO.addItemToOC(item);
		}
		catch(Exception e)
		{
			//this can be inserted into error log table
			e.printStackTrace();
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
	
	//This method will get the item list based on the item_id or item_name.
	public String getItemFromOmnicuris()
	{
		List<OcItem> items = null;
		try
		{
			List<OcItem> itemList = context.getItemList();
			OcItem item = null;
			if(null != itemList && itemList.size() > 0)
			{
				item = itemList.get(0);
			}
			
			ItemManageDAO itemDAO = new ItemManageDAOImpl();
			items = itemDAO.getItemFromOC(item);
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
	
	public String updateItemToOmnicuris()
	{
		OcItem item = null;
		try
		{
			List<OcItem> itemList = context.getItemList();
			//here it is assumed that user will add one item at a time
			if(null != itemList && itemList.size() > 0)
			{
				item = itemList.get(0);
			}
			
			ItemManageDAO itemDAO = new ItemManageDAOImpl();
			itemDAO.updateItemToOC(item);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
	
	public String deleteItemFromOmnicuris()
	{
		OcItem item = null;
		try
		{
			List<OcItem> itemList = context.getItemList();
			//here it is assumed that user will add one item at a time
			if(null != itemList && itemList.size() > 0)
			{
				item = itemList.get(0);
			}
			
			ItemManageDAO itemDAO = new ItemManageDAOImpl();
			itemDAO.deleteItemFromOC(item);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return Action.ERROR;
		}
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
