package com.TBD.backbone.services;

import com.TBD.backbone.services.id.IdException;

public class TestIdService
{
	public static void main(String[] args)
	{
		try
		{
			String nextId = Tier3ServiceLocator.getInstance().getIdService().getNextIdentifier("Whatever");
			System.out.println("Next Identifier : " + nextId);
		}
		catch (IdException e)
		{
			e.printStackTrace();
		}
	}
}
