package net.gameslabs.model;

import net.gameslabs.api.IPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Helper {
    public static void log(Object ... arguments) {
        System.out.println(Arrays.asList(arguments).toString());
    }

    public static void logPlayer(IPlayer player) {
        Helper.log(player);
        Helper.logSkill(player, "CONSTRUCTION", Skills.CONSTRUCTION);
        Helper.logSkill(player, "EXPLORATION", Skills.EXPLORATION);
        Helper.logSkill(player, "MINING", Skills.MINING);
        Helper.log(player.getInventory());
    }

    public static void logSkill(IPlayer player, String skillName, Skills skill) {
        // use a string builder here
        String out = skillName;
        out += ": Level ";
        out += player.getStats().getLevel(skill);
        out += " (exp: ";
        out += player.getStats().getXp(skill);
        out += ")";

        log(out);
    }

    public static List<Skills> getAllSkills() {
        //must be maintained manually for now
        // You shouldn't use this way to create instances + should it really be an array list when it comes to implementation?
        // should never be maintained manually neither.
        return Arrays.asList(Skills.values());
    }
}
