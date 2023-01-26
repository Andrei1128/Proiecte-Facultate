package cod;

import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.util.Arrays;
import javax.swing.*;

public class RegisterFrame extends JFrame implements ScriereCitire {

    private JButton submit, cancel;
    private JLabel l1, l2, l3, l4, l5, e1, e2, e3, e4;
    private JRadioButton elev, profesor;
    private JTextField nume, prenume, numeUtilizator;
    private JPasswordField parola, confirmaParola;
    private ButtonGroup radio;

    public RegisterFrame() {
        super("Creare cont");
        cancel = new JButton("Anulare");
        submit = new JButton("Creare cont");
        nume = new JTextField();
        prenume = new JTextField();
        numeUtilizator = new JTextField();
        parola = new JPasswordField();
        confirmaParola = new JPasswordField();
        l1 = new JLabel("Nume");
        l2 = new JLabel("Prenume");
        l3 = new JLabel("Nume utilizator");
        l4 = new JLabel("Parola");
        l5 = new JLabel("Confirma parola");
        e1 = new JLabel("* Nume de utilizator indisponibil!");
        e2 = new JLabel("* Parola nu coincide!");
        e3 = new JLabel("* Toate campurile trebuiesc completate!");
        e4 = new JLabel("* Marimea parolei trebuie sa fie intre 8-12 caractere!");
        elev = new JRadioButton("Elev/Student");
        profesor = new JRadioButton("Profesor");
        radio = new ButtonGroup();
        radio.add(elev);
        radio.add(profesor);
        AscultatorB a = new AscultatorB();
        submit.addActionListener(a);
        cancel.addActionListener(a);
        elev.setBounds(320, 50, 200, 25);
        profesor.setBounds(320, 80, 200, 25);
        l1.setBounds(70, 30, 200, 25);
        l2.setBounds(70, 80, 200, 25);
        l3.setBounds(70, 130, 200, 25);
        l4.setBounds(70, 180, 200, 25);
        l5.setBounds(70, 230, 200, 25);
        nume.setBounds(70, 50, 200, 25);
        prenume.setBounds(70, 100, 200, 25);
        numeUtilizator.setBounds(70, 150, 200, 25);
        parola.setBounds(70, 200, 200, 25);
        confirmaParola.setBounds(70, 250, 200, 25);
        submit.setBounds(70, 360, 120, 40);
        cancel.setBounds(310, 360, 120, 40);
        e1.setBounds(70, 290, 300, 25);
        e2.setBounds(70, 290, 300, 25);
        e3.setBounds(70, 290, 300, 25);
        e4.setBounds(70, 290, 300, 25);
        e1.setForeground(Color.red);
        e2.setForeground(Color.red);
        e3.setForeground(Color.red);
        e4.setForeground(Color.red);
        e1.setVisible(false);
        e2.setVisible(false);
        e3.setVisible(false);
        e4.setVisible(false);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(e1);
        add(e2);
        add(e3);
        add(e4);
        add(nume);
        add(prenume);
        add(numeUtilizator);
        add(parola);
        add(confirmaParola);
        add(submit);
        add(elev);
        add(profesor);
        add(cancel);
        setLayout(null);
        setSize(500, 490);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                new LogInFrame();
            }
        });
    }

    private class AscultatorB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submit)
                submit();
            else {
                dispose();
                new LogInFrame();
            }
        }

        public boolean emptyFields() {
            if (nume.getText().equals("") ||
                    prenume.getText().equals("") ||
                    numeUtilizator.getText().equals("") ||
                    parola.getPassword().length == 0 ||
                    confirmaParola.getPassword().length == 0 ||
                    radio.getSelection() == null)
                return true;
            return false;
        }

        public boolean pwNotMatch() {
            if (!(Arrays.equals(parola.getPassword(), confirmaParola.getPassword())))
                return true;
            return false;
        }

        public boolean pwLength() {
            if (parola.getPassword().length < 8 ||
                    parola.getPassword().length > 12)
                return true;
            return false;
        }

        public void submitSucces(Profil p) {
            scrie(p);
            dispose();
            new LogInFrame();
        }

        public void submit() {
            e1.setVisible(false);
            e2.setVisible(false);
            e3.setVisible(false);
            e4.setVisible(false);

            if (emptyFields())
                e3.setVisible(true);
            else if (new File("conturi\\" + numeUtilizator.getText()).isDirectory())
                e1.setVisible(true);
            else if (pwLength())
                e4.setVisible(true);
            else if (pwNotMatch())
                e2.setVisible(true);
            else if (profesor.isSelected()) {
                Profil p = new Profil(new Profesor(nume.getText(), prenume.getText()),
                        new Cont(numeUtilizator.getText(), parola.getPassword()));
                submitSucces(p);
            } else {
                Profil p = new Profil(new Persoana(nume.getText(), prenume.getText()),
                        new Cont(numeUtilizator.getText(), parola.getPassword()));
                submitSucces(p);
            }
        }
    }
}
