package net.gameslabs.model;

import net.gameslabs.api.ComponentRegistry;
import net.gameslabs.api.Player;

import java.util.Arrays;

public class Helper {
    public static void logSkill(ComponentRegistry registry, Player player, String skillName, Skills skill) {
        String out = skillName;
        out += ": ";
        out += player.getLevel(registry, skill);
        out += " (";
        out += player.getStats().getXp(skill);
        out += ")";

        log(out);
    }

    public static void log(Object ... arguments) {
        System.out.println(Arrays.asList(arguments).toString());
    }
}
