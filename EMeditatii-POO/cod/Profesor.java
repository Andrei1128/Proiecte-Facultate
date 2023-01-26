package cod;

import java.util.ArrayList;

public class Profesor extends Persoana {
     private ArrayList<Certificat> certificate;

    Profesor(String nume, String prenume) {
        super(nume, prenume);
        certificate = new ArrayList<>();
    }
    public void adaugaCert(String titlu,String cale){
        certificate.add(new Certificat(titlu, cale));
        System.out.println(certificate);
    }
    public ArrayList<Certificat> getCertificate() {
        return certificate;
    }
}
