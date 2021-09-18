package AboutAppScreen;

import javax.swing.*;

public class InfoScreen extends JFrame {


    private View view = new View(this);

    public void displayInfo(){
        defOptions(this);
        add(view);
    }


    private void defOptions(JFrame jf) {
        setTitle("About Application");
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jf.setSize(350, 250);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }

}
