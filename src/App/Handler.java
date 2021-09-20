package App;


import AboutAppScreen.InfoScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

public class Handler implements ActionListener {
    private JFrame frame;
    private View v;
    private int howManyClicked = 0;
    private InfoScreen infoScreen =  new InfoScreen();

    Handler(JFrame f, View v) {
        frame = f;
        this.v = v;
    }

    public void actionPerformed(ActionEvent event) {
        String source = event.getActionCommand();

        Pattern patternForSpeedMenu = Pattern.compile("S");
        Matcher matcherSpeedCommand = patternForSpeedMenu.matcher(source);
        boolean suitable = matcherSpeedCommand.find();

        if (source.equals("exit")) {

            frame.dispose();
            v.getIcon().resume();
            v.getIcon().interrupt();
            infoScreen.dispose();
            Thread.currentThread().interrupt();

        } else if (suitable) {

            String[] data = source.split("S");
            v.getIcon().setBasicSpeedThread(Integer.parseInt(data[1]));

        } else if (source.equals("P")) {
            v.getIcon().pause();

        } else if (source.equals("R")) {

            v.getIcon().resume();

        } else if (source.equals("Demo")) {

            if (frame.getWidth() > 480 && frame.getHeight() > 640 && howManyClicked == 0){
                JOptionPane.showMessageDialog(null, "Best performance at resolution 640x480 with speed 5x.","Info",JOptionPane.INFORMATION_MESSAGE);
            }
            howManyClicked++;
            v.getIcon().setDemoPosition(240, 240, -v.getAnimationSpeed());

        } else if (source.equals("About")) {

            infoScreen.dispose();
            infoScreen.displayInfo();

        } else {
            int indexOfColor = Integer.parseInt(source);
            frame.setBackground(v.getColors()[indexOfColor]);
        }

    }
}
