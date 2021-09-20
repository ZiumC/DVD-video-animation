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
    private int[] arrayOfImageIndexes;
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

    public void resume() {

        synchronized (pause_resume_Lock) {
            paused = false;
            pause_resume_Lock.notifyAll();
        }

    }

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
                if (x + vx - img[i].getWidth() + img[i].getWidth() < 0 || x + vx + img[i].getWidth() + 12 > weight) {

                    vx *= -1;
                    imageIndex = arrayOfImageIndexes[horizontalTouches++];

                    if (horizontalTouches == img.length) {
                        horizontalTouches = 0;
                    }

                }

                if (y + vy < 0 || y + vy + 2 * img[i].getHeight() > height) {
                    vy *= -1;

                    if (verticalTouches < 0) {
                        verticalTouches = img.length - 1;
                    }

                    imageIndex = arrayOfImageIndexes[verticalTouches--];
                }
            }

            touchCorner(x, y);
            x += vx;
            y += vy;

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
        if (x == 0 && y == 0) {
            this.hittedCorner = true;
        }

        if (x == parent.getWidth() - img[0].getWidth() - 12 && y == parent.getHeight() - 2 * img[0].getHeight()) {
            hittedCorner = true;
        }

        if (x == 0 && y == parent.getHeight() - 2 * img[0].getHeight()) {
            hittedCorner = true;
        }

        if (x == parent.getWidth() - img[0].getWidth() - 12 && y == 0) {
            hittedCorner = true;
        }

    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(img[imageIndex], x, y, null);
    }

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

    public void setArrayOfImageIndexes(int[] imagesIndex) {
        arrayOfImageIndexes = imagesIndex;
    }
}
