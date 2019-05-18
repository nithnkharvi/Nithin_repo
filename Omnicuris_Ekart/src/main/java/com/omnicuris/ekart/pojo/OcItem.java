package com.omnicuris.ekart.pojo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="OC_ITEM", uniqueConstraints= {@UniqueConstraint(columnNames={"ITEM_ID"})})
public class OcItem 
{
	@Column(name="ITEM_NAME", nullable=false, length=20)
	private String itemName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ITEM_ID", nullable=false, unique=true, length=10)
	private Integer itemId;
	
	@Column(name="ITEM_PRICE", nullable=false)
	private BigDecimal price;
	
	@Column(name="ITEM_COUNT", nullable=false, length=10)
	private Integer itemCount;
	
	@Column(name="ITEM_DESC", length=40)
	private String itemDesc;
	
	@Column(name="CREATE_ID", length=10)
	private Integer createId;

	public String getItemName() 
	{
		return itemName;
	}

	public void setItemName(String itemName) 
	{
		this.itemName = itemName;
	}

	public Integer getItemId() 
	{
		return itemId;
	}

	public void setItemId(Integer itemId) 
	{
		this.itemId = itemId;
	}

	public BigDecimal getPrice() 
	{
		return price;
	}

	public void setPrice(BigDecimal price) 
	{
		this.price = price;
	}

	public Integer getItemCount() 
	{
		return itemCount;
	}

	public void setItemCount(Integer itemCount) 
	{
		this.itemCount = itemCount;
	}

	public String getItemDesc() 
	{
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) 
	{
		this.itemDesc = itemDesc;
	}

	public Integer getCreateId() 
	{
		return createId;
	}

	public void setCreateId(Integer createId) 
	{
		this.createId = createId;
	}
}
