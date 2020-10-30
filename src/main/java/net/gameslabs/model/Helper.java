package net.gameslabs.model;

import net.gameslabs.api.ComponentRegistry;
import net.gameslabs.api.IPlayer;

import java.util.ArrayList;
import java.util.Arrays;

public class Helper {
    public static void logSkill(ComponentRegistry registry, IPlayer player, String skillName, Skills skill) {
        String out = skillName;
        out += ": Level ";
        out += player.getStats().getLevel(skill);
        out += " (exp: ";
        out += player.getStats().getXp(skill);
        out += ")";

        log(out);
    }

    public static void log(Object ... arguments) {
        System.out.println(Arrays.asList(arguments).toString());
    }

    public static ArrayList<Skills> getAllSkills() {
        //must be maintained manually for now
        return new ArrayList<Skills>() {
            {
                add(Skills.CONSTRUCTION);
                add(Skills.EXPLORATION);
                add(Skills.MINING);
            }
        };
    }
}
