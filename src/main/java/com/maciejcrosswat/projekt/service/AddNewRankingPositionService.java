package com.maciejcrosswat.projekt.service;

import com.maciejcrosswat.projekt.Main;
import com.maciejcrosswat.projekt.data.RankingProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.time.Duration;

public class AddNewRankingPositionService extends Service<RankingProperty> {

    @Override
    protected Task<RankingProperty> createTask() {
        return new Task<RankingProperty>() {
            protected RankingProperty call() throws InterruptedException {

                System.out.println("time taken calculated: " + Duration.between(Main.timeStart, Main.timeEnd).toSeconds());
                System.out.println("points calculated: " + Main.scoreNumber.get() + " " + Main.scoreString.get());

                RankingProperty rankingProperty = new RankingProperty(
                        Main.scoreString.get(),
                        Duration.between(Main.timeStart, Main.timeEnd).toSeconds()
                );

                updateValue(rankingProperty);
                return rankingProperty;
            }
        };
    }
}
