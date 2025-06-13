package com.maciejcrosswat.projekt.service;

import com.maciejcrosswat.projekt.Main;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class GameScoreResetService extends Service<Integer> {

    @Override
    protected Task<Integer> createTask() {
        return new Task<>() {
            protected Integer call() throws InterruptedException {
                return 0;
            }
        };
    }
}
