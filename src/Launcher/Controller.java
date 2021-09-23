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

        //This lambda expression is responsible for opening main application in new window.
        view.getStart().addActionListener((e) -> {
            model.writeCache();
            view.getFrame().dispose();
            new Application();
        });


        //This lambda expression close launcher if button 'exit' was clicked.
        view.getExit().addActionListener((e) -> {
            view.getFrame().dispose();
            Thread.currentThread().interrupt();
        });


    }
}
