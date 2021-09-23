package Launcher;

import javax.swing.*;

public class FirstMenu extends JFrame {
    private String logoInLauncher = "dvdicon.jpg";
    private String cache_fileName = "cache.txt";
    private Model model;
    private View view;

    public FirstMenu() {
        model = new Model(cache_fileName);
        view = new View(model, this, logoInLauncher);
        setTitle("DVD video animation Launcher");
        defOptions(this);
        add(view);
        Controller controller = new Controller(model, view);
        controller.initController();
    }


    private void defOptions(JFrame jf) {
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(550, 400);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
        jf.setResizable(false);

    }
}
