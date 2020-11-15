package model;

import java.util.List;

public class Extra {
    private List<String> bike_uids;
    private String number;
    private String uid;
    private int slots;

    public List<String> getBike_uids() {
        return bike_uids;
    }

    public void setBike_uids(List<String> bike_uids) {
        this.bike_uids = bike_uids;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }
}
