package net.gameslabs.model;

public enum Rock {
    COPPER("copper", 1, 10, 100),
    TIN("tin", 1, 10, 101),
    IRON("iron", 2, 20, 102),
    COAL("coal", 5, 50, 103),
    MITHRIL("mithril", 10, 100, 104),
    ADAMANTITE("adamantite", 20, 150, 105),
    RUNITE("runite", 30, 200, 106);

    private final String name;
    private final int levelRequired;
    private final int xpRewarded;
    private final int oreItemId;

    private Rock(String name, int levelRequired, int xpRewarded, int oreItemId) {
        this.name = name;
        this.levelRequired = levelRequired;
        this.xpRewarded = xpRewarded;
        this.oreItemId = oreItemId;
    }

    public String getName() {
        return this.name;
    }

    public int getLevelRequired() {
        return this.levelRequired;
    }

    public int getXpRewarded() {
        return this.xpRewarded;
    }

    public int getOreItemId() {
        return oreItemId;
    }
}
