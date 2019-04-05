package sample;

public class InitiativeEntry {
    private String initName;
    private int initScore;

    public InitiativeEntry(String initName, int initScore) {
        this.initName = initName;
        this.initScore = initScore;
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
}
