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
    private JButton btVolver;
    private JButton btNatJuice;
    private JTextField tfTotalP;
    private JButton btUnHotdog;
    private JButton btUnSandwich;
    private JButton btUnPizza;
    private JButton btUnBorgar;
    private JButton btUnChicken;
    private JButton btUnCoke;
    private JButton btUnNatJuice;
    private JButton btUnPopCorn;
    private JButton btUnIcecream;
    private JCheckBox efectivoCheckBox;
    private JTextField tfEfect;
    public ArrayList<Integer> cart = new ArrayList<>();
    public ArrayList<String> txtCartList = new ArrayList<>();
    public String txtCart = "";

    public QueHambreHermano() {
        setTitle("FUM diviertete como quieras");
        setContentPane(pnMain);
        setBounds(0, 0, 600, 800); // dimensiones iniciales
        setMinimumSize(new Dimension(600, 800)); // dimensiones minimas
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        btVolver.addActionListener(e -> {
            UI.openServices();
            dispose();
        });

        btHotdog.addActionListener(e -> registerPurchase("HotDog", 9500));

        btSandwich.addActionListener(e -> registerPurchase("Sandwich", 9000));

        btPizza.addActionListener(e -> registerPurchase("Pizza", 8000));

        btBorgar.addActionListener(e -> registerPurchase("Hamburguesa", 15000));

        btChicken.addActionListener(e -> registerPurchase("Nuggets", 6500));

        btCoke.addActionListener(e -> registerPurchase("CocaCola", 2800));

        btNatJuice.addActionListener(e -> registerPurchase("Jugo Natural", 3000));

        btPopCorn.addActionListener(e -> registerPurchase("Crispetas", 5600));

        btIcecream.addActionListener(e -> registerPurchase("Helado", 4000));

        btUnHotdog.addActionListener(e -> deleteItem("HotDog", 9500));

        btUnSandwich.addActionListener(e -> deleteItem("Sandwich", 9000));

        btUnPizza.addActionListener(e -> deleteItem("Pizza", 8000));

        btUnBorgar.addActionListener(e -> deleteItem("Hamburguesa", 15000));

        btUnChicken.addActionListener(e -> deleteItem("Nuggets", 6500));

        btUnCoke.addActionListener(e -> deleteItem("CocaCola", 2800));

        btUnNatJuice.addActionListener(e -> deleteItem("Jugo Natural", 3000));

        btUnPopCorn.addActionListener(e -> deleteItem("Crispetas", 5600));

        btUnIcecream.addActionListener(e -> deleteItem("Helado", 4000));

        if (UI.loggedMember != null) {
            Member member = UI.loggedMember;
            tfMember.setText(member.getuCode());
        }

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

    private void registerPurchase(String product, int price) {
        txtCartList.add(product);
        taItemlist.setText(Utils.createCartText(txtCartList));
        cart.add(price);
        tfTotalP.setText(String.valueOf(Utils.addArrayList(cart)));
    }

    private void deleteItem(String product, int price) {
        txtCartList.remove(product);
        taItemlist.setText(Utils.createCartText(txtCartList));
        cart.remove(Integer.valueOf(price));
        tfTotalP.setText(String.valueOf(Utils.addArrayList(cart)));
    }

    public static void main(String[] args) {
        new QueHambreHermano();
    }
}
