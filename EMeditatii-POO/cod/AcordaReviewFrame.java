package cod;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class AcordaReviewFrame extends JFrame implements ScriereCitire {
    public JButton submit, cancel;
    private JTextArea descriere;
    private JTextField nota;
    private JRadioButton da, nu;
    private ButtonGroup butonGroup;
    private JLabel n, d, r;
    private JPanel butoane, form, notal;
    private Intalnire s;
    private String id, idd;
    private int index;

    public AcordaReviewFrame(String id, Intalnire s, int index, String idd) {
        super("Acorda review");
        this.id = id;
        this.s = s;
        this.index = index;
        this.idd = idd;
        submit = new JButton("Acorda");
        cancel = new JButton("Anuleaza");
        descriere = new JTextArea();
        descriere.setLineWrap(true);
        descriere.setWrapStyleWord(true);
        nota = new JTextField(5);
        da = new JRadioButton("Da");
        nu = new JRadioButton("Nu");
        butonGroup = new ButtonGroup();
        n = new JLabel("Nota");
        r = new JLabel("Recomanzi?");
        d = new JLabel("Descriere:");
        butonGroup.add(da);
        butonGroup.add(nu);
        butoane = new JPanel();
        form = new JPanel();
        notal = new JPanel();
        notal.setLayout(new FlowLayout(FlowLayout.LEFT));
        form.setLayout(new GridLayout(5, 1));
        notal.add(n);
        notal.add(nota);
        form.add(notal);
        form.add(r);
        form.add(da);
        form.add(nu);
        form.add(d);
        butoane.add(submit);
        butoane.add(cancel);
        JScrollPane scrollPane = new JScrollPane(descriere);
        descriere.setBorder(new EmptyBorder(5, 5, 5, 5));
        scrollPane.setBorder(new EmptyBorder(0, 20, 10, 20));
        form.setBorder(new EmptyBorder(10, 40, 0, 40));
        add(scrollPane);
        add(form, BorderLayout.NORTH);
        add(butoane, BorderLayout.SOUTH);
        AscultatorB a = new AscultatorB();
        submit.addActionListener(a);
        cancel.addActionListener(a);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private class AscultatorB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submit) {
                try {
                    if (butonGroup.getSelection() == null)
                        throw new Exception();
                    String b;
                    if (butonGroup.getSelection() == da)
                        b = "DA";
                    else
                        b = "NU";
                    int n = Integer.parseInt(nota.getText());
                    if (n < 1 || n > 10)
                        throw new Exception();
                    scrie(id, new Recenzie(n, descriere, b), 0);
                    s.setReviewed();
                    scrie(idd, s, index);
                    dispose();
                } catch (Exception i) {
                    JOptionPane.showMessageDialog(null, "Date introduse incorect!",
                            "Eroare!", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                scrie(idd, s, index);
                dispose();
            }
        }
    }
}
