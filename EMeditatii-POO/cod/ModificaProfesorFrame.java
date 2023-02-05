package cod;

import java.nio.file.*;
import java.util.List;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ModificaProfesorFrame extends ModificaProfilFrame {
    private Cont p;
    private JButton adaugaCert;
    private String s2;

    public ModificaProfesorFrame(Cont p, MainFrame m) {
        super(p, m);
        adaugaCert = new JButton("Adauga Certificat");
        adauga.add(adaugaCert);
        AscultatorB a = new AscultatorB();
        adaugaCert.addActionListener(a);
        this.p = p;
        adaugaMaterie.setText("Adauga materie");
        adaugaMaterie.removeActionListener(adaugaMaterie.getActionListeners()[0]);
        adaugaMaterie.addActionListener(a);
    }

    private class AscultatorB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == adaugaMaterie) {
                JComboBox comboBox = new JComboBox(Materii.materii);
                JOptionPane.showMessageDialog(null, comboBox, "Adauga Materie",
                        JOptionPane.PLAIN_MESSAGE);
                if (p.getPersoana().adaugaMaterie((String) comboBox.getSelectedItem())) {
                    try {
                        int index = comboBox.getSelectedIndex();
                        Path path = Paths.get("altele\\materii");
                        List<String> lines = Files.readAllLines(path);
                        if (lines.get(index).equals(""))
                            lines.set(index, p.getId() + "," + p.getPersoana().toString());
                        else
                            lines.set(index,
                                    lines.get(index) + "," + p.getId() + "," + p.getPersoana().toString());
                        Files.write(path, lines);
                    } catch (Exception i) {
                        i.printStackTrace();
                    }
                    scrie(p);
                }
            } else if (e.getSource() == adaugaCert) {

                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                JTextField textField1 = new JTextField(7);
                panel.add(new JLabel("Titlu document"));
                panel.add(textField1);
                JFileChooser j = new JFileChooser();
                j.setFileFilter(new FileNameExtensionFilter("Pdf file(.pdf)", "pdf"));
                JButton b = new JButton("Alege documentul");
                JLabel l = new JLabel("Documentul nu a fost incarcat!");
                panel.add(b);
                panel.add(l);
                panel.setBorder(new EmptyBorder(10, 30, 10, 10));

                b.addActionListener(i -> {
                    int r = j.showSaveDialog(null);
                    if (r == JFileChooser.APPROVE_OPTION) {
                        s2 = j.getSelectedFile().getAbsolutePath();
                        l.setText(s2);
                    }
                });
                int n = JOptionPane.showConfirmDialog(null, panel, "Adauga certificat", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                if (n == 0) {
                    String s1 = textField1.getText();
                    if (s1.isBlank() || s2 == null || !s2.endsWith(".pdf"))
                        JOptionPane.showMessageDialog(null, "Datele nu sunt valide!\nCeritificatul nu a fost adaugat!",
                                "Eroare!", JOptionPane.ERROR_MESSAGE);
                    else {
                        ((Profesor) p.getPersoana()).adaugaCert(s1, s2);
                    }
                }
            }
        }
    }
}
