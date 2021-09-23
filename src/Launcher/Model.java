package Launcher;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class Model {
    private Model.HandlerResolutionOption handlerResolutionOption;
    private String[] resolutionsTextArray;
    private String fileName;

    Model(String cache_fileName) {
        fileName = cache_fileName;
    }

    public JLabel addIcon(String fileName) {

        JLabel logoIcon = new JLabel("[Can't load image.]");
        Font font = new Font("Bold", Font.BOLD, 20);
        logoIcon.setForeground(Color.red);
        logoIcon.setFont(font);

        try {
            BufferedImage icon = ImageIO.read(Objects.requireNonNull(this.getClass().getResource(fileName)));
            logoIcon = new JLabel(new ImageIcon(icon));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return logoIcon;
    }

    /*
    This  method is responsible for setting resolution for main app.
    It gives to choose 640x480 4:3(480p), 1280x720 16:9 (720p) and 1920x1200 16:10 (1200p).
     */
    public JComboBox resolutions() {
        int steps = 3;
        int horizontal = 640;
        int vertical = 480;
        int[] horizontalArr = new int[steps];
        int[] verticalArr = new int[steps];
        int tmpH = 0;
        int tmpV = 0;

        int i;
        for (i = 0; i < steps; ++i) {
            tmpH += horizontal;
            if (i == 1) {
                tmpV += vertical / 2;
            } else {
                tmpV += vertical;
            }

            horizontalArr[i] = tmpH;
            verticalArr[i] = tmpV;
        }


        resolutionsTextArray = new String[steps];

        for (i = 0; i < steps; ++i) {
            resolutionsTextArray[i] = horizontalArr[i] + " x " + verticalArr[i];
        }

        JComboBox resolutionsToChoose = new JComboBox(resolutionsTextArray);
        resolutionsToChoose.addActionListener(handlerResolutionOption = new HandlerResolutionOption(resolutionsToChoose));
        return resolutionsToChoose;
    }

    /*
    This method is responsible for saving current chosen resolution into txt file.
    Later that file is read by Model class from App package.
     */

    public void writeCache() {
        File cache = new File(fileName);

        try {
            FileWriter out = new FileWriter(cache);
            if (cache.exists()) {
                cache.delete();
            }
            //there is written text from string array at current index into file. Index comes from JComboBox.
            out.write(resolutionsTextArray[handlerResolutionOption.getIndexAt()]);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
    This class listens which resolution user chosen.
     */
    private class HandlerResolutionOption implements ActionListener {
        private JComboBox resolutions;
        private int indexAt;

        HandlerResolutionOption(JComboBox jcb) {
            resolutions = jcb;
        }

        public void actionPerformed(ActionEvent e) {
            indexAt = resolutions.getSelectedIndex();
        }

        public int getIndexAt() {
            return indexAt;
        }
    }
}
