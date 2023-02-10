package Intefaces;

import Execute.Employee;
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


public class EmpManagement extends JFrame{
    private JPanel pnMain;
    private JTextField tfName;
    private JButton btSend;
    private JTextField tfLname;
    private JTextField tfDate;
    private JTextField tfDoc;
    private JTextField tfDinosaurrr;
    private JTextField tfEmail;
    private JButton btVolver;
    public Employee employee;

    public EmpManagement() {
        setTitle("FUM diviertete como quieras");
        setContentPane(pnMain);
        setBounds(0,0, 500, 530); // dimensiones iniciales
        setMinimumSize(new Dimension(500, 530)); // dimensiones minimas
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        btSend.addActionListener(e -> registerEmployee());
        btVolver.addActionListener(e -> {
            UI.openGestion();
            dispose();
        });

        tfDate.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Utils.onlyNumbers(e);
                if (tfDate.getText().length() >= 4) {
                    e.consume();
                }
            }
        });
        tfDoc.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Utils.onlyNumbers(e);
            }
        });
    }

    private void registerEmployee() {
        String name = tfName.getText().trim();
        String lName = tfLname.getText().trim();
        String birthYear = tfDate.getText().trim();
        String email = tfEmail.getText().trim();
        String doc = tfDoc.getText().trim();
        String favDino = tfDinosaurrr.getText().trim();

        if (name.isEmpty() || lName.isEmpty() ||birthYear.isEmpty() ||email.isEmpty() ||doc.isEmpty() ||favDino.isEmpty() ) {
            UI.emptyTf(this);
            return;
        } else if (!Utils.isValidEmail(email)) {
            UI.unValidEmail(this);
            return;
        }

        employee = addEmpToDatabase(name, lName, birthYear, email, doc, favDino);

        if (employee != null) {
            JOptionPane.showMessageDialog(this, "Se ha realizado el registro con exito\n" +
                    "Podra ver si fue aprovado en el apartado de aprovación", "Registro", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se ha podido registrar al el usuario", "Intenta otra vez", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Employee addEmpToDatabase(String name, String lName, String birthYear, String email, String doc, String favDino) {
        Employee employee = null;
        try {
            Statement st = Utils.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from employees where email = '" + email + "' or  id_doc = " + doc);

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "El usuario ya se encuentra registrado", "Intenta otra vez", JOptionPane.ERROR_MESSAGE);
            } else {
                String sql = "INSERT INTO employees (em_name, em_last, birth_year, email, id_doc, fav_dino, is_active, job) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = Utils.connect().prepareStatement(sql);
                pst.setString(1, name);
                pst.setString(2, lName);
                pst.setString(3, birthYear);
                pst.setString(4, email);
                pst.setString(5, doc);
                pst.setString(6, favDino);
                int isActive = Integer.parseInt(birthYear) < 2005 ? 1 : 0;//ඞ
                pst.setString(7, String.valueOf(isActive));
                int jobNum = 0;
                if (isActive == 1) {
                    jobNum = Utils.getRandomNumber(1, 4);
                }
                String job;
                switch (jobNum) {
                    case 1:
                        job = "Cajero";
                        break;
                    case 2:
                        job = "Logística";
                        break;
                    case 3:
                        job = "Administrador";
                        break;
                    case 4:
                        job = "Mecánico";
                        break;
                    default:
                        job = "No disponible";
                }
                pst.setString(8, job);
                int addedRows = pst.executeUpdate();
                if (addedRows > 0) {
                    employee = new Employee(name, lName, birthYear, email, doc, favDino);
                }
                pst.close();
            }

            st.close();
            Utils.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public static void main(String[] args) {
        new EmpManagement();
    }
}
