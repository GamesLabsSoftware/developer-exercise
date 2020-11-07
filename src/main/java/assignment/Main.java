package assignment;

import net.gameslabs.model.Engine;

public class Main {
    private static final Loader LOADER = new Loader();

    public static void main(String[] args) {
        LOADER.addExperienceComponent();
        LOADER.addPlayerComponents();
        LOADER.addExpMultiplierComponent();
        LOADER.addMiningComponent();

        new Engine(LOADER.getMainPlayer(), LOADER.getComponents()).run();
    }
}
