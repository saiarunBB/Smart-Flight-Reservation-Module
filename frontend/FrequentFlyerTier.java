package com.flight.reservation.module1;

public enum FrequentFlyerTier {
    STANDARD, SILVER, GOLD, PLATINUM;

    public double getDiscount() {
        switch (this) {
            case SILVER:   return 0.05;
            case GOLD:     return 0.10;
            case PLATINUM: return 0.15;
            default:       return 0.0;
        }
    }
}