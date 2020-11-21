package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.api.IPlayer;
import net.gameslabs.events.DeathEvent;
import net.gameslabs.events.GiveExpToPlayerEvent;
import net.gameslabs.model.Helper;
import net.gameslabs.model.Skills;

import java.util.List;

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
        // Never type by implementation, always prefer the interfacce
        List<Skills> skills = Helper.getAllSkills();
        // Use for each when possible (when not using indexes)
        for (Skills skill : skills) {
            player.getStats().setXp(skill, 0);
        }
    }
}
