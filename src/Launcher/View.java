package Launcher;


import java.awt.FlowLayout;
import java.awt.Frame;
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
    private JLabel resolutionText = new JLabel("Resolutions:");

    View(Model model, JFrame fr, String fileName) {
        this.frame = fr;
        this.model = model;
        this.northPanel.add(model.addIcon(fileName));
        this.frame.add(this.northPanel, "First");
        this.centerPanel.add(this.centerInfo());
        this.frame.add(this.centerPanel, "Center");
        this.bottomPanel.add(this.buttons());
        this.frame.add(this.bottomPanel, "Last");
    }

    private JPanel centerInfo() {
        JPanel mainPanel = new JPanel();
        JPanel leftInfo = new JPanel();
        JPanel rightInfo = new JPanel();
        leftInfo.add(this.resolutionText, 0);
        rightInfo.add(this.model.resolutions());
        mainPanel.add(leftInfo);
        mainPanel.add(rightInfo);
        mainPanel.setLayout(new FlowLayout());
        return mainPanel;
    }

    private JPanel buttons() {
        JPanel mainPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel leftButton = new JPanel();
        JPanel rightButton = new JPanel();
        leftButton.add(this.start);
        rightButton.add(this.exit);
        this.start.setMnemonic('S');
        this.exit.setMnemonic('X');
        bottomPanel.add(leftButton, new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(rightButton, new FlowLayout(FlowLayout.RIGHT));
        mainPanel.add(bottomPanel, "Last");
        return mainPanel;
    }

    public JButton getStart() {
        return this.start;
    }

    public JButton getExit() {
        return this.exit;
    }

    public Frame getFrame() {
        return this.frame;
    }
}
