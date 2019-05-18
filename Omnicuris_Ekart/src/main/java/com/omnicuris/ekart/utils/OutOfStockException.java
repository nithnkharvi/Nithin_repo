package com.omnicuris.ekart.utils;

public class OutOfStockException extends Exception 
{
	private static final long serialVersionUID = 1L;

	public OutOfStockException()
	{
		super("Selected item is out of stock");
	}
}
