package com.omnicuris.ekart.pojo;

import java.util.List;

public class BusinessContext 
{
	private OcUser ocUser;
	
	private List<OcItem> itemList;

	public OcUser getOcUser() 
	{
		return ocUser;
	}

	public void setOcUser(OcUser ocUser) 
	{
		this.ocUser = ocUser;
	}

	public List<OcItem> getItemList() 
	{
		return itemList;
	}

	public void setItemList(List<OcItem> itemList) 
	{
		this.itemList = itemList;
	}
}
