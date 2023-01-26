package proiect1;
import java.io.*;
import java.util.HashSet;

public class Automat {

    private String st_init;
    private String st_finale[];
    private ListaTranzitii lt= new ListaTranzitii();
    ListaTranzitii getListaTranzitii(){
        return lt;
    }
    Automat(String nume_fis) throws Exception{
        BufferedReader buf= new BufferedReader(new FileReader(nume_fis));
        this.st_init=buf.readLine();
        String st_finale_str=buf.readLine();
        this.st_finale=st_finale_str.split(" ");
        while(true){
            String linie=buf.readLine();
            if(linie==null)
                break;
            String tbl[]=linie.split(" ");
            Tranzitie tr = tbl.length==3 ? new Tranzitie(tbl[0],tbl[1].charAt(0),tbl[2]) : new Tranzitie(tbl[0],tbl[1].charAt(0),tbl[2],tbl[3]);
            this.lt.adaugaTranzitie(tr);
        }
        buf.close();
    }
    boolean analizeazaCuvant(String sir_intrare){
        HashSet<String> stariInit = new HashSet<>();
        HashSet<String> stariFinale = new HashSet<>();
        stariInit.add(st_init);
        for(char ch:sir_intrare.toCharArray()){
            for(String i:stariInit)
                stariFinale.addAll(lt.gasesteStariFinale(i, ch));
            stariInit=(HashSet)stariFinale.clone();
            stariFinale.clear();
        }
        for(String i:stariInit)
            for(int j=0;j<st_finale.length;j++)
                if(i.equals(st_finale[j]))
                    return true;
        return false;
    }
    boolean analizezazaStareAccesibila(String finala){
        HashSet<String> stariInit = new HashSet<>();
        HashSet<String> stariFinale = new HashSet<>();
        HashSet<String> toateStarileFinale = new HashSet<>();
        toateStarileFinale.add(st_init);
        stariInit.add(st_init);
        int lastSize=0;
        while(true){
            for(String i:stariInit)
                stariFinale.addAll(lt.gasesteStariFinale(i));
            toateStarileFinale.addAll(stariFinale);
            if(toateStarileFinale.size()==lastSize)
                break;
            lastSize=toateStarileFinale.size();
            stariInit=(HashSet)stariFinale.clone();
            stariFinale.clear();
        }
        for(String i:toateStarileFinale)
            if(i.equals(finala))
                return true;
        return false;
    }
}
