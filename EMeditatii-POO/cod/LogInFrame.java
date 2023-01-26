package cod;

import java.awt.Color;
import java.io.File;
import javax.swing.*;
import java.awt.event.*;

public class LogInFrame extends JFrame implements ScriereCitire {

    private JTextField id;
    private JPasswordField pw;
    private JLabel l1, l2, l3, l4, l5;
    private JButton logIn, register;
    private final String path = "conturi\\id\\cont";

    public LogInFrame() {
        super("Autentificare");
        logIn = new JButton("Autentificare");
        register = new JButton("Creaza cont");
        id = new JTextField();
        pw = new JPasswordField();
        l1 = new JLabel("Nume de utilizator");
        l2 = new JLabel("Parola");
        l3 = new JLabel("* Nume de utilizator invalid!");
        l4 = new JLabel("* Parola incorecta!");
        l5 = new JLabel("* Introduceti toate datele!");

        AscultatorB a = new AscultatorB();
        logIn.addActionListener(a);
        register.addActionListener(a);

        l4.setForeground(Color.red);
        l3.setForeground(Color.red);
        l5.setForeground(Color.red);
        l3.setVisible(false);
        l4.setVisible(false);
        l5.setVisible(false);

        l1.setBounds(70, 30, 200, 25);
        l2.setBounds(70, 80, 200, 25);
        l3.setBounds(70, 140, 200, 25);
        l4.setBounds(70, 140, 200, 25);
        l5.setBounds(70, 140, 200, 25);
        logIn.setBounds(310, 45, 130, 40);
        register.setBounds(338, 98, 102, 25);
        id.setBounds(70, 50, 200, 25);
        pw.setBounds(70, 100, 200, 25);

        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(logIn);
        add(register);
        add(id);
        add(pw);

        setLayout(null);
        setSize(500, 240);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private class AscultatorB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == logIn) {
                l3.setVisible(false);
                l4.setVisible(false);
                l5.setVisible(false);
                if (id.getText().equals("") || pw.getPassword().length == 0) {
                    l5.setVisible(true);
                    return;
                }
                String cale = path.replace("id", id.getText());
                if (new File(cale).isFile() != true) {
                    l3.setVisible(true);
                    return;
                }
                Profil p = (Profil) citire(cale);
                if (!p.getCont().compareTo(pw.getPassword()))
                    l4.setVisible(true);
                else {
                    dispose();
                    if (p.getPersoana() instanceof Profesor)
                        new ProfesorMainFrame(p);
                    else
                        new ElevMainFrame(p);
                }
            } else if (e.getSource() == register) {
                dispose();
                new RegisterFrame();
            }
        }
    }
}
