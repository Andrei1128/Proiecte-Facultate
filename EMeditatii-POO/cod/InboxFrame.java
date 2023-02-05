package cod;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.*;

public class InboxFrame extends JFrame implements ScriereCitire {
    private ArrayList<Mesaj> inbox;
    private JList<Mesaj> listaMesaje;
    private DefaultListModel<Mesaj> modelList;
    private JButton close;
    private JScrollPane scrollPane;
    private JPanel p;
    private final String path = "conturi\\id\\inbox";
    private String cale,id,nume;

    public InboxFrame(String id, String nume) {
        super("Mesaje");
        this.id=id;
        this.nume=nume;
        cale = path.replace("id", id);
        inbox = (ArrayList<Mesaj>) citire(cale);
        modelList = new DefaultListModel<Mesaj>();
        modelList.addAll(inbox);
        listaMesaje = new JList<Mesaj>(modelList);
        scrollPane = new JScrollPane(listaMesaje);
        listaMesaje.setBorder(new EmptyBorder(10, 10, 10, 10));
        scrollPane.setBorder(new EmptyBorder(10, 20, 10, 20));
        add(scrollPane);
        close = new JButton("Inchide");
        close.addActionListener(e -> dispose());
        p = new JPanel();
        p.add(close);
        add(p, BorderLayout.SOUTH);
        Ascultator a = new Ascultator();
        listaMesaje.addMouseListener(a);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    private class Ascultator implements MouseListener {
        public void mouseClicked(MouseEvent evt) {
            JList listaMesaje = (JList) evt.getSource();
            if (evt.getClickCount() == 2) {
                int index = listaMesaje.locationToIndex(evt.getPoint());
                if(index==-1)
                    return;
                Mesaj mesaj = modelList.get(index);
                if (mesaj.getContinut() instanceof Intalnire) {
                    if (((Intalnire) mesaj.getContinut()).getData().after(new Date())) {
                        Object[] options = { "Accepta",
                                "Refuza",
                                "Inapoi" };
                        int n = JOptionPane.showOptionDialog(null,
                                ((Intalnire) mesaj.getContinut()).getInfo(),
                                "Cerere meditatie",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                options,
                                options[2]);

                        if (n == 0) {
                            scrie(mesaj.getEmitator(), mesaj.getContinut(), 0);
                            scrie(id, mesaj.getContinut(), 0);
                            scrie(mesaj.getEmitator(),
                                    new Mesaj(id, nume,
                                            "Cererea de meditatie a fost acceptata.\nDetaliile intalnirii:\n"
                                                    + ((Intalnire) mesaj.getContinut()).getInfo()),
                                    0);
                            remove(mesaj, cale);
                            modelList.remove(index);

                        } else if (n == 1) {
                            scrie(mesaj.getEmitator(),
                                    new Mesaj(id, nume,
                                            "Cererea de meditatie a fost refuzata.\nDetaliile intalnirii:\n"
                                                    + ((Intalnire) mesaj.getContinut()).getInfo()),
                                    0);
                            remove(mesaj, cale);
                            modelList.remove(index);
                        }
                    } else
                        JOptionPane.showMessageDialog(null,
                                ((Intalnire) mesaj.getContinut()).getInfo(),
                                "Cerere de meditatie veche",
                                JOptionPane.INFORMATION_MESSAGE,
                                null);
                } else {
                    JOptionPane.showMessageDialog(null,
                            mesaj.getContinut(), "Mesaj", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }
}
