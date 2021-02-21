package net.gameslabs.events;

import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;
import net.gameslabs.model.Spell;

public class SpellCastEvent extends PlayerEvent
{
    private final Spell spell;

    public SpellCastEvent(Player player, Spell spell)
    {
    	super(player);
    	this.spell = spell;
    }

    public Spell        getSpell        () { return spell;      } 
}
