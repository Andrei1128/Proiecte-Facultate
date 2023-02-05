package cod;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public interface ScriereCitire {
    default void scrie(Cont p) {
        StringBuilder sb = new StringBuilder("conturi\\");
        sb.append(p.getId());
        try {
            if (!new File(sb.toString()).exists()) {
                Path path = Paths.get(sb.toString());
                Files.createDirectory(path);
                createList(sb.toString() + "\\intalniri");
                createList(sb.toString() + "\\inbox");
                if (p.getPersoana() instanceof Profesor)
                    createList(sb.toString() + "\\recenzii");
            }
            FileOutputStream fileOut = new FileOutputStream(sb.toString() + "\\cont");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(p);
            out.flush();
            out.close();
            fileOut.close();
        } catch (Exception i) {
            i.printStackTrace();
            JOptionPane.showMessageDialog(null, "Eroare la scriere in fisier!", "ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createList(String s) throws Exception {
        FileOutputStream fileOut = new FileOutputStream(s);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        ArrayList l = new ArrayList<>();
        out.writeObject(l);
        out.flush();
        out.close();
        fileOut.close();
    }

    default void scrie(String id, Object continut, int index) {
        StringBuilder sb = new StringBuilder("conturi\\");
        sb.append(id);
        String s;
        if (continut instanceof Mesaj)
            s = "\\inbox";
        else if (continut instanceof Intalnire)
            s = "\\intalniri";
        else
            s = "\\recenzii";
        sb.append(s);
        System.out.println(sb.toString());
        ArrayList l = (ArrayList) citire(sb.toString());
        l.add(index, continut);
        try {
            FileOutputStream fileOut = new FileOutputStream(sb.toString());
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(l);
            out.flush();
            out.close();
            fileOut.close();
        } catch (IOException i) {
            JOptionPane.showMessageDialog(null, "Eroare la scriere in fisier!", "ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    default Object citire(String path) {
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object p = in.readObject();
            in.close();
            fileIn.close();
            return p;
        } catch (Exception i) {
            JOptionPane.showMessageDialog(null, "Eroare la citire din fisier!", "ERROR!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    default void remove(Object o, String path) {
        ArrayList list = (ArrayList) citire(path);
        list.remove(o);
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.flush();
            out.close();
            fileOut.close();
        } catch (IOException i) {
            JOptionPane.showMessageDialog(null, "Eroare la scriere in fisier!", "ERROR!", JOptionPane.ERROR_MESSAGE);
        }

    }
}
