package net.gameslabs.model;

import java.util.ArrayList;
import java.util.Arrays;

import net.gameslabs.api.Component;
import net.gameslabs.api.ComponentRegistry;
import net.gameslabs.api.Player;
import net.gameslabs.components.ChartComponent;
import net.gameslabs.events.ConsumeItemEvent;
import net.gameslabs.events.GetInventoryEvent;
import net.gameslabs.events.GetPlayerLevel;
import net.gameslabs.events.GiveXpEvent;
import net.gameslabs.events.MineOreEvent;
import net.gameslabs.events.ObtainItemEvent;
import net.gameslabs.events.SpellCastEvent;
import net.gameslabs.implem.PlayerImplem;

public class Assignment {

    protected   final   ComponentRegistry   registry;
    private     final   Player              player;

    public Assignment(Component ... myComponentsToAdd) {
        
        registry = new ComponentRegistry();
        
        Arrays.asList(myComponentsToAdd).forEach(registry::registerComponent);
        
        registry.registerComponent(new ChartComponent());
        registry.load();
        
        player = PlayerImplem.newPlayer("PesStringWeeheehee");
    }

    public final void run() {
        
        //REGISTRY TESTER EVENTS
        registry.sendEvent(new GiveXpEvent      (player, Skill.CONSTRUCTION, 25));
        registry.sendEvent(new GiveXpEvent      (player, Skill.EXPLORATION, 25));
        registry.sendEvent(new GiveXpEvent      (player, Skill.MINING, 200));

        registry.sendEvent(new ObtainItemEvent  (player, Item.SILVERLIGHT, 1));
        registry.sendEvent(new ObtainItemEvent  (player, Item.PARTY_HAT, 1));

        //WILL NOT CONSUME BECAUSE ITS AN INVALID AMOUNT (5 - 1 < 0 )
        //registry.sendEvent(new ConsumeItemEvent(player, Item.PARTY_HAT, 5));
        //A remove all function exists in the inventory, this is just to show it handles values.
        registry.sendEvent(new ConsumeItemEvent (player, Item.PARTY_HAT, 1));

        registry.sendEvent(new ObtainItemEvent  (player, Item.FIREBALL_SCROLL, 1));

        registry.sendEvent(new MineOreEvent     (player, Ore.COAL));
        registry.sendEvent(new MineOreEvent     (player, Ore.FOOLS_GOLD));

        registry.sendEvent(new SpellCastEvent   (player, Spell.FIREBALL));


        //TESTER EVENT CHECKS
        if (getLevel(Skill.EXPLORATION)     != 1)   throw new AssignmentFailed("Exploration XP should be set to level 1");
        if (getLevel(Skill.CONSTRUCTION)    != 2)   throw new AssignmentFailed("Construction XP should be set to level 2");
        if (getLevel(Skill.MINING)          != 5)   throw new AssignmentFailed("Mining XP should be set to level 5!");

        if (!hasItem(Item.SILVERLIGHT))             throw new AssignmentFailed("The player SHOULD have Silverlight!");
        if (!hasItem(Item.COAL))                    throw new AssignmentFailed("The player SHOULD have Coal!");
        if (!hasItem(Item.REAL_GOLD_ITS_ALL_MINE))  throw new AssignmentFailed("The player is not a fool!");
        
        if (hasItem(Item.PARTY_HAT))                throw new AssignmentFailed("The player SHOULD NOT have a Party Hat!");

        log("Construction LvL: ",   getLevel(Skill.CONSTRUCTION));
        log("Exploration LvL: ",    getLevel(Skill.EXPLORATION));
        log("Mining Lvl:",          getLevel(Skill.MINING));

        ArrayList<String> current_inventory = new ArrayList<>();

        for ( Item item : Item.values() )
        {
            if(hasItem(item))
                current_inventory.add(item.toString());
        }

        log("Inventory: ", current_inventory);

        registry.unload();
    }

    private boolean hasItem(Item item)
    {
        GetInventoryEvent getInv = new GetInventoryEvent(player);

        registry.sendEvent(getInv);

        return getInv.getInventory().contains(item);
    }

    private int getLevel(Skill skill) {
        GetPlayerLevel getPlayerLevel = new GetPlayerLevel(player, skill);
        registry.sendEvent(getPlayerLevel);
        return getPlayerLevel.getLevel();
    }

    public void log(Object ... arguments) {
        System.out.println(Arrays.asList(arguments).toString());
    }
}
