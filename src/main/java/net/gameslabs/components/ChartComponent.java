package net.gameslabs.components;

import net.gameslabs.api.Component;
import net.gameslabs.events.GiveExpToPlayerEvent;
import net.gameslabs.events.ResetExpEvent;
import net.gameslabs.model.Helper;
import net.gameslabs.model.Skills;

import java.util.ArrayList;

public class ChartComponent extends Component {
    public static final int XP_STEP = 50;

    public ChartComponent() {
    }

    @Override
    public void onLoad() {
        registerEvent(GiveExpToPlayerEvent.class, this::onGiveExpToPlayer);
        registerEvent(ResetExpEvent.class, this::onResetExp);
    }

    private void onGiveExpToPlayer(GiveExpToPlayerEvent event) {
        event.getPlayer().getStats().addXp(event.getSkill(), event.getExp());
    }

    private void onResetExp(ResetExpEvent event) {
         ArrayList<Skills> skills = Helper.getAllSkills();

         for (int i = 0; i < skills.size(); i++) {
             event.getPlayer().getStats().setXp(skills.get(i), 0);
         }
    }

    @Override
    public void onUnload() {
        // Nothing to do
    }
}
