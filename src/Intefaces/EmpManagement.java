package Intefaces;

import Execute.Employee;
import Execute.Utils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

public class EmpManagement extends JFrame{
    private JPanel pnMain;
    private JTextField tfName;
    private JButton btSend;
    private JTextField tfLname;
    private JTextField tfDate;
    private JTextField tfDoc;
    private JTextField tfDinosaurrr;
    private JTextField tfEmail;
    public Employee employee;

    public EmpManagement() {
        setTitle("FUM diviertete como quieras");
        setContentPane(pnMain);
        setBounds(0,0, 500, 530); // dimensiones iniciales
        setMinimumSize(new Dimension(500, 530)); // dimensiones minimas
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btSend.addActionListener(e -> registerEmployee());

        setVisible(true);
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
            JOptionPane.showMessageDialog(this, "Porfavor introduce los datos solicitados", "Intenta otra vez", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (!Utils.isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Porfavor introduce un correo electronico valido", "Intenta otra vez", JOptionPane.ERROR_MESSAGE);
            return;
        }

        employee = addEmpToDatabase(name, lName, birthYear, email, doc, favDino);

        if (employee != null) {
            JOptionPane.showMessageDialog(this, "Se ha realizado el registro con exito\n" +
                    "Podra ver si fue aprovado en el apartado de aprovación", "Registro", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se ha podido registrar al el usuario", "Intenta otra vez", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Employee addEmpToDatabase(String name, String lName, String birthYear, String email, String doc, String favDino) {
        Employee employee = null;
        try {
            Statement statement = Utils.connect().createStatement();
            String sql = "INSERT INTO employees (em_name, em_last, birth_year, email, id_doc, fav_dino, is_active) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = Utils.connect().prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lName);
            preparedStatement.setString(3, birthYear);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, doc);
            preparedStatement.setString(6, favDino);
            int isActive = Integer.parseInt(birthYear) < 2005 ? 1 : 0;//ඞ
            preparedStatement.setString(7, String.valueOf(isActive));

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                employee = new Employee(name, lName, birthYear, email, doc, favDino);
            }

            statement.close();
            preparedStatement.close();
            Utils.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
}
