package assignment;

import net.gameslabs.model.Engine;

public class Main {
    // Why do you create a static instance that has no function on its own?
   // private static final Loader LOADER = new Loader();

    public static void main(String[] args) {
        // Changing to static references
        Loader.addExperienceComponent();
        Loader.addPlayerComponents();
        Loader.addExpMultiplierComponent();
        Loader.addMiningComponent();

        new Engine(Loader.getPlayers(), Loader.getComponents()).run();
    }
}
