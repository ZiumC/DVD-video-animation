package App;


import javax.swing.JOptionPane;

public class Controller {
    private Model model;
    private View view;

    Controller(Model m, View v) {
        model = m;
        view = v;
    }

    public void initializeController() {
        if (model.isReadCacheError()) {
            view.getIcon().interrupt();
            Thread.currentThread().interrupt();
            view.getFrame().dispose();
        }

        model.readImages();
        view.getIcon().setImages(model.getImages());
        view.getIcon().setArrayOfImageIndexes(model.getArrayOfImageIndexes());
        model.readSound();

        if (model.isAllProperImagesRead() && model.isProperMusicRead()) {

            view.getIcon().start();

        } else {
            JOptionPane.showMessageDialog(null, "Unable to read all images or some files witch are necessary to properly run that program.", "ERROR", JOptionPane.ERROR_MESSAGE);
            view.getIcon().interrupt();
            Thread.currentThread().interrupt();
            view.getFrame().dispose();
        }

    }

    public void initializeMusicPlay() {

        if (view.getIcon().isCornerHitted()) {
            model.playAudio();
        }

    }
}
