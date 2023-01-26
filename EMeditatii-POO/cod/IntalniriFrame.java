package cod;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.*;

public class IntalniriFrame extends JFrame implements ScriereCitire{
    private ArrayList<Intalnire> intalniri;
    private JList<Intalnire> listaIntalniree;
    private DefaultListModel<Intalnire> modelList;
    private JButton close;
    private JScrollPane scrollPane;
    private final String path = "conturi\\id\\intalniri";
    private String cale,id,nume;

    IntalniriFrame(String id, String nume) {
        super("Intalniri");
        this.id=id;
        this.nume=nume;
        cale = path.replaceFirst("id", id);
        intalniri = ((ArrayList<Intalnire>) citire(cale));
        modelList = new DefaultListModel<Intalnire>();
        modelList.addAll(intalniri);
        listaIntalniree = new JList<Intalnire>(modelList);
        scrollPane = new JScrollPane(listaIntalniree);
        listaIntalniree.setBorder(new EmptyBorder(10, 10, 10, 10));
        scrollPane.setBorder(new EmptyBorder(10, 20, 10, 20));
        add(scrollPane);
        close = new JButton("Inchide");
        close.addActionListener(e -> dispose());
        JPanel p = new JPanel();
        p.add(close);
        add(p, BorderLayout.SOUTH);
        Ascultator a = new Ascultator();
        listaIntalniree.addMouseListener(a);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private class Ascultator implements MouseListener {
        public void mouseClicked(MouseEvent evt) {
            JList listaIntalniree = (JList) evt.getSource();
            if (evt.getClickCount() == 2) {
                int index = listaIntalniree.locationToIndex(evt.getPoint());
                if (index == -1)
                    return;
                Intalnire i = modelList.get(index);
                if (new Date().before(i.getData())) {
                    Object[] options = { "Anuleaza meditatia",
                            "Inapoi" };
                    int n = JOptionPane.showOptionDialog(null,
                            i.getInfo(),
                            "Detalii intalnire",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            options,
                            options[1]);
                    if (n == 0) {
                        String catre = id.equals(i.getElev()) ? i.getProfesor() : i.getElev();
                        scrie(catre, new Mesaj(id, nume,
                                "O meditatie a fost anulata.\nDetaliile intalnirii:\n" + i.getInfo()), 0);
                        remove(i, cale);
                        remove(i, path.replace("id", catre));
                        modelList.remove(index);
                    }
                } else {
                    if (id.equals(i.getElev()) && !i.getReviewed()) {
                        Object[] options = { "Acorda Review",
                                "Inapoi" };
                        int n = JOptionPane.showOptionDialog(null,
                                i.getInfo(),
                                "Detalii intalnire",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                options,
                                options[1]);
                        if (n == 0) {
                            new AcordaReviewFrame(i.getProfesor(), i, index, id);
                            remove(i, "conturi\\" + id + "\\intalniri");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                i.getInfo(),
                                "Detalii intalnire",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }
}
