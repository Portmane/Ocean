import javax.swing.*;
import java.awt.*;


public class ManeProg extends JFrame {
    public ManeProg() {
        setSize(1000, 500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new JLabel("This programme is created to get coordinates of triangular witch coordinates make parties" +
                "where corners of previous it's the central point of the partied."));
        add(new JLabel("Set 3 coordinates of triangular and number of the action have to be done. Coordinates have" +
                "to be set how shone in this example: (x, y)."));
        var jtfA = new JTextField(10);
        jtfA.setText("25, 15");
        var jtfB = new JTextField(10);
        jtfB.setText("75, 30");
        var jtfC = new JTextField(10);
        jtfC.setText("70, 65");
        add(new JLabel("A"));
        add(jtfA);
        add(new JLabel("B"));
        add(jtfB);
        add(new JLabel("C"));
        add(jtfC);
        add(new JLabel("Number of increasing times:"));
        var jtfN = new JTextField(5);
        jtfN.setText("1");
        add(jtfN);
        var but = new JButton("Run");
        add(but);
        var ender = new JLabel("");
        ender.setMinimumSize(new Dimension(100, 100));
        ender.setPreferredSize(new Dimension(1000, 300));
        ender.setMinimumSize(new Dimension(1920, 1000));
        add(ender);

        but.addActionListener(e -> {
            if ((jtfA.getText().equals("")) || jtfB.getText().equals("") || jtfC.getText().equals("") || jtfN.getText().equals("") || jtfN.getText().equals("0")) {
                ender.setText("One of the data is not selected.");
            } else if (!jtfA.getText().contains(",") || !jtfB.getText().contains(",") || !jtfC.getText().contains(",")) {
                ender.setText("2-nd value in A, B, C coordinates is not determined.");
            } else {
                ender.setText("");
                double[] masA = null;
                double[] masB = null;
                double[] masC = null;
                JTextField[] points = {jtfA, jtfB, jtfC};
                for (int i = 0; i < 3; i++) {
                    String[] helper = points[i].getText().replaceAll("[^,\\d]", "").split(",");
                    switch (i) {
                        case 0:
                                masA = new double[]{Double.parseDouble(helper[0]), Double.parseDouble(helper[1])};
                            break;
                        case 1:
                            masB = new double[]{Double.parseDouble(helper[0]), Double.parseDouble(helper[1])};
                            break;
                        case 2:
                            masC = new double[]{Double.parseDouble(helper[0]), Double.parseDouble(helper[1])};
                    }
                }
                double[][] coordinatesOfPoints = {masA, masB, masC};
                if ((masA[0] == masB[0] && masA[1] == masB[1]) || (masA[0] == masC[0] && masA[1] == masC[1]) || (masB[0] == masC[0] && masB[1] == masC[1]))
                    ender.setText("Two points of triangular are sated in the same coordinate.");
                else {
                    double k = (masA[1] - masB[1]) / (masA[0] - masB[0]);
                    double b = masA[1] - k * masA[0];
                    if (masC[1] == k * masC[0] + b)
                        ender.setText("This points are doesn't creating the triangular.");
                    else {
                        double[][] newCoordinatesOfPoints = new double[3][2];
                        int numberOfIterations = Integer.parseInt(jtfN.getText().replaceAll("[^,\\d]", ""));
                        double[] K;
                        double[] vectorK;
                        for (int i = 0; i < numberOfIterations; i++) {
                            for (int j = 0; j < 3; j++) {
                                K = new double[]{(masA[0] + masB[0]) / 2, (masA[1] + masB[1]) / 2};
                                vectorK = new double[]{K[0] - masC[0], K[1] - masC[1]};
                                vectorK[0] = (vectorK[0] - vectorK[0] / 3) * 3;
                                vectorK[1] = (vectorK[1] - vectorK[1] / 3) * 3;
                                newCoordinatesOfPoints[j][0] = masC[0] + vectorK[0];
                                newCoordinatesOfPoints[j][1] = masC[1] + vectorK[1];
                                //System.out.println(j + ": prom: " + "A: " + masA[0] + ", " + masA[1] + ". B: " + masB[0] + ", " + masB[1] + ". C: " + masC[0] + ", " + masC[1]);
                                replacer(coordinatesOfPoints);
                            }
                            setter(coordinatesOfPoints, newCoordinatesOfPoints);
                            ender.setText(ender.getText() + "   Triangular number " + i + ". " + "A: " + masA[0] + ", " + masA[1] + ". B: " + masB[0] + ", " + masB[1] + ". C: " + masC[0] + ", " + masC[1]);
                        }
                    }
                }
            }
        });
        setVisible(true);
    }
    private void replacer(double[][] mas) {
        double[] saver = {mas[1][0], mas[1][1]};
        mas[1][0] = mas[0][0];
        mas[1][1] = mas[0][1];

        mas[0][0] = mas[2][0];
        mas[0][1] = mas[2][1];

        mas[2][0] = saver[0];
        mas[2][1] = saver[1];
    }
    private void setter(double[][] masOld, double[][] masNew) {
        for (int i = 0; i < 3; i++) {
            masOld[i][0] = masNew[i][0];
            masOld[i][1] = masNew[i][1];
        }
    }
}
