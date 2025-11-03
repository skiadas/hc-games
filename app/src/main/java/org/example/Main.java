package org.example;

import core.GameRunner;
import core.Presenter;
import core.TextPresenter;

public class Main {
    public static void main(String[] args) {
        Presenter presenter = new TextPresenter();
        GameRunner runner = new GameRunner(presenter);
        runner.play();
    }
}
