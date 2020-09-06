package com.TBD.backbone.services.id;

import com.TBD.backbone.services.Locator;

public class TestIdService
{
	public static void main(String[] args)
	{
		try
		{
			String nextId = Locator.getInstance().getIdService().getNextIdentifier("Whatever");
			System.out.println("Next Identifier : " + nextId);
		}
		catch (IdException e)
		{
			e.printStackTrace();
		}
	}
}
