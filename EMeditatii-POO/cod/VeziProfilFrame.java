package cod;

import javax.swing.*;

public class VeziProfilFrame extends JFrame implements ScriereCitire {
    private JLabel nume, prenume, poza;
    private JTextArea materii;
    private JButton cancel;

    public VeziProfilFrame(Persoana c) {
        super("Informatii Profil");
        nume = new JLabel("Nume: " + c.getNume());
        materii = new JTextArea(c.getMaterii().toString().replace(", ", "\n       ").replace("]", ""));
        materii.disable();
        if (c instanceof Profesor)
            materii.replaceRange("Materii predate:\n\n       ", 0, 1);
        else
            materii.replaceRange("Materii preferate:\n\n       ", 0, 1);
        materii.setDisabledTextColor(nume.getForeground());
        materii.setBackground(getBackground());
        materii.setFont(nume.getFont());
        cancel = new JButton("Inchide");
        prenume = new JLabel("Prenume: " + c.getPrenume());
        poza = new JLabel(new ImageIcon(c.getPoza()));
        cancel.addActionListener(e -> dispose());
        poza.setBounds(60, 40, 70, 70);
        add(poza);
        nume.setBounds(60, 120, 150, 30);
        add(nume);
        prenume.setBounds(60, 145, 150, 30);
        add(prenume);
        materii.setBounds(250, 20, 200, 160);
        add(materii);
        cancel.setBounds(160, 200, 100, 30);
        add(cancel);
        setSize(440, 290);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
