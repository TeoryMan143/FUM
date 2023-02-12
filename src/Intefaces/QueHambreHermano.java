package Intefaces;

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
    private JButton btVolver;
    private JButton btNatJuice;
    private JTextField tfTotalP;
    private ButtonGroup bgBuy;
    public ArrayList<Integer> cart = new ArrayList<>();
    public String txtCart = "";

    public QueHambreHermano() {
        setTitle("FUM diviertete como quieras");
        setContentPane(pnMain);
        setBounds(0, 0, 600, 800); // dimensiones iniciales
        setMinimumSize(new Dimension(600, 800)); // dimensiones minimas
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        btVolver.addActionListener(e -> {
            UI.openServices();
            dispose();
        });

        btHotdog.addActionListener(e -> {
            cart.add(9500);
            txtCart += "Perro, ";
            taItemlist.setText(txtCart);
            tfTotalP.setText(String.valueOf(Utils.addArrayList(cart)));
        });
        btSandwich.addActionListener(e -> {
            cart.add(9000);
            txtCart += "Sandwich, ";
            taItemlist.setText(txtCart);
            tfTotalP.setText(String.valueOf(Utils.addArrayList(cart)));
        });
        btPizza.addActionListener(e -> {
            cart.add(8000);
            txtCart += "Pizza, ";
            taItemlist.setText(txtCart);
            tfTotalP.setText(String.valueOf(Utils.addArrayList(cart)));
        });
        btBorgar.addActionListener(e -> {
            cart.add(15000);
            txtCart += "Borgar, ";
            taItemlist.setText(txtCart);
            tfTotalP.setText(String.valueOf(Utils.addArrayList(cart)));
        });
        btChicken.addActionListener(e -> {
            cart.add(6500);
            txtCart += "Nugets, ";
            taItemlist.setText(txtCart);
            tfTotalP.setText(String.valueOf(Utils.addArrayList(cart)));
        });
        btCoke.addActionListener(e -> {
            cart.add(2800);
            txtCart += "Coke, ";
            taItemlist.setText(txtCart);
            tfTotalP.setText(String.valueOf(Utils.addArrayList(cart)));
        });
        btNatJuice.addActionListener(e -> {
            cart.add(3000);
            txtCart += "Jugo Natural, ";
            taItemlist.setText(txtCart);
            tfTotalP.setText(String.valueOf(Utils.addArrayList(cart)));
        });
        btPopCorn.addActionListener(e -> {
            cart.add(5600);
            txtCart += "Crispetas, ";
            taItemlist.setText(txtCart);
            tfTotalP.setText(String.valueOf(Utils.addArrayList(cart)));
        });
        btIcecream.addActionListener(e -> {
            cart.add(4000);
            txtCart += "Helado, ";
            taItemlist.setText(txtCart);
            tfTotalP.setText(String.valueOf(Utils.addArrayList(cart)));
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
            Statement st = Utils.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from members where u_code = '" + uCode + "'");
            if (rs.next()) {
                String discount = "";
                double originFunds = rs.getDouble("funds");
                String metype = rs.getString("type");
                double purchase;
                if (metype.equals("Pase especial anual") || metype.equals("Pase tercera edad")) {
                    purchase = Utils.addArrayList(cart) * 0.8;
                    discount = "Con un descuento del 20%\n";
                } else purchase = Utils.addArrayList(cart);
                double f_funds = originFunds - purchase;
                PreparedStatement pst = Utils.connect().prepareStatement("update members set funds = " + f_funds + " where u_code = '" + uCode + "'");
                if (f_funds < 0) {
                    JOptionPane.showMessageDialog(this, "Fondos insuficientes", "Intenta otra vez", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Se ha realizado la compra satisfactoriamente\n" +
                            discount +
                            "Usted pago: " + purchase + "\n" +
                            "Fondos restates: " + f_funds,
                            "Registro", JOptionPane.INFORMATION_MESSAGE);
                }
                pst.executeUpdate();
                pst.close();
            } else {
                JOptionPane.showMessageDialog(this, "Codigo de membresia no encontrado", "Intenta otra vez", JOptionPane.ERROR_MESSAGE);
            }
            st.close();
            rs.close();
            Utils.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new QueHambreHermano();
    }
}
