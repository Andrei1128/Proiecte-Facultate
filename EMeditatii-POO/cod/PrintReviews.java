package cod;

import java.awt.Graphics;
import java.awt.print.*;
import java.util.ArrayList;

public class PrintReviews implements ScriereCitire {
    private final String path = "conturi\\id\\recenzii";
    public void printList(String id) {
        PrinterJob job = PrinterJob.getPrinterJob();
        ArrayList list = (ArrayList) citire(path.replace("id", id));
        job.setPrintable(new ArrayListPrintable(list));
        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }
    public class ArrayListPrintable implements Printable {
        private ArrayList<Recenzie> list;

        public ArrayListPrintable(ArrayList<Recenzie> list) {
            this.list = list;
        }

        public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
            if (pageIndex >= list.size())
                return NO_SUCH_PAGE;
            Recenzie r = list.get(pageIndex);
            String tbl[] = r.toString().split(", ");
            g.drawString(tbl[0], 50, 50);
            g.drawString(tbl[1], 50, 70);
            g.drawString("Descriere:", 50, 90);
            String str = r.getDescriere().getText();
            int size = str.length();
            int y=110;
            for (int i = 0; i < size; i += 70) {
                g.drawString(str.substring(i, Math.min(size, i + 70)), 50, y);
                y+=20;
            }
            System.out.println(y);
            return PAGE_EXISTS;
        }
    }
}
