package cod;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.*;

public class ProfesorMainFrame extends MainFrame {
    private JMenu jrecenzii;
    private JMenuItem printeaza, veziRecenzii;
    private JFrame vezi;
    private String id;

    ProfesorMainFrame(Profil p) {
        super(p);
        id = p.getCont().getId();
        jrecenzii = new JMenu("Recenzii");
        printeaza = new JMenuItem("Printeaza Recenzii");
        veziRecenzii = new JMenuItem("Vezi Recenzii");
        jrecenzii.add(printeaza);
        jrecenzii.add(veziRecenzii);
        AscultatorB a = new AscultatorB();
        veziRecenzii.addActionListener(a);
        printeaza.addActionListener(a);
        super.toolBar.add(jrecenzii);
        logOut.addActionListener(a);
    }

    private class AscultatorB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == veziRecenzii) {
                if (vezi == null || !vezi.isVisible())
                    vezi = new VeziReviewFrame(id);
                vezi.toFront();
            } else if (e.getSource() == printeaza) {
                var a = new PrintReviewsFrame();
                a.printList(id);
            } else if (e.getSource() == logOut) {
                if (vezi != null)
                    vezi.dispose();
            }
        }
    }
}
