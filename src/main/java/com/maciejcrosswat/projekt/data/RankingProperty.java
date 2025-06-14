package com.maciejcrosswat.projekt.data;

import javafx.beans.property.*;

public class RankingProperty {
    private StringProperty pointsValue;
    private DoubleProperty timeTakenValue;

    public StringProperty getPointsValue() {
        return pointsValue;
    }

    public DoubleProperty getTimeTakenValue() {
        return timeTakenValue;
    }

    public void setPointsValue(String value) {
        this.pointsValue.set(value);
    }

    public void setTimeTakenValue(double time) {
        this.timeTakenValue.set(time);
    }

    public RankingProperty(String points, double timeTaken) {
        this.pointsValue = new SimpleStringProperty(points);
        this.timeTakenValue = new SimpleDoubleProperty(timeTaken);
    }
}
