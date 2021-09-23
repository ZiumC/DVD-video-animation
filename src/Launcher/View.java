package Launcher;


import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends JPanel {
    private Model model;
    private Frame frame;
    private JButton start = new JButton("Start");
    private JButton exit = new JButton("Exit");
    private JPanel northPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();

    View(Model model, JFrame fr, String fileName) {
        frame = fr;
        this.model = model;

        northPanel.add(model.addIcon(fileName));
        frame.add(northPanel, "First");

        centerPanel.add(centerInfo());
        frame.add(centerPanel, "Center");

        bottomPanel.add(buttons());
        frame.add(bottomPanel, "Last");
    }

    private JPanel centerInfo() {
        JPanel mainPanel = new JPanel();

        JPanel leftInfo = new JPanel();
        JPanel rightInfo = new JPanel();

        leftInfo.add(new JLabel("Resolutions:"));
        rightInfo.add(model.resolutions());

        mainPanel.add(leftInfo);
        mainPanel.add(rightInfo);

        return mainPanel;
    }

    private JPanel buttons() {
        JPanel mainPanel = new JPanel();

        JPanel bottomPanel = new JPanel();

        JPanel leftButton = new JPanel();
        JPanel rightButton = new JPanel();

        leftButton.add(start);
        start.setMnemonic('S');

        rightButton.add(exit);
        exit.setMnemonic('X');

        bottomPanel.add(leftButton, new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(rightButton, new FlowLayout(FlowLayout.RIGHT));

        mainPanel.add(bottomPanel);
        return mainPanel;
    }

    public JButton getStart() {
        return start;
    }

    public JButton getExit() {
        return exit;
    }

    public Frame getFrame() {
        return frame;
    }
}
