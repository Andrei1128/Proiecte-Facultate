package cod;

import javax.swing.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    protected JButton logOut, inbox, exit;
    private JMenuItem veziProfil, modProfil, programari;
    protected JMenu jprofil, jintalniri;
    private JLabel nume, prenume, poza;
    private Persoana persoana;
    private JFrame mesaje, veziprofil, intalniri, modificaFrame;
    protected JMenuBar toolBar;
    private Cont p;
    private MainFrame main;

    MainFrame(Cont p) {
        super("E-Meditatii");
        this.p = p;
        main = this;
        persoana = p.getPersoana();
        toolBar = new JMenuBar();
        jintalniri = new JMenu("Meditatii");
        logOut = new JButton("Log out");
        inbox = new JButton("Mesaje");
        veziProfil = new JMenuItem("Vezi");
        modProfil = new JMenuItem("Modifica");
        jprofil = new JMenu("Profil");
        programari = new JMenuItem("Vezi");
        exit = new JButton("Iesire");
        nume = new JLabel(persoana.getNume());
        prenume = new JLabel(persoana.getPrenume());
        poza = new JLabel(new ImageIcon(p.getPersoana().getPoza()));
        poza.setBounds(50, 30, 70, 70);
        add(poza);
        nume.setBounds(50, 110, 150, 30);
        add(nume);
        prenume.setBounds(50, 135, 150, 30);
        add(prenume);
        inbox.setBounds(240, 30, 100, 30);
        add(inbox);
        logOut.setBounds(240, 70, 100, 30);
        add(logOut);
        exit.setBounds(240, 110, 100, 30);
        add(exit);
        jprofil.add(veziProfil);
        jprofil.add(modProfil);
        jintalniri.add(programari);
        toolBar.add(jprofil);
        toolBar.add(jintalniri);
        AscultatorB a = new AscultatorB();
        logOut.addActionListener(a);
        inbox.addActionListener(a);
        veziProfil.addActionListener(a);
        modProfil.addActionListener(a);
        exit.addActionListener(a);
        programari.addActionListener(a);
        setJMenuBar(toolBar);
        setLayout(null);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private class AscultatorB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == logOut) {
                if (mesaje != null)
                    mesaje.dispose();
                if (veziprofil != null)
                    veziprofil.dispose();
                if (intalniri != null)
                    intalniri.dispose();
                if (modificaFrame != null)
                    modificaFrame.dispose();
                dispose();
                new LogInFrame();
            } else if (e.getSource() == inbox) {
                if (mesaje == null || !mesaje.isVisible())
                    mesaje = new InboxFrame(p.getId(), p.getPersoana().getNumePrenume());
                mesaje.toFront();
            } else if (e.getSource() == veziProfil) {
                if (veziprofil == null || !veziprofil.isVisible())
                    veziprofil = new VeziProfilFrame(p.getPersoana());
                veziprofil.toFront();
            } else if (e.getSource() == modProfil) {
                if (modificaFrame == null || !modificaFrame.isVisible())
                    if (p.getPersoana() instanceof Profesor)
                        modificaFrame = new ModificaProfesorFrame(p, main);
                    else
                        modificaFrame = new ModificaProfilFrame(p, main);
                modificaFrame.toFront();
            } else if (e.getSource() == programari) {
                if (intalniri == null || !intalniri.isVisible())
                    intalniri = new IntalniriFrame(p.getId(), p.getPersoana().getNumePrenume());
                intalniri.toFront();
            } else if (e.getSource() == exit)
                System.exit(0);
        }

    }

    public void setInfo() {
        nume.setText(persoana.getNume());
        prenume.setText(persoana.getPrenume());
        ImageIcon icon = new ImageIcon(persoana.getPoza());
        icon.getImage().flush();
        poza.setIcon(icon);
    }
}
