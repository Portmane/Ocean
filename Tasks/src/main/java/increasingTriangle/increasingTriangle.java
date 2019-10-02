package increasingTriangle;


import javax.swing.*;
public class increasingTriangle {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ManeProg();
            }
        });
    }
}
