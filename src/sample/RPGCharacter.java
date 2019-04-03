package sample;
//Hello world
public class RPGCharacter {

    private String character;
    private String player;
    private String race;
    private String subRace;
    private String primaryClass;
    private String secondaryClass;
    private String status;
    private String lastLocation;
    private String lastPlayed;
    private String level;

    private String c_str;
    private String c_dex;
    private String c_con;
    private String c_int;
    private String c_wis;
    private String c_cha;

    public RPGCharacter(String character, String player, String race, String subRace, String primaryClass,
                        String secondaryClass, String status, String lastLocation, String lastPlayed, String level){
        this.character = character;
        this.player = player;
        this.race = race;
        this.subRace = subRace;
        this.primaryClass = primaryClass;
        this.secondaryClass = secondaryClass;
        this.status = status;
        this.lastLocation = lastLocation;
        this.lastPlayed = lastPlayed;
        this.level = level;
    }

    public RPGCharacter(String character, String player, String race, String subRace, String primaryClass,
                        String secondaryClass, String status, String lastLocation, String lastPlayed, String level,
                        String c_str, String c_dex, String c_con, String c_int, String c_wis, String c_cha){
        this.character = character;
        this.player = player;
        this.race = race;
        this.subRace = subRace;
        this.primaryClass = primaryClass;
        this.secondaryClass = secondaryClass;
        this.status = status;
        this.lastLocation = lastLocation;
        this.lastPlayed = lastPlayed;
        this.level = level;

        this.c_str = c_str;
        this.c_dex = c_dex;
        this.c_con = c_con;
        this.c_int = c_int;
        this.c_wis = c_wis;
        this.c_cha = c_cha;
    }

    public String getCharacter() {
        return character;
    }

    public String getPlayer() {
        return player;
    }

    public String getRace() {
        return race;
    }

    public String getSubRace() {
        return subRace;
    }

    public String getPrimaryClass() {
        return primaryClass;
    }

    public String getSecondaryClass() {
        return secondaryClass;
    }

    public String getStatus() {
        return status;
    }

    public String getLastLocation() {
        return lastLocation;
    }

    public String getLastPlayed() {
        return lastPlayed;
    }

    public String getLevel() {
        return level;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setSubRace(String subRace) {
        this.subRace = subRace;
    }

    public void setPrimaryClass(String primaryClass) {
        this.primaryClass = primaryClass;
    }

    public void setSecondaryClass(String secondaryClass) {
        this.secondaryClass = secondaryClass;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLastLocation(String lastLocation) {
        this.lastLocation = lastLocation;
    }

    public void setLastPlayed(String lastPlayed) {
        this.lastPlayed = lastPlayed;
    }


    public void setLevel(String level){
        this.level = level;
    }

    public String getC_str() {
        return c_str;
    }

    public void setC_str(String c_str) {
        this.c_str = c_str;
    }

    public String getC_dex() {
        return c_dex;
    }

    public void setC_dex(String c_dex) {
        this.c_dex = c_dex;
    }

    public String getC_con() {
        return c_con;
    }

    public void setC_con(String c_con) {
        this.c_con = c_con;
    }

    public String getC_int() {
        return c_int;
    }

    public void setC_int(String c_int) {
        this.c_int = c_int;
    }

    public String getC_wis() {
        return c_wis;
    }

    public void setC_wis(String c_wis) {
        this.c_wis = c_wis;
    }

    public String getC_cha() {
        return c_cha;
    }

    public void setC_cha(String c_cha) {
        this.c_cha = c_cha;
    }
}
