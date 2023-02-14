package Execute;

import Intefaces.*;

import javax.swing.*;
import java.awt.*;

public class UI {
    public static Member loggedMember = null;
    public static void openMainPage() {
        new MainPage();
    }
    public static void openTarifas() {
        new Tarifas();
    }
    public static void openGetMember() {
        new Getmember();
    }
    public static void openChargemember() {
        new Chargemember();
    }
    public static void openServices() {
        new Services();
        UI.loggedMember = null;
    }
    public static void openServices(Member member) {
        new Services();
        loggedMember = member;
    }
    public static void openQueHambre() {
        new QueHambreHermano();
    }
    public static void openGestion() {
        new Gestion();
    }
    public static void openQuienesSomos() {
        new QuienesSomos();
    }
    public static void openEmpManagement() {
        new EmpManagement();
    }
    public static void openFumo() {
        new Fumo();
    }
    public static void emptyTf(Component component) {
        JOptionPane.showMessageDialog(component, "Porfavor introduce los datos solicitados", "Intenta otra vez", JOptionPane.ERROR_MESSAGE);
    }
    public static void unValidEmail(Component component) {
        JOptionPane.showMessageDialog(component, "Porfavor introduce un correo electronico valido", "Intenta otra vez", JOptionPane.ERROR_MESSAGE);
    }
}
