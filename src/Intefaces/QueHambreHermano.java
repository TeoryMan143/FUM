package Intefaces;

import Execute.Member;
import Execute.UI;
import Execute.Utils;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QueHambreHermano extends JFrame {
    private JPanel pnMain;
    private JButton btHotdog;
    private JButton btIcecream;
    private JButton btSandwich;
    private JButton btPizza;
    private JButton btBorgar;
    private JButton btChicken;
    private JButton btCoke;
    private JButton btPopCorn;
    private JTextField tfMember;
    private JButton btOrder;
    private JTextArea taItemlist;
    private JButton btNatJuice;
    public Member member;
    public ArrayList<Integer> cart = new ArrayList<>();
    public String txtCart = "";

    public QueHambreHermano() {
        setTitle("FUM diviertete como quieras");
        setContentPane(pnMain);
        setBounds(0, 0, 500, 530); // dimensiones iniciales
        setMinimumSize(new Dimension(500, 530)); // dimensiones minimas
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        btHotdog.addActionListener(e -> {
            cart.add(9500);
            txtCart += "Perro, ";
            taItemlist.setText(txtCart);
        });
        btSandwich.addActionListener(e -> {
            cart.add(9000);
            txtCart += "Sandwich, ";
            taItemlist.setText(txtCart);
        });
        btPizza.addActionListener(e -> {
            cart.add(8000);
            txtCart += "Pizza, ";
            taItemlist.setText(txtCart);
        });
        btBorgar.addActionListener(e -> {
            cart.add(15000);
            txtCart += "Borgar, ";
            taItemlist.setText(txtCart);
        });
        btChicken.addActionListener(e -> {
            cart.add(6500);
            txtCart += "Nugets, ";
            taItemlist.setText(txtCart);
        });
        btCoke.addActionListener(e -> {
            cart.add(2800);
            txtCart += "Coke, ";
            taItemlist.setText(txtCart);
        });
        btNatJuice.addActionListener(e -> {
            cart.add(3000);
            txtCart += "Jugo Natural, ";
            taItemlist.setText(txtCart);
        });
        btPopCorn.addActionListener(e -> {
            cart.add(5600);
            txtCart += "Crispetas, ";
            taItemlist.setText(txtCart);
        });
        btIcecream.addActionListener(e -> {
            cart.add(4000);
            txtCart += "Helado, ";
            taItemlist.setText(txtCart);
        });

        btOrder.addActionListener(e -> loadFunds());
    }

    private void loadFunds() {
        String uCode = tfMember.getText().trim();
        if (uCode.isEmpty()) {
            UI.emptyTf(this);
            return;
        }
        try {
            int purchase = Utils.addArrayList(cart);
            Statement st = Utils.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from members where u_code = '" + uCode + "'");
            if (rs.next()) {
                double originFunds = Double.parseDouble(rs.getString("funds"));
                double f_funds = originFunds - purchase;
                PreparedStatement pst = Utils.connect().prepareStatement("update members set funds = " + f_funds + " where u_code = '" + uCode + "'");
                pst.executeUpdate();
                if (f_funds < 0) {
                    JOptionPane.showMessageDialog(this, "Fondos insuficientes", "Intenta otra vez", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Se ha realizado la compra satisfactoriamente\n" +
                            "Fondos restates: " + f_funds, "Registro", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Codigo de membresia no encontrado", "Intenta otra vez", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new QueHambreHermano();
    }
}
