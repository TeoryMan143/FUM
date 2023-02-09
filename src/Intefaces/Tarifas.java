package Intefaces;

import Execute.UI;
import Execute.Utils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Tarifas extends JFrame{
    private JTextField tfMemberLogin;
    private JButton btGetMember;
    private JButton btCharge;
    private JButton btMemberLogin;
    private JPanel pnMain;

    public Tarifas() {
        setTitle("FUM diviertete como quieras");
        setContentPane(pnMain);
        setBounds(0,0, 500, 530); // dimensiones iniciales
        setMinimumSize(new Dimension(500, 530)); // dimensiones minimas
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        btGetMember.addActionListener(e -> {
            dispose();
            UI.openGetMember();
        });
        btCharge.addActionListener(e -> {
            dispose();
            UI.openChargemember();
        });

        btMemberLogin.addActionListener(e -> logInMember());
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
                JOptionPane.showMessageDialog(this, "Ha ingresado satisfactoriamente", "Registro", JOptionPane.INFORMATION_MESSAGE);
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
