package cod;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProgrameazaFrame extends JFrame {
    private JComboBox<String> materie, profesor;
    private JTextField ora, data, loc, pret;
    private JLabel oral, datal, locl, pretl, materiel, profesorl;
    private JButton submit, cancel;
    private List<String> profesori;
    private DefaultComboBoxModel<String> model;
    private String[] s;
    private String id, nume;

    ProgrameazaFrame(String id, String nume) {
        super("Programeaza intalnire");
        this.id = id;
        this.nume = nume;
        Path path = Paths.get("altele\\materii");
        try {
            profesori = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ora = new JTextField("ora:minute");
        data = new JTextField("ZZ/LL/AAAA");
        loc = new JTextField();
        pret = new JTextField("RON");
        materie = new JComboBox<String>(Materii.materii);
        profesor = new JComboBox<String>();
        model = new DefaultComboBoxModel<String>();
        createProfesorCombo();
        oral = new JLabel("Ora");
        datal = new JLabel("Data");
        locl = new JLabel("Loc");
        pretl = new JLabel("Pret");
        materiel = new JLabel("Materie");
        profesorl = new JLabel("Profesor");
        submit = new JButton("Programeaza");
        cancel = new JButton("Anuleaza");
        AscultatorB a = new AscultatorB();
        materie.addActionListener(a);
        submit.addActionListener(a);
        cancel.addActionListener(a);
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(13, 3));
        p1.add(materiel);
        p1.add(materie);
        p1.add(profesorl);
        p1.add(profesor);
        p1.add(datal);
        p1.add(data);
        p1.add(oral);
        p1.add(ora);
        p1.add(locl);
        p1.add(loc);
        p1.add(pretl);
        p1.add(pret);
        p1.setBorder(new EmptyBorder(20, 50, 10, 50));
        add(p1);
        JPanel butoane = new JPanel();
        butoane.add(submit);
        butoane.add(cancel);
        add(butoane, BorderLayout.SOUTH);

        setSize(300, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void createProfesorCombo() {
        s = profesori.get(materie.getSelectedIndex()).split(",");
        model.removeAllElements();
        for (int i = 1; i < s.length; i += 2)
            model.addElement(s[i]);
        profesor.setModel(model);

        if (s[0].equals("")) {
            profesor.disable();
        } else
            profesor.enable();

    }

    private class AscultatorB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submit) {
                if (profesor.isEnabled() && !ora.getText().isBlank() && !data.getText().isBlank()
                        && !loc.getText().isBlank() && !pret.getText().isBlank()) {

                    Date Data = DateValidator.isValid(data.getText(), ora.getText());
                    if (Data != null && pret.getText().matches("[0-9]+")) {
                        Mesaj m = new Mesaj(id, nume,
                                new Intalnire((String) materie.getSelectedItem(), Data,
                                        loc.getText(),
                                        pret.getText(), id, s[profesor.getSelectedIndex() * 2]));
                        m.trimite(s[profesor.getSelectedIndex() * 2]);
                        dispose();
                    } else
                        JOptionPane.showMessageDialog(null, "Date introduse incorect!",
                                "Eroare!", JOptionPane.ERROR_MESSAGE);
                } else
                    JOptionPane.showMessageDialog(null, "Introduceti toate datele!",
                            "Eroare!", JOptionPane.ERROR_MESSAGE);
            } else if (e.getSource() == materie) {
                createProfesorCombo();
                if (s[0].equals(""))
                    JOptionPane.showMessageDialog(null, "Niciun profesor nu a fost gasit!",
                            "Profesori negasiti!", JOptionPane.ERROR_MESSAGE);
            } else
                dispose();
        }
    }

    private class DateValidator {
        static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        private static Date isValid(String dataStr, String oraStr) {
            if (!oraStr.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]"))
                return null;
            Date data;
            try {
                data = formatter.parse(dataStr);
                String[] tbl = oraStr.split(":");
                data.setHours(Integer.parseInt(tbl[0]));
                data.setMinutes(Integer.parseInt(tbl[1]));
            } catch (Exception e) {
                return null;
            }
            if (new Date().before(data))
                return data;
            return null;
        }
    }
}
