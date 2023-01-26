package lab5;

import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.awt.event.ActionEvent;

public class GUI extends JFrame implements Serializable {
  private ArrayList<PSLG> pslgList;
  private JButton adauga, sterge, exit, modifica, localizeaza, salveaza;
  private DefaultListModel<PSLG> myList;
  private JList<PSLG> list;
  private static GUI instanta;
  private static final String nume_fis = "C:\\Users\\Administrator\\Facultate\\GC\\lab5\\PSLG_Salvate.txt";

  private GUI() {
    super();
    pslgList = new ArrayList<PSLG>();
    myList = new DefaultListModel<>();
    list = new JList();
    adauga = new JButton("Adauga PSLG");
    sterge = new JButton("Sterge PSLG");
    exit = new JButton("Iesire");
    modifica = new JButton("Modifica PSLG");
    localizeaza = new JButton("Localizeaza punct");
    salveaza = new JButton("Salveaza PSLG-uri");
    setUpGUI();
    setUpButtonListeners();
    setUpList();
    adaugaPSLG_Salvate();
  }

  public static GUI getInstanta() {
    if (instanta == null)
      instanta = new GUI();
    return instanta;
  }

  public void setUpList() {
    list.setModel(myList);
    list.setBounds(100, 50, 360, 420);
    add(list);
  }

  public void setUpGUI() {
    setSize(800, 600);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("PSLG");
    setLocation(550, 230);

    localizeaza.setBounds(550, 50, 150, 50);
    adauga.setBounds(550, 120, 150, 50);
    sterge.setBounds(550, 190, 150, 50);
    modifica.setBounds(550, 260, 150, 50);
    salveaza.setBounds(550, 330, 150, 50);
    exit.setBounds(550, 400, 150, 50);

    add(adauga);
    add(sterge);
    add(modifica);
    add(localizeaza);
    add(salveaza);
    add(exit);

    setLayout(null);
    setVisible(true);
    setResizable(false);
    
  }

  public void setUpButtonListeners() {
    ActionListener onClick = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        Object o = ae.getSource();
        if (o == adauga) {
          adauga();
        } else if (o == sterge) {
          sterge();
        } else if (o == modifica) {
          modifica();
        } else if (o == localizeaza) {
          cauta();
        } else if (o == salveaza) {
          salveaza();
        } else if (o == exit){
          System.exit(0);
        }
      }
    };
    adauga.addActionListener(onClick);
    sterge.addActionListener(onClick);
    exit.addActionListener(onClick);
    modifica.addActionListener(onClick);
    localizeaza.addActionListener(onClick);
    salveaza.addActionListener(onClick);
  }

  public void adaugaPSLG_Salvate() {
    try {
      FileInputStream fileIn = new FileInputStream(nume_fis);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      pslgList = (ArrayList<PSLG>) in.readObject();
      if (pslgList == null)
        pslgList = new ArrayList<>();
      else {
        for (PSLG a : pslgList)
          myList.addElement(a);
      }
      in.close();
      fileIn.close();
    } catch (IOException i) {
      i.printStackTrace();
      return;
    } catch (ClassNotFoundException c) {
      c.printStackTrace();
    }
  }

  public void cauta() {
    if (list.getSelectedIndex() != -1) {
      Desen d= list.getSelectedValue().getCanvas();
      d.setAlgoritm(list.getSelectedValue().getAlgoritm());
      JFrame afisare = new JFrame("Afisare PSLG");
      afisare.add(d);
      afisare.setBounds(580, 270, 520, 520);
      afisare.setVisible(true);
      afisare.setLayout(null);
      afisare.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      afisare.setResizable(false);
    }
  }

  public void salveaza() {
    try {
      FileOutputStream fileOut = new FileOutputStream(nume_fis);
      ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
      objectOut.writeObject(pslgList);
      objectOut.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void adauga() {
    String nume = (String) JOptionPane.showInputDialog("Nume PSLG:");
    if (!isAlphaNumeric(nume) || nume.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Nume invalid!", "ERROR", JOptionPane.ERROR_MESSAGE);
      return;
    }
    String cale = JOptionPane.showInputDialog("Cale catre DCEL:");
    File caleP = new File(cale);
    if (caleP.exists()) {
      try {
        PSLG a = new PSLG(cale);
        a.nume = nume;
        myList.addElement(a);
        pslgList.add(a);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      JOptionPane.showMessageDialog(null, "Cale incorecta!", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
  }

  public void sterge() {
    if (list.getSelectedIndex() != -1) {
      pslgList.remove(list.getSelectedValue());
      myList.removeElement(list.getSelectedValue());
    }
  }

  public void modifica() {
    if (list.getSelectedIndex() != -1) {
      String cale = JOptionPane.showInputDialog("Cale catre DCEL:");
      File caleP = new File(cale);

      if (caleP.exists()) {
        try {
          pslgList.remove(list.getSelectedValue());
          PSLG a = new PSLG(cale);
          a.nume = list.getSelectedValue().nume;
          myList.add(list.getSelectedIndex(), a);
          pslgList.add(a);
        } catch (Exception e) {
          e.printStackTrace();
        }
        myList.removeElement(list.getSelectedValue());
      } else {
        JOptionPane.showMessageDialog(null, "Cale incorecta!", "ERROR", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private static Pattern p = Pattern.compile("^[a-zA-Z0-9]*$");

  public static boolean isAlphaNumeric(String s) {
    return p.matcher(s).find();
  }
}