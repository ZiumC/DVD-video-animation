package AboutAppScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;

public class View extends JPanel {

    private JPanel mainPanel = new JPanel();

    View (JFrame frame){
        mainPanel.setLayout(new GridLayout(3,1));
        mainPanel.add(sound_App(), FlowLayout.LEFT);
        mainPanel.add(logoIcon_App(), FlowLayout.LEFT);
        mainPanel.add(logoIcon_FirstMenu(), FlowLayout.LEFT);
        frame.add(mainPanel);
    }

    private JPanel logoIcon_App(){
        String source = "https://pl.wikipedia.org/wiki/Plik:DVD-Video_Logo.svg";
        JPanel containerForTXT = new JPanel();

        JLabel logoIconDescription = new JLabel("DVD logo which is moving on the screen: ");
        JLabel logoHyperLink = new JLabel("here");
        JLabel ownNote = new JLabel("(Colored and resized by myself).");

        logoHyperLink.addMouseListener(setMouseListener(logoHyperLink, source));

        containerForTXT.add(logoIconDescription);
        containerForTXT.add(logoHyperLink);
        containerForTXT.add(ownNote);

        return containerForTXT;
    }

    private JPanel sound_App(){
        String source = "https://www.youtube.com/watch?v=miZHa7ZC6Z0";
        JPanel containerForTXT = new JPanel();

        JLabel soundDescription = new JLabel("Sound which is played when logo hit corner: ");
        JLabel soundHyperLink = new JLabel("here.");

        soundHyperLink.addMouseListener(setMouseListener(soundHyperLink, source));

        containerForTXT.add(soundDescription);
        containerForTXT.add(soundHyperLink);

        return containerForTXT;
    }

    private JPanel logoIcon_FirstMenu(){
        String source = "https://www.youtube.com/watch?v=z3HtsBNkOBk&t=3s";
        JPanel containerForTXT = new JPanel();

        JLabel logoIconDescription = new JLabel("DVD logo in Launcher used from: ");

        JLabel logoHyperLink = new JLabel("here.");

        logoHyperLink.addMouseListener(setMouseListener(logoHyperLink,source));

        containerForTXT.add(logoIconDescription, FlowLayout.LEFT);
        containerForTXT.add(logoHyperLink, FlowLayout.CENTER);


        return containerForTXT;
    }

    private MouseListener setMouseListener(JLabel textToLink, String source){
        textToLink.setForeground(Color.PINK.darker());

        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(source));
                } catch (Exception exception){
                    exception.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                textToLink.setForeground(Color.BLUE.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                textToLink.setForeground(Color.PINK.darker());

            }
        };
    }

}
