package Intefaces;

import Execute.UI;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JFrame {

    private JPanel pnMain;
    private JButton btTarMem;
    private JButton btQSomos;
    private JTextArea taInfantiles;
    private JTextArea taFamily;
    private JTextArea taJuve;
    private JButton btServices;
    private JButton btGestion;
    private JButton btSalir;
    private JButton btZurdos;

    public MainPage() {
        setTitle("FUM diviertete como quieras");
        setContentPane(pnMain);
        setBounds(0,0, 900, 730); // dimensiones iniciales
        setMinimumSize(new Dimension(900, 730)); // dimensiones minimas
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        final String txtInfant = "Estas atracciones están diseñadas para niños que midan entre 0,80 m y 1,10 m de altura, por lo \n" +
                "tanto, por seguridad los niños que midan menos de 0,80 m y más 1,10m, no deben hacer uso de \n" +
                "dichas atracciones.\n\n" +
                "El parque cuenta con atracciones como: Magicletas, Barkito, Choconitos, Convoy, Globitos, Mini \n" +
                "Torre, Tacitas Locas, Teletren, Carroza, Carro de Batería, Lona Saltarina, Abejones, Botes \n" +
                "Chocones, Caballitos, Montaña Rusa Ghibli y Trencito Eléctrico.";
        final String txtJuve = "Están diseñadas para personas que gustan de las emociones fuertes. Para hacer uso de este tipo \n" +
                "de atracciones debe cumplir con las siguientes condiciones: Si la edad de la persona es mayor de \n" +
                "16 años y menor de 18 años debe contar con un pase permiso que se adquiere en los puntos de \n" +
                "servicio del parque de lo contrario no necesita el pase; el sistema otorga el pase permiso validando \n" +
                "la edad a partir de su año de nacimiento. \n\n" +
                "El Parque cuenta con atracciones que han sido desarrolladas para producir sensaciones de alta \n" +
                "intensidad como: Tornado, La Torre, Bocaracá, Disko, Sky Master, Carros Chocones, Pulpo, Rueda \n" +
                "de Chicago y Búmeran.";
        final String txtFamily = "Están diseñadas para el disfrute de toda la familia y no tiene ningún tipo de restricción. El parque \n" +
                "cuenta con atracciones como los Juegos Electrónicos, o del Pascuaré y Reventazón Botes de Pedal, \n" +
                "que son atracciones de agua por lo que se recomienda traer prendas de vestir adicionales para \n" +
                "cambiarse posterior a su uso.";

        taInfantiles.setText(txtInfant);
        taJuve.setText(txtJuve);
        taFamily.setText(txtFamily);

        btSalir.addActionListener(e -> dispose());

        btZurdos.addActionListener(e -> {
            UI.openFumo();
            dispose();
        });

        btTarMem.addActionListener(e -> {
            UI.openTarifas();
            dispose();
        });
        btServices.addActionListener(e -> {
            UI.openServices();
            dispose();
        });
        btGestion.addActionListener(e -> {
            UI.openGestion();
            dispose();
        });
        btQSomos.addActionListener(e -> {
            UI.openQuienesSomos();
            dispose();
        });
    }
}
