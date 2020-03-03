package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.events.SkillLevel.GiveXpEvent;
import net.gameslabs.model.SkillLevel.Skill;

public class MyXPBoosterComponent extends Component
{
    private Skill[] dxpSkills = { Skill.CONSTRUCTION };

    @Override
    public void onLoad() {
        registerEvent(GiveXpEvent.class, this::onGiveXP);
    }

    private void onGiveXP(GiveXpEvent event) {
        int xpGiven = event.getXp();
        Skill eventSkill = event.getSkill();

        // Edit the MyXPBoosterComponent to enable DXP in the construction skill
        // if I want to enable multiple skills in the future, an easy to manage array
        // at the top of the file will be simple enough
        for(Skill skill : dxpSkills) {
            if (eventSkill == skill) {
                event.setXp(xpGiven * 2);
            }
        }
    }

    @Override
    public void onUnload() {
        // Do nothing
    }
}
