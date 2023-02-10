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
import java.util.ResourceBundle;

public class Tarifas extends JFrame{
    private JTextField tfMemberLogin;
    private JButton btGetMember;
    private JButton btCharge;
    private JButton btMemberLogin;
    private JButton btVolver;
    private JPanel pnMain;

    public Tarifas() {
        setTitle("FUM diviertete como quieras");
        setContentPane(pnMain);
        setBounds(0,0, 500, 530); // dimensiones iniciales
        setMinimumSize(new Dimension(500, 530)); // dimensiones minimas
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        btVolver.addActionListener(e -> {
            UI.openMainPage();
            dispose();
        });

        btGetMember.addActionListener(e -> {
            dispose();
            UI.openGetMember();
        });
        btCharge.addActionListener(e -> {
            dispose();
            UI.openChargemember();
        });

        btMemberLogin.addActionListener(e -> logInMember());
        tfMemberLogin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (tfMemberLogin.getText().length() >= 5) {
                    e.consume();
                }
            }
        });
    }

    private void logInMember() {
        String uCode = tfMemberLogin.getText().trim();

        if (uCode.isEmpty()) {
            UI.emptyTf(this);
            return;
        }

        try {
            Statement st = Utils.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from members where u_code = '" + uCode + "'");

            if (rs.next()) {
                String email = rs.getString("email");
                String funds = rs.getString("funds");
                JOptionPane.showMessageDialog(this, "Ha ingresado satisfactoriamente\n" +
                        "Correo registrado: " + email + "\n" +
                        "Saldo: " + funds, "Registro", JOptionPane.INFORMATION_MESSAGE);
                UI.openServices();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "El codigo de membresia ingresado no existe", "Intenta otra vez", JOptionPane.ERROR_MESSAGE);
            }

            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Tarifas();
    }
}
