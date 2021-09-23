package App;


import javax.swing.*;

public class Application extends JFrame {
    private String sound_fileName = "sound1.wav";
    private String cache_fileName = "cache.txt";
    private int animationSpeed = 2;
    private Model model;

    public Application() {
        model = new Model(sound_fileName,cache_fileName);
        model.readCache();
        defOptions(this);
        View view = new View(this, animationSpeed);

        Controller controller = new Controller(model, view);
        controller.initializeController();


        new Thread(() -> {

            byte sleepTime = 1000/240;

            while(!view.getIcon().isInterrupted()) {

                controller.initializeMusicPlay();

                add(view.getIcon());

                repaint();

                try {
                    Thread.sleep(sleepTime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (!view.getIcon().isInterrupted()) {
                Thread.currentThread().interrupt();
            }

        }).start();
    }

    private void defOptions(JFrame jf) {
        setTitle("DVD animation");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(model.getWight(), model.getHeight());
        jf.setVisible(true);
        jf.setResizable(false);
        setLocationRelativeTo(null);
    }
}
