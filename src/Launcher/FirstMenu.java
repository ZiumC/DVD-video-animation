package Launcher;

import javax.swing.*;
import java.awt.*;

public class FirstMenu extends JFrame {
    private String logoInLauncher = "dvdicon.jpg";
    private String cache_fileName = "cache.txt";
    private Model model;
    private View view;

    public FirstMenu() {
        this.model = new Model(this.cache_fileName);
        this.view = new View(this.model, this, this.logoInLauncher);
        this.setTitle("DVD video animation Launcher");
        this.defOptions(this);
        this.add(this.view);
        Controller controller = new Controller(this.model, this.view);
        controller.initController();
    }


    private void defOptions(JFrame jf) {
        jf.setDefaultCloseOperation(3);
        jf.setSize(350, 450);
        jf.setVisible(true);
        this.setLocationRelativeTo(null);
        jf.setResizable(false);
    }
}
