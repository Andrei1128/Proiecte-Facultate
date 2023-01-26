package lab91;

import java.io.*;
import javax.swing.*;

public class Test {
    // C:\Users\Administrator\Facultate\GC\lab78\puncte.txt
    public static void main(String[] args) throws Exception {
        File Cale;
        while (true) {
            String cale = JOptionPane.showInputDialog("Cale catre fisier cu puncte!");
            Cale = new File(cale);
            if (Cale.exists()) {
                Object[] options = { "Graham",
                        "Jarvis" };
                int n = JOptionPane.showOptionDialog(null,
                        "Alege un algoritm pentru rezolvarea problemei!",
                        "Alege algoritmul!",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (n == 0 || n == 1) {
                    new Desen(n, new Citesc(Cale).getListaPuncte());
                    break;
                }
            } else
                JOptionPane.showMessageDialog(null, "Cale incorecta!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
