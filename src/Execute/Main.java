package Execute;

import Intefaces.EmpManagement;

import java.util.regex.Pattern;

public class Main {
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
}
