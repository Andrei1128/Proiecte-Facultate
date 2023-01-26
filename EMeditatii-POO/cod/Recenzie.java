package cod;

import java.awt.*;
import java.io.Serializable;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Recenzie implements Serializable {
    private int nota;
    private JTextArea descriere;
    private String recomanda;

    public Recenzie(int nota, JTextArea descriere, String recomanda) {
        this.nota = nota;
        this.descriere = descriere;
        this.recomanda = recomanda;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Nota: ");
        sb.append(nota);
        sb.append(", Recomanda: ");
        sb.append(recomanda);
        return sb.toString();
    }

    public void getInfo() {
        JLabel l1 = new JLabel("Nota: " + nota);
        descriere.disable();
        descriere.setDisabledTextColor(l1.getForeground());
        descriere.setBackground(l1.getBackground());
        descriere.setFont(l1.getFont());
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 30, 10, 30));
        panel.setLayout(new BorderLayout());
        panel.add(l1, BorderLayout.NORTH);
        panel.add(new JLabel("Recomanda: " + recomanda));
        descriere.setText("Descriere:\n" + descriere.getText());
        JScrollPane scrollPane = new JScrollPane(descriere);
        panel.add(scrollPane, BorderLayout.SOUTH);
        JOptionPane.showMessageDialog(null, panel, "Recenzie", JOptionPane.PLAIN_MESSAGE);
    }

    public JTextArea getDescriere() {
        return descriere;
    }
}
