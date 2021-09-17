package App;


import java.awt.Component;
import javax.swing.JOptionPane;

public class Controller {
    private Model model;
    private View view;
    private Thread repeatMusicThread;

    Controller(Model m, View v) {
        this.model = m;
        this.view = v;
    }

    public void initializeController() {
        if (this.model.isReadCacheError()) {
            this.view.getIcon().interrupt();
            Thread.currentThread().interrupt();
            this.view.getFrame().dispose();
        }

        this.model.readImages();
        this.view.getIcon().setImages(this.model.getImages());
        this.view.getIcon().setArrayOfImageIndexes(this.model.getArrayOfImageIndexes());
        this.model.readSound();
        if (this.model.isAllProperImagesRead() && this.model.isProperMusicRead()) {
            this.view.getIcon().start();
        } else {
            JOptionPane.showMessageDialog((Component)null, "Unable to read all images or some files witch are necessary to properly run that program.", "ERROR", 0);
            this.view.getIcon().interrupt();
            Thread.currentThread().interrupt();
            this.view.getFrame().dispose();
        }

    }

    public void initializeMusicPlay() {
        int i = 0;
        if (this.view.getIcon().isInterrupted()) {
            this.repeatMusicThread.interrupt();
        }

        if (this.view.getIcon().isHitedCorner()) {
            this.model.getClip().start();
            ++i;
        }

        if (i > 0) {
            this.repeatMusicThread = new Thread(() -> {
                try {
                    this.model.getClip().stop();
                    this.model.getClip().close();
                    this.model.resetAudio();
                    this.model.getClip().start();
                } catch (Exception var2) {
                    var2.printStackTrace();
                }

            });
            this.repeatMusicThread.start();
        }

    }
}
