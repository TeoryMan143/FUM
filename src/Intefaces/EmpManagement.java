package Intefaces;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import Execute.Employee;
import Execute.Main;

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
        setBounds(0,0, 500, 530);
        setMinimumSize(new Dimension(500, 530));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btSend.addActionListener(e -> {
            registerEmployee();
        });

        setVisible(true);
    }

    private void registerEmployee() {
        String name = tfName.getText();
        String lName = tfLname.getText();
        String birthYear = tfDate.getText();
        String email = tfEmail.getText();
        String doc = tfDoc.getText();
        String favDino = tfDinosaurrr.getText();

        if (name.isEmpty() || lName.isEmpty() ||birthYear.isEmpty() ||email.isEmpty() ||doc.isEmpty() ||favDino.isEmpty() ) {
            JOptionPane.showMessageDialog(this, "Porfavor introduce los datos solicitados", "Intenta otra vez", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (!Main.isValidEmail(email)) {
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
        final String DB_URL = "jdbc:mysql://localhost:3306/fum?serverTimezone=UTC";
        final String USER = "root";
        final String PASS = "123";
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO employees (em_name, em_last, birth_year, email, id_doc, fav_dino) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lName);
            preparedStatement.setString(3, birthYear);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, doc);
            preparedStatement.setString(6, favDino);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                employee = new Employee(name, lName, birthYear, email, doc, favDino);
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public static void main(String[] args) {
        EmpManagement ges = new EmpManagement();
    }
}
