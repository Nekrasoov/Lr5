package ru.nekrasov.lr2.model;

import lombok.Getter;

@Getter
public enum Positions {
    DEV(2.2, false),
    HR(1.2, false),
    TL(2.6,false),
    MANAGER(2.9,true),
    MARK(1.6,false),
    ACCOUNTANT(1.8,true);
    private final double positionCoefficient;
    private final boolean isManager;
    Positions(double positionCoefficient, boolean isManager){
        this.positionCoefficient = positionCoefficient;
        this.isManager = isManager;
    }
}