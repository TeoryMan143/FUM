package Intefaces;

import Execute.Member;
import Execute.UI;
import Execute.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Getmember extends JFrame {
    private JTextField tfMail;
    private JComboBox<String> cbMember;
    private JButton btGetmember;
    private JTextField tfCharge;
    private JPanel pnMain;
    private JButton btVolver;
    public Member member;

    public Getmember() {
        setTitle("FUM diviertete como quieras");
        setContentPane(pnMain);
        setBounds(0, 0, 600, 530); // dimensiones iniciales
        setMinimumSize(new Dimension(600, 530)); // dimensiones minimas
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        cbMember.addItem("Pase especial anual");
        cbMember.addItem("Pase rapido");
        cbMember.addItem("Pase tercera edad");

        btGetmember.addActionListener(e -> registerMember());
        btVolver.addActionListener(e -> {
            UI.openTarifas();
            dispose();
        });

        tfCharge.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Utils.onlyNumbers(e);
            }
        });
    }

    private void registerMember() {
        String email = tfMail.getText().trim();
        String charge = tfCharge.getText().trim();

        if (email.isEmpty() || charge.isEmpty()) {
            UI.emptyTf(this);
            return;
        } else if (!Utils.isValidEmail(email)) {
            UI.unValidEmail(this);
            return;
        }

        member = addMemberToDB(email, charge);

        if (member != null) {
            JOptionPane.showMessageDialog(this, "Se ha realizado el registro con exito\n" +
                    "Podra ver si fue aprovado en el apartado de aprovación\n" +
                    "Codigo de membresia: " + member.getuCode(), "Registro", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se ha podido registrar al el usuario", "Intenta otra vez", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Member addMemberToDB(String email, String funds) {
        Member member = null;
        try {
            Statement st = Utils.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from members where email = '" + email + "'");
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "El correo electronico ya se encuentra en uso", "Intenta otra vez", JOptionPane.ERROR_MESSAGE);
            } else {
                PreparedStatement pst = Utils.connect().prepareStatement("insert into members (email, u_code, funds, type) values (?, ?, ?, ?)");
                pst.setString(1, email);
                String uCode = Utils.codeGenerator();
                pst.setString(2, uCode);

                double purchase;
                String metype;
                if (Objects.requireNonNull(cbMember.getSelectedItem()).toString().equals("Pase especial anual")) {
                    purchase = 50000;
                    metype = "Pase especial anual";
                } else if (Objects.requireNonNull(cbMember.getSelectedItem()).toString().equals("Pase rapido")) {
                    purchase = 2000;
                    metype = "Pase rapido";
                } else if (Objects.requireNonNull(cbMember.getSelectedItem()).toString().equals("Pase tercera edad")) {
                    purchase = 10000;
                    metype = "Pase tercera edad";
                } else {
                    purchase = 0;
                    metype = "Pase tercera edad";
                }

                double f_funds = Double.parseDouble(funds) - purchase;
                if (f_funds < 0) {
                    JOptionPane.showMessageDialog(this, "Fondos insuficientes", "Intenta otra vez", JOptionPane.ERROR_MESSAGE);
                }
                pst.setString(3, String.valueOf(f_funds));
                pst.setString(4, metype);

                int addedRows = pst.executeUpdate();
                if (addedRows > 0) {
                    member = new Member(email,uCode,f_funds);
                }
                pst.close();
            }
            st.close();
            rs.close();
            Utils.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }
}
