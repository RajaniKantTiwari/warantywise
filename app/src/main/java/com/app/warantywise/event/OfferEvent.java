package com.app.warantywise.event;

/**
 * Created by rajnikant on 26/03/18.
 */

public class OfferEvent {
    private int position;
    public OfferEvent(int position) {
        this.position=position;
    }

    public int getPosition() {
        return position;
    }
}
