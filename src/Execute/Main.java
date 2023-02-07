package Execute;

import Intefaces.EmpManagement;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class Main {
    private static Connection cx;
    public static Connection connect() {
        try {
            final String DB_URL = "jdbc:mysql://us-east.connect.psdb.cloud/fum?sslMode=VERIFY_IDENTITY";
            final String USER = "eelr4jpdrn5302k9ojhh";
            final String PRO_PASS = "pscale_pw_qz18c0tznggvRXO2dZebej5uPJDN8muZY868cxYQdVX";
            final String PASS = "pscale_pw_Br7fxnCnfsjJhnCSSCTcCZjdOiK65BWiB3N5oyFZI7Y";
            cx = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cx;
    }
    public static void disconnect() {
        try {
            cx.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    public static void openEmpManagement() {
        EmpManagement empManagement = new EmpManagement();
        Employee employee = empManagement.employee;

        if (employee != null) {
            System.out.println("Se registro el usuario " + employee.name);
        } else {
            System.out.println("Resgistro cancelado");
        }
    }
    public static void onlyNumbers(KeyEvent e) {
        char c = e.getKeyChar();
        if (c < '0' || c > '9'){
            e.consume();
        }
    }
}
