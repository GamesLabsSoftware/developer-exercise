package net.gameslabs.events;

import net.gameslabs.api.IPlayer;
import net.gameslabs.api.PlayerEvent;
import net.gameslabs.model.IFood;

public class EatFoodEvent extends PlayerEvent {
    private IFood food;

    public EatFoodEvent(IPlayer player, IFood food) {
        super(player);
        this.food = food;
    }

    public IFood getFood() {
        return this.food;
    }
}
