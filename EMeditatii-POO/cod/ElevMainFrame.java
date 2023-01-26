package cod;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import java.awt.event.*;

public class ElevMainFrame extends MainFrame {
    JMenuItem programeaza;
    JFrame prog;
    Profil p;

    ElevMainFrame(Profil p) {
        super(p);
        this.p = p;
        programeaza = new JMenuItem("Programeaza");
        super.jintalniri.add(programeaza);
        AscultatorB a = new AscultatorB();
        programeaza.addActionListener(a);
        logOut.addActionListener(a);
    }

    private class AscultatorB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == logOut) {
                if (prog != null)
                    prog.dispose();
            } else if (e.getSource() == programeaza) {
                if (prog == null || !prog.isVisible())
                    prog = new ProgrameazaFrame(p.getCont().getId(), p.getPersoana().getNumePrenume());
                prog.toFront();
            }
        }

    }
}
