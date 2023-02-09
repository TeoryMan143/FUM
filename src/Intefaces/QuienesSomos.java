package Intefaces;

import javax.swing.*;

public class QuienesSomos {
    private JTextArea taUs;
    private JTextArea taMision;
    private JTextArea taVision;
    private JButton btVolver;

    public QuienesSomos() {

        final String TXTUS = " \n" +
                "FUM! es una empresa enfocada en el bienestar y en el \n" +
                "entretenimiento de sus visitantes. \n" +
                "\n" +
                "¡Innovamos   cada   día    para   seguir   brindando \n" +
                "experiencias inolvidables! ᗜˬᗜ";

        final String TXTMISION = " \n" +
                "En una sociedad en la que los sentimientos negativos \n" +
                "están presentes  en  el  diaro vivir,  es  necesario \n" +
                "encontrar una forma de divertirnos y  desestresarnos, \n" +
                "de relajarnos y pasar un buen rato. \n" +
                " \n" +
                "Por  lo  tanto,  nos  enfocamos   en    crear nuevas \n" +
                "atracciones  estimulantes  y  en  hacer  de nuestros \n" +
                "parques  temáticos,  lugares en los que puedas estar \n" +
                "en espacios de calma y espacios de adrenalina. \n" +
                " \n" +
                "FUM!  es una empresa que eciste para brindar alegría \n" +
                "y  momentos   memorables  en  la  vida  de  nuestros \n" +
                "visitantes,  ofreciendo  un  servicio  optimo y gran \n" +
                "disposición para que tú  y  tus acompañantes  puedan \n" +
                "tener la mejor experiencia. \n" +
                " \n" +
                "- Bienvenidos a FUM! ᗜˬᗜ -";

        final String TXTVISION = " \n" +
                "Para el año 2032,  FUM! será una empresa  reconocida \n" +
                "a nivel  nacional,  ampliando su alcance y brindando \n" +
                "felicidad a  un  mayor  porcentaje de  la  población \n" +
                "colombiana. \n" +
                " \n" +
                "Contará  con una cadena de parques de diversiones en \n" +
                "Colombia.   10  Parques  ubicados  en  las  ciudades \n" +
                "principales dotados de la mejor calidad  y  servicio \n" +
                "para   nuestros   visitantes,   nuestros   socios  y \n" +
                "sus familiares. ᗜˬᗜ ";

        taUs.setText(TXTUS);
        taMision.setText(TXTMISION);
        taVision.setText(TXTVISION);
    }
}
