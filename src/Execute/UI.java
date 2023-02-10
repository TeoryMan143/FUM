package Execute;

import Intefaces.*;

import javax.swing.*;
import java.awt.*;

public class UI {
    public static void openMainPage() {
        new MainPage();
    }
    public static void openTarifas() {
        new Tarifas();
    }
    public static void openGetMember() {
        Getmember getmember = new Getmember();
        Member member = getmember.member;
        if (member != null) {
            System.out.println("Se registro la membresia: " + member.getuCode());
        } else {
            System.out.println("Resgistro cancelado");
        }
    }
    public static void openChargemember() {
        new Chargemember();
    }
    public static void openServices() {
        new Services();
    }
    public static void openQueHambre() {
        new QueHambreHermano();
    }
    public static void openGestion() {
        new Gestion();
    }
    public static void openQuienesSomos() {
        new QuienesSomos();
    };
    public static void openEmpManagement() {
        EmpManagement empManagement = new EmpManagement();
        Employee employee = empManagement.employee;

        if (employee != null) {
            System.out.println("Se registro el usuario: " + employee.name);
        } else {
            System.out.println("Resgistro cancelado");
        }
    }
    public static void emptyTf(Component component) {
        JOptionPane.showMessageDialog(component, "Porfavor introduce los datos solicitados", "Intenta otra vez", JOptionPane.ERROR_MESSAGE);
    }
    public static void unValidEmail(Component component) {
        JOptionPane.showMessageDialog(component, "Porfavor introduce un correo electronico valido", "Intenta otra vez", JOptionPane.ERROR_MESSAGE);
    }
}
