package Execute;

import org.jetbrains.annotations.NotNull;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

public class Utils {
    private static Connection cx;
    public static Connection connect() {
        try {
            final String DB_URL = "jdbc:mysql://us-east.connect.psdb.cloud/fum?sslMode=VERIFY_IDENTITY";
            final String USER = "1uue7o6s0swwhf5gbvg8";
            final String PASS = "pscale_pw_qz18c0tznggvRXO2dZebej5uPJDN8muZY868cxYQdVX";
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
    public static void onlyNumbers(@NotNull KeyEvent e) {
        char c = e.getKeyChar();
        if (c < '0' || c > '9'){
            e.consume();
        }
    }
    public static @NotNull String codeGenerator() {
        int length = 5;
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        Random random = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }
    public static int addArrayList(@NotNull ArrayList<Integer> list) {
        int sum = 0;

        for (int value : list) {
            sum += value;
        }

        return sum;
    }
    public static String createCartText(ArrayList<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s).append(" ");
        }
        return sb.toString().trim();
    }
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
