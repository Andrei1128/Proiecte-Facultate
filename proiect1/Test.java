package proiect1;

public class Test {
    public static void main(String args[]) throws Exception{
        Automat M=new Automat("C:\\Users\\Administrator\\Facultate\\LFC\\proiect1\\automat.txt");
        System.out.println(M.analizeazaCuvant("abba"));
        System.out.println(M.getListaTranzitii().automatDeterminist());
        System.out.println(M.analizezazaStareAccesibila("q2"));
    }
}
