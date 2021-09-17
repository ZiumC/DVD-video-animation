package Launcher;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
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
        this.fileName = cache_fileName;
    }

    public JLabel addIcon(String fileName) {
        JLabel logoIcon = new JLabel("[Can't load image.]");
        Font font = new Font("Bold", Font.BOLD, 20);
        logoIcon.setForeground(Color.red);
        logoIcon.setFont(font);

        try {
            BufferedImage icon = ImageIO.read(Objects.requireNonNull(this.getClass().getResource(fileName)));
            logoIcon = new JLabel(new ImageIcon(icon));
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return logoIcon;
    }

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

        this.resolutionsTextArray = new String[steps];

        for (i = 0; i < steps; ++i) {
            this.resolutionsTextArray[i] = horizontalArr[i] + " x " + verticalArr[i];
        }

        JComboBox resolutionsToChoose = new JComboBox(this.resolutionsTextArray);
        resolutionsToChoose.addActionListener(this.handlerResolutionOption = new Model.HandlerResolutionOption(resolutionsToChoose));
        return resolutionsToChoose;
    }

    public void writeCache() {
        File cache = new File(this.fileName);

        try {
            FileWriter out = new FileWriter(cache);
            if (cache.exists()) {
                cache.delete();
            }

            out.write(this.resolutionsTextArray[this.handlerResolutionOption.getIndexAt()]);
            out.close();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    private class HandlerResolutionOption implements ActionListener {
        private JComboBox resolutions;
        private int indexAt;

        HandlerResolutionOption(JComboBox jcb) {
            this.resolutions = jcb;
        }

        public void actionPerformed(ActionEvent e) {
            this.indexAt = this.resolutions.getSelectedIndex();
        }

        public int getIndexAt() {
            return this.indexAt;
        }
    }
}
