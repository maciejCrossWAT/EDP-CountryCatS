package com.maciejcrosswat.projekt.service;

import com.maciejcrosswat.projekt.Main;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class GetQuestionTimerService extends Service<Void> {
    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            protected Void call() throws InterruptedException {
                final int max = 1500;

                while (true) {
                    if (Main.canTimerMove.get()) {
                        for (int i=max; i>=0; i-=1) {
                            if (isCancelled()) {
                                break;
                            }
                            updateProgress(i, max);
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ignored) {

                            }
                        }
                        break;
                    }
                }

                return null;
            }
        };
    }
}
