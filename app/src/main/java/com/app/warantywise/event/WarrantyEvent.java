package com.app.warantywise.event;

/**
 * Created by rajnikant on 26/03/18.
 */

public class WarrantyEvent {
    private int position;
    public WarrantyEvent(int position) {
       this.position=position;
    }

    public int getPosition() {
        return position;
    }
}
