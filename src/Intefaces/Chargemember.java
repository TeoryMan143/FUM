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

public class Chargemember extends JFrame{
    private JTextField tfMemberLogin;
    private JTextField tfCharge;
    private JButton btSend;
    private JPanel pnMain;
    private JButton btVolver;
    private Member member;

    public Chargemember() {
        setTitle("FUM diviertete como quieras");
        setContentPane(pnMain);
        setBounds(0, 0, 800, 750); // dimensiones iniciales
        setMinimumSize(new Dimension(600, 700)); // dimensiones minimas
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        btSend.addActionListener(e -> loadFunds());
        tfCharge.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Utils.onlyNumbers(e);
            }
        });
        btVolver.addActionListener(e -> {
            UI.openTarifas();
            dispose();
        });
    }

    private void loadFunds() {
        String uCode = tfMemberLogin.getText().trim();
        String funds = tfCharge.getText().trim();

        if (uCode.isEmpty() || funds.isEmpty()) {
            UI.emptyTf(this);
            return;
        } 
        
        member = readMemberFromDB(uCode, funds);

        if (member != null) {
            JOptionPane.showMessageDialog(this, "Se ha realizado la recarga con exito en la membresia de codigo: " + member.getuCode() + "\nMonto final " + member.getFunds(), "Registro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private Member readMemberFromDB(String uCode, String funds) {
        Member member = null;
        try {
            Statement st = Utils.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from members where u_code = '" + uCode + "'");
            if (rs.next()) {
                double originFunds = Double.parseDouble(rs.getString("funds"));
                double f_funds = originFunds + Double.parseDouble(funds);
                PreparedStatement pst = Utils.connect().prepareStatement("update members set funds = " + f_funds + " where u_code = '" + uCode + "'");

                int addedRows = pst.executeUpdate();
                if (addedRows > 0) {
                    member = new Member("null",uCode,f_funds);
                }
                pst.close();
            } else {
                JOptionPane.showMessageDialog(this, "Codigo de membresia no encontrado", "Intenta otra vez", JOptionPane.ERROR_MESSAGE);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }
}
