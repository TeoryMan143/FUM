package Intefaces;

import Execute.UI;

import javax.swing.*;
import java.awt.*;

public class Fumo extends JFrame{
    private JPanel pnMain;
    private JButton btVolver;

    public Fumo() {
        setTitle("FUM diviertete como quieras");
        setContentPane(pnMain);
        setBounds(0, 0, 500, 530); // dimensiones iniciales
        setMinimumSize(new Dimension(500, 530)); // dimensiones minimas
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        btVolver.addActionListener(e -> {
            UI.openMainPage();
            dispose();
        });
    }
}
