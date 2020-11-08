package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.api.IPlayer;
import net.gameslabs.events.DeathEvent;
import net.gameslabs.events.GiveExpToPlayerEvent;
import net.gameslabs.model.Helper;
import net.gameslabs.model.Skills;

import java.util.ArrayList;

public class ExperienceComponent extends Component {
    public static final int XP_STEP = 50;

    @Override
    public void onLoad() {
        registerEvent(GiveExpToPlayerEvent.class, this::onGiveExpToPlayer);
        registerEvent(DeathEvent.class, this::onDeath);
    }

    @Override
    public void onUnload() {
    }

    private void onGiveExpToPlayer(GiveExpToPlayerEvent event) {
        event.getPlayer().getStats().addXp(event.getSkill(), event.getExp());
    }

    private void onDeath(DeathEvent event) {
        resetExp(event.getPlayer());
    }

    private void resetExp(IPlayer player) {
         ArrayList<Skills> skills = Helper.getAllSkills();

         for (int i = 0; i < skills.size(); i++) {
             player.getStats().setXp(skills.get(i), 0);
         }
    }
}
