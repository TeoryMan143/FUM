package Intefaces;

import Execute.UI;

import javax.swing.*;
import java.awt.*;

public class Services extends JFrame{
    private JButton btOrder;
    private JPanel pnMain;
    private JButton btVolver;

    public Services() {
        setTitle("FUM diviertete como quieras");
        setContentPane(pnMain);
        setBounds(0,0, 700, 800); // dimensiones iniciales
        setMinimumSize(new Dimension(700, 800)); // dimensiones minimas
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        btOrder.addActionListener(e -> {
            UI.openQueHambre();
            dispose();
        });
        btVolver.addActionListener(e -> {
            UI.openMainPage();
            dispose();
        });
    }
}
