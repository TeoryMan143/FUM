package Intefaces;

import Execute.UI;

import javax.swing.*;
import java.awt.*;

public class Services extends JFrame{
    private JButton btOrder;
    private JPanel pnMain;

    public Services() {
        setTitle("FUM diviertete como quieras");
        setContentPane(pnMain);
        setBounds(0,0, 500, 530); // dimensiones iniciales
        setMinimumSize(new Dimension(500, 530)); // dimensiones minimas
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        btOrder.addActionListener(e -> {
            UI.openQueHambre();
            dispose();
        });
    }
}
