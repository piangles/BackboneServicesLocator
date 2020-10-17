package org.piangles.backbone.services.id;

import org.piangles.backbone.services.Locator;

import org.piangles.backbone.services.id.IdException;

public class TestIdService
{
	public static void main(String[] args)
	{
		try
		{
			Identifier nextId = Locator.getInstance().getIdService().getNextIdentifier("Whatever");
			System.out.println("Next Identifier : " + nextId.getValue());
		}
		catch (IdException e)
		{
			e.printStackTrace();
		}
	}
}
