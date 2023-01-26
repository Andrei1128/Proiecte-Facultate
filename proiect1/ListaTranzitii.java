package proiect1;
import java.util.*;

public class ListaTranzitii {

    private ArrayList<Tranzitie>lista=new ArrayList<Tranzitie>();

    void adaugaTranzitie(Tranzitie tr){
       this.lista.add(tr); 
    }
    Tranzitie gasesteTranzitie(String stare,char simbol){
        for (Tranzitie i:lista){
            if(i.spuneStInceput().equals(stare)&&i.spuneSimbol()==simbol)
                return i;
        }
        return null;
    }
    HashSet<String> gasesteStariFinale(String stare){
        HashSet<String> stariFinale = new HashSet<>();
        for(Tranzitie i:lista){
            if(i.spuneStInceput().equals(stare))
                stariFinale.add(i.spuneStSfarsit());
        }
        return stariFinale;
    }
    HashSet<String> gasesteStariFinale(String stare,char simbol){
        HashSet<String> stariFinale = new HashSet<>();
        for(Tranzitie i:lista){
            if(i.spuneStInceput().equals(stare)&&i.spuneSimbol()==simbol){
                stariFinale.add(i.spuneStSfarsit());
                if(i.spuneOutput()==null)
                    System.out.println(stare+" "+simbol+" "+i.spuneStSfarsit());
                else
                    System.out.println(stare+" "+simbol+" "+i.spuneStSfarsit()+" "+i.spuneOutput());
            }
        }
        return stariFinale;
    }
    boolean automatDeterminist(){
        for(int i=0;i<this.lista.size()-1;i++){
            Tranzitie tr1=this.lista.get(i);
            for(int j=i+1;j<this.lista.size();j++){
                Tranzitie tr2=this.lista.get(j);
                if(tr1.spuneStInceput()==tr2.spuneStInceput())
                    if(tr1.spuneSimbol()==tr2.spuneSimbol())
                        return false;
            }
        }
        return true;
    }
}
