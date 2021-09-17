package App;


import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class Model {
    private int height;
    private int wight;
    private int[] arrayOfImageIndexes;
    private boolean allProperImagesRead = true;
    private boolean properMusicRead = true;
    private boolean readCacheError = false;
    private BufferedImage[] arrayOfImages;
    private Clip clip;
    private AudioInputStream input;
    private String sound_fileName;
    private String cache_fileName;

    Model(String sound_fileName, String cache_fileName) {
        this.sound_fileName = sound_fileName;
        this.cache_fileName = cache_fileName;
    }

    public void readSound() {
        try {
            clip = AudioSystem.getClip();
            input = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream(sound_fileName)));
        } catch (Exception e) {
            e.printStackTrace();
            properMusicRead = false;
        }

    }

    public void resetAudio() {
        try {
            clip.close();
            input = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream(sound_fileName)));
            clip.open(input);
        } catch (Exception e) {
            e.printStackTrace();
            properMusicRead = false;
        }

    }

    public void readImages() {
        arrayOfImages = new BufferedImage[5];
        arrayOfImageIndexes = new int[arrayOfImages.length];

        try {
            for(int i = 0; i < arrayOfImages.length; ++i) {
                String images_fileNames = i + 1 + ".png";
                arrayOfImageIndexes[i] = i;
                arrayOfImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResource(images_fileNames)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            allProperImagesRead = false;
        }

    }

    public void readCache() {
        String raw_Data = "";

        File cache = new File(cache_fileName);

        if (cache.exists()) {
            try {

                Scanner sc = new Scanner(cache);
                while (sc.hasNextLine()){
                    raw_Data = sc.nextLine();
                }

                sc.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                this.readCacheError = true;
            }

            String[] data = raw_Data.split(" x ");

            try {
                this.wight = Integer.parseInt(data[0]);
                this.height = Integer.parseInt(data[1]);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                this.readCacheError = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Unable to read cache data.", "ERROR", JOptionPane.ERROR_MESSAGE);
            this.readCacheError = true;
        }

    }

    public Clip getClip() {
        return clip;
    }

    public boolean isProperMusicRead() {
        return properMusicRead;
    }

    public boolean isAllProperImagesRead() {
        return allProperImagesRead;
    }

    public BufferedImage[] getImages() {
        return arrayOfImages;
    }

    public int[] getArrayOfImageIndexes() {
        return arrayOfImageIndexes;
    }

    public int getHeight() {
        return height;
    }

    public int getWight() {
        return wight;
    }

    public boolean isReadCacheError() {
        return readCacheError;
    }
}

