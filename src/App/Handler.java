package App;


import AboutAppScreen.InfoScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;

public class Handler implements ActionListener {
    private JFrame frame;
    private View v;
    private int i = 0;

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
            v.getIcon().interrupt();
            Thread.currentThread().interrupt();

        } else if (suitable) {

            String[] data = source.split("S");
            v.getIcon().setBasicSpeedThread(Integer.parseInt(data[1]));

        } else if (source.equals("P")) {
            i = 0;
            v.getIcon().pause();

        } else if (source.equals("R")) {

            v.getIcon().resume();

        } else if (source.equals("Demo")) {

            v.getIcon().setDemoPosition(240, 240, -v.getAnimationSpeed());

        } else if (source.equals("About")) {
            InfoScreen infoScreen =  new InfoScreen();

            if (i == 0){
                infoScreen.displayInfo();
            }

            i++;

            i = infoScreen.getB();

        } else {
            int indexOfColor = Integer.parseInt(source);
            frame.setBackground(v.getColors()[indexOfColor]);
        }

    }
}
