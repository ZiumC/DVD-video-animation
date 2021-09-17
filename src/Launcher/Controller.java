package Launcher;

import App.Application;

public class Controller {
    private Model model;
    private View view;

    Controller(Model m, View v) {
        this.model = m;
        this.view = v;
    }

    public void initController() {
        this.view.getStart().addActionListener((e) -> {
            this.model.writeCache();
            this.view.getFrame().dispose();
            new Application();
        });
        this.view.getExit().addActionListener((e) -> {
            this.view.getFrame().dispose();
            Thread.currentThread().interrupt();
        });
    }
}
