package com.omnicuris.ekart.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.omnicuris.ekart.pojo.OcItem;
import com.omnicuris.ekart.pojo.OcOrder;
import com.omnicuris.ekart.pojo.OcUser;
import com.omnicuris.ekart.utils.DbConnectionUtil;
import com.omnicuris.ekart.utils.OutOfStockException;

public class ItemManageDAOImpl implements ItemManageDAO  
{
	@Override
	public void addItemToOC(OcItem item) throws Exception 
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction trans = null;
		try
		{
			sessionFactory = DbConnectionUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			System.out.println("Hibernate session created...");
			
			trans = session.beginTransaction();
			session.save(item);
			trans.commit();
			System.out.println("Transaction committed...");
		}
		catch(Throwable e)
		{
			e.printStackTrace();
			throw new Exception("Failed to get Session Factory");
		}
		finally
		{
			if(!sessionFactory.isClosed())
			{
				sessionFactory.close();
				System.out.println("Session closed...");
			}
		}
	}

	@Override
	public List<OcItem> getItemFromOC(OcItem item) throws Exception 
	{
		SessionFactory sessionFact = null;
		Session session = null;
		Transaction trans = null;
		List<OcItem> itemList = null;
		try
		{
			sessionFact = DbConnectionUtil.getSessionFactory();
			session = sessionFact.getCurrentSession();
			trans = session.beginTransaction();
			
			//Get the items based on the values in the OcItem object
			Query query = session.getNamedQuery("OC_QUERY_OC_ITEM_BY_CONDITION");
			query.setString("itemName", item.getItemName());
			query.setInteger("itemId", item.getItemId());
			query.setEntity("i", OcItem.class);
			itemList = query.list();
		}
		catch(Throwable e)
		{
			e.printStackTrace();
			throw new Exception("Failed to get Session Factory");
		}
		finally
		{
			if(!sessionFact.isClosed())
			{
				sessionFact.close();
				System.out.println("Session closed...");
			}
		}
		return itemList;
	}

	@Override
	public void updateItemToOC(OcItem item) throws Exception 
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction trans = null;
		try
		{
			sessionFactory = DbConnectionUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			System.out.println("Hibernate session created...");
			
			trans = session.beginTransaction();
			session.update(item);
			trans.commit();
			System.out.println("Transaction committed...");
		}
		catch(Throwable e)
		{
			e.printStackTrace();
			throw new Exception("Failed to get Session Factory");
		}
		finally
		{
			if(!sessionFactory.isClosed())
			{
				sessionFactory.close();
				System.out.println("Session closed...");
			}
		}
	}

	@Override
	public void deleteItemFromOC(OcItem item) throws Exception 
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction trans = null;
		try
		{
			sessionFactory = DbConnectionUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			System.out.println("Hibernate session created...");
			
			trans = session.beginTransaction();
			session.delete(item);
			trans.commit();
			System.out.println("Transaction committed...");
		}
		catch(Throwable e)
		{
			e.printStackTrace();
			throw new Exception("Failed to get Session Factory");
		}
		finally
		{
			if(!sessionFactory.isClosed())
			{
				sessionFactory.close();
				System.out.println("Session closed...");
			}
		}
	}

	@Override
	public List<OcItem> getAllItemFromOC() throws Exception 
	{
		SessionFactory sessionFact = null;
		Session session = null;
		Transaction trans = null;
		List<OcItem> itemList = null;
		try
		{
			sessionFact = DbConnectionUtil.getSessionFactory();
			session = sessionFact.getCurrentSession();
			trans = session.beginTransaction();
			
			Query query = session.getNamedQuery("OC_QUERY_ALL_OC_ITEM");
			query.setEntity("i", OcItem.class);
			itemList = query.list();
		}
		catch(Throwable e)
		{
			e.printStackTrace();
			throw new Exception("Failed to get Session Factory");
		}
		finally
		{
			if(!sessionFact.isClosed())
			{
				sessionFact.close();
				System.out.println("Session closed...");
			}
		}
		return itemList;
	}

	@Override
	public void putOrderToOC(OcOrder order) throws Exception 
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction trans = null;
		try
		{
			sessionFactory = DbConnectionUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			trans = session.beginTransaction();
			List<OcItem> updateItems = new ArrayList<OcItem>();
			
			for(OcItem temp : order.getItemList())
			{
				Query query = session.getNamedQuery("OC_QUERY_OC_ITEM_BY_CONDITION");
				query.setInteger("itemId", temp.getItemId());
				query.setEntity("i", OcItem.class);
				OcItem item = (OcItem) query.list().get(0);
				
				int remainItem = item.getItemCount() - temp.getItemCount();
				if(remainItem < 0)
				{
					throw new OutOfStockException();
				}
				item.setItemCount(remainItem);
				updateItems.add(item);
			}
			
			for(OcItem temp: updateItems)
			{
				session.update(temp);
			}
			
			for(OcItem  ord : order.getItemList())
			{
				Query query = session.getNamedQuery("OC_INSERT_OC_ORDER_BY_ID");
				query.setLong("orderId", order.getOrderId());
				query.setString("orderStatus", order.getOrderStatus());
				query.setInteger("itemId", ord.getItemId());
				query.setString("emailId", order.getOcUser().getEmailId());
				query.setInteger("itemCount", ord.getItemCount());
				query.executeUpdate();
			}
			
			trans.commit();
			System.out.println("Transaction committed...");
		}
		finally
		{
			if(!sessionFactory.isClosed())
			{
				sessionFactory.close();
				System.out.println("Session closed...");
			}
		}
	}

	@Override
	public List<OcOrder> getAllOrderFromOC() throws Exception 
	{
		SessionFactory sessionFact = null;
		Session session = null;
		Transaction trans = null;
		List<OcOrder> orderList = new ArrayList<OcOrder>();
		try
		{
			sessionFact = DbConnectionUtil.getSessionFactory();
			session = sessionFact.getCurrentSession();
			trans = session.beginTransaction();
			
			Query query = session.getNamedQuery("OC_QUERY_ALL_OC_ORDER");
			List rows = query.list();
			
			OcOrder order = null;
			List<OcItem> items = null;
			for(Object row : rows)
			{
				Map map = (Map)row;
				Long id = (Long) map.get("order_id");
				if(null == order || order.getOrderId() != id)
				{
					order = new OcOrder();
					order.setOrderId(id);
					order.setOrderStatus((String) map.get("order_status"));
					
					OcUser ou = new OcUser();
					ou.setEmailId((String) map.get("email_id"));
					order.setOcUser(ou);
					
					items = new ArrayList<OcItem>();
					order.setItemList(items);
					
					orderList.add(order);
				}
				
				OcItem item = new OcItem();
				item.setItemId((Integer) map.get("item_id"));
				item.setItemCount((Integer) map.get("item_count"));
				items.add(item);
			}
		}
		catch(Throwable e)
		{
			e.printStackTrace();
			throw new Exception("Failed to get Session Factory");
		}
		finally
		{
			if(!sessionFact.isClosed())
			{
				sessionFact.close();
				System.out.println("Session closed...");
			}
		}
		return orderList;
	}
	
	
}
