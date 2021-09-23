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
        //if any error occurred to read cache, program will not start.
        if (model.isReadCacheError()) {
            view.getIcon().interrupt();
            Thread.currentThread().interrupt();
            view.getFrame().dispose();
        }

        model.readImages(); //reading images into array 'BufferedImage'
        view.getIcon().setImages(model.getImages()); //setting images into private variable 'img' in Icon class.
        model.readSound();


        //if any error occurred to read images or music, program will not start.
        if (model.isProperImagesRead() && model.isProperMusicRead()) {

            view.getIcon().start();

        } else {
            JOptionPane.showMessageDialog(null, "Unable to read all images or some files witch are necessary to properly run that program.", "ERROR", JOptionPane.ERROR_MESSAGE);
            view.getIcon().interrupt();
            Thread.currentThread().interrupt();
            view.getFrame().dispose();
        }

    }

    //this method is called until button 'exit' is clicked.
    public void initializeMusicPlay() {

        if (view.getIcon().isCornerHitted()) {
            model.playAudio();
        }

    }
}
