package net.gameslabs.model;

import java.util.EnumMap;
import java.util.Map;

public class Inventory
{
    Map<Item, Integer> inventory;

    public Inventory()
    {
        inventory = new EnumMap<>(Item.class);
    }

    public boolean contains(Item item)
    {
        if(inventory.containsKey(item))
        {
            // CHECK FOR INVALID ITEM ENTRY -- Item amount must be greater than zero.
            if(inventory.get(item) <= 0)
            {
                inventory.remove(item);
                return false;
            }

            //VALID ENTRY
            return true;
        }

        //NO ENTRY
        return false;
    }

    public void addItem(Item item, int amount)
    {
        inventory.put(item, amount);
    }
    
    public void removeItem(Item item)
    {
        if(contains(item))
            inventory.remove(item);
    }
    
    public int getAmount(Item item)
    {
    	if(contains(item))
    	{
    		return inventory.get(item);
    	}
    	
    	return 0;
    }
}
