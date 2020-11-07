package net.gameslabs.model;

import net.gameslabs.api.IPlayer;

import java.util.ArrayList;
import java.util.Arrays;

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
        String out = skillName;
        out += ": Level ";
        out += player.getStats().getLevel(skill);
        out += " (exp: ";
        out += player.getStats().getXp(skill);
        out += ")";

        log(out);
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
