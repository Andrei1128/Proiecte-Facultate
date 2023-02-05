package cod;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ModificaProfilFrame extends JFrame implements ScriereCitire {
    protected JButton submit, cancel, adaugaMaterie, poza;
    private JTextField nume, prenume;
    private JLabel numel, prenumel;
    private MainFrame m;
    protected JPanel form, butoane, adauga;
    private final String path = "altele\\id.png";
    private String cale;
    private BufferedImage newImage;
    private String id;
    private JFileChooser j;
    private Cont p;
    private Persoana c;

    ModificaProfilFrame(Cont p, MainFrame m) {
        super("Modifica Profil");
        this.p = p;
        c = p.getPersoana();
        id = p.getId();
        this.m = m;
        submit = new JButton("Salveaza");
        cancel = new JButton("Inapoi");
        nume = new JTextField(c.getNume(), 18);
        prenume = new JTextField(c.getPrenume(), 16);
        numel = new JLabel("Nume:");
        prenumel = new JLabel("Prenume :");
        adaugaMaterie = new JButton("Adauga materie preferata");
        form = new JPanel();
        form.add(new JLabel());
        form.setLayout(new GridLayout(5, 1));
        JPanel p2 = new JPanel();
        p2.add(numel);
        p2.add(nume);
        form.add(p2);
        JPanel p3 = new JPanel();
        p3.add(prenumel);
        p3.add(prenume);
        form.add(p3);
        j = new JFileChooser();
        j.setFileFilter(new FileNameExtensionFilter("Images(.png)(.jpg)(.jpeg)", "png", "jpg", "jpeg"));
        poza = new JButton("Schimba poza");
        AscultatorB a = new AscultatorB();
        poza.addActionListener(a);
        JPanel p4 = new JPanel();
        p4.add(poza);
        form.add(p4);
        form.add(new JLabel());
        adauga = new JPanel();
        adauga.add(adaugaMaterie);
        add(adauga);
        add(form, BorderLayout.NORTH);
        butoane = new JPanel();
        butoane.add(submit);
        butoane.add(cancel);
        add(butoane, BorderLayout.SOUTH);
        submit.addActionListener(a);
        cancel.addActionListener(a);
        adaugaMaterie.addActionListener(a);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private class AscultatorB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == poza) {
                int n = j.showSaveDialog(null);
                if (n == JFileChooser.APPROVE_OPTION) {
                    try {
                        BufferedImage bufferedImage = ImageIO.read(j.getSelectedFile());
                        Image image = bufferedImage.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                        newImage = new BufferedImage(70, 70, BufferedImage.TYPE_INT_RGB);
                        newImage.createGraphics().drawImage(image, 0, 0, null);
                        cale = path.replace("id", id);
                        JOptionPane.showMessageDialog(null, "Poza a fost adaugata cu succes!",
                                "Mesaj!", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "Poza nu a fost adaugata!\nFormat incorect!",
                                "Eroare!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else if (e.getSource() == submit) {
                if (!nume.getText().isBlank())
                    p.getPersoana().setNume(nume.getText());
                if (!prenume.getText().isBlank())
                    p.getPersoana().setPrenume(prenume.getText());
                if (cale != null) {
                    try {
                        File outputfile = new File(cale);
                        ImageIO.write(newImage, "png", outputfile);
                        newImage.flush();
                        p.getPersoana().setPoza(cale);
                    } catch (IOException ei) {
                        System.out.println("ceva nu e bine");
                    }
                }
                scrie(p);
                m.setInfo();
                m.validate();
                dispose();
            } else if (e.getSource() == adaugaMaterie) {
                JComboBox comboBox = new JComboBox(Materii.materii);
                JOptionPane.showMessageDialog(null, comboBox, "Adauga Materie",
                        JOptionPane.PLAIN_MESSAGE);
                c.adaugaMaterie((String) comboBox.getSelectedItem());
                scrie(p);
            } else if (e.getSource() == cancel)
                dispose();
        }
    }
}
