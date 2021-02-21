package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.events.GiveXpEvent;

public class MyXPBoosterComponent extends Component 
{
    @Override
    public void onLoad() 
    {
        registerEvent(GiveXpEvent.class, this::onGiveXP);
    }

    private void onGiveXP(GiveXpEvent event) 
    {
        switch(event.getSkill())
        {
            case CONSTRUCTION:
            {
                event.setXp(event.getXp() * 2);
            }break;
            
            case EXPLORATION:
            {
            }break;
        }
    }
    @Override
    public void onUnload() {}
}
