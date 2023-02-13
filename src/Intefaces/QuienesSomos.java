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
        setBounds(0, 0, 800, 600); // dimensiones iniciales
        setMinimumSize(new Dimension(800, 600)); // dimensiones minimas
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        btVolver.addActionListener(e -> {
            UI.openMainPage();
            dispose();
        });

        final String TXT_US = "FUM! es una empresa enfocada en el bienestar y el entretenimiento de sus visitantes\n " +
                "¡Innovamos cada día para seguir brindando experiencias inolvidables!";
        final String TXT_MISION = "En  una sociedad en la que los sentimientos  negativos  están  presentes en el diario  vivir,  es \n" +
                "necesario encontrar una forma de divertirnos y desestresarnos, de relajarnos y  pasar un buen \n" +
                "rato.\n\n" +
                "Por lo tanto,  nos enfocamos  en crear nuevas atracciones  estimulantes y en hacer de  nuestros \n" +
                "parques temáticos lugares en los que puedas encontrar espacios de calma y adrenalina. \n\n" +
                "Fum!  es  una  empresa  que existe  para  brindar  alegría y  momentos  memorables  a la  vida de\n" +
                "nuestros visitantes, ofreciendo un servicio optimo y gran disposición para que todos puedan tener\n" +
                "la mejor experiencia.";
        final String TXT_VISION = "Para el año 2044, ¡FUM! será una  empresa  reconocida  a nivel nacional,  ampliando su alcance y\n" +
                "brindando felicidad a un mayor  porcentaje de la  población  colombiana.  Contará con una cadena\n" +
                "de parques en todo el país. 10 parques temáticos ubicados en las ciudades principales dotados de\n" +
                "la mejor calidad y  servicio  para  nuestros visitantes,  socios  y sus familias. \n\n" +
                "¡Para ese entonces, contará con nuevas  atracciones  enfocadas  en  la  salud mental  para hacer \n" +
                "de FUM! un lugar de disfrute y tranquilidad.";

        taUs.setText(TXT_US);
        taMision.setText(TXT_MISION);
        taVision.setText(TXT_VISION);
    }
}
