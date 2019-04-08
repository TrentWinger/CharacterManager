package sample;

public class InitiativeEntry {
    private String initName;
    private int initScore;
    private String health;

    public InitiativeEntry(String initName, int initScore, String health) {
        this.initName = initName;
        this.initScore = initScore;
        this.health = health;
    }

    public String getInitName() {
        return initName;
    }

    public void setInitName(String initName) {
        this.initName = initName;
    }

    public int getInitScore() {
        return initScore;
    }

    public void setInitScore(int initScore) {
        this.initScore = initScore;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }
}
