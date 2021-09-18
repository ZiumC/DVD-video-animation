package App;


import java.awt.Color;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;

public class View extends JPanel {
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenuItem exit;
    private String[] namesOfColors;
    private String[] rangeOfSpeed;
    private Color[] colors;
    private Handler handler_MenuButtons;
    private Icon icon;
    private Font font = new Font(null, Font.BOLD, 12);
    private final int animationSpeed;

    View(JFrame fr, int animationSpeed) {
        frame = fr;

        this.animationSpeed = animationSpeed;
        icon = new Icon(frame, animationSpeed);
        rangeOfSpeed = new String[5];

        for(int i = 0; i < 5; ++i) {
            rangeOfSpeed[i] = i + 1 + "x ";
        }

        namesOfColors = new String[]{"Black", "White"};
        colors = new Color[]{Color.black, Color.white};

        initializeMenuBar();
        frame.setJMenuBar(menuBar);
    }

    private void initializeMenuBar() {
        menuBar = new JMenuBar();

        handler_MenuButtons = new Handler(frame, this);

        JMenu options = new JMenu("Options");
        options.setMnemonic('O');

        JMenu file = new JMenu("File");
        file.setMnemonic('F');

        exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke("ctrl shift X"));
        exit.setMnemonic('X');

        menuBar.add(file);
        menuBar.add(options);

        options.add(initializeMenuBG());
        options.add(initializeSpeedMenu());
        options.add(initializeManipulationMenu());
        options.add(exit);

        file.add(initializeDemoMenu());
        file.add(initializeInfo());
    }

    private JMenuItem initializeInfo(){
        JMenuItem mAbout = new JMenuItem("About app");

        mAbout.addActionListener(handler_MenuButtons);
        mAbout.setActionCommand("About");

        return mAbout;
    }

    private JMenuItem initializeDemoMenu() {
        JMenuItem mDemo = new JMenuItem("Demo");

        mDemo.addActionListener(handler_MenuButtons);
        mDemo.setActionCommand("Demo");

        return mDemo;
    }

    private JMenu initializeManipulationMenu() {
        JMenu mManipulationOptions = new JMenu("Manipulate");

        JMenuItem pause = new JMenuItem("Pause");
        pause.addActionListener(handler_MenuButtons);
        pause.setActionCommand("P");

        JMenuItem resume = new JMenuItem("Resume");
        resume.addActionListener(handler_MenuButtons);
        resume.setActionCommand("R");

        mManipulationOptions.add(pause);
        mManipulationOptions.add(resume);

        return mManipulationOptions;
    }

    private JMenu initializeSpeedMenu() {
        JMenu mSpeedOptions = new JMenu("Speed");
        ButtonGroup buttonGroup = new ButtonGroup();

        for(int i = 0; i < rangeOfSpeed.length; ++i) {
            JRadioButton speedButt = new JRadioButton(rangeOfSpeed[i]);

            speedButt.isSelected();
            buttonGroup.add(speedButt);

            speedButt.setFont(font);
            mSpeedOptions.add(speedButt);
            speedButt.setActionCommand("S" + i);

            speedButt.addActionListener(handler_MenuButtons);
        }

        return mSpeedOptions;
    }

    private JMenu initializeMenuBG() {
        JMenu mBackground = new JMenu("Background");
        ButtonGroup buttonGroup = new ButtonGroup();

        for(int i = 0; i < namesOfColors.length; ++i) {
            JRadioButton bgColor = new JRadioButton(namesOfColors[i]);

            bgColor.isSelected();
            buttonGroup.add(bgColor);

            bgColor.setForeground(colors[i]);
            bgColor.setFont(font);
            bgColor.setActionCommand(String.valueOf(i));
            bgColor.addActionListener(handler_MenuButtons);

            mBackground.add(bgColor);
        }

        exit.setActionCommand("exit");
        exit.addActionListener(handler_MenuButtons);
        return mBackground;
    }

    public Icon getIcon() {
        return icon;
    }

    public Color[] getColors() {
        return colors;
    }

    public JFrame getFrame() {
        return frame;
    }

    public int getAnimationSpeed() {
        return animationSpeed;
    }
}
