package Intefaces;

import Execute.UI;
import Execute.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Gestion extends JFrame{
    private JTextField tfMail;
    private JButton btLogin;
    private JButton btRegister;
    private JTextField tfDoc;
    private JPanel pnMain;

    public Gestion() {
        setTitle("FUM diviertete como quieras");
        setContentPane(pnMain);
        setBounds(0, 0, 500, 530); // dimensiones iniciales
        setMinimumSize(new Dimension(500, 530)); // dimensiones minimas
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        btRegister.addActionListener(e -> {
            UI.openGetMember();
            dispose();
        });
        btLogin.addActionListener(e -> logInMember());
        tfDoc.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Utils.onlyNumbers(e);
            }
        });
    }

    private void logInMember() {
        String email = tfMail.getText().trim();
        String doc = tfDoc.getText().trim();

        if (email.isEmpty() || doc.isEmpty()) {
            UI.emptyTf(this);
            return;
        }

        try {
            Statement st = Utils.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from employees where email = '" + email + "' and id_doc = '" + doc + "'");

            if (rs.next()) {
                String job = rs.getString("job");
                String salary;
                String time;
                switch (job) {
                    case "Cajero":
                        salary = "$25.000";
                        time = "30";
                        break;
                    case "Logística":
                        salary = "$30.000";
                        time = "25";
                        break;
                    case "Administrador":
                        salary = "$50.000";
                        time = "28";
                        break;
                    case "Mecánico":
                        salary = "$45.000";
                        time = "20";
                        break;
                    default:
                        time = "";
                        salary = "";
                        JOptionPane.showMessageDialog(this, "How? ඞ", "Intenta otra vez", JOptionPane.ERROR_MESSAGE);
                }
                JOptionPane.showMessageDialog(this, "Usted ha sido aprobado\n" +
                        "Puesto: " + job + "\n" +
                        "Salario diario: " + salary + "\n" +
                        "Dias de trabajo al mes: " + time, "Resistro de empleado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Correo o documento no encontrado", "Intenta otra vez", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Gestion();
    }
}
