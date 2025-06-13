package com.maciejcrosswat.projekt.data;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class RankingProperty {
    private IntegerProperty pointsValue;
    private DoubleProperty timeTakenValue;

    public IntegerProperty getPointsValue() {
        return pointsValue;
    }

    public DoubleProperty getTimeTakenValue() {
        return timeTakenValue;
    }

    public void setPointsValue(int value) {
        this.pointsValue.set(value);
    }

    public void setTimeTakenValue(double time) {
        this.timeTakenValue.set(time);
    }

    public RankingProperty(int points, double timeTaken) {
        this.pointsValue = new SimpleIntegerProperty(0);
        this.timeTakenValue = new SimpleDoubleProperty(0.0);
    }
}
