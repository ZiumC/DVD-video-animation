package App;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Icon extends JPanel implements Runnable {
    private boolean interrupted = false;
    private final JFrame parent;
    private int x;
    private int y;
    private int vx;
    private int vy;
    private final int height;
    private final int weight;
    private int speedThread = 0;
    private BufferedImage[] img;
    private int imageIndex = 0;
    private Thread iconThread;
    private boolean hittedCorner = false;
    private boolean paused = false;
    private final Object pause_resume_Lock = new Object();

    Icon(JFrame fr, int animationSpeed) {
        parent = fr;

        int basicSpeedAnimation = direction(animationSpeed);
        vx = basicSpeedAnimation;
        vy = basicSpeedAnimation;

        height = parent.getHeight();
        weight = parent.getWidth();

        x = (int) (Math.random() * (height - height / 2));
        y = (int) (Math.random() * (weight - weight / 2));

        iconThread = new Thread(this);
    }

    public void start() {
        iconThread.start();
    }

    public void pause() {
        paused = true;
    }

    //this method is responsible for resuming thread which move logo. This operation requires object.
    public void resume() {

        synchronized (pause_resume_Lock) {
            paused = false;
            pause_resume_Lock.notifyAll();
        }

    }

    /*
    this method tells that what direction animation should go.
    If variable 'direction' is true animation goes into positive direction (right)
    If variable 'direction' is false animation goes into negative direction (left)
     */
    private int direction(int speedAnimation) {
        boolean direction = Math.random() > 0.5;
        if (direction) {
            return speedAnimation;
        } else {
            return speedAnimation * -1;
        }
    }

    public void interrupt() {

        interrupted = true;
        Thread.currentThread().interrupt();

    }

    public boolean isInterrupted() {
        return interrupted;
    }

    public boolean isCornerHitted() {
        return hittedCorner;
    }

    public void run() {

        int horizontalTouches = 4;
        int verticalTouches = 2;

        while (!interrupted) {
            //this if statement is responsible for pausing thread responsible for moving logo
            if (paused) {
                try {
                    synchronized (pause_resume_Lock) {
                        pause_resume_Lock.wait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            for (int i = 0; i < img.length; i++) {
            /*
            This if statements are very important because they are responsible for detecting if logo touched border and change color of logo.

            Change of colors when logo hits horizontal borders is very simple. That works like that:
            -temporary variable 'horizontalTouches' basically equals 4.
            -variable 'imageIndex' equals variable 'horizontalTouches' and that temporary variable is increased. When that variable equals img.length, is set to 0.

            Change of colors when logo hits vertical boarders work similar but temporary variable 'verticalTouches' is decreased.
             */
                if (x + vx - img[i].getWidth() + img[i].getWidth() < 0 || x + vx + img[i].getWidth() + 12 > weight) {

                    vx *= -1;
                    imageIndex = horizontalTouches++;

                    if (horizontalTouches == img.length) {
                        horizontalTouches = 0;
                    }

                }

                if (y + vy < 0 || y + vy + 2 * img[i].getHeight() > height) {
                    vy *= -1;

                    if (verticalTouches < 0) {
                        verticalTouches = img.length - 1;
                    }

                    imageIndex = verticalTouches--;
                }
            }

            //this method detects if logo hits the corner.
            touchCorner(x, y);
            x += vx;
            y += vy;


            //This try catch section is responsible for setting animation speed.
            try {

                int basicSpeedThread = 25;
                Thread.sleep(Math.abs(basicSpeedThread - speedThread * 5L));

            } catch (Exception e) {
                e.printStackTrace();
            }
            hittedCorner = false;
        }

    }

    private void touchCorner(int x, int y) {
        //Left upper corner
        if (x == 0 && y == 0) {
            this.hittedCorner = true;
        }
        //Right down corner
        if (x == parent.getWidth() - img[0].getWidth() - 12 && y == parent.getHeight() - 2 * img[0].getHeight()) {
            hittedCorner = true;
        }
        //Left down corner
        if (x == 0 && y == parent.getHeight() - 2 * img[0].getHeight()) {
            hittedCorner = true;
        }
        //Right upper corner
        if (x == parent.getWidth() - img[0].getWidth() - 12 && y == 0) {
            hittedCorner = true;
        }

    }


    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        /*
        this is responsible for displaying images.
        Array img contains 5 images, only variable imageIndex is dynamically changed when logo touches any border.
         */
        g2D.drawImage(img[imageIndex], x, y, null);
    }

    /*
     This method set image logo at that position to show as well as possible one feature: playing music when logo touch every corner.
     Many touches in one minute.
     Best performance in resolution 640x480 at speed 5x.
    */
    public void setDemoPosition(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        vx = direction;
        vy = direction;
    }

    public void setBasicSpeedThread(int speed) {
        speedThread = speed;
    }

    public void setImages(BufferedImage[] img) {
        this.img = img;
    }

}
