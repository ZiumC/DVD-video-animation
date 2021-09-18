package AboutAppScreen;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InfoScreen extends JFrame {

    private int b  = 1;

    private View view = new View(this);

    public InfoScreen() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("zamkniete chuju");
                b = 0;
            }
        });
    }

    public void displayInfo(){
        defOptions(this);
        add(view);
    }

    public int getB() {
        return b;
    }

    private void defOptions(JFrame jf) {
        setTitle("About Application");
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jf.setSize(300, 300);
        setVisible(true);
        jf.pack();
        setLocationRelativeTo(null);
    }

}
