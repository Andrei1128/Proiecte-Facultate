package cod;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class VeziReviewFrame extends JFrame implements ScriereCitire {
    private ArrayList<Recenzie> lista;
    private JList<Recenzie> listaRecenzie;
    private DefaultListModel<Recenzie> modelList;
    private JButton close;
    private JScrollPane scrollPane;
    private final String path ="conturi\\id\\recenzii";

    VeziReviewFrame(String id) {
        super("Recenzie");
        lista = ((ArrayList<Recenzie>) citire(path.replace("id", id)));
        modelList = new DefaultListModel<Recenzie>();
        modelList.addAll(lista);
        listaRecenzie = new JList<Recenzie>(modelList);
        scrollPane = new JScrollPane(listaRecenzie);
        listaRecenzie.setBorder(new EmptyBorder(10, 10, 10, 10));
        scrollPane.setBorder(new EmptyBorder(10, 20, 10, 20));
        add(scrollPane);
        close = new JButton("Inchide");
        close.addActionListener(e -> dispose());
        JPanel p = new JPanel();
        p.add(close);
        add(p, BorderLayout.SOUTH);
        Ascultator a = new Ascultator();
        listaRecenzie.addMouseListener(a);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    private class Ascultator implements MouseListener {
        public void mouseClicked(MouseEvent evt) {
            JList listaRecenzie = (JList) evt.getSource();
            if (evt.getClickCount() == 2) {
                int index = listaRecenzie.locationToIndex(evt.getPoint());
                if(index==-1)
                    return;
                lista.get(index).getInfo();
            }
        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }
}
