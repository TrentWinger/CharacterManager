package sample;

import java.util.GregorianCalendar;

public class campaignTrack {
    String campaignName;
    GregorianCalendar time;
    int era;
    String timeOfDay;

    public campaignTrack(String campaignName, GregorianCalendar calendar, int era, String timeOfDay){
        this.campaignName = campaignName;
        this.time = calendar;
        this.era = era;
        this.timeOfDay = timeOfDay;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public GregorianCalendar getTime() {
        return time;
    }

    public void setTime(GregorianCalendar time) {
        this.time = time;
    }

    public int getEra() {
        return era;
    }

    public void setEra(int era) {
        this.era = era;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }
}
