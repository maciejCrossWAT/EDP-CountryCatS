package com.maciejcrosswat.projekt.service;

import com.maciejcrosswat.projekt.Main;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class GetRoundNumberService extends Service<Integer> {

    @Override
    protected Task<Integer> createTask() {
        return new Task<>() {
            protected Integer call() throws InterruptedException {
                int currentRoundNumber = Main.roundNumber.get();
                if (currentRoundNumber < Main.maxRoundNumber)
                    currentRoundNumber++;
                else {
                    currentRoundNumber = 0;
                }


                if (isCancelled()) {
                    return null;
                }

                return currentRoundNumber;
            }
        };
    }
}
