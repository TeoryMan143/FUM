package Intefaces;

import Execute.UI;

import javax.swing.*;
import java.awt.*;

public class QuienesSomos extends JFrame{
    private JTextArea taUs;
    private JTextArea taMision;
    private JTextArea taVision;
    private JButton btVolver;
    private JPanel pnMain;

    public QuienesSomos() {
        setTitle("FUM diviertete como quieras");
        setContentPane(pnMain);
        setBounds(0, 0, 500, 530); // dimensiones iniciales
        setMinimumSize(new Dimension(500, 530)); // dimensiones minimas
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        btVolver.addActionListener(e -> {
            UI.openMainPage();
            dispose();
        });
    }
}
